package gui.partecipanti;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.awt.EventQueue;
import gui.gruppo.DettagliGruppoGUI;

public class VisualizzaPartecipantiGUI extends JFrame {

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    VisualizzaPartecipantiGUI frame = new VisualizzaPartecipantiGUI();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public VisualizzaPartecipantiGUI() {
        super("Partecipanti gruppo");

        JPanel panelPrincipale = new JPanel();
        panelPrincipale.setLayout(new GridBagLayout());

        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(650, 500));
        panel.setLayout(new BorderLayout(10, 20));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

        JLabel titolo = new JLabel("Partecipanti del gruppo", SwingConstants.CENTER);
        titolo.setFont(new Font("Arial", Font.BOLD, 28));

        String[] nomiUtenti = {
                "Davide Cotena",
                "Marco Rossi",
                "Luca Bianchi"
        };

        String[][] dettagliUtenti = {
                {"Davide", "Cotena", "davide@unina.it"},
                {"Marco", "Rossi", "marco@unina.it"},
                {"Luca", "Bianchi", "luca@unina.it"}
        };

        JList<String> listaUtenti = new JList<>(nomiUtenti);
        listaUtenti.setFont(new Font("Arial", Font.PLAIN, 20));

        JScrollPane scrollPane = new JScrollPane(listaUtenti);

        JPanel panelBottoni = new JPanel();
        panelBottoni.setLayout(new FlowLayout());

        JButton vediDettagli = new JButton("VEDI DETTAGLI");
        JButton tornaGruppo = new JButton("TORNA AL GRUPPO");
        
        JButton btnAggiungiPartecipante = new JButton("AGGIUNGI PARTECIPANTE");
        panelBottoni.add(btnAggiungiPartecipante);

        panelBottoni.add(vediDettagli);
        panelBottoni.add(tornaGruppo);

        JLabel messaggio = new JLabel("", SwingConstants.CENTER);
        messaggio.setFont(new Font("Arial", Font.BOLD, 15));

        JPanel panelBasso = new JPanel();
        panelBasso.setLayout(new GridLayout(2, 1, 10, 10));
        panelBasso.add(panelBottoni);
        panelBasso.add(messaggio);

        panel.add(titolo, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(panelBasso, BorderLayout.SOUTH);

        panelPrincipale.add(panel);

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

                    JOptionPane.showMessageDialog(VisualizzaPartecipantiGUI.this,"Nome: " + nome + "\nCognome: " + cognome + "\nEmail: " + email);
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

        setContentPane(panelPrincipale);
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}