package control;

import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import gui.spesa.InserisciSpesaGUI;
import gui.spesa.StoricoSpeseGUI;
import jdbc.JDBCSpesaDAO;
import model.Gruppo;
import model.Spesa;
import model.Utente;

public class SpesaController {

    private Utente utenteLoggato;
    private Gruppo gruppoSelezionato;

    private GruppoController gruppoController;
    private JFrame finestraAttiva;
    private StoricoSpeseGUI storicoSpeseGUI;

    private JDBCSpesaDAO spesaDAO;

    public SpesaController(GruppoController gruppoController) {
        try {
            this.gruppoController = gruppoController;
            this.spesaDAO = JDBCSpesaDAO.getSelf();
            this.utenteLoggato = gruppoController.getUtenteLoggato();
            this.gruppoSelezionato = gruppoController.getGruppoSelezionato();
            ArrayList<Spesa> spese = spesaDAO.cercaSpesaByGruppo(gruppoController.getGruppoSelezionato(), utenteLoggato);
            for (Spesa s : spese) {
                gruppoSelezionato.addSpesa(s);
            }
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    private void mostraFinestra(JFrame nuovaFinestra) {
        if (finestraAttiva != null) {
            finestraAttiva.dispose();
        }
        finestraAttiva = nuovaFinestra;
        finestraAttiva.setVisible(true);
    }

    public void impostaStorico() {
        try {
            if (storicoSpeseGUI == null) return;
            DefaultTableModel model = storicoSpeseGUI.getTableModel();
            model.setRowCount(0);
            for (Spesa o : gruppoSelezionato.getSpese()) {
                Object[] riga = new Object[]{
                        o,
                        o.getData(),
                        String.valueOf(o.getImporto()),
                        o.getValuta().getNome(),
                        o.isComune() ? "Comune" : "Personale",
                        o.getUtenteEffettuante().toString()
                };
                model.addRow(riga);
            }
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public void avviaInserisciSpesa() {
        mostraFinestra(new InserisciSpesaGUI(this));
    }

    public void avviaStoricoSpese() {
        storicoSpeseGUI = new StoricoSpeseGUI(this);
        impostaStorico();
        mostraFinestra(storicoSpeseGUI);
    }

    public void btn_inserisciSpesa_registraSpesa(String nome, String descrizione, String importo, String valuta, LocalDate data, String tipo) {
        if (finestraAttiva != null) {
            finestraAttiva.dispose();
        }
        gruppoController.tornaDettagliGruppoDaSpesa();
    }

    public void btn_inserisciSpesa_tornaGruppo() {
        if (finestraAttiva != null) {
            finestraAttiva.dispose();
        }
        gruppoController.tornaDettagliGruppoDaSpesa();
    }

    public void btn_storicoSpese_tornaGruppo() {
        if (finestraAttiva != null) {
            finestraAttiva.dispose();
        }
        gruppoController.tornaDettagliGruppoDaSpesa();
    }
}