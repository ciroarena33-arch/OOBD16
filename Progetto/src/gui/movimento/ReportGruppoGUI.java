package gui.movimento;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import control.MovimentoController;

public class ReportGruppoGUI extends JFrame {

    private static final long serialVersionUID = 1L;

    private MovimentoController controller;

    private JPanel contentPane;
    private JLabel titolo;
    private JLabel riepilogo;
    private JTable tabellaReport;
    private JScrollPane scrollPane;
    private JButton btnListaSpese;
    private JButton btnTornaGruppo;
    private DefaultTableModel tableModel;

    public ReportGruppoGUI(MovimentoController controller) {
        super();
        this.controller = controller;

        setTitle("Report Gruppo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(780, 540);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBackground(new Color(245, 245, 250));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        titolo = new JLabel("Report Gruppo", SwingConstants.CENTER);
        titolo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titolo.setForeground(new Color(30, 41, 59));
        titolo.setBounds(0, 20, 780, 36);
        contentPane.add(titolo);

        riepilogo = new JLabel("", SwingConstants.CENTER);
        riepilogo.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        riepilogo.setForeground(new Color(100, 116, 139));
        riepilogo.setBounds(0, 58, 780, 22);
        contentPane.add(riepilogo);

        String[] colonne = {"Partecipante", "Saldo", "Debiti arretrati"};
        tableModel = new DefaultTableModel(colonne, 0);
        tabellaReport = new JTable(tableModel);
        tabellaReport.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        tabellaReport.setRowHeight(28);

        JTableHeader header = tabellaReport.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 13));
        header.setBackground(new Color(60, 120, 216));
        header.setForeground(Color.WHITE);

        scrollPane = new JScrollPane(tabellaReport);
        scrollPane.setBounds(40, 90, 700, 350);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(203, 213, 225)));
        contentPane.add(scrollPane);

        btnListaSpese = new JButton("LISTA SPESE");
        btnListaSpese.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnListaSpese.setBackground(new Color(60, 120, 216));
        btnListaSpese.setForeground(Color.WHITE);
        btnListaSpese.setFocusPainted(false);
        btnListaSpese.setBounds(180, 460, 210, 38);
        btnListaSpese.addActionListener(e -> controller.btn_reportGruppo_listaSpese());
        contentPane.add(btnListaSpese);

        btnTornaGruppo = new JButton("TORNA AL GRUPPO");
        btnTornaGruppo.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnTornaGruppo.setBackground(new Color(110, 120, 135));
        btnTornaGruppo.setForeground(Color.WHITE);
        btnTornaGruppo.setFocusPainted(false);
        btnTornaGruppo.setBounds(410, 460, 210, 38);
        btnTornaGruppo.addActionListener(e -> controller.btn_reportGruppo_tornaGruppo());
        contentPane.add(btnTornaGruppo);
    }

    public void aggiornaIntestazione(String nomeGruppo, int numeroSpese, double importoTotale) {
        titolo.setText("Report Gruppo: " + nomeGruppo);
        riepilogo.setText("Numero spese: " + numeroSpese + "  |  Totale speso: "
                + String.format("%.2f €", importoTotale));
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }
}
