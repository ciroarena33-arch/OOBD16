package control;

import java.util.HashMap;

import gui.movimento.ReportGeneraleGUI;
import jdbc.JDBCGruppoDAO;
import model.PartecipazioneGruppo;
import model.Utente;

public class MovimentoController {

	private Utente utenteLoggato;
	private HashMap<PartecipazioneGruppo,Double> map;
	
	private UtenteController utenteController;
	private JDBCGruppoDAO gruppoDAO;
	
    private ReportGeneraleGUI reportGeneraleGUI;

    public MovimentoController(UtenteController utenteController) {
        this.utenteController = utenteController;
        this.utenteLoggato=utenteController.getUtente();
        this.gruppoDAO=new JDBCGruppoDAO(utenteController.getUtenteDAO());
    }

    public void impostaReport() {
    	
    }
    
    public void avvia() {
        reportGeneraleGUI = new ReportGeneraleGUI(this);
        impostaReport();
        reportGeneraleGUI.setVisible(true);
    }

    public void btn_reportGenerale_tornaHome() {
        if (reportGeneraleGUI != null) {
            reportGeneraleGUI.dispose();
        }
        utenteController.tornaHome();
    }
}
