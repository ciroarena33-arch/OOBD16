package gui.notifica;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.*;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import control.NotificaController;

public class SceltaDebito extends JFrame {

	private static final long serialVersionUID = 1L;
	private NotificaController controller;

	private JPanel contentPane;
	private JTable table;
	private JLabel lblNewLabel;
	private JTable tabellaSpese;
	private JScrollPane scrollPane;
	private JButton btnNewButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SceltaDebito frame = new SceltaDebito(null);
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
	public SceltaDebito(NotificaController controller) {
		this.controller = controller;

		setTitle("Scelta Debito");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setSize(680, 380);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
        String[] colonne = {
                "Nome spesa",
                "Debitore",
                "Importo",
        };

        String[][] dati = {
                {"Cena", "Luca", "45.00 €"},
                {"Taxi", "Davide", "20.00 €"},
                {"Libro", "Mirko", "18.00 €"}
        };
        
		lblNewLabel = new JLabel("Scegli il debito da notificare");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel.setBounds(200, 18, 280, 22);
		contentPane.add(lblNewLabel);

        tabellaSpese = new JTable(dati, colonne);
        scrollPane = new JScrollPane(tabellaSpese);
        scrollPane.setBounds(61, 44, 571, 231);
		contentPane.add(scrollPane);
		
		btnNewButton = new JButton("Ok");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(274, 286, 162, 44);
		contentPane.add(btnNewButton);

	}
}
