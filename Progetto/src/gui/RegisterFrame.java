package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.AutenticationController;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RegisterFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private AutenticationController theController;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JPasswordField passwordField;


	public RegisterFrame(AutenticationController theController) {
			this.theController=theController;
			
		setResizable(false);
		setTitle("Registrazione");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 300);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(51, 70, 85, 14);
		contentPane.add(lblNome);
		
		JLabel lblCognome = new JLabel("Cognome");
		lblCognome.setBounds(51, 95, 85, 14);
		contentPane.add(lblCognome);
		
		JLabel lblMail = new JLabel("Email Istituzionale");
		lblMail.setBounds(51, 120, 85, 14);
		contentPane.add(lblMail);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(51, 145, 85, 14);
		contentPane.add(lblPassword);
		
		textField = new JTextField();
		textField.setBounds(152, 67, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(152, 92, 86, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(152, 117, 86, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(152, 142, 86, 20);
		contentPane.add(passwordField);
		
		JButton btnRegistrazione = new JButton("Registrati");
		btnRegistrazione.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				theController.btnEffettuaregistrazione();
			}
		});
		btnRegistrazione.setBounds(92, 187, 89, 23);
		contentPane.add(btnRegistrazione);
		
		JLabel lblTitolo = new JLabel("Inserire dati per la Registrazione");
		lblTitolo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTitolo.setBounds(51, 11, 187, 56);
		contentPane.add(lblTitolo);
		
		JButton btnIndietro = new JButton("Indietro");
		btnIndietro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				theController.btnIndietro();
			}
		});
		btnIndietro.setBounds(92, 214, 89, 23);
		contentPane.add(btnIndietro);

	}
}
