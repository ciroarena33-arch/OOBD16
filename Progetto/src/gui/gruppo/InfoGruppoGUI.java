package gui.gruppo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import control.GruppoController;
import de.wannawork.jcalendar.JCalendarComboBox;

public class InfoGruppoGUI extends JFrame {

    private GruppoController controller;

    public InfoGruppoGUI(GruppoController controller) {
        super();
        setTitle("Info Gruppo");
        this.controller = controller;

        model.Gruppo g = controller.getGruppoSelezionato();
        String nomeAttuale = g.getNome();
        String tipoAttuale = "GENERICO";
        if (g instanceof model.Viaggio) tipoAttuale = "VIAGGIO";
        else if (g instanceof model.Coinquilini) tipoAttuale = "COINQUILINI";
        else if (g instanceof model.Studio) tipoAttuale = "STUDIO";

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(245, 245, 250));

        JLabel titolo = new JLabel("Info Gruppo", SwingConstants.CENTER);
        titolo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titolo.setForeground(new Color(30, 41, 59));
        titolo.setBounds(50, 20, 500, 36);

        JLabel labelNomeGruppo = new JLabel("Nome gruppo:");
        labelNomeGruppo.setFont(new Font("Segoe UI", Font.BOLD, 13));
        labelNomeGruppo.setForeground(new Color(70, 85, 105));
        labelNomeGruppo.setBounds(60, 80, 150, 30);

        JTextField fieldNomeGruppo = new JTextField(nomeAttuale);
        fieldNomeGruppo.setEditable(false);
        fieldNomeGruppo.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        fieldNomeGruppo.setBounds(220, 80, 300, 30);

        JLabel labelTipologia = new JLabel("Tipologia:");
        labelTipologia.setFont(new Font("Segoe UI", Font.BOLD, 13));
        labelTipologia.setForeground(new Color(70, 85, 105));
        labelTipologia.setBounds(60, 125, 150, 30);

        String[] tipiGruppo = {"GENERICO", "VIAGGIO", "COINQUILINI", "STUDIO"};
        JComboBox<String> comboTipologia = new JComboBox<>(tipiGruppo);
        comboTipologia.setSelectedItem(tipoAttuale);
        comboTipologia.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        comboTipologia.setBounds(220, 125, 300, 30);

        JPanel panelSpecifico = new JPanel();
        panelSpecifico.setLayout(null);
        panelSpecifico.setBackground(new Color(245, 245, 250));
        panelSpecifico.setBounds(60, 175, 460, 170);

        JButton salva = new JButton("SALVA MODIFICHE");
        salva.setFont(new Font("Segoe UI", Font.BOLD, 13));
        salva.setBackground(new Color(60, 120, 216));
        salva.setForeground(Color.WHITE);
        salva.setFocusPainted(false);
        salva.setBounds(60, 362, 460, 38);

        JButton indietro = new JButton("INDIETRO");
        indietro.setFont(new Font("Segoe UI", Font.BOLD, 13));
        indietro.setBackground(new Color(110, 120, 135));
        indietro.setForeground(Color.WHITE);
        indietro.setFocusPainted(false);
        indietro.setBounds(60, 415, 220, 38);

        JButton tornaHome = new JButton("TORNA HOME");
        tornaHome.setFont(new Font("Segoe UI", Font.BOLD, 13));
        tornaHome.setBackground(new Color(110, 120, 135));
        tornaHome.setForeground(Color.WHITE);
        tornaHome.setFocusPainted(false);
        tornaHome.setBounds(300, 415, 220, 38);

        JLabel messaggio = new JLabel("", SwingConstants.CENTER);
        messaggio.setFont(new Font("Segoe UI", Font.BOLD, 13));
        messaggio.setBounds(60, 468, 460, 25);

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
                    destinazione.setFont(new Font("Segoe UI", Font.BOLD, 13));
                    destinazione.setForeground(new Color(70, 85, 105));
                    destinazione.setBounds(0, 10, 140, 30);

                    JTextField fieldDestinazione = new JTextField();
                    fieldDestinazione.setFont(new Font("Segoe UI", Font.PLAIN, 13));
                    fieldDestinazione.setBounds(145, 10, 260, 30);

                    JLabel dataPartenza = new JLabel("Data partenza:");
                    dataPartenza.setFont(new Font("Segoe UI", Font.BOLD, 13));
                    dataPartenza.setForeground(new Color(70, 85, 105));
                    dataPartenza.setBounds(0, 55, 140, 30);

                    JCalendarComboBox fieldDataPartenza = new JCalendarComboBox();
                    fieldDataPartenza.setFont(new Font("Segoe UI", Font.PLAIN, 13));
                    fieldDataPartenza.setBounds(145, 55, 260, 30);

                    JLabel dataRitorno = new JLabel("Data ritorno:");
                    dataRitorno.setFont(new Font("Segoe UI", Font.BOLD, 13));
                    dataRitorno.setForeground(new Color(70, 85, 105));
                    dataRitorno.setBounds(0, 100, 140, 30);

                    JCalendarComboBox fieldDataRitorno = new JCalendarComboBox();
                    fieldDataRitorno.setFont(new Font("Segoe UI", Font.PLAIN, 13));
                    fieldDataRitorno.setBounds(145, 100, 260, 30);

                    panelSpecifico.add(destinazione);
                    panelSpecifico.add(fieldDestinazione);
                    panelSpecifico.add(dataPartenza);
                    panelSpecifico.add(fieldDataPartenza);
                    panelSpecifico.add(dataRitorno);
                    panelSpecifico.add(fieldDataRitorno);
                }

                if (tipologia.equals("COINQUILINI")) {
                    JLabel provincia = new JLabel("Provincia:");
                    provincia.setFont(new Font("Segoe UI", Font.BOLD, 13));
                    provincia.setForeground(new Color(70, 85, 105));
                    provincia.setBounds(0, 10, 140, 30);

                    JTextField fieldProvincia = new JTextField();
                    fieldProvincia.setFont(new Font("Segoe UI", Font.PLAIN, 13));
                    fieldProvincia.setBounds(145, 10, 260, 30);

                    JLabel citta = new JLabel("Città:");
                    citta.setFont(new Font("Segoe UI", Font.BOLD, 13));
                    citta.setForeground(new Color(70, 85, 105));
                    citta.setBounds(0, 55, 140, 30);

                    JTextField fieldCitta = new JTextField();
                    fieldCitta.setFont(new Font("Segoe UI", Font.PLAIN, 13));
                    fieldCitta.setBounds(145, 55, 260, 30);

                    JLabel via = new JLabel("Via:");
                    via.setFont(new Font("Segoe UI", Font.BOLD, 13));
                    via.setForeground(new Color(70, 85, 105));
                    via.setBounds(0, 100, 140, 30);

                    JTextField fieldVia = new JTextField();
                    fieldVia.setFont(new Font("Segoe UI", Font.PLAIN, 13));
                    fieldVia.setBounds(145, 100, 260, 30);

                    JLabel numeroCivico = new JLabel("Numero Civico:");
                    numeroCivico.setFont(new Font("Segoe UI", Font.BOLD, 13));
                    numeroCivico.setForeground(new Color(70, 85, 105));
                    numeroCivico.setBounds(0, 145, 140, 30);

                    JTextField fieldNumeroCivico = new JTextField();
                    fieldNumeroCivico.setFont(new Font("Segoe UI", Font.PLAIN, 13));
                    fieldNumeroCivico.setBounds(145, 145, 260, 30);

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
                    esame.setFont(new Font("Segoe UI", Font.BOLD, 13));
                    esame.setForeground(new Color(70, 85, 105));
                    esame.setBounds(0, 10, 140, 30);

                    JTextField fieldEsame = new JTextField();
                    fieldEsame.setFont(new Font("Segoe UI", Font.PLAIN, 13));
                    fieldEsame.setBounds(145, 10, 260, 30);

                    JLabel dataAppello = new JLabel("Data Appello:");
                    dataAppello.setFont(new Font("Segoe UI", Font.BOLD, 13));
                    dataAppello.setForeground(new Color(70, 85, 105));
                    dataAppello.setBounds(0, 55, 140, 30);

                    JCalendarComboBox fieldDataAppello = new JCalendarComboBox();
                    fieldDataAppello.setFont(new Font("Segoe UI", Font.PLAIN, 13));
                    fieldDataAppello.setBounds(145, 55, 260, 30);

                    panelSpecifico.add(dataAppello);
                    panelSpecifico.add(fieldDataAppello);
                    panelSpecifico.add(esame);
                    panelSpecifico.add(fieldEsame);
                }

                panelSpecifico.revalidate();
                panelSpecifico.repaint();
            }
        });

        salva.addActionListener(e -> {
            String nome = fieldNomeGruppo.getText();
            String tipologia = comboTipologia.getSelectedItem().toString();
            if (nome.isEmpty()) {
                messaggio.setText("Il nome del gruppo non può essere vuoto");
            } else {
                messaggio.setText("Modifiche salvate: " + nome + " - " + tipologia);
                controller.btn_infoGruppo_salvaModifiche(nome, tipologia);
            }
        });

        indietro.addActionListener(e -> controller.btn_infoGruppo_tornaDettagli());
        tornaHome.addActionListener(e -> controller.btn_infoGruppo_tornaHome());

        setContentPane(panel);
        setSize(600, 540);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
