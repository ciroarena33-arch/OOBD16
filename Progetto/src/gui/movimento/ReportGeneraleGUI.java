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

public class ReportGeneraleGUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ReportGeneraleGUI frame = new ReportGeneraleGUI();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public ReportGeneraleGUI() {
        setTitle("Report generale");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null); 
        
        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE); 
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        JLabel titolo = new JLabel("Report generale", SwingConstants.CENTER);
        titolo.setFont(new Font("Arial", Font.BOLD, 28));
        titolo.setBounds(300, 30, 400, 40);
        contentPane.add(titolo);

        JLabel totaleSpeso = new JLabel("Totale speso: 350.00 €", SwingConstants.CENTER);
        totaleSpeso.setFont(new Font("Arial", Font.PLAIN, 18));
        totaleSpeso.setBounds(300, 80, 400, 30);
        contentPane.add(totaleSpeso);

        JButton tornaHome = new JButton("TORNA HOME");
        tornaHome.setFont(new Font("Arial", Font.BOLD, 14));
        tornaHome.setBounds(539, 581, 200, 40);
        contentPane.add(tornaHome);
        
        JButton btnVisualizzaDettagli = new JButton("VISUALIZZA DETTAGLI");
        btnVisualizzaDettagli.setFont(new Font("Arial", Font.BOLD, 14));
        btnVisualizzaDettagli.setBounds(250, 581, 200, 40);
        contentPane.add(btnVisualizzaDettagli);

        tornaHome.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                HomeGUI.main(null);
            }
        });
    }
}