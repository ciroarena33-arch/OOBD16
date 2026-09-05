package gui.gruppo;

import javax.swing.*;
import java.awt.*;
import control.GruppoController;

public class DettagliGruppoGUI extends JFrame {

    private GruppoController controller;

    public DettagliGruppoGUI(GruppoController controller, String nomeGruppo, String proprietarioStr, boolean isUltimoMembro) {
        super();
        setTitle("Dettaglio Gruppo");
        this.controller = controller;

        JPanel panel = new JPanel();
        panel.setBackground(new Color(245, 245, 250));
        panel.setLayout(null);

        JLabel logo = new JLabel("UninaMoneySplit", SwingConstants.CENTER);
        logo.setFont(new Font("Segoe UI", Font.BOLD, 26));
        logo.setForeground(new Color(30, 41, 59));
        logo.setBounds(0, 18, 515, 35);

        JLabel titoloGruppo = new JLabel("Gruppo: " + nomeGruppo, SwingConstants.CENTER);
        titoloGruppo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titoloGruppo.setForeground(new Color(60, 120, 216));
        titoloGruppo.setBounds(0, 58, 515, 30);

        JLabel proprietario = new JLabel("Proprietario: " + proprietarioStr, SwingConstants.CENTER);
        proprietario.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        proprietario.setForeground(new Color(100, 116, 139));
        proprietario.setBounds(0, 90, 515, 25);

        int startY = 125;
        int stepY = 46;

        JButton inserisciSpesa = new JButton("INSERISCI SPESA");
        inserisciSpesa.setFont(new Font("Segoe UI", Font.BOLD, 13));
        inserisciSpesa.setBackground(new Color(60, 120, 216));
        inserisciSpesa.setForeground(Color.WHITE);
        inserisciSpesa.setFocusPainted(false);
        inserisciSpesa.setBounds(85, startY, 345, 36);

        JButton storicoSpese = new JButton("STORICO SPESE");
        storicoSpese.setFont(new Font("Segoe UI", Font.BOLD, 13));
        storicoSpese.setBackground(new Color(60, 120, 216));
        storicoSpese.setForeground(Color.WHITE);
        storicoSpese.setFocusPainted(false);
        storicoSpese.setBounds(85, startY + stepY, 345, 36);

        JButton visualizzaPartecipanti = new JButton("VISUALIZZA PARTECIPANTI");
        visualizzaPartecipanti.setFont(new Font("Segoe UI", Font.BOLD, 13));
        visualizzaPartecipanti.setBackground(new Color(60, 120, 216));
        visualizzaPartecipanti.setForeground(Color.WHITE);
        visualizzaPartecipanti.setFocusPainted(false);
        visualizzaPartecipanti.setBounds(85, startY + stepY * 2, 345, 36);

        JButton scadenze = new JButton("SCADENZE");
        scadenze.setFont(new Font("Segoe UI", Font.BOLD, 13));
        scadenze.setBackground(new Color(60, 120, 216));
        scadenze.setForeground(Color.WHITE);
        scadenze.setFocusPainted(false);
        scadenze.setBounds(85, startY + stepY * 3, 345, 36);

        JButton infoGruppo = new JButton("INFO GRUPPO");
        infoGruppo.setFont(new Font("Segoe UI", Font.BOLD, 13));
        infoGruppo.setBackground(new Color(60, 120, 216));
        infoGruppo.setForeground(Color.WHITE);
        infoGruppo.setFocusPainted(false);
        infoGruppo.setBounds(85, startY + stepY * 4, 345, 36);


        JButton tornaGruppi = new JButton("TORNA AI GRUPPI");
        tornaGruppi.setFont(new Font("Segoe UI", Font.BOLD, 13));
        tornaGruppi.setBackground(new Color(110, 120, 135));
        tornaGruppi.setForeground(Color.WHITE);
        tornaGruppi.setFocusPainted(false);
        tornaGruppi.setBounds(85, startY + stepY * 6, 345, 36);

        JButton abbandonaOElimina = new JButton(isUltimoMembro ? "ELIMINA GRUPPO" : "ABBANDONA GRUPPO");
        abbandonaOElimina.setFont(new Font("Segoe UI", Font.BOLD, 13));
        abbandonaOElimina.setBackground(new Color(210, 60, 60));
        abbandonaOElimina.setForeground(Color.WHITE);
        abbandonaOElimina.setFocusPainted(false);
        abbandonaOElimina.setBounds(85, startY + stepY * 7, 345, 36);

        panel.add(logo);
        panel.add(titoloGruppo);
        panel.add(proprietario);
        panel.add(inserisciSpesa);
        panel.add(storicoSpese);
        panel.add(visualizzaPartecipanti);
        panel.add(scadenze);
        panel.add(infoGruppo);
        panel.add(tornaGruppi);
        panel.add(abbandonaOElimina);

        inserisciSpesa.addActionListener(e -> controller.btn_dettagliGruppo_inserisciSpesa());
        storicoSpese.addActionListener(e -> controller.btn_dettagliGruppo_storicoSpese());
        visualizzaPartecipanti.addActionListener(e -> controller.btn_dettagliGruppo_visualizzaPartecipanti());
        scadenze.addActionListener(e -> controller.btn_dettagliGruppo_scadenze());
        infoGruppo.addActionListener(e -> controller.btn_dettagliGruppo_infoGruppo());
        tornaGruppi.addActionListener(e -> controller.btn_dettagliGruppo_tornaGruppi());
        abbandonaOElimina.addActionListener(e -> controller.btn_dettagliGruppo_abbandonaOElimina());

        setContentPane(panel);
        setSize(525, 540);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
