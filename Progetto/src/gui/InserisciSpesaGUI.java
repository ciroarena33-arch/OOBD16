package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class InserisciSpesaGUI {
    public static void main(String[] args) {

        JFrame frame = new JFrame("Inserisci spesa");

        JPanel panelPrincipale = new JPanel();
        panelPrincipale.setLayout(new GridBagLayout());

        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(750, 520));
        panel.setLayout(new BorderLayout(10, 20));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

        JLabel titolo = new JLabel("Inserisci nuova spesa", SwingConstants.CENTER);
        titolo.setFont(new Font("Arial", Font.BOLD, 28));

        JPanel panelCampi = new JPanel();
        panelCampi.setLayout(new GridLayout(6, 2, 10, 15));

        JLabel labelNome = new JLabel("Nome spesa:");
        JTextField fieldNome = new JTextField();

        JLabel labelDescrizione = new JLabel("Descrizione:");
        JTextField fieldDescrizione = new JTextField();

        JLabel labelImporto = new JLabel("Importo:");
        JTextField fieldImporto = new JTextField();

        JLabel labelData = new JLabel("Data spesa:");
        JTextField fieldData = new JTextField();

        JLabel labelTipo = new JLabel("Tipo spesa:");
        String[] tipiSpesa = {"COMUNE", "PERSONALE"};
        JComboBox<String> comboTipo = new JComboBox<>(tipiSpesa);

        JLabel labelPagataDa = new JLabel("Pagata da:");
        JTextField fieldPagataDa = new JTextField();

        panelCampi.add(labelNome);
        panelCampi.add(fieldNome);

        panelCampi.add(labelDescrizione);
        panelCampi.add(fieldDescrizione);

        panelCampi.add(labelImporto);
        panelCampi.add(fieldImporto);

        panelCampi.add(labelData);
        panelCampi.add(fieldData);

        panelCampi.add(labelTipo);
        panelCampi.add(comboTipo);

        panelCampi.add(labelPagataDa);
        panelCampi.add(fieldPagataDa);

        JPanel panelBottoni = new JPanel();
        panelBottoni.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));

        JButton registraSpesa = new JButton("REGISTRA SPESA");
        JButton tornaGruppo = new JButton("TORNA AL GRUPPO");

        panelBottoni.add(registraSpesa);
        panelBottoni.add(tornaGruppo);

        JLabel messaggio = new JLabel("", SwingConstants.CENTER);
        messaggio.setFont(new Font("Arial", Font.BOLD, 15));

        JPanel panelBasso = new JPanel();
        panelBasso.setLayout(new GridLayout(2, 1, 10, 10));
        panelBasso.add(panelBottoni);
        panelBasso.add(messaggio);

        panel.add(titolo, BorderLayout.NORTH);
        panel.add(panelCampi, BorderLayout.CENTER);
        panel.add(panelBasso, BorderLayout.SOUTH);

        panelPrincipale.add(panel);

        registraSpesa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = fieldNome.getText();
                String descrizione = fieldDescrizione.getText();
                String importo = fieldImporto.getText();
                String data = fieldData.getText();
                String tipo = comboTipo.getSelectedItem().toString();
                String pagataDa = fieldPagataDa.getText();

                if (nome.isEmpty() || importo.isEmpty() || data.isEmpty() || pagataDa.isEmpty()) {
                    messaggio.setText("Compila nome, importo, data e pagante");
                } else {
                    messaggio.setText("Spesa registrata: " + nome + " - " + importo + "€ - " + tipo);
                }
            }
        });

        tornaGruppo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                DettaglioGruppoGUI.main(null);
            }
        });

        frame.setContentPane(panelPrincipale);
        frame.setSize(1000, 700);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}