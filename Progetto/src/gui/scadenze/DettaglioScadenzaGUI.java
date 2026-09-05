package gui.scadenze;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Font;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import control.ScadenzeController;
import de.wannawork.jcalendar.JCalendarComboBox;

public class DettaglioScadenzaGUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private ScadenzeController controller;

    private JPanel contentPane;
    private JTextField fieldNome;
    private JCalendarComboBox fieldDataScadenza;
    private JTextField fieldImporto;
    private JLabel lblNome;
    private JLabel lblDataScadenza;
    private JLabel lblImporto;
    private JLabel lblTitolo;
    private JButton btnSalva;
    private JButton btnCancella;
    private JButton btnIndietro;

    public DettaglioScadenzaGUI(ScadenzeController controller, String nome, LocalDate data, String importo) {
        this.controller = controller;

        setTitle("Dettaglio Scadenza");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(480, 360);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setLayout(null);
        contentPane.setBackground(new Color(245, 245, 250));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        lblTitolo = new JLabel("Dettaglio Scadenza", SwingConstants.CENTER);
        lblTitolo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitolo.setForeground(new Color(30, 41, 59));
        lblTitolo.setBounds(0, 20, 480, 36);
        contentPane.add(lblTitolo);

        lblNome = new JLabel("Nome");
        lblNome.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblNome.setForeground(new Color(70, 85, 105));
        lblNome.setBounds(60, 80, 110, 30);
        contentPane.add(lblNome);

        lblDataScadenza = new JLabel("Data di Scadenza");
        lblDataScadenza.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblDataScadenza.setForeground(new Color(70, 85, 105));
        lblDataScadenza.setBounds(60, 130, 130, 30);
        contentPane.add(lblDataScadenza);

        lblImporto = new JLabel("Importo");
        lblImporto.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblImporto.setForeground(new Color(70, 85, 105));
        lblImporto.setBounds(60, 180, 110, 30);
        contentPane.add(lblImporto);

        fieldNome = new JTextField(nome != null ? nome : "");
        fieldNome.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        fieldNome.setBounds(200, 80, 220, 32);
        fieldNome.setColumns(10);
        contentPane.add(fieldNome);

        fieldDataScadenza = new JCalendarComboBox();
        if (data != null) {
            fieldDataScadenza.setDate(Date.valueOf(data));
        }
        fieldDataScadenza.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        fieldDataScadenza.setBounds(200, 130, 220, 32);
        contentPane.add(fieldDataScadenza);

        fieldImporto = new JTextField(importo != null ? importo : "");
        fieldImporto.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        fieldImporto.setBounds(200, 180, 220, 32);
        fieldImporto.setColumns(10);
        contentPane.add(fieldImporto);

        btnSalva = new JButton("Salva");
        btnSalva.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnSalva.setBackground(new Color(60, 120, 216));
        btnSalva.setForeground(Color.WHITE);
        btnSalva.setFocusPainted(false);
        btnSalva.setBounds(50, 260, 110, 38);
        btnSalva.addActionListener(e -> {
            if (fieldNome.getText().trim().isEmpty() || fieldImporto.getText().trim().isEmpty() || fieldDataScadenza.getDate() == null) {
                JOptionPane.showMessageDialog(DettaglioScadenzaGUI.this, "Inserisci tutti i campi correttamente.");
                return;
            }
            LocalDate dataSel = fieldDataScadenza.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            controller.btn_dettaglioScadenza_salva(
                fieldNome.getText().trim(),
                dataSel,
                fieldImporto.getText().trim()
            );
        });
        contentPane.add(btnSalva);

        btnCancella = new JButton("Cancella");
        btnCancella.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnCancella.setBackground(new Color(210, 60, 60));
        btnCancella.setForeground(Color.WHITE);
        btnCancella.setFocusPainted(false);
        btnCancella.setBounds(185, 260, 110, 38);
        btnCancella.addActionListener(e -> controller.btn_dettaglioScadenza_cancella());
        contentPane.add(btnCancella);

        btnIndietro = new JButton("Indietro");
        btnIndietro.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnIndietro.setBackground(new Color(110, 120, 135));
        btnIndietro.setForeground(Color.WHITE);
        btnIndietro.setFocusPainted(false);
        btnIndietro.setBounds(320, 260, 110, 38);
        btnIndietro.addActionListener(e -> controller.btn_dettaglioScadenza_indietro());
        contentPane.add(btnIndietro);
    }
}
