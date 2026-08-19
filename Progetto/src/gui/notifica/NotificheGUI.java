package gui.notifica;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import control.NotificaController;

public class NotificheGUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private NotificaController controller;

    private JPanel contentPane;
    private JTable tableInviti, tableRicevute, tableInviate;
    private DefaultTableModel modelInviti, modelRicevute, modelInviate;
    private JTabbedPane tabbedPane;

    private JButton btnAccettaInvito;
    private JButton btnRifiutaInvito;
    private JButton btnSaldaDebito;
    private JButton btnRispondi;
    private JButton btnCreaNotifica;
    private JButton btnTornaHome;

    public NotificheGUI(NotificaController controller) {
        this.controller = controller;

        setTitle("Centro Notifiche");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(760, 540);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBackground(new Color(245, 245, 250));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        JLabel lblTitolo = new JLabel("Centro Notifiche", SwingConstants.CENTER);
        lblTitolo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitolo.setForeground(new Color(30, 41, 59));
        lblTitolo.setBounds(0, 18, 760, 36);
        contentPane.add(lblTitolo);

        tabbedPane = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);
        tabbedPane.setFont(new Font("Segoe UI", Font.BOLD, 13));
        tabbedPane.setBounds(20, 65, 720, 360);
        contentPane.add(tabbedPane);

        // Tab Inviti
        JPanel inviti = new JPanel(new BorderLayout());
        inviti.setBackground(Color.WHITE);
        modelInviti = new DefaultTableModel(new String[]{"Partecipazione", "Gruppo", "Data Invito", "Proprietario"}, 0);
        tableInviti = new JTable(modelInviti);
        tableInviti.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        tableInviti.setRowHeight(28);
        styleTableHeader(tableInviti);
        inviti.add(new JScrollPane(tableInviti), BorderLayout.CENTER);

        JPanel panelBottoniInviti = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 8));
        panelBottoniInviti.setBackground(new Color(245, 245, 250));

        btnAccettaInvito = new JButton("ACCETTA INVITO");
        btnAccettaInvito.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnAccettaInvito.setBackground(new Color(60, 120, 216));
        btnAccettaInvito.setForeground(Color.WHITE);
        btnAccettaInvito.setFocusPainted(false);
        btnAccettaInvito.addActionListener(e -> {
            int row = tableInviti.getSelectedRow();
            if (row != -1) {
                Object pObj = modelInviti.getValueAt(row, 0);
                controller.btn_inviti_accetta(pObj);
            } else {
                JOptionPane.showMessageDialog(NotificheGUI.this, "Seleziona prima un invito");
            }
        });
        panelBottoniInviti.add(btnAccettaInvito);

        btnRifiutaInvito = new JButton("RIFIUTA INVITO");
        btnRifiutaInvito.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnRifiutaInvito.setBackground(new Color(210, 60, 60));
        btnRifiutaInvito.setForeground(Color.WHITE);
        btnRifiutaInvito.setFocusPainted(false);
        btnRifiutaInvito.addActionListener(e -> {
            int row = tableInviti.getSelectedRow();
            if (row != -1) {
                Object pObj = modelInviti.getValueAt(row, 0);
                controller.btn_inviti_rifiuta(pObj);
            } else {
                JOptionPane.showMessageDialog(NotificheGUI.this, "Seleziona prima un invito");
            }
        });
        panelBottoniInviti.add(btnRifiutaInvito);
        inviti.add(panelBottoniInviti, BorderLayout.SOUTH);

        // Tab Notifiche Ricevute
        JPanel notRicevute = new JPanel(new BorderLayout());
        notRicevute.setBackground(Color.WHITE);
        modelRicevute = new DefaultTableModel(new String[]{"Notifica", "Spesa/Debito", "Importo", "Data", "Messaggio"}, 0);
        tableRicevute = new JTable(modelRicevute);
        tableRicevute.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        tableRicevute.setRowHeight(28);
        styleTableHeader(tableRicevute);
        notRicevute.add(new JScrollPane(tableRicevute), BorderLayout.CENTER);

        JPanel panelBottoniRicevute = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 8));
        panelBottoniRicevute.setBackground(new Color(245, 245, 250));

        btnSaldaDebito = new JButton("SALDA DEBITO");
        btnSaldaDebito.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnSaldaDebito.setBackground(new Color(60, 120, 216));
        btnSaldaDebito.setForeground(Color.WHITE);
        btnSaldaDebito.setFocusPainted(false);
        btnSaldaDebito.addActionListener(e -> {
            int row = tableRicevute.getSelectedRow();
            if (row != -1) {
                Object notificaObj = modelRicevute.getValueAt(row, 0);
                controller.btn_notificheRicevute_saldaDebito(notificaObj);
            } else {
                JOptionPane.showMessageDialog(NotificheGUI.this, "Seleziona una notifica ricevuta");
            }
        });
        panelBottoniRicevute.add(btnSaldaDebito);

        btnRispondi = new JButton("RISPONDI");
        btnRispondi.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnRispondi.setBackground(new Color(110, 120, 135));
        btnRispondi.setForeground(Color.WHITE);
        btnRispondi.setFocusPainted(false);
        btnRispondi.addActionListener(e -> {
            int row = tableRicevute.getSelectedRow();
            if (row != -1) {
                Object notificaObj = modelRicevute.getValueAt(row, 0);
                String risposta = JOptionPane.showInputDialog(NotificheGUI.this, "Inserisci la tua risposta al mittente:");
                if (risposta != null && !risposta.trim().isEmpty()) {
                    controller.btn_notificheRicevute_rispondi(notificaObj, risposta.trim());
                }
            } else {
                JOptionPane.showMessageDialog(NotificheGUI.this, "Seleziona una notifica ricevuta");
            }
        });
        panelBottoniRicevute.add(btnRispondi);
        notRicevute.add(panelBottoniRicevute, BorderLayout.SOUTH);

        // Tab Notifiche Inviate
        JPanel notInviate = new JPanel(new BorderLayout());
        notInviate.setBackground(Color.WHITE);
        modelInviate = new DefaultTableModel(new String[]{"Notifica", "Spesa/Debito", "Importo", "Data Invio", "Stato Debito"}, 0);
        tableInviate = new JTable(modelInviate);
        tableInviate.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        tableInviate.setRowHeight(28);
        styleTableHeader(tableInviate);
        notInviate.add(new JScrollPane(tableInviate), BorderLayout.CENTER);

        tabbedPane.add("Inviti Gruppo", inviti);
        tabbedPane.add("Notifiche Ricevute", notRicevute);
        tabbedPane.add("Notifiche Inviate", notInviate);

        btnCreaNotifica = new JButton("CREA NOTIFICA");
        btnCreaNotifica.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnCreaNotifica.setBackground(new Color(60, 120, 216));
        btnCreaNotifica.setForeground(Color.WHITE);
        btnCreaNotifica.setFocusPainted(false);
        btnCreaNotifica.setBounds(200, 445, 170, 38);
        btnCreaNotifica.addActionListener(e -> controller.btn_notifiche_creaNotifica());
        contentPane.add(btnCreaNotifica);

        btnTornaHome = new JButton("TORNA HOME");
        btnTornaHome.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnTornaHome.setBackground(new Color(110, 120, 135));
        btnTornaHome.setForeground(Color.WHITE);
        btnTornaHome.setFocusPainted(false);
        btnTornaHome.setBounds(390, 445, 170, 38);
        btnTornaHome.addActionListener(e -> controller.btn_notifiche_tornaHome());
        contentPane.add(btnTornaHome);
    }

    private void styleTableHeader(JTable table) {
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 13));
        header.setBackground(new Color(60, 120, 216));
        header.setForeground(Color.WHITE);
    }

    public DefaultTableModel getModelInviti() {
        return modelInviti;
    }

    public DefaultTableModel getModelInviate() {
        return modelInviate;
    }

    public DefaultTableModel getModelRicevute() {
        return modelRicevute;
    }
}
