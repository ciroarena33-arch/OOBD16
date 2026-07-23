package gui.gruppo;

import javax.swing.*;

import control.GruppoController;
import de.wannawork.jcalendar.JCalendarComboBox;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InfoGruppoGUI extends JFrame {

    private GruppoController controller;

    public InfoGruppoGUI(GruppoController controller) {
        super("Info gruppo");
        this.controller = controller;

        model.Gruppo g = controller.getGruppoSelezionato();
        String nomeAttuale = g.getNome();
        String tipoAttuale = "GENERICO";
        if (g instanceof model.Viaggio) tipoAttuale = "VIAGGIO";
        else if (g instanceof model.Coinquilini) tipoAttuale = "COINQUILINI";
        else if (g instanceof model.Studio) tipoAttuale = "STUDIO";

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel titolo = new JLabel("Info gruppo", SwingConstants.CENTER);
        titolo.setFont(new Font("Arial", Font.BOLD, 45));
        titolo.setBounds(200, 45, 600, 70);

        JLabel labelNomeGruppo = new JLabel("Nome gruppo:");
        labelNomeGruppo.setFont(new Font("Arial", Font.BOLD, 16));
        labelNomeGruppo.setBounds(315, 145, 180, 30);

        JTextField fieldNomeGruppo = new JTextField(nomeAttuale);
        fieldNomeGruppo.setEditable(false);
        fieldNomeGruppo.setFont(new Font("Arial", Font.PLAIN, 18));
        fieldNomeGruppo.setBounds(455, 145, 260, 35);

        JLabel labelTipologia = new JLabel("Tipologia:");
        labelTipologia.setFont(new Font("Arial", Font.BOLD, 16));
        labelTipologia.setBounds(315, 200, 180, 30);

        String[] tipiGruppo = {"GENERICO", "VIAGGIO", "COINQUILINI", "STUDIO"};
        JComboBox<String> comboTipologia = new JComboBox<>(tipiGruppo);
        comboTipologia.setSelectedItem(tipoAttuale);
        comboTipologia.setFont(new Font("Arial", Font.PLAIN, 16));
        comboTipologia.setBounds(455, 200, 260, 35);

        JPanel panelSpecifico = new JPanel();
        panelSpecifico.setLayout(null);
        panelSpecifico.setBounds(315, 260, 400, 130);

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

                    JTextField fieldDestinazione = new JTextField();
                    fieldDestinazione.setFont(new Font("Arial", Font.PLAIN, 16));
                    fieldDestinazione.setBounds(140, 10, 260, 32);

                    JLabel dataPartenza = new JLabel("Data partenza:");
                    dataPartenza.setFont(new Font("Arial", Font.BOLD, 15));
                    dataPartenza.setBounds(0, 60, 140, 30);

                    JCalendarComboBox fieldDataPartenza = new JCalendarComboBox();
                    fieldDataPartenza.setFont(new Font("Arial", Font.PLAIN, 16));
                    fieldDataPartenza.setBounds(140, 60, 260, 32);
                    
                    JLabel dataRitorno = new JLabel("Data ritorno:");
                    dataRitorno.setFont(new Font("Arial", Font.BOLD, 15));
                    dataRitorno.setBounds(0, 110, 140, 30);

                    JCalendarComboBox fieldDataRitorno = new JCalendarComboBox();
                    fieldDataRitorno.setFont(new Font("Arial", Font.PLAIN, 16));
                    fieldDataRitorno.setBounds(140, 110, 260, 32);

                    panelSpecifico.add(destinazione);
                    panelSpecifico.add(fieldDestinazione);
                    panelSpecifico.add(dataPartenza);
                    panelSpecifico.add(fieldDataPartenza);
                    panelSpecifico.add(dataRitorno);
                    panelSpecifico.add(fieldDataRitorno);
                }

                if (tipologia.equals("COINQUILINI")) {
                	JLabel provincia = new JLabel("Provincia:");
                    provincia.setFont(new Font("Arial", Font.BOLD, 15));
                    provincia.setBounds(0, 10, 140, 30);

                    JTextField fieldProvincia = new JTextField();
                    fieldProvincia.setFont(new Font("Arial", Font.PLAIN, 16));
                    fieldProvincia.setBounds(140, 10, 260, 32);

                    JLabel citta = new JLabel("Città:");
                    citta.setFont(new Font("Arial", Font.BOLD, 15));
                    citta.setBounds(0, 60, 140, 30);

                    JTextField fieldCitta = new JTextField();
                    fieldCitta.setFont(new Font("Arial", Font.PLAIN, 16));
                    fieldCitta.setBounds(140, 60, 260, 32);

                    JLabel via = new JLabel("Via:");
                    via.setFont(new Font("Arial", Font.BOLD, 15));
                    via.setBounds(0, 110, 140, 30);

                    JTextField fieldVia = new JTextField();
                    fieldVia.setFont(new Font("Arial", Font.PLAIN, 16));
                    fieldVia.setBounds(140, 110, 260, 32);

                    JLabel numeroCivico = new JLabel("Numero Civico:");
                    numeroCivico.setFont(new Font("Arial", Font.BOLD, 15));
                    numeroCivico.setBounds(0, 160, 140, 30);

                    JTextField fieldNumeroCivico = new JTextField();
                    fieldNumeroCivico.setFont(new Font("Arial", Font.PLAIN, 16));
                    fieldNumeroCivico.setBounds(140, 160, 260, 32);

                    panelSpecifico.add(provincia);
                    panelSpecifico.add(fieldProvincia);
                    panelSpecifico.add(citta);
                    panelSpecifico.add(fieldCitta);
                    panelSpecifico.add(via);
                    panelSpecifico.add(fieldVia);
                    panelSpecifico.add(numeroCivico);
                    panelSpecifico.add(fieldNumeroCivico);
                }

                if (tipologia.equals("STUDIO")) {
                	JLabel esame = new JLabel("Nome Esame:");
                    esame.setFont(new Font("Arial", Font.BOLD, 15));
                    esame.setBounds(0, 10, 140, 30);

                    JTextField fieldEsame = new JTextField();
                    fieldEsame.setFont(new Font("Arial", Font.PLAIN, 16));
                    fieldEsame.setBounds(140, 10, 260, 32);
                    
                    JLabel dataAppello = new JLabel("data Appello:");
                    dataAppello.setFont(new Font("Arial", Font.BOLD, 15));
                    dataAppello.setBounds(0, 60, 140, 30);

                    JCalendarComboBox fieldDataAppello = new JCalendarComboBox();
                    fieldDataAppello.setFont(new Font("Arial", Font.PLAIN, 16));
                    fieldDataAppello.setBounds(140, 60, 260, 32);
                    
                    panelSpecifico.add(dataAppello);
                    panelSpecifico.add(fieldDataAppello);
                    panelSpecifico.add(esame);
                    panelSpecifico.add(fieldEsame);
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
                    messaggio.setText("Il nome del gruppo non può essere vuoto");
                } else {
                    messaggio.setText("Modifiche salvate: " + nome + " - " + tipologia);
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
