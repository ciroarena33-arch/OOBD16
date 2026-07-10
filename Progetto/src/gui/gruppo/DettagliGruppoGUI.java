package gui.gruppo;

import javax.swing.*;

import control.GruppoController;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DettagliGruppoGUI extends JFrame {

    private GruppoController controller;

    public DettagliGruppoGUI(GruppoController controller) {
        super("Dettaglio gruppo");
        this.controller = controller;

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel logo = new JLabel("UninaMoneySplit", SwingConstants.CENTER);
        logo.setFont(new Font("Arial", Font.BOLD, 50));
        logo.setBounds(250, 35, 500, 70);

        JLabel titoloGruppo = new JLabel("Gruppo: " + controller.getGruppoSelezionato().getNome(), SwingConstants.CENTER);
        titoloGruppo.setFont(new Font("Arial", Font.BOLD, 24));
        titoloGruppo.setBounds(200, 115, 600, 40);

        JLabel numeroPartecipanti = new JLabel("Numero partecipanti: 3", SwingConstants.CENTER);
        numeroPartecipanti.setFont(new Font("Arial", Font.BOLD, 16));
        numeroPartecipanti.setBounds(250, 160, 500, 30);

        JButton inserisciSpesa = new JButton("INSERISCI SPESA");
        inserisciSpesa.setFont(new Font("Arial", Font.BOLD, 16));
        inserisciSpesa.setBounds(315, 220, 400, 40);

        JButton storicoSpese = new JButton("STORICO SPESE");
        storicoSpese.setFont(new Font("Arial", Font.BOLD, 16));
        storicoSpese.setBounds(315, 275, 400, 40);

        JButton visualizzaPartecipanti = new JButton("VISUALIZZA PARTECIPANTI");
        visualizzaPartecipanti.setFont(new Font("Arial", Font.BOLD, 16));
        visualizzaPartecipanti.setBounds(315, 330, 400, 40);

        JButton scadenze = new JButton("SCADENZE");
        scadenze.setFont(new Font("Arial", Font.BOLD, 16));
        scadenze.setBounds(315, 385, 400, 40);

        JButton infoGruppo = new JButton("INFO GRUPPO");
        infoGruppo.setFont(new Font("Arial", Font.BOLD, 16));
        infoGruppo.setBounds(315, 440, 400, 40);

        JButton tornaGruppi = new JButton("TORNA AI GRUPPI");
        tornaGruppi.setFont(new Font("Arial", Font.BOLD, 16));
        tornaGruppi.setBounds(315, 495, 400, 40);

        JLabel messaggio = new JLabel("Seleziona un'operazione", SwingConstants.CENTER);
        messaggio.setFont(new Font("Arial", Font.BOLD, 15));
        messaggio.setBounds(250, 555, 500, 35);

        panel.add(logo);
        panel.add(titoloGruppo);
        panel.add(numeroPartecipanti);
        panel.add(inserisciSpesa);
        panel.add(storicoSpese);
        panel.add(visualizzaPartecipanti);
        panel.add(scadenze);
        panel.add(infoGruppo);
        panel.add(tornaGruppi);
        panel.add(messaggio);

        inserisciSpesa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.btn_dettagliGruppo_inserisciSpesa();
            }
        });

        storicoSpese.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.btn_dettagliGruppo_storicoSpese();
            }
        });

        visualizzaPartecipanti.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.btn_dettagliGruppo_visualizzaPartecipanti();
            }
        });

        scadenze.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.btn_dettagliGruppo_scadenze();
            }
        });

        infoGruppo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.btn_dettagliGruppo_infoGruppo();
            }
        });

        tornaGruppi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.btn_dettagliGruppo_tornaGruppi();
            }
        });

        setContentPane(panel);
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}