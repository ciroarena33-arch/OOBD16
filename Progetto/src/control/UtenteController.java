package control;
import javax.swing.JOptionPane;

import gui.utente.DatiUtenteGUI;
import gui.utente.HomeGUI;
import gui.utente.LoginGUI;
import gui.utente.RegistrazioneGUI;
import model.Utente;
import dao.*;


public class UtenteController {
	private Utente utente;
	private DatiUtenteGUI datiUtenteGUI;
	private HomeGUI homeGUI;
	private LoginGUI loginGUI;
	private RegistrazioneGUI registrazioneGUI;
	
	public UtenteController() {
		loginGUI=new LoginGUI(this);
		registrazioneGUI=new RegistrazioneGUI(this);
		loginGUI.setVisible(true);
	};
	
	public Utente getUtente() {
		return utente;
	}
	
	public void tornaHome() {
		homeGUI.setVisible(true);
	}
	
	public void btn_login_esci() {
		System.exit(0);
	}
	
	public void btn_login_registrati() {
		loginGUI.setVisible(false);
		registrazioneGUI.setVisible(true);
	}
	
	public void btn_login_accedi(String email, String password) {
		loginGUI.dispose();
		registrazioneGUI.dispose();
		
		utente=null;
		
		homeGUI=new HomeGUI(this);
		
		homeGUI.setVisible(true);
	}
	
	public void btn_registrazione_registrati(String email, String password, String nome, String cognome) {
		loginGUI.dispose();
		registrazioneGUI.dispose();
		
		homeGUI=new HomeGUI(this);
		datiUtenteGUI=new DatiUtenteGUI(this);
		
		homeGUI.setVisible(true);
	}
	
	public void btn_registrazione_tornaLogin() {
		loginGUI.setVisible(true);
		registrazioneGUI.setVisible(false);
	}
	
	public void btn_datiUtente_salvaModifiche(String password, String nome, String cognome, String telefono) {
		JOptionPane.showMessageDialog(datiUtenteGUI, "Modifiche Salvate");
	}
	
	public void btn_datiUtente_tornaHome() {
		datiUtenteGUI.dispose();
		homeGUI.setVisible(true);
	}
	
	public void btn_home_mieiGruppi() {
		homeGUI.setVisible(false);
		GruppoController gruppoController=new GruppoController(this);
		gruppoController.avvia();
	}
	
	public void btn_home_datiUtente() {
		datiUtenteGUI=new DatiUtenteGUI(this);
		datiUtenteGUI.setVisible(true);
		homeGUI.setVisible(false);
	}
	
	public void btn_home_esci() {
		System.exit(0);
	}
	
	
	
	
	
}

