package control;

import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

import model.Coinquilini;
import model.Gruppo;
import model.Indirizzo;
import model.PartecipazioneGruppo;
import model.Studio;
import model.Utente;
import model.Viaggio;
import gui.gruppo.*;
import jdbc.JDBCGruppoDAO;
import jdbc.JDBCIndirizzoDAO;
import jdbc.JDBCPartecipazioneGruppoDAO;
import jdbc.JDBCUtenteDAO;

public class GruppoController {

	private Gruppo gruppoSelezionato;
	private Utente utenteLoggato;
	private PartecipazioneGruppo partecipazioneSelezionata;
	
	private CreazioneGruppoGUI creazioneGruppoGUI;
	private DettagliGruppoGUI dettagliGruppoGUI;
	private IMieiGruppiGUI iMieiGruppiGUI;
	private InfoGruppoGUI infoGruppoGUI;
	
	private JDBCUtenteDAO utenteDAO;
	private JDBCPartecipazioneGruppoDAO partecipazioneGruppoDAO;
	private JDBCGruppoDAO gruppoDAO;
	private JDBCIndirizzoDAO indirizzoDAO;
	
	public UtenteController utenteController;
	public SpesaController spesaController;
	

	public Gruppo getGruppoSelezionato() {
		return gruppoSelezionato;
	}

	public Utente getUtenteLoggato() {
		return utenteLoggato;
	}
	
	public PartecipazioneGruppo getPartecipazioneSelezionata() {
		return partecipazioneSelezionata;
	}

	public UtenteController getUtenteController() {
		return utenteController;
	}

	public GruppoController(UtenteController utenteController) {
		try {
			this.utenteDAO=utenteController.getUtenteDAO(); 
			this.gruppoDAO=new JDBCGruppoDAO(utenteDAO);
			this.partecipazioneGruppoDAO=new JDBCPartecipazioneGruppoDAO(gruppoDAO, utenteDAO);
			this.indirizzoDAO=new JDBCIndirizzoDAO();
			
			this.utenteController=utenteController;
			this.spesaController = new SpesaController(this);
			
			this.utenteLoggato=utenteController.getUtente();
			
			partecipazioneGruppoDAO.cercaPartecipazioniByUtenteId(utenteLoggato);
		}
		catch(RuntimeException e) {
			JOptionPane.showMessageDialog(null, e.getStackTrace());
		}
		
	}
	
	public void caricaGruppi() {
		try {

		    DefaultListModel<Object> model = new DefaultListModel<>();
		    
		    for (PartecipazioneGruppo g : utenteLoggato.getPartecipazioniGruppi()) {
		        model.addElement(g);
		    }
		    
		    iMieiGruppiGUI.aggiornaJList(model);
		
		}catch(RuntimeException e) {
			JOptionPane.showMessageDialog(null, e.getStackTrace());
		}
		
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
	
	public void btn_iMieiGruppi_apriGruppo(Object gruppo) {
		partecipazioneSelezionata = (PartecipazioneGruppo) gruppo;
		try {
			gruppoSelezionato = partecipazioneSelezionata.getGruppo();
			dettagliGruppoGUI = new DettagliGruppoGUI(this);
			dettagliGruppoGUI.setVisible(true);
			iMieiGruppiGUI.dispose();
		}
		catch(RuntimeException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
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
		caricaGruppi();
        iMieiGruppiGUI.setVisible(true);
    }
	
	
	
	
	public void btn_creazioneGruppo_generico(String nome) {
		try {
		if(JOptionPane.showConfirmDialog(null, "Confermi la creazione del gruppo \""+nome+"\"?")==JOptionPane.YES_OPTION) {
			Gruppo g=new Gruppo(nome, utenteLoggato, LocalDate.now());
			
			gruppoDAO.inserisciGruppo(g);
			PartecipazioneGruppo p =partecipazioneGruppoDAO.getPartecipazione(utenteLoggato, g);
			utenteLoggato.addGruppo(p);
			
			creazioneGruppoGUI.dispose();
			iMieiGruppiGUI = new IMieiGruppiGUI(this);
			caricaGruppi();
	        iMieiGruppiGUI.setVisible(true);
			}
		}
		catch(RuntimeException e) {
			JOptionPane.showMessageDialog(creazioneGruppoGUI, e.getMessage());
		}
		
	}
	
	public void btn_creazioneGruppo_studio(String nome, String nomeEsame, String dataAppelloStr) {
		try {
			if (JOptionPane.showConfirmDialog(null, "Confermi la creazione del gruppo studio \"" + nome + "\"?") == JOptionPane.YES_OPTION) {
				LocalDate dataEsame = LocalDate.parse(dataAppelloStr);
				Studio g = new Studio(nome, utenteLoggato, LocalDate.now(), nomeEsame, dataEsame);
				
				gruppoDAO.inserisciGruppo(g);
				PartecipazioneGruppo p = partecipazioneGruppoDAO.getPartecipazione(utenteLoggato, g);
				utenteLoggato.addGruppo(p);
				
				creazioneGruppoGUI.dispose();
				iMieiGruppiGUI = new IMieiGruppiGUI(this);
				caricaGruppi();
				iMieiGruppiGUI.setVisible(true);
			}
		} catch (RuntimeException e) {
			JOptionPane.showMessageDialog(creazioneGruppoGUI, e.getMessage());
		}
	}

	public void btn_creazioneGruppo_viaggio(String nome, String destinazione, String dataInizioStr, String dataFineStr) {
		try {
			if (JOptionPane.showConfirmDialog(null, "Confermi la creazione del gruppo viaggio \"" + nome + "\"?") == JOptionPane.YES_OPTION) {
				LocalDate dataInizio = LocalDate.parse(dataInizioStr);
				LocalDate dataFine = LocalDate.parse(dataFineStr);
				Viaggio g = new Viaggio(nome, utenteLoggato, LocalDate.now(), dataInizio, dataFine, destinazione);
				
				gruppoDAO.inserisciGruppo(g);
				PartecipazioneGruppo p = partecipazioneGruppoDAO.getPartecipazione(utenteLoggato, g);
				utenteLoggato.addGruppo(p);
				
				creazioneGruppoGUI.dispose();
				iMieiGruppiGUI = new IMieiGruppiGUI(this);
				caricaGruppi();
				iMieiGruppiGUI.setVisible(true);
			}
		} catch (RuntimeException e) {
			JOptionPane.showMessageDialog(creazioneGruppoGUI, e.getMessage());
		}
	}

	public void btn_creazioneGruppo_coinquilini(String nome, String provincia, String citta, String via, String numCivicoStr) {
		try {
			if (JOptionPane.showConfirmDialog(null, "Confermi la creazione del gruppo coinquilini \"" + nome + "\"?") == JOptionPane.YES_OPTION) {
				int numCivico = Integer.parseInt(numCivicoStr);
				Indirizzo indirizzo = new Indirizzo(provincia.toUpperCase(), citta, via, numCivico);
				indirizzoDAO.nuovoIndirizzo(indirizzo);
				Coinquilini g = new Coinquilini(nome, utenteLoggato, LocalDate.now(), indirizzo);
				gruppoDAO.inserisciGruppo(g);
				PartecipazioneGruppo p = partecipazioneGruppoDAO.getPartecipazione(utenteLoggato, g);
				utenteLoggato.addGruppo(p);
				
				creazioneGruppoGUI.dispose();
				iMieiGruppiGUI = new IMieiGruppiGUI(this);
				caricaGruppi();
				iMieiGruppiGUI.setVisible(true);
			}
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(creazioneGruppoGUI, "Numero civico non valido");
		} catch (RuntimeException e) {
			JOptionPane.showMessageDialog(creazioneGruppoGUI, e.getMessage());
		}
	}
	


    public void btn_creazioneGruppo_annulla() {
        if (creazioneGruppoGUI != null) {
            creazioneGruppoGUI.dispose();
        }

        iMieiGruppiGUI = new IMieiGruppiGUI(this);
        caricaGruppi();
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

        spesaController.avviaInserisciSpesa();
    }

    public void btn_dettagliGruppo_storicoSpese() {
        if (dettagliGruppoGUI != null) {
            dettagliGruppoGUI.dispose();
        }

        spesaController.avviaStoricoSpese();
    }

    public void btn_dettagliGruppo_tornaGruppi() {
        if (dettagliGruppoGUI != null) {
            dettagliGruppoGUI.dispose();
        }

        iMieiGruppiGUI = new IMieiGruppiGUI(this);
        caricaGruppi();
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
        
    	if(gruppoSelezionato instanceof Coinquilini) {
    		if (dettagliGruppoGUI != null) {
                dettagliGruppoGUI.dispose();
            }

            ScadenzeController scadenzeController = new ScadenzeController(this);
            scadenzeController.avvia();
    	}
    	else {
    		JOptionPane.showMessageDialog(dettagliGruppoGUI, "Funzione non utilizzabile perchè il gruppo non è di tipo Coinquilini");
    	}
    	
    	
    }

    public void tornaDettagliGruppoDaScadenze() {
        dettagliGruppoGUI = new DettagliGruppoGUI(this);
        dettagliGruppoGUI.setVisible(true);
    }
    
	
}
