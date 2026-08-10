package gui.scadenze;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Font;
import java.time.ZoneId;
import control.ScadenzeController;
import de.wannawork.jcalendar.JCalendarComboBox;

public class AggiungiScadenzaGUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private ScadenzeController controller;

    private JPanel contentPane;
    private JTextField fieldNome;
    private JCalendarComboBox fieldDataScadenza;
    private JTextField fieldImporto;
    private JLabel lblTitolo;
    private JLabel lblNome;
    private JLabel lblDataScadenza;
    private JLabel lblImportoLabel;
    private JButton btnAggiungi;
    private JButton btnIndietro;

    public AggiungiScadenzaGUI(ScadenzeController controller) {
        this.controller = controller;

        setTitle("Nuova Scadenza");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(520, 380);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setLayout(null);
        contentPane.setBackground(new Color(245, 245, 250));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        lblTitolo = new JLabel("Nuova Scadenza", SwingConstants.CENTER);
        lblTitolo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitolo.setForeground(new Color(30, 41, 59));
        lblTitolo.setBounds(0, 20, 520, 36);
        contentPane.add(lblTitolo);

        lblNome = new JLabel("Nome");
        lblNome.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblNome.setForeground(new Color(70, 85, 105));
        lblNome.setBounds(70, 90, 130, 30);
        contentPane.add(lblNome);

        lblDataScadenza = new JLabel("Data di Scadenza");
        lblDataScadenza.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblDataScadenza.setForeground(new Color(70, 85, 105));
        lblDataScadenza.setBounds(70, 140, 130, 30);
        contentPane.add(lblDataScadenza);

        lblImportoLabel = new JLabel("Importo");
        lblImportoLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblImportoLabel.setForeground(new Color(70, 85, 105));
        lblImportoLabel.setBounds(70, 190, 130, 30);
        contentPane.add(lblImportoLabel);

        fieldNome = new JTextField();
        fieldNome.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        fieldNome.setBounds(220, 90, 230, 32);
        fieldNome.setColumns(10);
        contentPane.add(fieldNome);

        fieldDataScadenza = new JCalendarComboBox();
        fieldDataScadenza.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        fieldDataScadenza.setBounds(220, 140, 230, 32);
        contentPane.add(fieldDataScadenza);

        fieldImporto = new JTextField();
        fieldImporto.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        fieldImporto.setColumns(10);
        fieldImporto.setBounds(220, 190, 230, 32);
        contentPane.add(fieldImporto);

        btnAggiungi = new JButton("Aggiungi");
        btnAggiungi.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnAggiungi.setBackground(new Color(60, 120, 216));
        btnAggiungi.setForeground(Color.WHITE);
        btnAggiungi.setFocusPainted(false);
        btnAggiungi.setBounds(100, 275, 140, 38);
        btnAggiungi.addActionListener(e ->
            controller.btn_aggiungiScadenza_aggiungi(
                fieldNome.getText(),
                fieldDataScadenza.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                fieldImporto.getText()
            )
        );
        contentPane.add(btnAggiungi);

        btnIndietro = new JButton("Indietro");
        btnIndietro.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnIndietro.setBackground(new Color(110, 120, 135));
        btnIndietro.setForeground(Color.WHITE);
        btnIndietro.setFocusPainted(false);
        btnIndietro.setBounds(270, 275, 140, 38);
        btnIndietro.addActionListener(e -> controller.btn_aggiungiScadenza_indietro());
        contentPane.add(btnIndietro);
    }
}
