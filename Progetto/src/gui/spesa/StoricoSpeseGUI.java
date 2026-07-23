package gui.spesa;

import javax.swing.*;
import gui.gruppo.DettagliGruppoGUI;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import control.SpesaController;

public class StoricoSpeseGUI extends JFrame {
    private static final long serialVersionUID = 1L;

    private SpesaController controller;

    private JPanel contentPane;
    private JLabel titolo;
    private JTable tabellaSpese;
    private JScrollPane scrollPane;
    private JButton tornaGruppo;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    StoricoSpeseGUI frame = new StoricoSpeseGUI(null);
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
    public StoricoSpeseGUI(SpesaController controller) {
        this.controller = controller;

        setTitle("Storico spese");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(650, 500);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setLayout(null);
        setContentPane(contentPane);

        titolo = new JLabel("Storico spese", SwingConstants.CENTER);
        titolo.setFont(new Font("Arial", Font.BOLD, 26));
        titolo.setBounds(150, 30, 350, 35);
        contentPane.add(titolo);

        String[] colonne = {
                "Nome spesa",
                "Data",
                "Importo",
                "Valuta",
                "Pagata da"
        };

        String[][] dati = {
                {"Cena", "10/06/2026", "45.00", "Euro", "Davide"},
                {"Taxi", "11/06/2026", "20.00", "Dollaro Americano", "Marco"},
                {"Libro", "12/06/2026", "18.00", "Sterlina", "Luca"}
        };

        tabellaSpese = new JTable(dati, colonne);
        tabellaSpese.setFont(new Font("Arial", Font.PLAIN, 13));
        tabellaSpese.setRowHeight(24);
        scrollPane = new JScrollPane(tabellaSpese);
        scrollPane.setBounds(50, 80, 550, 260);
        contentPane.add(scrollPane);

        tornaGruppo = new JButton("TORNA AL GRUPPO");
        tornaGruppo.setFont(new Font("Arial", Font.BOLD, 14));
        tornaGruppo.setBounds(225, 370, 200, 35);
        contentPane.add(tornaGruppo);

        tornaGruppo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.btn_storicoSpese_tornaGruppo();
            }
        });
    }
}