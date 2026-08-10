package gui.movimento;

import control.MovimentoController;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class SpesaUtenteGruppoGUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private MovimentoController controller;
    private JPanel contentPane;
    private JTextField fieldNomeSpesa;
    private JTextField fieldImporto;
    private JTextField fieldDestinatario;

    public SpesaUtenteGruppoGUI(MovimentoController controller) {
        super();
        setTitle("Dettaglio Spesa");
        this.controller = controller;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(480, 360);
        setResizable(false);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBackground(new Color(245, 245, 250));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitolo = new JLabel("Dettaglio Spesa", SwingConstants.CENTER);
        lblTitolo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitolo.setForeground(new Color(30, 41, 59));
        lblTitolo.setBounds(0, 15, 480, 35);
        contentPane.add(lblTitolo);

        int startY = 60;
        int stepY = 40;

        JLabel lblNomeSpesa = new JLabel("Nome Spesa:");
        lblNomeSpesa.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblNomeSpesa.setForeground(new Color(70, 85, 105));
        lblNomeSpesa.setBounds(40, startY, 110, 25);
        contentPane.add(lblNomeSpesa);

        fieldNomeSpesa = new JTextField();
        fieldNomeSpesa.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        fieldNomeSpesa.setEditable(false);
        fieldNomeSpesa.setBackground(new Color(230, 232, 240));
        fieldNomeSpesa.setBounds(160, startY, 270, 28);
        contentPane.add(fieldNomeSpesa);

        JLabel lblDescrizione = new JLabel("Descrizione:");
        lblDescrizione.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblDescrizione.setForeground(new Color(70, 85, 105));
        lblDescrizione.setBounds(40, startY + stepY, 110, 25);
        contentPane.add(lblDescrizione);

        JTextArea areaDescrizione = new JTextArea();
        areaDescrizione.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        areaDescrizione.setEditable(false);
        areaDescrizione.setBackground(new Color(230, 232, 240));
        areaDescrizione.setLineWrap(true);
        areaDescrizione.setWrapStyleWord(true);
        JScrollPane scrollDesc = new JScrollPane(areaDescrizione);
        scrollDesc.setBounds(160, startY + stepY, 270, 60);
        contentPane.add(scrollDesc);

        JLabel lblImporto = new JLabel("Importo:");
        lblImporto.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblImporto.setForeground(new Color(70, 85, 105));
        lblImporto.setBounds(40, startY + stepY + 70, 110, 25);
        contentPane.add(lblImporto);

        fieldImporto = new JTextField();
        fieldImporto.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        fieldImporto.setEditable(false);
        fieldImporto.setBackground(new Color(230, 232, 240));
        fieldImporto.setBounds(160, startY + stepY + 70, 270, 28);
        contentPane.add(fieldImporto);

        JLabel lblDestinatario = new JLabel("Destinatario:");
        lblDestinatario.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblDestinatario.setForeground(new Color(70, 85, 105));
        lblDestinatario.setBounds(40, startY + stepY + 105, 110, 25);
        contentPane.add(lblDestinatario);

        fieldDestinatario = new JTextField();
        fieldDestinatario.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        fieldDestinatario.setEditable(false);
        fieldDestinatario.setBackground(new Color(230, 232, 240));
        fieldDestinatario.setBounds(160, startY + stepY + 105, 270, 28);
        contentPane.add(fieldDestinatario);

        JButton btnPagaDebito = new JButton("PAGA SPESA");
        btnPagaDebito.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnPagaDebito.setBackground(new Color(60, 120, 216));
        btnPagaDebito.setForeground(Color.WHITE);
        btnPagaDebito.setFocusPainted(false);
        btnPagaDebito.setBounds(90, 265, 140, 38);
        contentPane.add(btnPagaDebito);

        JButton btnIndietro = new JButton("INDIETRO");
        btnIndietro.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnIndietro.setBackground(new Color(110, 120, 135));
        btnIndietro.setForeground(Color.WHITE);
        btnIndietro.setFocusPainted(false);
        btnIndietro.setBounds(250, 265, 140, 38);
        btnIndietro.addActionListener(e -> dispose());
        contentPane.add(btnIndietro);
    }
}
