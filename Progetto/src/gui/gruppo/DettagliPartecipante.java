package gui.gruppo;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class DettagliPartecipante extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DettagliPartecipante frame = new DettagliPartecipante();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public DettagliPartecipante() {
		setTitle("DettagliPartecipante");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(109, 56, 98, 14);
		contentPane.add(lblNome);
		
		JLabel lblCognome = new JLabel("Cognome");
		lblCognome.setBounds(109, 94, 98, 14);
		contentPane.add(lblCognome);
		
		JLabel lblEmailIstituzionale = new JLabel("Email Istituzionale");
		lblEmailIstituzionale.setBounds(109, 134, 98, 14);
		contentPane.add(lblEmailIstituzionale);
		
		JLabel lblTelefono = new JLabel("Telefono");
		lblTelefono.setBounds(109, 171, 98, 14);
		contentPane.add(lblTelefono);
		
		textField = new JTextField();
		textField.setBounds(217, 53, 96, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(217, 91, 96, 20);
		contentPane.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(217, 131, 96, 20);
		contentPane.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(217, 168, 96, 20);
		contentPane.add(textField_3);
		
		JButton btnNewProprietario = new JButton("Rendi Proprietario");
		btnNewProprietario.setBounds(129, 215, 142, 22);
		contentPane.add(btnNewProprietario);
		

	}

}
