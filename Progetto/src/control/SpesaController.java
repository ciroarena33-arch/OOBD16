package control;

import java.util.ArrayList;

import gui.spesa.InserisciSpesaGUI;
import gui.spesa.StoricoSpeseGUI;
import jdbc.JDBCSpesaDAO;
import model.PartecipazioneGruppo;
import model.Spesa;
import model.Utente;

public class SpesaController {

	private Utente utenteLoggato;
	private PartecipazioneGruppo partecipazione;
	
    private GruppoController gruppoController;

    private InserisciSpesaGUI inserisciSpesaGUI;
    private StoricoSpeseGUI storicoSpeseGUI;
    
    private JDBCSpesaDAO spesaDAO;

    public SpesaController(GruppoController gruppoController) {
        this.gruppoController = gruppoController;
        this.spesaDAO=new JDBCSpesaDAO();
        this.utenteLoggato=gruppoController.getUtenteLoggato();
        this.partecipazione=gruppoController.getPartecipazioneSelezionata();
        ArrayList<Spesa> spese=spesaDAO.cercaSpesaByGruppo(gruppoController.getGruppoSelezionato(), utenteLoggato);
        for(Spesa s:spese) {
        	partecipazione.addSpesa(s);
        }
    }
    
    public void impostaStorico() {
    	
    }

    public void avviaInserisciSpesa() {
        inserisciSpesaGUI = new InserisciSpesaGUI(this);
        inserisciSpesaGUI.setVisible(true);
    }

    public void avviaStoricoSpese() {
        storicoSpeseGUI = new StoricoSpeseGUI(this);
        impostaStorico();
        storicoSpeseGUI.setVisible(true);
    }

    public void btn_inserisciSpesa_registraSpesa(String nome, String descrizione, String importo, String valuta, String data, String tipo) {
        System.out.println("Spesa registrata:");
        System.out.println("Nome: " + nome);
        System.out.println("Descrizione: " + descrizione);
        System.out.println("Importo: " + importo);
        System.out.println("Valuta: " + valuta);
        System.out.println("Data: " + data);
        System.out.println("Tipo: " + tipo);

        if (inserisciSpesaGUI != null) {
            inserisciSpesaGUI.dispose();
        }

        gruppoController.tornaDettagliGruppoDaSpesa();
    }

    public void btn_inserisciSpesa_tornaGruppo() {
        if (inserisciSpesaGUI != null) {
            inserisciSpesaGUI.dispose();
        }

        gruppoController.tornaDettagliGruppoDaSpesa();
    }

    public void btn_storicoSpese_tornaGruppo() {
        if (storicoSpeseGUI != null) {
            storicoSpeseGUI.dispose();
        }

        gruppoController.tornaDettagliGruppoDaSpesa();
    }
}