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

public class SceltaDebito extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SceltaDebito frame = new SceltaDebito();
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
	public SceltaDebito() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
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
        
		JLabel lblNewLabel = new JLabel("Scegli il debito da notificare");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblNewLabel.setBounds(103, 22, 221, 12);
		contentPane.add(lblNewLabel);

        JTable tabellaSpese = new JTable(dati, colonne);
        JScrollPane scrollPane = new JScrollPane(tabellaSpese);
        scrollPane.setBounds(61, 44, 305, 168);
		contentPane.add(scrollPane);
		
		JButton btnNewButton = new JButton("Ok");
		btnNewButton.setBounds(160, 222, 84, 20);
		contentPane.add(btnNewButton);
		

	}
}
