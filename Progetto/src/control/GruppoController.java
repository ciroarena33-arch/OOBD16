package control;

import java.util.ArrayList;

import model.Gruppo;
import model.PartecipazioneGruppo;
import gui.gruppo.*;

public class GruppoController {

	private ArrayList<PartecipazioneGruppo> partecipazioniGruppi;
	private Gruppo gruppoSelezionato;
	private CreazioneGruppoGUI creazioneGruppoGUI;
	private DettagliGruppoGUI dettagliGruppoGUI;
	private IMieiGruppiGUI iMieiGruppiGUI;
	private InfoGruppoGUI infoGruppoGUI;
	
	public UtenteController utenteController;
	public GruppoController(UtenteController utenteController) {
		this.utenteController=utenteController;
		this.partecipazioniGruppi=utenteController.getUtente().getPartecipazioniGruppi();
	}
	
	public void avvia() {
		iMieiGruppiGUI=new IMieiGruppiGUI(this);
		iMieiGruppiGUI.setVisible(true);
	}
	
	public void btn_iMieiGruppi_apriGruppo(PartecipazioneGruppo p) {
		gruppoSelezionato=p.getGruppo();
		
	}
	
	public void btn_iMieiGruppi_tornaHome() {
		iMieiGruppiGUI.dispose();
		utenteController.tornaHome();
	}
}
