package gui.movimento;

import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import control.MovimentoController;

public class ListaSpese extends JFrame {

	private static final long serialVersionUID = 1L;
	private MovimentoController controller;

	private JPanel contentPane;
	private JLabel titolo;
	private JTable tabellaDebiti;
	private JScrollPane scrollPane;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JButton btnApriSpesa;
	private JButton btnIndietro;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListaSpese frame = new ListaSpese(null);
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
	public ListaSpese(MovimentoController controller) {
		this.controller = controller;

		setTitle("Lista Spese");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setSize(498, 480);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		titolo = new JLabel("Gestione debiti", SwingConstants.CENTER);
		titolo.setFont(new Font("Arial", Font.BOLD, 28));

		String[] colonne = {
				"Creditore",
				"Importo",
				"Tipo di Spesa",
				"Pagata"
		};

		String[][] dati = {
				{"Marco", "10.00 €", "Spesa comune", "Y"},
				{"Luca M.", "10.00 €", "Spesa personale", "Y"},
				{"Luca P.", "5.00 €", "Spesa comune", "F"}
		};
		contentPane.setLayout(null);

		tabellaDebiti = new JTable(dati, colonne);
		scrollPane = new JScrollPane(tabellaDebiti);
		scrollPane.setBounds(15,92,452,279);
		contentPane.add(scrollPane);
		
		lblNewLabel = new JLabel("Spese del Gruppo");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblNewLabel.setBounds(142, 11, 189, 55);
		contentPane.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("Nome gruppo");
		lblNewLabel_1.setBounds(204, 67, 64, 14);
		contentPane.add(lblNewLabel_1);
		
		btnApriSpesa = new JButton("Apri Spesa");
		btnApriSpesa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnApriSpesa.setBounds(126, 382, 105, 23);
		contentPane.add(btnApriSpesa);
		
		btnIndietro = new JButton("Indietro");
		btnIndietro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnIndietro.setBounds(241, 382, 119, 23);
		contentPane.add(btnIndietro);

	}
}
