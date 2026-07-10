package control;

import java.util.ArrayList;

import javax.swing.DefaultListModel;

import model.Gruppo;
import model.PartecipazioneGruppo;
import model.Utente;
import gui.gruppo.*;
import jdbc.JDBCGruppoDAO;
import jdbc.JDBCPartecipazioneGruppoDAO;
import jdbc.JDBCUtenteDAO;

public class GruppoController {

	private ArrayList<PartecipazioneGruppo> partecipazioniGruppi;
	private Gruppo gruppoSelezionato;
	private Utente utenteLoggato;
	
	private CreazioneGruppoGUI creazioneGruppoGUI;
	private DettagliGruppoGUI dettagliGruppoGUI;
	private IMieiGruppiGUI iMieiGruppiGUI;
	private InfoGruppoGUI infoGruppoGUI;
	
	private JDBCUtenteDAO utenteDAO;
	private JDBCPartecipazioneGruppoDAO partecipazioneGruppoDAO;
	private JDBCGruppoDAO gruppoDAO;
	
	public UtenteController utenteController;
	
	
	public ArrayList<PartecipazioneGruppo> getPartecipazioniGruppi() {
		return partecipazioniGruppi;
	}

	public Gruppo getGruppoSelezionato() {
		return gruppoSelezionato;
	}

	public Utente getUtenteLoggato() {
		return utenteLoggato;
	}

	public UtenteController getUtenteController() {
		return utenteController;
	}

	public void setPartecipazioniGruppi(ArrayList<PartecipazioneGruppo> partecipazioniGruppi) {
		this.partecipazioniGruppi = partecipazioniGruppi;
	}

	public GruppoController(UtenteController utenteController) {
		this.utenteDAO=utenteController.getUtenteDAO(); 
		this.gruppoDAO=new JDBCGruppoDAO(utenteDAO);
		this.partecipazioneGruppoDAO=new JDBCPartecipazioneGruppoDAO(gruppoDAO, utenteDAO);
		
		this.utenteController=utenteController;
		this.utenteLoggato=utenteController.getUtente();
		partecipazioneGruppoDAO.cercaPartecipazioniByUtenteId(utenteLoggato);
		this.partecipazioniGruppi=utenteController.getUtente().getPartecipazioniGruppi();
	}
	
	public void caricaGruppi() {
		
	    ArrayList<PartecipazioneGruppo> partecipazioniUtente = partecipazioneGruppoDAO.cercaPartecipazioniByUtenteId(utenteLoggato);
	    DefaultListModel<ListaGruppi> model = new DefaultListModel<>();
	    
	    for (PartecipazioneGruppo g : partecipazioniUtente) {
	        model.addElement(new ListaGruppi(g.getId(), g.getGruppo().getNome()));
	    }
	    
	    iMieiGruppiGUI.aggiornaJList(model);
	}
	
	public void avvia() {
		iMieiGruppiGUI=new IMieiGruppiGUI(this);
		caricaGruppi();
		iMieiGruppiGUI.setVisible(true);
	}
	
	public void btn_iMieiGruppi_tornaHome() {
		iMieiGruppiGUI.dispose();
		utenteController.tornaHome();
	}	
	
	public void btn_iMieiGruppi_apriGruppo(ListaGruppi l) {
		gruppoSelezionato=gruppoDAO.cercaGruppoById(l.getId());
		iMieiGruppiGUI.dispose();
		dettagliGruppoGUI= new DettagliGruppoGUI(this);
		dettagliGruppoGUI.setVisible(true);
	}
	
	public void btn_iMieiGruppi_creaGruppo() {
        if (iMieiGruppiGUI != null) {
            iMieiGruppiGUI.dispose();
        }

        creazioneGruppoGUI = new CreazioneGruppoGUI(this);
        creazioneGruppoGUI.setVisible(true);
    }
	
	public void btn_creazioneGruppo_conferma(String nomeGruppo, String categoriaGruppo) {
        System.out.println("Gruppo creato: " + nomeGruppo + " - " + categoriaGruppo);

        if (creazioneGruppoGUI != null) {
            creazioneGruppoGUI.dispose();
        }

        iMieiGruppiGUI = new IMieiGruppiGUI(this);
        iMieiGruppiGUI.setVisible(true);
    }

    public void btn_creazioneGruppo_annulla() {
        if (creazioneGruppoGUI != null) {
            creazioneGruppoGUI.dispose();
        }

        iMieiGruppiGUI = new IMieiGruppiGUI(this);
        iMieiGruppiGUI.setVisible(true);
    }

    public void btn_creazioneGruppo_tornaHome() {
        if (creazioneGruppoGUI != null) {
            creazioneGruppoGUI.dispose();
        }

        utenteController.tornaHome();
    }

    public void btn_dettagliGruppo_inserisciSpesa() {
        if (dettagliGruppoGUI != null) {
            dettagliGruppoGUI.dispose();
        }

        SpesaController spesaController = new SpesaController(this);
        spesaController.avviaInserisciSpesa();
    }

    public void btn_dettagliGruppo_storicoSpese() {
        if (dettagliGruppoGUI != null) {
            dettagliGruppoGUI.dispose();
        }

        SpesaController spesaController = new SpesaController(this);
        spesaController.avviaStoricoSpese();
    }

    public void btn_dettagliGruppo_tornaGruppi() {
        if (dettagliGruppoGUI != null) {
            dettagliGruppoGUI.dispose();
        }

        iMieiGruppiGUI = new IMieiGruppiGUI(this);
        iMieiGruppiGUI.setVisible(true);
    }

    public void btn_dettagliGruppo_infoGruppo() {
        if (dettagliGruppoGUI != null) {
            dettagliGruppoGUI.dispose();
        }

        infoGruppoGUI = new InfoGruppoGUI(this);
        infoGruppoGUI.setVisible(true);
    }

    public void btn_dettagliGruppo_tornaHome() {
        if (dettagliGruppoGUI != null) {
            dettagliGruppoGUI.dispose();
        }

        utenteController.tornaHome();
    }

    public void tornaDettagliGruppoDaSpesa() {
        dettagliGruppoGUI = new DettagliGruppoGUI(this);
        dettagliGruppoGUI.setVisible(true);
    }

    public void btn_infoGruppo_tornaDettagli() {
        if (infoGruppoGUI != null) {
            infoGruppoGUI.dispose();
        }

        dettagliGruppoGUI = new DettagliGruppoGUI(this);
        dettagliGruppoGUI.setVisible(true);
    }

    public void btn_infoGruppo_tornaHome() {
        if (infoGruppoGUI != null) {
            infoGruppoGUI.dispose();
        }

        utenteController.tornaHome();
    }

    public void btn_infoGruppo_salvaModifiche(String nomeGruppo, String tipologiaGruppo) {
        System.out.println("Modifiche gruppo salvate: " + nomeGruppo + " - " + tipologiaGruppo);
    }

    public void btn_dettagliGruppo_visualizzaPartecipanti() {
        if (dettagliGruppoGUI != null) {
            dettagliGruppoGUI.dispose();
        }

        PartecipantiController partecipantiController = new PartecipantiController(this);
        partecipantiController.avvia();
    }

    public void tornaDettagliGruppoDaPartecipanti() {
        dettagliGruppoGUI = new DettagliGruppoGUI(this);
        dettagliGruppoGUI.setVisible(true);
    }
    
    public void btn_dettagliGruppo_scadenze() {
        if (dettagliGruppoGUI != null) {
            dettagliGruppoGUI.dispose();
        }

        ScadenzeController scadenzeController = new ScadenzeController(this);
        scadenzeController.avvia();
    }

    public void tornaDettagliGruppoDaScadenze() {
        dettagliGruppoGUI = new DettagliGruppoGUI(this);
        dettagliGruppoGUI.setVisible(true);
    }
    
	
}
