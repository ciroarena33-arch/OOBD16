package control;

import gui.notifica.NotificheGUI;
import gui.notifica.CreaNotificaGUI;
import gui.notifica.SceltaDebitoGUI;

public class NotificaController {

    private UtenteController utenteController;

    private NotificheGUI notificheGUI;
    private CreaNotificaGUI creaNotificaGUI;
    private SceltaDebitoGUI sceltaDebitoGUI;

    public NotificaController(UtenteController utenteController) {
        this.utenteController = utenteController;
    }

    public void avvia() {
        notificheGUI = new NotificheGUI(this);
        notificheGUI.setVisible(true);
    }

    public void btn_notifiche_creaNotifica() {
        if (notificheGUI != null) {
            notificheGUI.dispose();
        }

        creaNotificaGUI = new CreaNotificaGUI(this);
        creaNotificaGUI.setVisible(true);
    }

    public void btn_notifiche_tornaHome() {
        if (notificheGUI != null) {
            notificheGUI.dispose();
        }

        utenteController.tornaHome();
    }

    public void btn_creaNotifica_sceltaDebito() {
        sceltaDebitoGUI = new SceltaDebitoGUI(this);
        sceltaDebitoGUI.setVisible(true);
    }

    public void btn_creaNotifica_invia(String debito, String descrizione) {
        System.out.println("Notifica inviata");
        System.out.println("Debito: " + debito);
        System.out.println("Descrizione: " + descrizione);

        if (creaNotificaGUI != null) {
            creaNotificaGUI.dispose();
        }

        notificheGUI = new NotificheGUI(this);
        notificheGUI.setVisible(true);
    }

    public void btn_creaNotifica_annulla() {
        if (creaNotificaGUI != null) {
            creaNotificaGUI.dispose();
        }

        notificheGUI = new NotificheGUI(this);
        notificheGUI.setVisible(true);
    }

    public void btn_sceltaDebito_ok(String debito) {
        if (sceltaDebitoGUI != null) {
            sceltaDebitoGUI.dispose();
        }

        if (creaNotificaGUI != null) {
            creaNotificaGUI.setDebitoSelezionato(debito);
        }
    }

    public void btn_sceltaDebito_annulla() {
        if (sceltaDebitoGUI != null) {
            sceltaDebitoGUI.dispose();
        }
    }
}