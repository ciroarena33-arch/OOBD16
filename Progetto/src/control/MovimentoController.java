package control;

import java.util.ArrayList; 
import java.util.HashMap;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import gui.movimento.ListaSpeseGUI;
import gui.movimento.ReportGeneraleGUI;
import gui.movimento.ReportGruppoGUI;
import gui.movimento.SpesaUtenteGruppoGUI;
import jdbc.JDBCDebitoDAO;
import jdbc.JDBCGruppoDAO;
import jdbc.JDBCPartecipazioneGruppoDAO;
import jdbc.JDBCSpesaDAO;
import model.Debito;
import model.Gruppo;
import model.Movimento;
import model.PartecipazioneGruppo;
import model.Spesa;
import model.Utente;

public class MovimentoController {

    private Utente utenteLoggato;
    private HashMap<Object, Double> map;

    private UtenteController utenteController;
    private GruppoController gruppoController;
    private Gruppo gruppoSelezionato;

    private JFrame finestraAttiva;
    private ReportGeneraleGUI reportGeneraleGUI;
    private ReportGruppoGUI reportGruppoGUI;
    private ListaSpeseGUI listaSpeseGUI;

    private JDBCGruppoDAO gruppoDAO;
    private JDBCSpesaDAO spesaDAO;
    private JDBCDebitoDAO debitoDAO;
    private JDBCPartecipazioneGruppoDAO partecipazioneGruppoDAO;

    public MovimentoController(UtenteController utenteController) {
        this.utenteController = utenteController;
        this.utenteLoggato = utenteController.getUtente();
        this.gruppoDAO = JDBCGruppoDAO.getSelf();
        this.spesaDAO = JDBCSpesaDAO.getSelf();
        this.debitoDAO = JDBCDebitoDAO.getSelf();
        this.partecipazioneGruppoDAO = JDBCPartecipazioneGruppoDAO.getSelf();
    }

    private void mostraFinestra(JFrame nuovaFinestra) {
        if (finestraAttiva != null) {
            finestraAttiva.dispose();
        }
        finestraAttiva = nuovaFinestra;
        finestraAttiva.setVisible(true);
    }

    public void impostaReport() {
        if (this.map == null) {
            this.map = new HashMap<>();
        } else {
            this.map.clear();
        }

        ArrayList<PartecipazioneGruppo> partecipazioni = utenteLoggato.getPartecipazioniGruppi();
        if (partecipazioni == null || partecipazioni.isEmpty()) {
            partecipazioni = JDBCPartecipazioneGruppoDAO.getSelf().cercaPartecipazioniByUtenteId(utenteLoggato);
        }
        if (partecipazioni == null) throw new RuntimeException("L'utente non appartiene a nessun gruppo");

        for (PartecipazioneGruppo p : partecipazioni) {
            if (p.isInvitoAccettato()) {
                ArrayList<Movimento> movimenti = new ArrayList<>();
                ArrayList<Spesa> spese = spesaDAO.cercaSpesePersonali(p.getGruppo(), utenteLoggato);
                ArrayList<Debito> debiti = debitoDAO.cercaDebitoByGruppoUtente(p.getGruppo(), utenteLoggato);
                if (spese != null) movimenti.addAll(spese);
                if (debiti != null) movimenti.addAll(debiti);

                for (Movimento m : movimenti) {
                    map.merge(p, m.getImporto(), Double::sum);
                }
            }
        }
        if (reportGeneraleGUI != null) {
            reportGeneraleGUI.aggiornaReport(map);
        }
    }

    public void avvia() {
        reportGeneraleGUI = new ReportGeneraleGUI(this);
        impostaReport();
        mostraFinestra(reportGeneraleGUI);
    }

    public void btn_reportGenerale_tornaHome() {
        if (finestraAttiva != null) {
            finestraAttiva.dispose();
        }
        utenteController.tornaHome();
    }

    public void gestisciSelezione(Object oggetto) {
        if (oggetto instanceof PartecipazioneGruppo) {
            PartecipazioneGruppo p = (PartecipazioneGruppo) oggetto;
            listaSpeseGUI = new ListaSpeseGUI(this);
            caricaListaSpese(p);
            mostraFinestra(listaSpeseGUI);
        } else {
            JOptionPane.showMessageDialog(null, "Tipo di elemento non riconosciuto.");
        }
    }

    public void caricaListaSpese(PartecipazioneGruppo p) {
        if (listaSpeseGUI == null) return;
        DefaultTableModel model = listaSpeseGUI.getTableModel();
        model.setRowCount(0);

        ArrayList<Spesa> spesePersonali = spesaDAO.cercaSpesePersonali(p.getGruppo(), utenteLoggato);
        ArrayList<Spesa> speseComuni = spesaDAO.cercaSpeseComuni(p.getGruppo());
        ArrayList<Debito> debiti = debitoDAO.cercaDebitoByGruppoUtente(p.getGruppo(), utenteLoggato);

        if (spesePersonali != null) {
            for (Spesa s : spesePersonali) {
                model.addRow(new Object[]{s, s.getUtenteEffettuante().toString(), s.getImporto() + " €", "Spesa Personale"});
            }
        }
        if (speseComuni != null) {
            for (Spesa s : speseComuni) {
                model.addRow(new Object[]{s, s.getUtenteEffettuante().toString(), s.getImporto() + " €", "Spesa Comune"});
            }
        }
        if (debiti != null) {
            for (Debito d : debiti) {
                model.addRow(new Object[]{d, d.getSpesa().getUtenteEffettuante().toString(), d.getImporto() + " €", d.isDebitoSaldato() ? "Saldato" : "Da Saldare"});
            }
        }
    }

    public void apriDettaglioMovimento(Object movimento) {
        SpesaUtenteGruppoGUI gui = new SpesaUtenteGruppoGUI(this);
        if (movimento instanceof Spesa) {
            Spesa s = (Spesa) movimento;
            gui.setDettagli(s.getNomeSpesa(), s.getDescrizione(), s.getImporto() + " €", s.getUtenteEffettuante().toString(), false, true, s);
        } else if (movimento instanceof Debito) {
            Debito d = (Debito) movimento;
            gui.setDettagli(d.getSpesa().getNomeSpesa(), d.getSpesa().getDescrizione(), d.getImporto() + " €", d.getSpesa().getUtenteEffettuante().toString(), true, d.isDebitoSaldato(), d);
        }
        gui.setVisible(true);
    }

    public void sollecitaDebito(Object movimentoRef) {
        if (finestraAttiva != null) {
            finestraAttiva.dispose();
        }
        if (movimentoRef instanceof Debito) {
            NotificaController notificaController = new NotificaController(utenteController);
            notificaController.avviaConDebito((Debito) movimentoRef);
        }
    }

	public void btn_reportGruppo_listaSpese() {
		
	}

	public void btn_reportGruppo_tornaGruppo() {
		
	}
    
    
}
