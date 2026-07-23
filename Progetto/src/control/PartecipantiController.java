package control;

import gui.partecipanti.VisualizzaPartecipantiGUI;
import jdbc.JDBCGruppoDAO;
import jdbc.JDBCPartecipazioneGruppoDAO;
import jdbc.JDBCUtenteDAO;
import model.Gruppo;
import model.PartecipazioneGruppo;
import model.Utente;
import gui.partecipanti.InfoPartecipanteGUI;

import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

import gui.partecipanti.AggiungiPartecipanteGruppoGUI;

public class PartecipantiController {

    private GruppoController gruppoController;
    
    private Utente utenteLoggato;
    private Gruppo gruppoSelezionato;
    private Utente partecipanteSelezionato;

    private JDBCUtenteDAO utenteDAO;
    private JDBCGruppoDAO gruppoDAO;
    private JDBCPartecipazioneGruppoDAO partecipazioneGruppoDAO;
    
    
    private VisualizzaPartecipantiGUI visualizzaPartecipantiGUI;
    private InfoPartecipanteGUI infoPartecipanteGUI;
    private AggiungiPartecipanteGruppoGUI aggiungiPartecipanteGUI;

    public PartecipantiController(GruppoController gruppoController) {
        this.gruppoController = gruppoController;
        
        this.utenteDAO=new JDBCUtenteDAO();
        this.gruppoDAO=new JDBCGruppoDAO(utenteDAO);
        this.partecipazioneGruppoDAO = new JDBCPartecipazioneGruppoDAO(gruppoDAO,utenteDAO);
        
        this.utenteLoggato=gruppoController.getUtenteLoggato();
        this.gruppoSelezionato=gruppoController.getGruppoSelezionato();
        ArrayList<PartecipazioneGruppo>partecipanti=partecipazioneGruppoDAO.cercaPartecipazioniByGruppoId(gruppoSelezionato);
        for(PartecipazioneGruppo p:partecipanti) {
        	if(!p.getUtente().getEmailIstituzionale().equals(utenteLoggato.getEmailIstituzionale())) {
        		gruppoSelezionato.addComponente(p);
        	}
        }
        
    }
    
    public Utente getUtenteLoggato() {
		return utenteLoggato;
    }

	public Gruppo getGruppoSelezionato() {
		return gruppoSelezionato;
	}

	public Utente getPartecipanteSelezionato() {
		return partecipanteSelezionato;
	}


	public void avvia() {
        visualizzaPartecipantiGUI = new VisualizzaPartecipantiGUI(this);
        caricaPartecipanti();
        visualizzaPartecipantiGUI.setVisible(true);
    }

    
    public void caricaPartecipanti() {
	    DefaultListModel<Object> model = new DefaultListModel<>();
	    
	    for (PartecipazioneGruppo g : gruppoSelezionato.getComponenti()) {
	        Utente u=g.getUtente();
	        if(!utenteLoggato.getEmailIstituzionale().equals(u.getEmailIstituzionale())&&Boolean.TRUE.equals(g.isInvitoAccettato())) {
	        	model.addElement(g.getUtente());
	        }
	    }
	    
	    visualizzaPartecipantiGUI.aggiornaJList(model);
	}
    
    public void btn_visualizzaPartecipanti_vediDettagli(Object u) {
        partecipanteSelezionato=(Utente)u;

        infoPartecipanteGUI = new InfoPartecipanteGUI(this);
        infoPartecipanteGUI.setVisible(true);
        visualizzaPartecipantiGUI.dispose();
    }

    public void btn_visualizzaPartecipanti_aggiungiPartecipante() {
    	visualizzaPartecipantiGUI.dispose();
        aggiungiPartecipanteGUI = new AggiungiPartecipanteGruppoGUI(this);
        aggiungiPartecipanteGUI.setVisible(true);
    }

    public void btn_visualizzaPartecipanti_tornaGruppo() {
        if (visualizzaPartecipantiGUI != null) {
            visualizzaPartecipantiGUI.dispose();
        }

        gruppoController.tornaDettagliGruppoDaPartecipanti();
    }

    public void btn_infoPartecipante_indietro() {
        if (infoPartecipanteGUI != null) {
            infoPartecipanteGUI.dispose();
        }

        visualizzaPartecipantiGUI = new VisualizzaPartecipantiGUI(this);
        caricaPartecipanti();
        visualizzaPartecipantiGUI.setVisible(true);
    }

    public void btn_infoPartecipante_rendiProprietario() {
    	try {
		if (JOptionPane.showConfirmDialog(null, "Confermi di voler trasferire il ruolo di proprietario a " + partecipanteSelezionato + "?") == JOptionPane.YES_OPTION) {
			gruppoSelezionato.setProprietario(partecipanteSelezionato);
    		gruppoDAO.aggiornaGruppo(gruppoSelezionato);
    		
    		visualizzaPartecipantiGUI = new VisualizzaPartecipantiGUI(this);
            caricaPartecipanti();
            visualizzaPartecipantiGUI.setVisible(true);
            infoPartecipanteGUI.dispose();
			}
    	}
    	catch(RuntimeException e) {
    		JOptionPane.showMessageDialog(infoPartecipanteGUI, e);
    	}
    }

    public void btn_aggiungiPartecipante_aggiungi(String email) {
        try {
        	Boolean invito=null;
        	PartecipazioneGruppo partecipazione=null;
        	for(PartecipazioneGruppo partecipante:gruppoSelezionato.getComponenti()) {
        		if(partecipante.getUtente().getEmailIstituzionale().equals(email)||utenteLoggato.getEmailIstituzionale().equals(email)) {
        			invito=partecipante.isInvitoAccettato();
        			partecipazione=partecipante;
        			if(invito==true) {
        				throw new RuntimeException("L'utente già appartiene al gruppo");
        			}
        			else if(invito==null) {
        				throw new RuntimeException("L'utente ha già ricevuto una richiesta di partecipazione al gruppo");
        			}
        			
        		}
        	}
        	if(Boolean.FALSE.equals(invito)) {
        		partecipazione.setData(LocalDate.now());
        		partecipazione.setInvitoAccettato(null);
        		partecipazioneGruppoDAO.aggiornaPartecipazione(partecipazione);
        		
        	}
        	Utente u=utenteDAO.cercaUtentePerEmail(email);
        	PartecipazioneGruppo p=new PartecipazioneGruppo(LocalDate.now(), u, gruppoSelezionato);
        	partecipazioneGruppoDAO.nuovaPartecipazione(p);
        	gruppoSelezionato.addComponente(p);
        	
            visualizzaPartecipantiGUI = new VisualizzaPartecipantiGUI(this);
            caricaPartecipanti();
            visualizzaPartecipantiGUI.setVisible(true);	
            aggiungiPartecipanteGUI.dispose();
            
        }catch(RuntimeException e) {
        	JOptionPane.showMessageDialog(aggiungiPartecipanteGUI, e.getMessage());
        }       
    }

    public void btn_aggiungiPartecipante_annulla() {
        aggiungiPartecipanteGUI.dispose();
        
        visualizzaPartecipantiGUI = new VisualizzaPartecipantiGUI(this);
        caricaPartecipanti();
        visualizzaPartecipantiGUI.setVisible(true);
    }
}