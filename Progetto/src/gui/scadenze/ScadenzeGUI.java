package gui.scadenze;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import control.ScadenzeController;

public class ScadenzeGUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private ScadenzeController controller;

    private JPanel contentPane;
    private JTable table;
    private DefaultTableModel tableModel;
    private JLabel lblTitolo;
    private JScrollPane scrollPane;
    private JButton btnAggiungi;
    private JButton btnModifica;
    private JButton btnIndietro;

    public ScadenzeGUI(ScadenzeController controller) {
        this.controller = controller;

        setTitle("Scadenze");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(600, 420);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setLayout(null);
        contentPane.setBackground(new Color(245, 245, 250));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        lblTitolo = new JLabel("Lista Scadenze", SwingConstants.CENTER);
        lblTitolo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitolo.setForeground(new Color(30, 41, 59));
        lblTitolo.setBounds(0, 20, 600, 36);
        contentPane.add(lblTitolo);

        String[] colonne = {"Nome", "Data di Scadenza", "Importo"};
        tableModel = new DefaultTableModel(colonne, 0);
        table = new JTable(tableModel);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        table.setRowHeight(28);
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 13));
        header.setBackground(new Color(60, 120, 216));
        header.setForeground(Color.WHITE);

        scrollPane = new JScrollPane(table);
        scrollPane.setBounds(30, 70, 540, 250);
        contentPane.add(scrollPane);

        btnAggiungi = new JButton("Aggiungi");
        btnAggiungi.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnAggiungi.setBackground(new Color(60, 120, 216));
        btnAggiungi.setForeground(Color.WHITE);
        btnAggiungi.setFocusPainted(false);
        btnAggiungi.setBounds(30, 340, 165, 36);
        btnAggiungi.addActionListener(e -> controller.btn_scadenze_aggiungi());
        contentPane.add(btnAggiungi);

        btnModifica = new JButton("Modifica");
        btnModifica.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnModifica.setBackground(new Color(60, 120, 216));
        btnModifica.setForeground(Color.WHITE);
        btnModifica.setFocusPainted(false);
        btnModifica.setBounds(205, 340, 165, 36);
        btnModifica.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row != -1) {
                int modelRow = table.convertRowIndexToModel(row);
                Object scadenza = tableModel.getValueAt(modelRow, 0);
                controller.btn_scadenze_modifica(scadenza);
            } else {
                JOptionPane.showMessageDialog(ScadenzeGUI.this, "Seleziona prima una scadenza");
            }
        });
        contentPane.add(btnModifica);

        btnIndietro = new JButton("Indietro");
        btnIndietro.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnIndietro.setBackground(new Color(110, 120, 135));
        btnIndietro.setForeground(Color.WHITE);
        btnIndietro.setFocusPainted(false);
        btnIndietro.setBounds(380, 340, 190, 36);
        btnIndietro.addActionListener(e -> controller.btn_scadenze_indietro());
        contentPane.add(btnIndietro);
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    public JTable getTable() {
        return table;
    }
}
