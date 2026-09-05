package control;

import gui.notifica.NotificheGUI;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import gui.notifica.CreaNotificaGUI;
import gui.notifica.SceltaDebitoGUI;
import jdbc.JDBCDebitoDAO;
import jdbc.JDBCNotificaDAO;
import jdbc.JDBCPartecipazioneGruppoDAO;
import model.Debito;
import model.Notifica;
import model.PartecipazioneGruppo;
import model.Utente;

public class NotificaController {

    private Utente utenteLoggato;
    private ArrayList<PartecipazioneGruppo> inviti = new ArrayList<>();
    private ArrayList<Notifica> notificheInviate = new ArrayList<>();
    private ArrayList<Notifica> notificheRicevute = new ArrayList<>();

    private UtenteController utenteController;
    private JFrame finestraAttiva;
    private NotificheGUI notificheGUI;
    private CreaNotificaGUI creaNotificaGUI;
    private Debito debitoSelezionato;

    private JDBCPartecipazioneGruppoDAO partecipazioneDAO = JDBCPartecipazioneGruppoDAO.getSelf();
    private JDBCDebitoDAO debitoDAO = JDBCDebitoDAO.getSelf();
    private JDBCNotificaDAO notificaDAO = JDBCNotificaDAO.getSelf();

    public NotificaController(UtenteController utenteController) {
        try {
            this.utenteController = utenteController;
            this.utenteLoggato = utenteController.getUtente();
            if (utenteLoggato.getPartecipazioniGruppi().isEmpty()) {
                partecipazioneDAO.cercaPartecipazioniByUtenteId(utenteLoggato);
            }
            for (PartecipazioneGruppo p : utenteLoggato.getPartecipazioniGruppi()) {
                if (!p.isInvitoAccettato()) {
                    inviti.add(p);
                }
            }
            notificheInviate = notificaDAO.cercaNotificheInviateByUtente(utenteLoggato);
            notificheRicevute = notificaDAO.cercaNotificheRicevuteByUtente(utenteLoggato);
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

    public void caricaTabelleNotifiche() {
        if (notificheGUI == null) return;

        DefaultTableModel modelInviti = notificheGUI.getModelInviti();
        modelInviti.setRowCount(0);
        for (PartecipazioneGruppo p : inviti) {
            modelInviti.addRow(new Object[]{
                    p,
                    p.getGruppo().getNome(),
                    p.getData(),
                    p.getGruppo().getProprietario().toString()
            });
        }

        DefaultTableModel modelRicevute = notificheGUI.getModelRicevute();
        modelRicevute.setRowCount(0);
        for (Notifica n : notificheRicevute) {
            Debito debito = n.getDebito();
            String stato = debito != null && debito.isDebitoSaldato() ? "Saldato" : "Da saldare";
            modelRicevute.addRow(new Object[]{
                    n,
                    debito != null && debito.getSpesa() != null
                            ? debito.getSpesa().getUtenteEffettuante().toString() : "N/D",
                    n.getDebito() != null ? n.getDebito().getImporto() + " €" : "0.00 €",
                    n.getData1(),
                    n.getDescrizione1(),
                    stato
            });
        }

        DefaultTableModel modelInviate = notificheGUI.getModelInviate();
        modelInviate.setRowCount(0);
        for (Notifica n : notificheInviate) {
            Debito debito = n.getDebito();
            String stato = debito != null && debito.isDebitoSaldato() ? "Saldato" : "In attesa";
            modelInviate.addRow(new Object[]{
                    n,
                    debito != null && debito.getDebitore() != null ? debito.getDebitore().toString() : "N/D",
                    n.getDebito() != null ? n.getDebito().getImporto() + " €" : "0.00 €",
                    n.getData1(),
                    stato
            });
        }
    }

    public void avvia() {
        notificheGUI = new NotificheGUI(this);
        caricaTabelleNotifiche();
        mostraFinestra(notificheGUI);
    }

    public void avviaConDebito(Debito d) {
        this.debitoSelezionato = d;
        creaNotificaGUI = new CreaNotificaGUI(this);
        if (d != null && d.getSpesa() != null) {
            creaNotificaGUI.setDebitoSelezionato(d.getSpesa().getNomeSpesa() + " (" + d.getImporto() + " €)");
        }
        mostraFinestra(creaNotificaGUI);
    }

    public void btn_inviti_accetta(Object pObj) {
        if (pObj instanceof PartecipazioneGruppo) {
            PartecipazioneGruppo p = (PartecipazioneGruppo) pObj;
            try {
                p.setInvitoAccettato(true);
                partecipazioneDAO.aggiornaPartecipazione(p);
                utenteLoggato.addGruppo(p);
                inviti.remove(p);
                JOptionPane.showMessageDialog(null, "Invito accettato! Ora sei nel gruppo " + p.getGruppo().getNome());
                caricaTabelleNotifiche();
            } catch (RuntimeException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
    }

    public void btn_inviti_rifiuta(Object pObj) {
        if (pObj instanceof PartecipazioneGruppo) {
            PartecipazioneGruppo p = (PartecipazioneGruppo) pObj;
            try {
                partecipazioneDAO.eliminaPartecipazione(p);
                inviti.remove(p);
                JOptionPane.showMessageDialog(null, "Invito rifiutato.");
                caricaTabelleNotifiche();
            } catch (RuntimeException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
    }

    public void btn_notificheRicevute_saldaDebito(Object notificaObj) {
        if (notificaObj instanceof Notifica) {
            Notifica n = (Notifica) notificaObj;
            Debito d = n.getDebito();
            if (d != null) {
                try {
                    d.setDebitoSaldato(true);
                    debitoDAO.aggiornaDebito(d);
                    JOptionPane.showMessageDialog(null, "Debito saldato con successo!");
                    caricaTabelleNotifiche();
                } catch (RuntimeException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }
            }
        }
    }

    public void btn_notificheRicevute_rispondi(Object notificaObj, String testo) {
        if (notificaObj instanceof Notifica) {
            Notifica n = (Notifica) notificaObj;
            try {
                n.setDataRisposta(LocalDate.now());
                n.setDescrizioneRisposta(testo);
                notificaDAO.rispostaNotifica(n);
                JOptionPane.showMessageDialog(null, "Risposta inviata al mittente!");
                caricaTabelleNotifiche();
            } catch (RuntimeException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
    }

    public void btn_notifiche_creaNotifica() {
        creaNotificaGUI = new CreaNotificaGUI(this);
        mostraFinestra(creaNotificaGUI);
    }

    public void btn_notifiche_tornaHome() {
        if (finestraAttiva != null) {
            finestraAttiva.dispose();
        }
        utenteController.tornaHome();
    }

    public void btn_creaNotifica_sceltaDebito() {
        SceltaDebitoGUI scelta = new SceltaDebitoGUI(this);
        DefaultTableModel model = scelta.getTableModel();
        model.setRowCount(0);
        ArrayList<Debito> debiti = debitoDAO.cercaDebitoByUtente(utenteLoggato);
        for (Debito d : debiti) {
            model.addRow(new Object[]{
                    d,
                    d.getSpesa().getNomeSpesa(),
                    d.getImporto() + " €"
            });
        }
        scelta.setVisible(true);
    }

    public void btn_creaNotifica_invia(String debitoText, String descrizione) {
        if (debitoSelezionato == null) {
            JOptionPane.showMessageDialog(null, "Seleziona prima un debito da notificare");
            return;
        }
        if (descrizione == null || descrizione.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Inserisci un messaggio di testo per la notifica");
            return;
        }
        try {
            Notifica n = new Notifica(debitoSelezionato, LocalDate.now(), descrizione);
            notificaDAO.nuovaNotifica(n);
            notificheInviate.add(n);

            JOptionPane.showMessageDialog(null, "Notifica inviata con successo!");
            avvia();
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public void btn_creaNotifica_annulla() {
        avvia();
    }

    public void btn_sceltaDebito_ok(Object debitoObj) {
        if (debitoObj instanceof Debito) {
            debitoSelezionato = (Debito) debitoObj;
            if (creaNotificaGUI != null) {
                creaNotificaGUI.setDebitoSelezionato(debitoSelezionato.getSpesa().getNomeSpesa() + " (" + debitoSelezionato.getImporto() + " €)");
            }
        }
    }

    public void btn_sceltaDebito_annulla() {
    }
}
