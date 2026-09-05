package control;

import gui.scadenze.ScadenzeGUI;
import jdbc.JDBCScadenzaDAO;
import model.Coinquilini;
import model.Scadenza;

import java.time.LocalDate;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import gui.scadenze.AggiungiScadenzaGUI;
import gui.scadenze.DettaglioScadenzaGUI;

public class ScadenzeController {

    private GruppoController gruppoController;

    private Coinquilini gruppoSelezionato;
    private Scadenza scadenzaSelezionata;

    private JFrame finestraAttiva;
    private ScadenzeGUI scadenzeGUI;

    private JDBCScadenzaDAO scadenzaDAO;

    public Scadenza getScadenza() {
        return scadenzaSelezionata;
    }

    public ScadenzeController(GruppoController gruppoController) {
        this.gruppoController = gruppoController;
        this.gruppoSelezionato = (Coinquilini) gruppoController.getGruppoSelezionato();
        this.scadenzaDAO = JDBCScadenzaDAO.getSelf();
        List<Scadenza> lista = scadenzaDAO.cercaScadenzePerGruppo(gruppoSelezionato);
        for (Scadenza s : lista) {
            gruppoSelezionato.addScadenza(s);
        }
    }

    private void mostraFinestra(JFrame nuovaFinestra) {
        if (finestraAttiva != null) {
            finestraAttiva.dispose();
        }
        finestraAttiva = nuovaFinestra;
        finestraAttiva.setVisible(true);
    }

    public void caricaLista() {
        if (scadenzeGUI == null) return;
        DefaultTableModel model = scadenzeGUI.getTableModel();
        model.setRowCount(0);
        for (Scadenza o : gruppoSelezionato.getScadenze()) {
            Object[] riga = new Object[]{
                    o,
                    o.getDataScadenza(),
                    o.getImporto()
            };
            model.addRow(riga);
        }
    }

    public void avvia() {
        scadenzeGUI = new ScadenzeGUI(this);
        caricaLista();
        mostraFinestra(scadenzeGUI);
    }

    public void btn_scadenze_aggiungi() {
        mostraFinestra(new AggiungiScadenzaGUI(this));
    }

    public void btn_scadenze_modifica(Object s) {
        scadenzaSelezionata = (Scadenza) s;
        if (scadenzaSelezionata != null) {
            mostraFinestra(new DettaglioScadenzaGUI(this, scadenzaSelezionata.getNome(), scadenzaSelezionata.getDataScadenza(), String.valueOf(scadenzaSelezionata.getImporto())));
        }
    }

    public void btn_scadenze_indietro() {
        if (finestraAttiva != null) {
            finestraAttiva.dispose();
        }
        gruppoController.tornaDettagliGruppoDaScadenze();
    }

    public void btn_aggiungiScadenza_aggiungi(String nome, LocalDate localDate, String importo) {
        try {
            double totale = Double.parseDouble(importo);
            int idScadenza = (scadenzaSelezionata != null) ? scadenzaSelezionata.getId() : 0;
            Scadenza s = new Scadenza(idScadenza, nome, localDate, totale, gruppoSelezionato);
            scadenzaDAO.nuovaScadenza(s);
            gruppoSelezionato.addScadenza(s);

            scadenzeGUI = new ScadenzeGUI(this);
            caricaLista();
            mostraFinestra(scadenzeGUI);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(finestraAttiva, "Il valore inserito in importo non è numerico.");
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(finestraAttiva, e.getMessage());
        }
    }

    public void btn_aggiungiScadenza_indietro() {
        scadenzeGUI = new ScadenzeGUI(this);
        caricaLista();
        mostraFinestra(scadenzeGUI);
    }

    public void btn_dettaglioScadenza_salva(String nome, LocalDate date, String importo) {
        try {
            double totale = Double.parseDouble(importo);
            int idScadenza = (scadenzaSelezionata != null) ? scadenzaSelezionata.getId() : 0;
            Scadenza s = new Scadenza(idScadenza, nome, date, totale, gruppoSelezionato);
            scadenzaDAO.aggiornaScadenza(s);

            if (scadenzaSelezionata != null) {
                scadenzaSelezionata.setNome(nome);
                scadenzaSelezionata.setImporto(totale);
                scadenzaSelezionata.setDataScadenza(date);
            }

            scadenzeGUI = new ScadenzeGUI(this);
            caricaLista();
            mostraFinestra(scadenzeGUI);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(finestraAttiva, "Il valore inserito in importo non è numerico.");
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(finestraAttiva, e.getMessage());
        }
    }

    public void btn_dettaglioScadenza_cancella() {
        if (scadenzaSelezionata != null) {
            scadenzaDAO.eliminaScadenza(scadenzaSelezionata);
        }
        scadenzeGUI = new ScadenzeGUI(this);
        caricaLista();
        mostraFinestra(scadenzeGUI);
    }

    public void btn_dettaglioScadenza_indietro() {
        scadenzeGUI = new ScadenzeGUI(this);
        caricaLista();
        mostraFinestra(scadenzeGUI);
    }
}