package gui.notifica;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import control.NotificaController;

public class NotificheGUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private NotificaController controller;

    private JPanel contentPane;
    private JTable table, table2, table3;
    private DefaultTableModel tm1, tm2, tm3;
    private JTabbedPane tabbedPane;
    private JButton btnNewButton;
    private JPanel inviti;
    private JPanel notRicevute;
    private JPanel notInviate;

    public NotificheGUI(NotificaController controller) {
        this.controller = controller;

        setTitle("Centro Notifiche");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(700, 500);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBackground(new Color(245, 245, 250));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        JLabel lblTitolo = new JLabel("Centro Notifiche", SwingConstants.CENTER);
        lblTitolo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitolo.setForeground(new Color(30, 41, 59));
        lblTitolo.setBounds(0, 18, 700, 36);
        contentPane.add(lblTitolo);

        tabbedPane = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);
        tabbedPane.setFont(new Font("Segoe UI", Font.BOLD, 13));
        tabbedPane.setBounds(20, 65, 660, 360);
        contentPane.add(tabbedPane);

        inviti = new JPanel(new BorderLayout());
        inviti.setBackground(Color.WHITE);
        table = new JTable();
        table.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        table.setRowHeight(28);
        inviti.add(new JScrollPane(table), BorderLayout.CENTER);

        notRicevute = new JPanel(new BorderLayout());
        notRicevute.setBackground(Color.WHITE);
        table2 = new JTable();
        table2.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        table2.setRowHeight(28);
        notRicevute.add(new JScrollPane(table2), BorderLayout.CENTER);

        notInviate = new JPanel(new BorderLayout());
        notInviate.setBackground(Color.WHITE);
        table3 = new JTable();
        table3.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        table3.setRowHeight(28);
        notInviate.add(new JScrollPane(table3), BorderLayout.CENTER);

        tabbedPane.add("Inviti Gruppo", inviti);
        tabbedPane.add("Notifiche Inviate", notInviate);
        tabbedPane.add("Notifiche Ricevute", notRicevute);

        btnNewButton = new JButton("CREA NOTIFICA");
        btnNewButton.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnNewButton.setBackground(new Color(60, 120, 216));
        btnNewButton.setForeground(Color.WHITE);
        btnNewButton.setFocusPainted(false);
        btnNewButton.setBounds(250, 438, 200, 38);
        btnNewButton.addActionListener(e -> controller.btn_notifiche_creaNotifica());
        contentPane.add(btnNewButton);
    }
}
