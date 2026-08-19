package gui.partecipanti;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import control.PartecipantiController;

public class InfoPartecipanteGUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private PartecipantiController controller;

    private JPanel contentPane;
    private JTextField textNome;
    private JTextField textCognome;
    private JTextField textEmail;
    private JTextField textTelefono;
    private JLabel lblNome;
    private JLabel lblCognome;
    private JLabel lblEmailIstituzionale;
    private JLabel lblNumTelefono;
    private JButton btnIndietro;
    private JButton btnNewProprietario;

    public InfoPartecipanteGUI(PartecipantiController controller, String nome, String cognome, String email, String telefono, boolean puoRendereProprietario) {
        this.controller = controller;

        setTitle("Dati Partecipante");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(480, 360);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBackground(new Color(245, 245, 250));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        JLabel lblTitolo = new JLabel("Info Partecipante", SwingConstants.CENTER);
        lblTitolo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitolo.setForeground(new Color(30, 41, 59));
        lblTitolo.setBounds(0, 20, 480, 30);
        contentPane.add(lblTitolo);

        int startY = 70;
        int stepY = 42;

        lblNome = new JLabel("Nome:");
        lblNome.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblNome.setForeground(new Color(70, 85, 105));
        lblNome.setBounds(50, startY, 100, 25);
        contentPane.add(lblNome);

        textNome = new JTextField(nome);
        textNome.setEditable(false);
        textNome.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        textNome.setBackground(new Color(230, 232, 240));
        textNome.setBounds(160, startY, 250, 28);
        contentPane.add(textNome);

        lblCognome = new JLabel("Cognome:");
        lblCognome.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblCognome.setForeground(new Color(70, 85, 105));
        lblCognome.setBounds(50, startY + stepY, 100, 25);
        contentPane.add(lblCognome);

        textCognome = new JTextField(cognome);
        textCognome.setEditable(false);
        textCognome.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        textCognome.setBackground(new Color(230, 232, 240));
        textCognome.setBounds(160, startY + stepY, 250, 28);
        contentPane.add(textCognome);

        lblEmailIstituzionale = new JLabel("Email:");
        lblEmailIstituzionale.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblEmailIstituzionale.setForeground(new Color(70, 85, 105));
        lblEmailIstituzionale.setBounds(50, startY + stepY * 2, 100, 25);
        contentPane.add(lblEmailIstituzionale);

        textEmail = new JTextField(email);
        textEmail.setEditable(false);
        textEmail.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        textEmail.setBackground(new Color(230, 232, 240));
        textEmail.setBounds(160, startY + stepY * 2, 250, 28);
        contentPane.add(textEmail);

        lblNumTelefono = new JLabel("Telefono:");
        lblNumTelefono.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblNumTelefono.setForeground(new Color(70, 85, 105));
        lblNumTelefono.setBounds(50, startY + stepY * 3, 100, 25);
        contentPane.add(lblNumTelefono);

        textTelefono = new JTextField(telefono != null && !telefono.isEmpty() ? telefono : "Nessuno");
        textTelefono.setEditable(false);
        textTelefono.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        textTelefono.setBackground(new Color(230, 232, 240));
        textTelefono.setBounds(160, startY + stepY * 3, 250, 28);
        contentPane.add(textTelefono);

        btnIndietro = new JButton("INDIETRO");
        btnIndietro.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnIndietro.setBackground(new Color(110, 120, 135));
        btnIndietro.setForeground(Color.WHITE);
        btnIndietro.setFocusPainted(false);
        btnIndietro.addActionListener(e -> controller.btn_infoPartecipante_indietro());

        btnNewProprietario = new JButton("RENDI PROPRIETARIO");
        btnNewProprietario.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnNewProprietario.setBackground(new Color(60, 120, 216));
        btnNewProprietario.setForeground(Color.WHITE);
        btnNewProprietario.setFocusPainted(false);
        btnNewProprietario.addActionListener(e -> controller.btn_infoPartecipante_rendiProprietario());

        if (puoRendereProprietario) {
            btnNewProprietario.setBounds(50, 255, 190, 40);
            contentPane.add(btnNewProprietario);
            btnIndietro.setBounds(255, 255, 155, 40);
            contentPane.add(btnIndietro);
        } else {
            btnIndietro.setBounds(160, 255, 160, 40);
            contentPane.add(btnIndietro);
        }
    }
}
