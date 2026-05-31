package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

public class LoginPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField MailField;
	private JPasswordField PasswordField;

		public LoginPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblMail = new JLabel("Mail:");
		lblMail.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMail.setBounds(154, 80, 46, 14);
		contentPane.add(lblMail);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(154, 105, 46, 14);
		contentPane.add(lblPassword);
		
		JButton btnAccesso = new JButton("Accedi");
		btnAccesso.setBounds(170, 149, 89, 23);
		contentPane.add(btnAccesso);
		
		MailField = new JTextField();
		MailField.setBounds(210, 77, 86, 20);
		contentPane.add(MailField);
		MailField.setColumns(10);
		
		JButton btnNewButton = new JButton("Registrati");
		btnNewButton.setBounds(170, 176, 89, 23);
		contentPane.add(btnNewButton);
		
		PasswordField = new JPasswordField();
		PasswordField.setBounds(210, 102, 86, 20);
		contentPane.add(PasswordField);
		
		JLabel lblTitle = new JLabel("Welcome to Unina-MoneySplit");
		lblTitle.setBounds(149, 43, 147, 14);
		contentPane.add(lblTitle);

	}
}
