package gui.scadenze;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Font;
import control.ScadenzeController;

public class DettaglioScadenza extends JFrame {

	private static final long serialVersionUID = 1L;
	private ScadenzeController controller;

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_1_1;
	private JButton btnNewButton;
	private JButton btnCancella;
	private JButton btnIndietro;
	private JLabel lblNewLabel_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DettaglioScadenza frame = new DettaglioScadenza(null);
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
	public DettaglioScadenza(ScadenzeController controller) {
		this.controller = controller;

		setTitle("Dettaglio Scadenza");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setSize(430, 290);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblNewLabel = new JLabel("Nome");
		lblNewLabel.setBounds(83, 72, 46, 14);
		contentPane.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("Data di Scadenza");
		lblNewLabel_1.setBounds(83, 121, 100, 14);
		contentPane.add(lblNewLabel_1);
		
		lblNewLabel_1_1 = new JLabel("Importo");
		lblNewLabel_1_1.setBounds(83, 172, 100, 14);
		contentPane.add(lblNewLabel_1_1);
		
		textField = new JTextField();
		textField.setBounds(204, 69, 120, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(204, 118, 120, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(204, 169, 120, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		btnNewButton = new JButton("Salva");
		btnNewButton.setBounds(60, 214, 89, 23);
		contentPane.add(btnNewButton);
		
		btnCancella = new JButton("Cancella");
		btnCancella.setBounds(160, 214, 89, 23);
		contentPane.add(btnCancella);
		
		btnIndietro = new JButton("Indietro");
		btnIndietro.setBounds(259, 214, 89, 23);
		contentPane.add(btnIndietro);
		
		lblNewLabel_2 = new JLabel("Dettaglio scadenza");
		lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 22));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(83, 15, 239, 35);
		contentPane.add(lblNewLabel_2);

	}
}
