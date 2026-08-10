package gui.notifica;

import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import control.NotificaController;

public class CreaNotificaGUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private NotificaController controller;

    private JPanel contentPane;
    private JTextField fieldDebito;
    private JLabel lblTitolo;
    private JLabel lblDebito;
    private JLabel lblMessaggio;
    private JTextArea areaMessaggio;
    private JButton btnSceltaDebito;
    private JButton btnInvia;
    private JButton btnAnnulla;

    public CreaNotificaGUI(NotificaController controller) {
        this.controller = controller;

        setTitle("Crea Notifica");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(550, 420);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBackground(new Color(245, 245, 250));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        lblTitolo = new JLabel("Crea Notifica", SwingConstants.CENTER);
        lblTitolo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitolo.setForeground(new Color(30, 41, 59));
        lblTitolo.setBounds(0, 20, 550, 36);
        contentPane.add(lblTitolo);

        lblDebito = new JLabel("Debito:");
        lblDebito.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblDebito.setForeground(new Color(70, 85, 105));
        lblDebito.setBounds(60, 85, 90, 30);
        contentPane.add(lblDebito);

        fieldDebito = new JTextField();
        fieldDebito.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        fieldDebito.setEditable(false);
        fieldDebito.setBackground(new Color(230, 232, 240));
        fieldDebito.setBounds(150, 85, 220, 32);
        contentPane.add(fieldDebito);

        btnSceltaDebito = new JButton("Scelta");
        btnSceltaDebito.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnSceltaDebito.setBackground(new Color(60, 120, 216));
        btnSceltaDebito.setForeground(Color.WHITE);
        btnSceltaDebito.setFocusPainted(false);
        btnSceltaDebito.setBounds(380, 85, 100, 32);
        btnSceltaDebito.addActionListener(e -> controller.btn_creaNotifica_sceltaDebito());
        contentPane.add(btnSceltaDebito);

        lblMessaggio = new JLabel("Messaggio:");
        lblMessaggio.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblMessaggio.setForeground(new Color(70, 85, 105));
        lblMessaggio.setBounds(60, 140, 90, 30);
        contentPane.add(lblMessaggio);

        areaMessaggio = new JTextArea();
        areaMessaggio.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        areaMessaggio.setLineWrap(true);
        areaMessaggio.setWrapStyleWord(true);
        JScrollPane scrollText = new JScrollPane(areaMessaggio);
        scrollText.setBounds(150, 140, 330, 150);
        scrollText.setBorder(BorderFactory.createLineBorder(new Color(203, 213, 225)));
        contentPane.add(scrollText);

        btnInvia = new JButton("INVIA");
        btnInvia.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnInvia.setBackground(new Color(60, 120, 216));
        btnInvia.setForeground(Color.WHITE);
        btnInvia.setFocusPainted(false);
        btnInvia.setBounds(150, 320, 155, 38);
        btnInvia.addActionListener(e -> controller.btn_creaNotifica_invia(fieldDebito.getText(), areaMessaggio.getText()));
        contentPane.add(btnInvia);

        btnAnnulla = new JButton("ANNULLA");
        btnAnnulla.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnAnnulla.setBackground(new Color(110, 120, 135));
        btnAnnulla.setForeground(Color.WHITE);
        btnAnnulla.setFocusPainted(false);
        btnAnnulla.setBounds(325, 320, 155, 38);
        btnAnnulla.addActionListener(e -> controller.btn_creaNotifica_annulla());
        contentPane.add(btnAnnulla);
    }

    public void setDebitoSelezionato(String debito) {
        fieldDebito.setText(debito);
    }
}
