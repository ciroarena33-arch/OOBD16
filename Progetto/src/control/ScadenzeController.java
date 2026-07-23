package control;

import gui.scadenze.ScadenzeGUI;
import jdbc.JDBCScadenzaDAO;
import model.Coinquilini;
import model.Scadenza;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import gui.scadenze.AggiungiScadenzaGUI;
import gui.scadenze.DettaglioScadenzaGUI;

public class ScadenzeController {

    private GruppoController gruppoController;
    
    private Coinquilini gruppoSelezionato;
    private Scadenza scadenzaSelezionata;

    private ScadenzeGUI scadenzeGUI;
    private AggiungiScadenzaGUI aggiungiScadenzaGUI;
    private DettaglioScadenzaGUI dettaglioScadenzaGUI;
    
    private JDBCScadenzaDAO scadenzaDAO;
    
    public Scadenza getScadenza() {
    	return scadenzaSelezionata;
    }

    public ScadenzeController(GruppoController gruppoController) {
        this.gruppoController = gruppoController;
        this.gruppoSelezionato=(Coinquilini)gruppoController.getGruppoSelezionato();
        this.scadenzaDAO = new JDBCScadenzaDAO();
        List<Scadenza> lista = scadenzaDAO.cercaScadenzePerGruppo(gruppoSelezionato);
        for(Scadenza s:lista) {
        	gruppoSelezionato.addScadenza(s);
        }   
    }
    
    public void caricaLista() {
        DefaultTableModel model = scadenzeGUI.getTableModel();
        model.setRowCount(0); 
        for (Scadenza o : gruppoSelezionato.getScadenze()) {
        	System.out.println(o.toString());
            Object[] riga = new Object[] {
                o,
                o.getDataScadenza(),
                o.getImporto()
            };
            model.addRow(riga); // Aggiungi la riga al modello graficamente
        }
        
    }

    public void avvia() {
        scadenzeGUI = new ScadenzeGUI(this);
        caricaLista();
        scadenzeGUI.setVisible(true);
    }

    public void btn_scadenze_aggiungi() {
        if (scadenzeGUI != null) {
            scadenzeGUI.dispose();
        }

        aggiungiScadenzaGUI = new AggiungiScadenzaGUI(this);
        aggiungiScadenzaGUI.setVisible(true);
    }

    public void btn_scadenze_modifica(Object s) {
        if (scadenzeGUI != null) {
            scadenzeGUI.dispose();
        }
        scadenzaSelezionata=(Scadenza) s;
        
        dettaglioScadenzaGUI = new DettaglioScadenzaGUI(this);
        
        dettaglioScadenzaGUI.setVisible(true);
    }

    public void btn_scadenze_indietro() {
        if (scadenzeGUI != null) {
            scadenzeGUI.dispose();
        }

        gruppoController.tornaDettagliGruppoDaScadenze();
    }

    public void btn_aggiungiScadenza_aggiungi(String nome, LocalDate localDate, String importo) {
    	try {
    		double totale=Double.parseDouble(importo);
    		Scadenza s=new Scadenza(scadenzaSelezionata.getId(),nome,localDate,totale,gruppoSelezionato);
    		scadenzaDAO.nuovaScadenza(s);
    		gruppoSelezionato.addScadenza(s);
    		
    		scadenzeGUI = new ScadenzeGUI(this);
    		caricaLista();
    		scadenzeGUI.setVisible(true);
    		aggiungiScadenzaGUI.dispose();
    	}catch(NumberFormatException e) {
    		JOptionPane.showMessageDialog(aggiungiScadenzaGUI, "Il valore inserito in importo non è numerico.");
    	}catch(RuntimeException e) {
    		JOptionPane.showMessageDialog(aggiungiScadenzaGUI, e.getStackTrace());
    	}
    }

    public void btn_aggiungiScadenza_indietro() {
        if (aggiungiScadenzaGUI != null) {
            aggiungiScadenzaGUI.dispose();
        }

        scadenzeGUI = new ScadenzeGUI(this);
        caricaLista();
        scadenzeGUI.setVisible(true);
    }

    public void btn_dettaglioScadenza_salva(String nome, LocalDate date, String importo) {
    	try {
    		double totale=Double.parseDouble(importo);
    		Scadenza s=new Scadenza(scadenzaSelezionata.getId(),nome,date,totale,gruppoSelezionato);
    		scadenzaDAO.aggiornaScadenza(s);
    		
    		scadenzaSelezionata.setNome(nome);
    		scadenzaSelezionata.setImporto(totale);
    		scadenzaSelezionata.setDataScadenza(date);
    		
    		scadenzeGUI = new ScadenzeGUI(this);
    		caricaLista();
    		scadenzeGUI.setVisible(true);
    		dettaglioScadenzaGUI.dispose();
    	}catch(NumberFormatException e) {
    		JOptionPane.showMessageDialog(dettaglioScadenzaGUI, "Il valore inserito in importo non è numerico.");
    	}catch(RuntimeException e) {
    		JOptionPane.showMessageDialog(dettaglioScadenzaGUI, e.getStackTrace());
    	}
        
    }

    public void btn_dettaglioScadenza_cancella() {
        System.out.println("Scadenza cancellata");

        if (dettaglioScadenzaGUI != null) {
            dettaglioScadenzaGUI.dispose();
        }

        scadenzeGUI = new ScadenzeGUI(this);
        caricaLista();
        scadenzeGUI.setVisible(true);
    }

    public void btn_dettaglioScadenza_indietro() {
        if (dettaglioScadenzaGUI != null) {
            dettaglioScadenzaGUI.dispose();
        }

        scadenzeGUI = new ScadenzeGUI(this);
        caricaLista();
        scadenzeGUI.setVisible(true);
    }
}