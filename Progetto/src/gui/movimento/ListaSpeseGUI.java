package gui.movimento;

import java.awt.Color;
import java.awt.Font;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.JTableHeader;
import control.MovimentoController;

public class ListaSpeseGUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private MovimentoController controller;

    public ListaSpeseGUI(MovimentoController controller) {
        super();
        setTitle("Lista delle Spese");
        this.controller = controller;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(600, 520);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBackground(new Color(245, 245, 250));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitolo = new JLabel("Spese del Gruppo", SwingConstants.CENTER);
        lblTitolo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitolo.setForeground(new Color(30, 41, 59));
        lblTitolo.setBounds(0, 20, 600, 36);
        contentPane.add(lblTitolo);

        JLabel lblNomeGruppo = new JLabel("Resoconto spese per la partecipazione selezionata", SwingConstants.CENTER);
        lblNomeGruppo.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblNomeGruppo.setForeground(new Color(100, 116, 139));
        lblNomeGruppo.setBounds(0, 58, 600, 22);
        contentPane.add(lblNomeGruppo);

        String[] colonne = {"Creditore", "Importo", "Tipo di Spesa", "Pagata"};
        String[][] dati = {
                {"Marco", "10.00 €", "Spesa comune", "Y"},
                {"Luca M.", "10.00 €", "Spesa personale", "Y"},
                {"Luca P.", "5.00 €", "Spesa comune", "F"}
        };

        JTable tabellaSpese = new JTable(dati, colonne);
        tabellaSpese.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        tabellaSpese.setRowHeight(28);
        JTableHeader header = tabellaSpese.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 13));
        header.setBackground(new Color(60, 120, 216));
        header.setForeground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(tabellaSpese);
        scrollPane.setBounds(40, 90, 520, 320);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(203, 213, 225)));
        contentPane.add(scrollPane);

        JButton btnApriSpesa = new JButton("Apri Spesa");
        btnApriSpesa.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnApriSpesa.setBackground(new Color(60, 120, 216));
        btnApriSpesa.setForeground(Color.WHITE);
        btnApriSpesa.setFocusPainted(false);
        btnApriSpesa.setBounds(130, 425, 160, 38);
        btnApriSpesa.addActionListener(e -> {});
        contentPane.add(btnApriSpesa);

        JButton btnIndietro = new JButton("Indietro");
        btnIndietro.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnIndietro.setBackground(new Color(110, 120, 135));
        btnIndietro.setForeground(Color.WHITE);
        btnIndietro.setFocusPainted(false);
        btnIndietro.setBounds(310, 425, 160, 38);
        btnIndietro.addActionListener(e -> controller.btn_reportGenerale_tornaHome());
        contentPane.add(btnIndietro);
    }
}
