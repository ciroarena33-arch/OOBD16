package gui.spesa;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import control.SpesaController;

public class StoricoSpeseGUI extends JFrame {
    private static final long serialVersionUID = 1L;

    private SpesaController controller;

    private JPanel contentPane;
    private JLabel titolo;
    private JTable tabellaSpese;
    private JScrollPane scrollPane;
    private JButton tornaGruppo;
    private DefaultTableModel tableModel;

    public StoricoSpeseGUI(SpesaController controller) {
        super();
        setTitle("Storico Spese");
        this.controller = controller;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(780, 540);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBackground(new Color(245, 245, 250));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        titolo = new JLabel("Storico Spese del Gruppo", SwingConstants.CENTER);
        titolo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titolo.setForeground(new Color(30, 41, 59));
        titolo.setBounds(0, 20, 780, 36);
        contentPane.add(titolo);

        String[] colonne = {
                "Nome",
                "Data",
                "Importo",
                "Valuta",
                "Tipo Spesa",
                "Pagata da"
        };

        tableModel = new DefaultTableModel(colonne, 0);
        tabellaSpese = new JTable(tableModel);
        tabellaSpese.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        tabellaSpese.setRowHeight(28);

        JTableHeader header = tabellaSpese.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 13));
        header.setBackground(new Color(60, 120, 216));
        header.setForeground(Color.WHITE);

        scrollPane = new JScrollPane(tabellaSpese);
        scrollPane.setBounds(40, 75, 700, 350);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(203, 213, 225)));
        contentPane.add(scrollPane);

        tornaGruppo = new JButton("TORNA AL GRUPPO");
        tornaGruppo.setFont(new Font("Segoe UI", Font.BOLD, 13));
        tornaGruppo.setBackground(new Color(110, 120, 135));
        tornaGruppo.setForeground(Color.WHITE);
        tornaGruppo.setFocusPainted(false);
        tornaGruppo.setBounds(265, 440, 250, 40);
        contentPane.add(tornaGruppo);

        tornaGruppo.addActionListener(e -> controller.btn_storicoSpese_tornaGruppo());
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }
}
