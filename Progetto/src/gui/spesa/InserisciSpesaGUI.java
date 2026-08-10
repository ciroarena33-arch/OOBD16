package gui.spesa;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import control.SpesaController;
import de.wannawork.jcalendar.JCalendarComboBox;

public class InserisciSpesaGUI extends JFrame {

    private SpesaController controller;
    private JPanel contentPane;
    private JLabel titolo, messaggio;
    private JLabel labelNome, labelDescrizione, labelImporto, labelData, labelTipo, labelPagataDa;
    private JTextField fieldNome, fieldDescrizione, fieldImporto, fieldPagataDa;
    private JCalendarComboBox fieldData;
    private JComboBox<String> comboTipo;
    private JButton registraSpesa, tornaGruppo;

    public InserisciSpesaGUI(SpesaController controller) {
        super();
        setTitle("Inserisci Spesa");
        this.controller = controller;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(520, 520);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setLayout(null);
        contentPane.setBackground(new Color(245, 245, 250));
        setContentPane(contentPane);

        JLabel titolo = new JLabel("Inserisci nuova spesa", SwingConstants.CENTER);
        titolo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titolo.setForeground(new Color(30, 41, 59));
        titolo.setBounds(0, 18, 520, 36);
        contentPane.add(titolo);

        labelNome = new JLabel("Nome spesa:");
        labelNome.setFont(new Font("Segoe UI", Font.BOLD, 13));
        labelNome.setForeground(new Color(70, 85, 105));
        labelNome.setBounds(35, 70, 110, 36);
        contentPane.add(labelNome);

        fieldNome = new JTextField();
        fieldNome.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        fieldNome.setBounds(150, 70, 330, 36);
        contentPane.add(fieldNome);

        labelDescrizione = new JLabel("Descrizione:");
        labelDescrizione.setFont(new Font("Segoe UI", Font.BOLD, 13));
        labelDescrizione.setForeground(new Color(70, 85, 105));
        labelDescrizione.setBounds(35, 118, 110, 36);
        contentPane.add(labelDescrizione);

        fieldDescrizione = new JTextField();
        fieldDescrizione.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        fieldDescrizione.setBounds(150, 118, 330, 36);
        contentPane.add(fieldDescrizione);

        labelImporto = new JLabel("Importo (€):");
        labelImporto.setFont(new Font("Segoe UI", Font.BOLD, 13));
        labelImporto.setForeground(new Color(70, 85, 105));
        labelImporto.setBounds(35, 166, 110, 36);
        contentPane.add(labelImporto);

        fieldImporto = new JTextField();
        fieldImporto.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        fieldImporto.setBounds(150, 166, 330, 36);
        contentPane.add(fieldImporto);

        labelData = new JLabel("Data spesa:");
        labelData.setFont(new Font("Segoe UI", Font.BOLD, 13));
        labelData.setForeground(new Color(70, 85, 105));
        labelData.setBounds(35, 214, 110, 36);
        contentPane.add(labelData);

        fieldData = new JCalendarComboBox();
        fieldData.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        fieldData.setBounds(150, 214, 330, 36);
        contentPane.add(fieldData);

        labelTipo = new JLabel("Tipo spesa:");
        labelTipo.setFont(new Font("Segoe UI", Font.BOLD, 13));
        labelTipo.setForeground(new Color(70, 85, 105));
        labelTipo.setBounds(35, 262, 110, 36);
        contentPane.add(labelTipo);

        String[] tipiSpesa = {"COMUNE", "PERSONALE"};
        comboTipo = new JComboBox<>(tipiSpesa);
        comboTipo.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        comboTipo.setBounds(150, 262, 330, 36);
        contentPane.add(comboTipo);

        labelPagataDa = new JLabel("Pagata da:");
        labelPagataDa.setFont(new Font("Segoe UI", Font.BOLD, 13));
        labelPagataDa.setForeground(new Color(70, 85, 105));
        labelPagataDa.setBounds(35, 310, 110, 36);
        contentPane.add(labelPagataDa);

        fieldPagataDa = new JTextField();
        fieldPagataDa.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        fieldPagataDa.setBounds(150, 310, 330, 36);
        contentPane.add(fieldPagataDa);

        registraSpesa = new JButton("REGISTRA SPESA");
        registraSpesa.setFont(new Font("Segoe UI", Font.BOLD, 13));
        registraSpesa.setBackground(new Color(60, 120, 216));
        registraSpesa.setForeground(Color.WHITE);
        registraSpesa.setFocusPainted(false);
        registraSpesa.setBounds(35, 370, 215, 40);
        contentPane.add(registraSpesa);

        tornaGruppo = new JButton("TORNA AL GRUPPO");
        tornaGruppo.setFont(new Font("Segoe UI", Font.BOLD, 13));
        tornaGruppo.setBackground(new Color(110, 120, 135));
        tornaGruppo.setForeground(Color.WHITE);
        tornaGruppo.setFocusPainted(false);
        tornaGruppo.setBounds(265, 370, 215, 40);
        contentPane.add(tornaGruppo);

        messaggio = new JLabel("", SwingConstants.CENTER);
        messaggio.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        messaggio.setBounds(35, 425, 445, 25);
        contentPane.add(messaggio);

        registraSpesa.addActionListener(e -> {
            String nome = fieldNome.getText().trim();
            String descrizione = fieldDescrizione.getText().trim();
            String importo = fieldImporto.getText().trim();
            String tipo = comboTipo.getSelectedItem().toString();
            String pagataDa = fieldPagataDa.getText().trim();

            if (nome.isEmpty() || importo.isEmpty() || pagataDa.isEmpty() || fieldData.getDate() == null) {
                messaggio.setText("Compila nome, importo, data e pagante");
            } else {
                LocalDate data = fieldData.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                controller.btn_inserisciSpesa_registraSpesa(nome, descrizione, importo, "EUR", data, tipo);
            }
        });

        tornaGruppo.addActionListener(e -> controller.btn_inserisciSpesa_tornaGruppo());
    }
}