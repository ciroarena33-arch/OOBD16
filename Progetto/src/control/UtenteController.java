package control;

import javax.swing.JOptionPane;
import gui.utente.DatiUtenteGUI;
import gui.utente.HomeGUI;
import gui.utente.LoginGUI;
import gui.utente.RegistrazioneGUI;
import jdbc.JDBCUtenteDAO;
import model.Utente;

public class UtenteController {
    private Utente utenteLoggato;
    private DatiUtenteGUI datiUtenteGUI;
    private HomeGUI homeGUI;
    private LoginGUI loginGUI;
    private RegistrazioneGUI registrazioneGUI;
    private JDBCUtenteDAO utenteDAO;

    public UtenteController() {
        utenteDAO = JDBCUtenteDAO.getSelf();

        loginGUI = new LoginGUI(this);
        registrazioneGUI = new RegistrazioneGUI(this);
        loginGUI.setVisible(true);
    }

    public Utente getUtente() {
        return utenteLoggato;
    }

    public void tornaHome() {
        if (homeGUI != null) {
            homeGUI.setVisible(true);
        }
    }

    public void btn_login_esci() {
        System.exit(0);
    }

    public void btn_login_registrati() {
        loginGUI.setVisible(false);
        registrazioneGUI.setVisible(true);
    }

    public void btn_login_accedi(String email, String password) {
        try {
            Utente u = utenteDAO.cercaUtentePerEmail(email.toLowerCase());
            if (u == null) {
                throw new RuntimeException("Utente non trovato");
            }
            u.accessoValido(email, password);
            loginGUI.dispose();
            registrazioneGUI.dispose();
            utenteLoggato = u;
            homeGUI = new HomeGUI(this, utenteLoggato.getNome());
            homeGUI.setVisible(true);
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(loginGUI, "Errore nel login: " + e.getMessage());
        }
    }

    public void btn_registrazione_registrati(String email, String password, String nome, String cognome) {
        try {
            if (utenteDAO.cercaUtentePerEmail(email.toLowerCase()) != null) {
                throw new RuntimeException("Utente già registrato con questa email");
            }
            utenteDAO.nuovoUtente(new Utente(email, nome, cognome, password));

            utenteLoggato = new Utente(email, nome, cognome, password);
            loginGUI.dispose();
            registrazioneGUI.dispose();

            homeGUI = new HomeGUI(this, utenteLoggato.getNome());
            homeGUI.setVisible(true);
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(registrazioneGUI, e.getMessage());
        }
    }

    public void btn_registrazione_tornaLogin() {
        loginGUI.setVisible(true);
        registrazioneGUI.setVisible(false);
    }

    public void btn_datiUtente_salvaModifiche(String password, String nome, String cognome, String telefono) {
        try {
            if (nome.isEmpty() || cognome.isEmpty() || password.isEmpty()) {
                throw new RuntimeException("Nome, cognome e password non possono essere vuoti");
            }
            Utente utenteModificato = new Utente(utenteLoggato.getEmailIstituzionale(), nome, cognome, password, telefono);
            utenteDAO.aggiornaUtente(utenteModificato);
            utenteLoggato.setCognome(cognome);
            utenteLoggato.setNome(nome);
            utenteLoggato.setPassword(password);
            utenteLoggato.setTelefono(telefono);
            JOptionPane.showMessageDialog(datiUtenteGUI, "Modifiche Salvate");

        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(datiUtenteGUI, e.getMessage());
        }
    }

    public void btn_datiUtente_tornaHome() {
        datiUtenteGUI.dispose();
        if (homeGUI != null) {
            homeGUI.setVisible(true);
        }
    }

    public void btn_home_mieiGruppi() {
        try {
            GruppoController gruppoController = new GruppoController(this);
            gruppoController.avvia();
            homeGUI.setVisible(false);
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(homeGUI, e.getMessage());
        }
    }

    public void btn_home_datiUtente() {
        datiUtenteGUI = new DatiUtenteGUI(
                this,
                utenteLoggato.getNome(),
                utenteLoggato.getCognome(),
                utenteLoggato.getPassword(),
                utenteLoggato.getEmailIstituzionale(),
                utenteLoggato.getTelefono()
        );
        datiUtenteGUI.setVisible(true);
        homeGUI.setVisible(false);
    }

    public void btn_home_visualizzaNotifiche() {
        homeGUI.setVisible(false);
        NotificaController notificaController = new NotificaController(this);
        notificaController.avvia();
    }

    public void btn_home_reportGenerale() {
        try {
            MovimentoController movimentoController = new MovimentoController(this);
            movimentoController.avvia();
            homeGUI.setVisible(false);
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public void btn_home_esci() {
        utenteLoggato = null;
        System.exit(0);
    }
}
