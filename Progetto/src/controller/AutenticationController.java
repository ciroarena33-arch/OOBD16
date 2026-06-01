package controller;
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
}
