package gui.movimento;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import control.MovimentoController;
import gui.utente.HomeGUI;

public class ReportGeneraleGUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private MovimentoController controller;

    private JPanel contentPane;
    private JLabel titolo;
    private JLabel totaleSpeso;
    private JButton tornaHome;
    private JButton btnVisualizzaDettagli;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ReportGeneraleGUI frame = new ReportGeneraleGUI(null);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public ReportGeneraleGUI(MovimentoController controller) {
        this.controller = controller;

        setTitle("Report generale");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(500, 420);
        setLocationRelativeTo(null);
        
        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        titolo = new JLabel("Report generale", SwingConstants.CENTER);
        titolo.setFont(new Font("Arial", Font.BOLD, 28));
        titolo.setBounds(100, 30, 300, 40);
        contentPane.add(titolo);

        totaleSpeso = new JLabel("Totale speso: 350.00 €", SwingConstants.CENTER);
        totaleSpeso.setFont(new Font("Arial", Font.PLAIN, 18));
        totaleSpeso.setBounds(100, 85, 300, 30);
        contentPane.add(totaleSpeso);

        tornaHome = new JButton("TORNA HOME");
        tornaHome.setFont(new Font("Arial", Font.BOLD, 14));
        tornaHome.setBounds(270, 320, 180, 35);
        contentPane.add(tornaHome);
        
        btnVisualizzaDettagli = new JButton("VISUALIZZA DETTAGLI");
        btnVisualizzaDettagli.setFont(new Font("Arial", Font.BOLD, 14));
        btnVisualizzaDettagli.setBounds(50, 320, 180, 35);
        contentPane.add(btnVisualizzaDettagli);

        tornaHome.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                HomeGUI.main(null);
            }
        });
    }
}