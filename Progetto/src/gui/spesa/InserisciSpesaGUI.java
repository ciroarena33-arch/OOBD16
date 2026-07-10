package gui.spesa;

import javax.swing.*;
import gui.gruppo.DettagliGruppoGUI;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import control.SpesaController;

public class InserisciSpesaGUI extends JFrame {
    private static final long serialVersionUID = 1L;

    private SpesaController controller;

    private JPanel contentPane;
    private JLabel titolo;
    private JLabel labelNome;
    private JTextField fieldNome;
    private JLabel labelDescrizione;
    private JTextField fieldDescrizione;
    private JLabel labelImporto;
    private JTextField fieldImporto;
    private JLabel labelData;
    private JTextField fieldData;
    private JLabel labelTipo;
    private JComboBox<String> comboTipo;
    private JLabel labelPagataDa;
    private JTextField fieldPagataDa;
    private JButton registraSpesa;
    private JButton tornaGruppo;
    private JLabel messaggio;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    InserisciSpesaGUI frame = new InserisciSpesaGUI(null);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public InserisciSpesaGUI(SpesaController controller) {
        this.controller = controller;

        setTitle("Inserisci spesa");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(550, 580);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setLayout(null);
        setContentPane(contentPane);

        titolo = new JLabel("Inserisci nuova spesa", SwingConstants.CENTER);
        titolo.setFont(new Font("Arial", Font.BOLD, 24));
        titolo.setBounds(100, 30, 350, 35);
        contentPane.add(titolo);

        // Nome spesa
        labelNome = new JLabel("Nome spesa:");
        labelNome.setFont(new Font("Arial", Font.BOLD, 14));
        labelNome.setBounds(60, 80, 120, 25);
        contentPane.add(labelNome);

        fieldNome = new JTextField();
        fieldNome.setFont(new Font("Arial", Font.PLAIN, 14));
        fieldNome.setBounds(190, 80, 280, 25);
        contentPane.add(fieldNome);

        // Descrizione
        labelDescrizione = new JLabel("Descrizione:");
        labelDescrizione.setFont(new Font("Arial", Font.BOLD, 14));
        labelDescrizione.setBounds(60, 125, 120, 25);
        contentPane.add(labelDescrizione);

        fieldDescrizione = new JTextField();
        fieldDescrizione.setFont(new Font("Arial", Font.PLAIN, 14));
        fieldDescrizione.setBounds(190, 125, 280, 25);
        contentPane.add(fieldDescrizione);

        // Importo
        labelImporto = new JLabel("Importo:");
        labelImporto.setFont(new Font("Arial", Font.BOLD, 14));
        labelImporto.setBounds(60, 170, 120, 25);
        contentPane.add(labelImporto);

        fieldImporto = new JTextField();
        fieldImporto.setFont(new Font("Arial", Font.PLAIN, 14));
        fieldImporto.setBounds(190, 170, 280, 25);
        contentPane.add(fieldImporto);

        // Data
        labelData = new JLabel("Data spesa:");
        labelData.setFont(new Font("Arial", Font.BOLD, 14));
        labelData.setBounds(60, 215, 120, 25);
        contentPane.add(labelData);

        fieldData = new JTextField();
        fieldData.setFont(new Font("Arial", Font.PLAIN, 14));
        fieldData.setBounds(190, 215, 280, 25);
        contentPane.add(fieldData);

        // Tipo spesa
        labelTipo = new JLabel("Tipo spesa:");
        labelTipo.setFont(new Font("Arial", Font.BOLD, 14));
        labelTipo.setBounds(60, 260, 120, 25);
        contentPane.add(labelTipo);

        String[] tipiSpesa = {"COMUNE", "PERSONALE"};
        comboTipo = new JComboBox<>(tipiSpesa);
        comboTipo.setFont(new Font("Arial", Font.PLAIN, 14));
        comboTipo.setBounds(190, 260, 280, 25);
        contentPane.add(comboTipo);

        // Pagata da
        labelPagataDa = new JLabel("Pagata da:");
        labelPagataDa.setFont(new Font("Arial", Font.BOLD, 14));
        labelPagataDa.setBounds(60, 305, 120, 25);
        contentPane.add(labelPagataDa);

        fieldPagataDa = new JTextField();
        fieldPagataDa.setFont(new Font("Arial", Font.PLAIN, 14));
        fieldPagataDa.setBounds(190, 305, 280, 25);
        contentPane.add(fieldPagataDa);

        // Pulsanti
        registraSpesa = new JButton("REGISTRA SPESA");
        registraSpesa.setFont(new Font("Arial", Font.BOLD, 13));
        registraSpesa.setBounds(60, 380, 190, 35);
        contentPane.add(registraSpesa);

        tornaGruppo = new JButton("TORNA AL GRUPPO");
        tornaGruppo.setFont(new Font("Arial", Font.BOLD, 13));
        tornaGruppo.setBounds(280, 380, 190, 35);
        contentPane.add(tornaGruppo);

        messaggio = new JLabel("", SwingConstants.CENTER);
        messaggio.setFont(new Font("Arial", Font.BOLD, 13));
        messaggio.setBounds(60, 440, 410, 25);
        contentPane.add(messaggio);

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
                dispose();
                DettagliGruppoGUI.main(null);
            }
        });
    }
}