package control;

import java.time.LocalDate; 
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.Coinquilini;
import model.Gruppo;
import model.Indirizzo;
import model.PartecipazioneGruppo;
import model.Studio;
import model.Utente;
import model.Viaggio;
import gui.gruppo.*;
import jdbc.JDBCGruppoDAO;
import jdbc.JDBCIndirizzoDAO;
import jdbc.JDBCPartecipazioneGruppoDAO;
import jdbc.JDBCDebitoDAO;
import jdbc.JDBCSpesaDAO;
import jdbc.JDBCUtenteDAO;

public class GruppoController {

    private Gruppo gruppoSelezionato;
    private Utente utenteLoggato;
    private PartecipazioneGruppo partecipazioneSelezionata;

    private JFrame finestraAttiva;
    private IMieiGruppiGUI iMieiGruppiGUI;

    private JDBCUtenteDAO utenteDAO;
    private JDBCPartecipazioneGruppoDAO partecipazioneGruppoDAO;
    private JDBCGruppoDAO gruppoDAO;
    private JDBCIndirizzoDAO indirizzoDAO;
    private JDBCSpesaDAO spesaDAO;
    private JDBCDebitoDAO debitoDAO;

    public UtenteController utenteController;
    public SpesaController spesaController;

    public Gruppo getGruppoSelezionato() {
        return gruppoSelezionato;
    }

    public Utente getUtenteLoggato() {
        return utenteLoggato;
    }

    public PartecipazioneGruppo getPartecipazioneSelezionata() {
        return partecipazioneSelezionata;
    }

    public UtenteController getUtenteController() {
        return utenteController;
    }

    public GruppoController(UtenteController utenteController) {
        try {
            this.utenteDAO = JDBCUtenteDAO.getSelf();
            this.gruppoDAO = JDBCGruppoDAO.getSelf();
            this.partecipazioneGruppoDAO = JDBCPartecipazioneGruppoDAO.getSelf();
            this.indirizzoDAO = JDBCIndirizzoDAO.getSelf();
            this.spesaDAO = JDBCSpesaDAO.getSelf();
            this.debitoDAO = JDBCDebitoDAO.getSelf();

            this.utenteController = utenteController;
            this.utenteLoggato = utenteController.getUtente();

            partecipazioneGruppoDAO.cercaPartecipazioniByUtenteId(utenteLoggato);
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    private void mostraFinestra(JFrame nuovaFinestra) {
        if (finestraAttiva != null) {
            finestraAttiva.dispose();
        }
        finestraAttiva = nuovaFinestra;
        finestraAttiva.setVisible(true);
    }

    public boolean isUltimoMembro() {
        if (gruppoSelezionato == null) return true;
        ArrayList<PartecipazioneGruppo> part = partecipazioneGruppoDAO.cercaPartecipazioniByGruppoId(gruppoSelezionato);
        int count = 0;
        if (part != null) {
            for (PartecipazioneGruppo p : part) {
                if (p.isInvitoAccettato()) {
                    count++;
                }
            }
        }
        return count <= 1;
    }

    public void caricaGruppi() {
        try {
            DefaultListModel<Object> model = new DefaultListModel<>();
            for (PartecipazioneGruppo g : utenteLoggato.getPartecipazioniGruppi()) {
                model.addElement(g);
            }
            if (iMieiGruppiGUI != null) {
                iMieiGruppiGUI.aggiornaJList(model);
            }
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public void avvia() {
        iMieiGruppiGUI = new IMieiGruppiGUI(this);
        caricaGruppi();
        mostraFinestra(iMieiGruppiGUI);
    }

    public void btn_iMieiGruppi_tornaHome() {
        if (finestraAttiva != null) {
            finestraAttiva.dispose();
        }
        utenteController.tornaHome();
    }

    public void btn_iMieiGruppi_apriGruppo(Object gruppo) {
        partecipazioneSelezionata = (PartecipazioneGruppo) gruppo;
        try {
            gruppoSelezionato = partecipazioneSelezionata.getGruppo();
            mostraFinestra(new DettagliGruppoGUI(this, gruppoSelezionato.getNome(), gruppoSelezionato.getProprietario().toString(), isUltimoMembro()));
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public void btn_iMieiGruppi_creaGruppo() {
        mostraFinestra(new CreazioneGruppoGUI(this));
    }

    public void btn_creazioneGruppo_generico(String nome) {
        try {
            if (JOptionPane.showConfirmDialog(null, "Confermi la creazione del gruppo \"" + nome + "\"?") == JOptionPane.YES_OPTION) {
                Gruppo g = new Gruppo(nome, utenteLoggato, LocalDate.now());
                gruppoDAO.inserisciGruppo(g);
                PartecipazioneGruppo p = partecipazioneGruppoDAO.getPartecipazione(utenteLoggato, g);
                utenteLoggato.addGruppo(p);

                iMieiGruppiGUI = new IMieiGruppiGUI(this);
                caricaGruppi();
                mostraFinestra(iMieiGruppiGUI);
            }
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public void btn_creazioneGruppo_studio(String nome, String nomeEsame, LocalDate dataAppello) {
        try {
            if (JOptionPane.showConfirmDialog(null, "Confermi la creazione del gruppo studio \"" + nome + "\"?") == JOptionPane.YES_OPTION) {
                Studio g = new Studio(nome, utenteLoggato, LocalDate.now(), nomeEsame, dataAppello);
                gruppoDAO.inserisciGruppo(g);
                PartecipazioneGruppo p = partecipazioneGruppoDAO.getPartecipazione(utenteLoggato, g);
                utenteLoggato.addGruppo(p);

                iMieiGruppiGUI = new IMieiGruppiGUI(this);
                caricaGruppi();
                mostraFinestra(iMieiGruppiGUI);
            }
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public void btn_creazioneGruppo_viaggio(String nome, String destinazione, LocalDate dataPartenza, LocalDate dataRitorno) {
        try {
            if (JOptionPane.showConfirmDialog(null, "Confermi la creazione del gruppo viaggio \"" + nome + "\"?") == JOptionPane.YES_OPTION) {
                Viaggio g = new Viaggio(nome, utenteLoggato, LocalDate.now(), dataPartenza, dataRitorno, destinazione);
                gruppoDAO.inserisciGruppo(g);
                PartecipazioneGruppo p = partecipazioneGruppoDAO.getPartecipazione(utenteLoggato, g);
                utenteLoggato.addGruppo(p);

                iMieiGruppiGUI = new IMieiGruppiGUI(this);
                caricaGruppi();
                mostraFinestra(iMieiGruppiGUI);
            }
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public void btn_creazioneGruppo_coinquilini(String nome, String provincia, String citta, String via, String numCivicoStr) {
        try {
            if (JOptionPane.showConfirmDialog(null, "Confermi la creazione del gruppo coinquilini \"" + nome + "\"?") == JOptionPane.YES_OPTION) {
                int numCivico = Integer.parseInt(numCivicoStr.trim());
                Indirizzo indirizzo = new Indirizzo(provincia.toUpperCase(), citta, via, numCivico);
                indirizzoDAO.nuovoIndirizzo(indirizzo);
                Coinquilini g = new Coinquilini(nome, utenteLoggato, LocalDate.now(), indirizzo);
                gruppoDAO.inserisciGruppo(g);
                PartecipazioneGruppo p = partecipazioneGruppoDAO.getPartecipazione(utenteLoggato, g);
                utenteLoggato.addGruppo(p);

                iMieiGruppiGUI = new IMieiGruppiGUI(this);
                caricaGruppi();
                mostraFinestra(iMieiGruppiGUI);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Numero civico non valido");
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public void btn_creazioneGruppo_annulla() {
        iMieiGruppiGUI = new IMieiGruppiGUI(this);
        caricaGruppi();
        mostraFinestra(iMieiGruppiGUI);
    }

    public void btn_creazioneGruppo_tornaHome() {
        if (finestraAttiva != null) {
            finestraAttiva.dispose();
        }
        utenteController.tornaHome();
    }

    public void btn_dettagliGruppo_inserisciSpesa() {
        spesaController = new SpesaController(this);
        spesaController.avviaInserisciSpesa();
    }

    public void btn_dettagliGruppo_storicoSpese() {
        spesaController = new SpesaController(this);
        spesaController.avviaStoricoSpese();
    }

    public void btn_dettagliGruppo_tornaGruppi() {
        iMieiGruppiGUI = new IMieiGruppiGUI(this);
        caricaGruppi();
        mostraFinestra(iMieiGruppiGUI);
    }

    public void btn_dettagliGruppo_infoGruppo() {
        mostraFinestra(new InfoGruppoGUI(this));
    }

    public void apriListaSpeseDaReport() {
        spesaController = new SpesaController(this);
        spesaController.avviaStoricoSpese();
    }


    public void btn_dettagliGruppo_tornaHome() {
        if (finestraAttiva != null) {
            finestraAttiva.dispose();
        }
        utenteController.tornaHome();
    }

    public void btn_dettagliGruppo_abbandonaOElimina() {
        if (gruppoSelezionato == null) return;
        boolean ultimo = isUltimoMembro();
        if (ultimo) {
            int confirm = JOptionPane.showConfirmDialog(finestraAttiva, "Sei l'unico membro rimasto. Confermi l'eliminazione definitiva del gruppo \"" + gruppoSelezionato.getNome() + "\"?", "Conferma Eliminazione Gruppo", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (confirm == JOptionPane.YES_OPTION) {
                try {
                    gruppoDAO.eliminaGruppo(gruppoSelezionato);
                    utenteLoggato.getPartecipazioniGruppi().removeIf(p -> p.getGruppo().getId() == gruppoSelezionato.getId());
                    JOptionPane.showMessageDialog(null, "Gruppo eliminato con successo.");
                    btn_dettagliGruppo_tornaGruppi();
                } catch (RuntimeException e) {
                    JOptionPane.showMessageDialog(finestraAttiva, e.getMessage());
                }
            }
        } else {
            int confirm = JOptionPane.showConfirmDialog(finestraAttiva, "Confermi di voler abbandonare il gruppo \"" + gruppoSelezionato.getNome() + "\"?", "Conferma Abbandono Gruppo", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (confirm == JOptionPane.YES_OPTION) {
                try {
                    PartecipazioneGruppo miaPartecipazione = partecipazioneGruppoDAO.getPartecipazione(utenteLoggato, gruppoSelezionato);
                    if (miaPartecipazione != null) {
                        partecipazioneGruppoDAO.eliminaPartecipazione(miaPartecipazione);
                        utenteLoggato.getPartecipazioniGruppi().removeIf(p -> p.getId() == miaPartecipazione.getId());
                    }
                    JOptionPane.showMessageDialog(null, "Hai abbandonato il gruppo.");
                    btn_dettagliGruppo_tornaGruppi();
                } catch (RuntimeException e) {
                    JOptionPane.showMessageDialog(finestraAttiva, e.getMessage());
                }
            }
        }
    }
    
    public void tornaDettagliGruppoDaSpesa() {
        if (gruppoSelezionato != null) {
            mostraFinestra(new DettagliGruppoGUI(this, gruppoSelezionato.getNome(), gruppoSelezionato.getProprietario().toString(), isUltimoMembro()));
        }
    }

    public void btn_infoGruppo_tornaDettagli() {
        if (gruppoSelezionato != null) {
            mostraFinestra(new DettagliGruppoGUI(this, gruppoSelezionato.getNome(), gruppoSelezionato.getProprietario().toString(), isUltimoMembro()));
        }
    }

    public void btn_infoGruppo_tornaHome() {
        if (finestraAttiva != null) {
            finestraAttiva.dispose();
        }
        utenteController.tornaHome();
    }

    public void btn_infoGruppo_salvaModifiche(String nomeGruppo, String tipologiaGruppo) {
    }

    public void btn_dettagliGruppo_visualizzaPartecipanti() {
        PartecipantiController partecipantiController = new PartecipantiController(this);
        partecipantiController.avvia();
    }

    public void tornaDettagliGruppoDaPartecipanti() {
        if (gruppoSelezionato != null) {
            mostraFinestra(new DettagliGruppoGUI(this, gruppoSelezionato.getNome(), gruppoSelezionato.getProprietario().toString(), isUltimoMembro()));
        }
    }

    public void btn_dettagliGruppo_scadenze() {
        if (gruppoSelezionato instanceof Coinquilini) {
            ScadenzeController scadenzeController = new ScadenzeController(this);
            scadenzeController.avvia();
        } else {
            JOptionPane.showMessageDialog(finestraAttiva, "Funzione non utilizzabile perché il gruppo non è di tipo Coinquilini");
        }
    }

    public void tornaDettagliGruppoDaScadenze() {
        if (gruppoSelezionato != null) {
            mostraFinestra(new DettagliGruppoGUI(this, gruppoSelezionato.getNome(), gruppoSelezionato.getProprietario().toString(), isUltimoMembro()));
        }
    }
}
