package gui.scadenze;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import control.ScadenzeController;

public class AggiungiScadenza extends JFrame {

	private static final long serialVersionUID = 1L;
	private ScadenzeController controller;

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JLabel lblTitolo;
	private JLabel lblNome;
	private JLabel lblNewLabel;
	private JLabel lblImporto;
	private JButton btnNewScadenza;
	private JButton btnIndietro;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AggiungiScadenza frame = new AggiungiScadenza(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public AggiungiScadenza(ScadenzeController controller) {
		this.controller = controller;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setSize(430, 290);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblTitolo = new JLabel("Nuova Scadenza");
		lblTitolo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitolo.setFont(new Font("Arial", Font.BOLD, 24));
		lblTitolo.setBounds(90, 10, 230, 35);
		contentPane.add(lblTitolo);
		
		lblNome = new JLabel("Nome");
		lblNome.setBounds(110, 66, 95, 14);
		contentPane.add(lblNome);
		
		lblNewLabel = new JLabel("Data di Scadenza");
		lblNewLabel.setBounds(110, 100, 95, 14);
		contentPane.add(lblNewLabel);
		
		lblImporto = new JLabel("Importo");
		lblImporto.setBounds(110, 134, 95, 14);
		contentPane.add(lblImporto);
		
		textField = new JTextField();
		textField.setBounds(229, 63, 96, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(229, 97, 96, 20);
		contentPane.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(229, 131, 96, 20);
		contentPane.add(textField_2);
		
		btnNewScadenza = new JButton("Aggiungi");
		btnNewScadenza.setBounds(117, 192, 88, 22);
		contentPane.add(btnNewScadenza);
		
		btnIndietro = new JButton("Indietro");
		btnIndietro.setBounds(229, 192, 88, 22);
		contentPane.add(btnIndietro);

	}
}
