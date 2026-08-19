package gui.notifica;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import control.NotificaController;

public class SceltaDebitoGUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private NotificaController controller;

    private JPanel contentPane;
    private JLabel lblTitolo;
    private JTable tabellaSpese;
    private DefaultTableModel tableModel;
    private JScrollPane scrollPane;
    private JButton btnConferma;

    public SceltaDebitoGUI(NotificaController controller) {
        this.controller = controller;

        setTitle("Scelta Debito");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(520, 360);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBackground(new Color(245, 245, 250));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        lblTitolo = new JLabel("Scegli il debito da notificare", SwingConstants.CENTER);
        lblTitolo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitolo.setForeground(new Color(30, 41, 59));
        lblTitolo.setBounds(0, 18, 520, 32);
        contentPane.add(lblTitolo);

        String[] colonne = {"Debito", "Nome Spesa", "Importo"};
        tableModel = new DefaultTableModel(colonne, 0);

        tabellaSpese = new JTable(tableModel);
        tabellaSpese.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        tabellaSpese.setRowHeight(28);
        JTableHeader header = tabellaSpese.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 13));
        header.setBackground(new Color(60, 120, 216));
        header.setForeground(Color.WHITE);

        scrollPane = new JScrollPane(tabellaSpese);
        scrollPane.setBounds(40, 65, 440, 180);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(203, 213, 225)));
        contentPane.add(scrollPane);

        btnConferma = new JButton("CONFERMA SELEZIONE");
        btnConferma.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnConferma.setBackground(new Color(60, 120, 216));
        btnConferma.setForeground(Color.WHITE);
        btnConferma.setFocusPainted(false);
        btnConferma.setBounds(135, 265, 250, 38);
        btnConferma.addActionListener(e -> {
            int selectedRow = tabellaSpese.getSelectedRow();
            if (selectedRow != -1) {
                Object debitoObj = tableModel.getValueAt(selectedRow, 0);
                controller.btn_sceltaDebito_ok(debitoObj);
            } else {
                JOptionPane.showMessageDialog(SceltaDebitoGUI.this, "Seleziona un debito");
            }
        });
        contentPane.add(btnConferma);
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }
}
