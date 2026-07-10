package gui.partecipanti;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import control.PartecipantiController;
import gui.gruppo.DettagliGruppoGUI;

public class VisualizzaPartecipantiGUI extends JFrame {
    private static final long serialVersionUID = 1L;

    private PartecipantiController controller;

    private JPanel contentPane;
    private JLabel titolo;
    private JList<String> listaUtenti;
    private JScrollPane scrollPane;
    private JButton btnAggiungiPartecipante;
    private JButton vediDettagli;
    private JButton tornaGruppo;
    private JLabel messaggio;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    VisualizzaPartecipantiGUI frame = new VisualizzaPartecipantiGUI(null);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public VisualizzaPartecipantiGUI(PartecipantiController controller) {
        this.controller = controller;

        setTitle("Partecipanti gruppo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(550, 520);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setLayout(null);
        setContentPane(contentPane);

        titolo = new JLabel("Partecipanti del gruppo", SwingConstants.CENTER);
        titolo.setFont(new Font("Arial", Font.BOLD, 26));
        titolo.setBounds(100, 30, 350, 35);
        contentPane.add(titolo);

        String[] nomiUtenti = {
                "Davide Cotena",
                "Marco Rossi",
                "Luca Bianchi"
        };

        final String[][] dettagliUtenti = {
                {"Davide", "Cotena", "davide@unina.it"},
                {"Marco", "Rossi", "marco@unina.it"},
                {"Luca", "Bianchi", "luca@unina.it"}
        };

        listaUtenti = new JList<>(nomiUtenti);
        listaUtenti.setFont(new Font("Arial", Font.PLAIN, 18));

        scrollPane = new JScrollPane(listaUtenti);
        scrollPane.setBounds(50, 80, 450, 250);
        contentPane.add(scrollPane);

        btnAggiungiPartecipante = new JButton("AGGIUNGI");
        btnAggiungiPartecipante.setFont(new Font("Arial", Font.BOLD, 13));
        btnAggiungiPartecipante.setBounds(50, 360, 140, 35);
        contentPane.add(btnAggiungiPartecipante);

        vediDettagli = new JButton("VEDI DETTAGLI");
        vediDettagli.setFont(new Font("Arial", Font.BOLD, 13));
        vediDettagli.setBounds(205, 360, 140, 35);
        contentPane.add(vediDettagli);

        tornaGruppo = new JButton("TORNA AL GRUPPO");
        tornaGruppo.setFont(new Font("Arial", Font.BOLD, 13));
        tornaGruppo.setBounds(360, 360, 140, 35);
        contentPane.add(tornaGruppo);

        messaggio = new JLabel("", SwingConstants.CENTER);
        messaggio.setFont(new Font("Arial", Font.BOLD, 13));
        messaggio.setBounds(50, 415, 450, 25);
        contentPane.add(messaggio);

        vediDettagli.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int indice = listaUtenti.getSelectedIndex();

                if (indice == -1) {
                    messaggio.setText("Seleziona prima un utente");
                } else {
                    String nome = dettagliUtenti[indice][0];
                    String cognome = dettagliUtenti[indice][1];
                    String email = dettagliUtenti[indice][2];

                    JOptionPane.showMessageDialog(VisualizzaPartecipantiGUI.this,
                            "Nome: " + nome + "\nCognome: " + cognome + "\nEmail: " + email);
                }
            }
        });

        tornaGruppo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                DettagliGruppoGUI.main(null);
            }
        });
    }
}