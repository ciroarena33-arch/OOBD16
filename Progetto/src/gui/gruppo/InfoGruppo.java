package gui.gruppo;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class InfoGruppo extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InfoGruppo frame = new InfoGruppo();
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
	public InfoGruppo() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 834, 524);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitolo = new JLabel("Info Gruppo");
		lblTitolo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitolo.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblTitolo.setBounds(273, 60, 286, 103);
		contentPane.add(lblTitolo);
		
		JLabel lblNewLabel = new JLabel("Nome del Gruppo");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(179, 176, 124, 19);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(312, 175, 312, 19);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblTipologia = new JLabel("Tipologia");
		lblTipologia.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTipologia.setBounds(179, 244, 132, 19);
		contentPane.add(lblTipologia);
		
		String[] tipiGruppo = {"GENERICO", "VIAGGIO", "COINQUILINI", "STUDIO"};
		JComboBox<String> comboBox = new JComboBox(tipiGruppo);
		
		comboBox.setBounds(312, 244, 312, 19);
		contentPane.add(comboBox);
		
		JButton btnSalvaModifiche = new JButton("Salva");
		btnSalvaModifiche.setBounds(251, 388, 124, 39);
		contentPane.add(btnSalvaModifiche);
		
		JButton btnIndietro = new JButton("Indietro");
		btnIndietro.setBounds(454, 388, 123, 39);
		contentPane.add(btnIndietro);

	}
}
