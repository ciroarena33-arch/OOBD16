package control;

import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import gui.movimento.ListaSpeseGUI;
import gui.movimento.ReportGeneraleGUI;
import jdbc.JDBCDebitoDAO;
import jdbc.JDBCGruppoDAO;
import jdbc.JDBCPartecipazioneGruppoDAO;
import jdbc.JDBCSpesaDAO;
import model.Debito;
import model.Movimento;
import model.PartecipazioneGruppo;
import model.Spesa;
import model.Utente;

public class MovimentoController {

    private Utente utenteLoggato;
    private HashMap<Object, Double> map;

    private UtenteController utenteController;
    private JFrame finestraAttiva;
    private ReportGeneraleGUI reportGeneraleGUI;

    private JDBCGruppoDAO gruppoDAO;
    private JDBCSpesaDAO spesaDAO;
    private JDBCDebitoDAO debitoDAO;

    public MovimentoController(UtenteController utenteController) {
        this.utenteController = utenteController;
        this.utenteLoggato = utenteController.getUtente();
        this.gruppoDAO = JDBCGruppoDAO.getSelf();
        this.spesaDAO = JDBCSpesaDAO.getSelf();
        this.debitoDAO = JDBCDebitoDAO.getSelf();
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
            mostraFinestra(new ListaSpeseGUI(this));
        } else {
            JOptionPane.showMessageDialog(null, "Tipo di elemento non riconosciuto.");
        }
    }
}
