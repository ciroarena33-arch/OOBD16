package control;

import gui.spesa.InserisciSpesaGUI;
import gui.spesa.StoricoSpeseGUI;

public class SpesaController {

    private GruppoController gruppoController;

    private InserisciSpesaGUI inserisciSpesaGUI;
    private StoricoSpeseGUI storicoSpeseGUI;

    public SpesaController(GruppoController gruppoController) {
        this.gruppoController = gruppoController;
    }

    public void avviaInserisciSpesa() {
        inserisciSpesaGUI = new InserisciSpesaGUI(this);
        inserisciSpesaGUI.setVisible(true);
    }

    public void avviaStoricoSpese() {
        storicoSpeseGUI = new StoricoSpeseGUI(this);
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