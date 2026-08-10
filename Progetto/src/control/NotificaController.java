package control;

import gui.notifica.NotificheGUI;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import gui.notifica.CreaNotificaGUI;
import gui.notifica.SceltaDebitoGUI;
import jdbc.JDBCDebitoDAO;
import jdbc.JDBCNotificaDAO;
import jdbc.JDBCPartecipazioneGruppoDAO;
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
    private CreaNotificaGUI creaNotificaGUI;

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

    public void avvia() {
        mostraFinestra(new NotificheGUI(this));
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
        scelta.setVisible(true);
    }

    public void btn_creaNotifica_invia(String debito, String descrizione) {
        mostraFinestra(new NotificheGUI(this));
    }

    public void btn_creaNotifica_annulla() {
        mostraFinestra(new NotificheGUI(this));
    }

    public void btn_sceltaDebito_ok(String debito) {
        if (creaNotificaGUI != null) {
            creaNotificaGUI.setDebitoSelezionato(debito);
        }
    }

    public void btn_sceltaDebito_annulla() {
    }
}