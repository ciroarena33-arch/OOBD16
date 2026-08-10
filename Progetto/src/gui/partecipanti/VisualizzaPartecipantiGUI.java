package gui.partecipanti;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import control.PartecipantiController;

public class VisualizzaPartecipantiGUI extends JFrame {
    private static final long serialVersionUID = 1L;

    private PartecipantiController controller;
    private JPanel contentPane;
    private JList<Object> listaUtenti;
    private JScrollPane scrollPane;
    private JButton btnAggiungiPartecipante;
    private JButton vediDettagli;
    private JButton tornaGruppo;

    public VisualizzaPartecipantiGUI(PartecipantiController controller) {
        this.controller = controller;

        setTitle("Partecipanti Gruppo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(580, 520);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBackground(new Color(245, 245, 250));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        JLabel titolo = new JLabel("Partecipanti del Gruppo", SwingConstants.CENTER);
        titolo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titolo.setForeground(new Color(30, 41, 59));
        titolo.setBounds(0, 20, 580, 36);
        contentPane.add(titolo);

        listaUtenti = new JList<>();
        listaUtenti.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        listaUtenti.setFixedCellHeight(36);
        listaUtenti.setBackground(Color.WHITE);

        scrollPane = new JScrollPane(listaUtenti);
        scrollPane.setBounds(40, 70, 500, 320);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(203, 213, 225)));
        contentPane.add(scrollPane);

        btnAggiungiPartecipante = new JButton("AGGIUNGI");
        btnAggiungiPartecipante.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnAggiungiPartecipante.setBackground(new Color(60, 120, 216));
        btnAggiungiPartecipante.setForeground(Color.WHITE);
        btnAggiungiPartecipante.setFocusPainted(false);
        btnAggiungiPartecipante.setBounds(40, 410, 155, 38);
        btnAggiungiPartecipante.addActionListener(e -> controller.btn_visualizzaPartecipanti_aggiungiPartecipante());
        contentPane.add(btnAggiungiPartecipante);

        vediDettagli = new JButton("DETTAGLI");
        vediDettagli.setFont(new Font("Segoe UI", Font.BOLD, 12));
        vediDettagli.setBackground(new Color(60, 120, 216));
        vediDettagli.setForeground(Color.WHITE);
        vediDettagli.setFocusPainted(false);
        vediDettagli.setBounds(212, 410, 155, 38);
        vediDettagli.addActionListener(e -> {
            Object utenteSelezionato = listaUtenti.getSelectedValue();
            if (utenteSelezionato == null) {
                JOptionPane.showMessageDialog(null, "Nessun partecipante selezionato", "Errore", JOptionPane.ERROR_MESSAGE);
            } else {
                controller.btn_visualizzaPartecipanti_vediDettagli(utenteSelezionato);
            }
        });
        contentPane.add(vediDettagli);

        tornaGruppo = new JButton("TORNA AL GRUPPO");
        tornaGruppo.setFont(new Font("Segoe UI", Font.BOLD, 12));
        tornaGruppo.setBackground(new Color(110, 120, 135));
        tornaGruppo.setForeground(Color.WHITE);
        tornaGruppo.setFocusPainted(false);
        tornaGruppo.setBounds(385, 410, 155, 38);
        tornaGruppo.addActionListener(e -> controller.btn_visualizzaPartecipanti_tornaGruppo());
        contentPane.add(tornaGruppo);
    }

    public void aggiornaJList(DefaultListModel<Object> model) {
        listaUtenti.setModel(model);
    }
}
