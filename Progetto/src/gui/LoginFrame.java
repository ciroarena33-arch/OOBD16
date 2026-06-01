package gui;
import controller.*;

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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class LoginFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JTextField MailField;
	private JPasswordField PasswordField;
	private AutenticationController theController;

		public LoginFrame(AutenticationController theController) {
			this.theController=theController;
			
			setResizable(false);
			setTitle("Unina MoneySplit");
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 300, 300);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(null);
			
			JLabel lblMail = new JLabel("Mail:");
			lblMail.setHorizontalAlignment(SwingConstants.RIGHT);
			lblMail.setBounds(63, 82, 46, 14);
			contentPane.add(lblMail);
			
			JLabel lblPassword = new JLabel("Password:");
			lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
			lblPassword.setBounds(47, 107, 62, 14);
			contentPane.add(lblPassword);
			
			JButton btnAccesso = new JButton("Accedi");
			btnAccesso.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});	
			btnAccesso.setBounds(91, 151, 99, 23);
			contentPane.add(btnAccesso);
			
			MailField = new JTextField();
			MailField.setBounds(119, 79, 86, 20);
			contentPane.add(MailField);
			MailField.setColumns(10);
			
			JButton btnNewButton = new JButton("Nuovo Utente");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					theController.btnNuovoUtente();
				}
			});
			btnNewButton.setBounds(91, 178, 99, 23);
			contentPane.add(btnNewButton);
			
			PasswordField = new JPasswordField();
			PasswordField.setBounds(119, 104, 86, 20);
			contentPane.add(PasswordField);
			
			JLabel lblTitle = new JLabel("\r\nInserire dati per accesso");
			lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
			lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 17));
			lblTitle.setBounds(31, 32, 225, 33);
			contentPane.add(lblTitle);
			
		}	
}
