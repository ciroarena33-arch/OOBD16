package gui.gruppo;

import javax.swing.*;

import control.GruppoController;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InfoGruppoGUI extends JFrame {

    private GruppoController controller;

    public InfoGruppoGUI(GruppoController controller) {
        super("Info gruppo");
        this.controller = controller;

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel titolo = new JLabel("Info gruppo", SwingConstants.CENTER);
        titolo.setFont(new Font("Arial", Font.BOLD, 45));
        titolo.setBounds(200, 45, 600, 70);

        JLabel labelNomeGruppo = new JLabel("Nome gruppo:");
        labelNomeGruppo.setFont(new Font("Arial", Font.BOLD, 16));
        labelNomeGruppo.setBounds(315, 145, 180, 30);

        JTextField fieldNomeGruppo = new JTextField("Viaggio Roma");
        fieldNomeGruppo.setEditable(false);
        fieldNomeGruppo.setFont(new Font("Arial", Font.PLAIN, 18));
        fieldNomeGruppo.setBounds(455, 143, 260, 35);

        JLabel labelTipologia = new JLabel("Tipologia:");
        labelTipologia.setFont(new Font("Arial", Font.BOLD, 16));
        labelTipologia.setBounds(315, 200, 180, 30);

        String[] tipiGruppo = {"GENERICO", "VIAGGIO", "COINQUILINI", "STUDIO"};
        JComboBox<String> comboTipologia = new JComboBox<>(tipiGruppo);
        comboTipologia.setFont(new Font("Arial", Font.PLAIN, 16));
        comboTipologia.setBounds(455, 200, 260, 35);

        JPanel panelSpecifico = new JPanel();
        panelSpecifico.setLayout(null);
        panelSpecifico.setBounds(315, 260, 400, 159);

        JButton salva = new JButton("SALVA MODIFICHE");
        salva.setFont(new Font("Arial", Font.BOLD, 16));
        salva.setBounds(315, 430, 400, 40);

        JButton indietro = new JButton("INDIETRO");
        indietro.setFont(new Font("Arial", Font.BOLD, 16));
        indietro.setBounds(315, 490, 190, 40);

        JButton tornaHome = new JButton("TORNA HOME");
        tornaHome.setFont(new Font("Arial", Font.BOLD, 16));
        tornaHome.setBounds(525, 490, 190, 40);

        JLabel messaggio = new JLabel("", SwingConstants.CENTER);
        messaggio.setFont(new Font("Arial", Font.BOLD, 15));
        messaggio.setBounds(200, 550, 600, 35);

        panel.add(titolo);
        panel.add(labelNomeGruppo);
        panel.add(fieldNomeGruppo);
        panel.add(labelTipologia);
        panel.add(comboTipologia);
        panel.add(panelSpecifico);
        panel.add(salva);
        panel.add(indietro);
        panel.add(tornaHome);
        panel.add(messaggio);

        comboTipologia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelSpecifico.removeAll();

                String tipologia = comboTipologia.getSelectedItem().toString();

                if (tipologia.equals("VIAGGIO")) {
                    JLabel destinazione = new JLabel("Destinazione:");
                    destinazione.setFont(new Font("Arial", Font.BOLD, 15));
                    destinazione.setBounds(0, 10, 140, 30);

                    JTextField fieldDestinazione = new JTextField("Roma");
                    fieldDestinazione.setFont(new Font("Arial", Font.PLAIN, 16));
                    fieldDestinazione.setBounds(140, 10, 260, 32);

                    JLabel dataPartenza = new JLabel("Data partenza:");
                    dataPartenza.setFont(new Font("Arial", Font.BOLD, 15));
                    dataPartenza.setBounds(0, 60, 140, 30);

                    JTextField fieldDataPartenza = new JTextField("20/07/2026");
                    fieldDataPartenza.setFont(new Font("Arial", Font.PLAIN, 16));
                    fieldDataPartenza.setBounds(140, 60, 260, 32);

                    panelSpecifico.add(destinazione);
                    panelSpecifico.add(fieldDestinazione);
                    panelSpecifico.add(dataPartenza);
                    panelSpecifico.add(fieldDataPartenza);
                }

                if (tipologia.equals("COINQUILINI")) {
                    JLabel indirizzo = new JLabel("Indirizzo:");
                    indirizzo.setFont(new Font("Arial", Font.BOLD, 15));
                    indirizzo.setBounds(0, 10, 140, 30);

                    JTextField fieldIndirizzo = new JTextField("Via Napoli 10");
                    fieldIndirizzo.setFont(new Font("Arial", Font.PLAIN, 16));
                    fieldIndirizzo.setBounds(140, 10, 260, 32);

                    JLabel canone = new JLabel("Canone casa:");
                    canone.setFont(new Font("Arial", Font.BOLD, 15));
                    canone.setBounds(0, 60, 140, 30);

                    JTextField fieldCanone = new JTextField("800");
                    fieldCanone.setFont(new Font("Arial", Font.PLAIN, 16));
                    fieldCanone.setBounds(140, 60, 260, 32);

                    panelSpecifico.add(indirizzo);
                    panelSpecifico.add(fieldIndirizzo);
                    panelSpecifico.add(canone);
                    panelSpecifico.add(fieldCanone);
                }

                if (tipologia.equals("STUDIO")) {
                    JLabel nomeEsame = new JLabel("Nome esame:");
                    nomeEsame.setFont(new Font("Arial", Font.BOLD, 15));
                    nomeEsame.setBounds(0, 10, 140, 30);

                    JTextField fieldNomeEsame = new JTextField("Basi di Dati");
                    fieldNomeEsame.setFont(new Font("Arial", Font.PLAIN, 16));
                    fieldNomeEsame.setBounds(140, 10, 260, 32);

                    JLabel data = new JLabel("Esame:");
                    data.setFont(new Font("Arial", Font.BOLD, 15));
                    data.setBounds(0, 60, 140, 30);

                    JTextField fieldData = new JTextField("SQL");
                    fieldData.setFont(new Font("Arial", Font.PLAIN, 16));
                    fieldData.setBounds(140, 60, 260, 32);

                    panelSpecifico.add(nomeEsame);
                    panelSpecifico.add(fieldNomeEsame);
                    panelSpecifico.add(data);
                    panelSpecifico.add(fieldData);
                }

                panelSpecifico.revalidate();
                panelSpecifico.repaint();
            }
        });

        salva.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = fieldNomeGruppo.getText();
                String tipologia = comboTipologia.getSelectedItem().toString();

                if (nome.isEmpty()) {
                    JOptionPane.showMessageDialog(null,"Il nome del gruppo non può essere vuoto");
                } else {
                    controller.btn_infoGruppo_salvaModifiche(nome, tipologia);
                }
            }
        });

        indietro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.btn_infoGruppo_tornaDettagli();
            }
        });

        tornaHome.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.btn_infoGruppo_tornaHome();
            }
        });

        setContentPane(panel);
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
