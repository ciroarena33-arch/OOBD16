package controller;
import javax.swing.JOptionPane;

import gui.*;

public class AutenticationController {

	private LoginFrame loginPage;
	private RegisterFrame registerPage;;

	public AutenticationController() {
		loginPage=new LoginFrame(this);
		registerPage=new RegisterFrame(this);
		loginPage.setVisible(true);
	}
	
	public void btnNuovoUtente() {
		loginPage.setVisible(false);
		registerPage.setVisible(true);
	}
	
	public void btnIndietro() {
		loginPage.setVisible(true);
		registerPage.setVisible(false);
	}
	
	public void btnEffettuaAccesso() {
		try {
			JOptionPane.showMessageDialog(loginPage, "Accesso effettuato", "Valide Login", 1);
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(loginPage, e.getMessage(), "Error", 0);
		}
	}
		public void btnEffettuaregistrazione() {
			try {
				JOptionPane.showMessageDialog(registerPage, "Registrazione utente effettuata", "Valide Registration", 1);
			}
			catch(Exception e) {
				JOptionPane.showMessageDialog(registerPage, e.getMessage(), "Error", 0);
			}
		}
	}

