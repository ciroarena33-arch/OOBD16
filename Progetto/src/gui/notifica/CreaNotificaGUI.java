package gui.notifica;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import control.NotificaController;

public class CreaNotificaGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private NotificaController controller;

	private JPanel contentPane;
	private JTextField textField;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JButton btnNewButton;
	private JLabel lblNewLabel_2;
	private JTextArea textArea;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreaNotificaGUI frame = new CreaNotificaGUI(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public CreaNotificaGUI(NotificaController controller) {
		this.controller = controller;

		setTitle("Crea Notifica");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setSize(550, 380);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblNewLabel = new JLabel("Crea Notifica");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 26));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(155, 15, 220, 35);
		contentPane.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("Debito");
		lblNewLabel_1.setBounds(82, 75, 59, 13);
		contentPane.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(158, 72, 280, 26);
		contentPane.add(textField);
		textField.setColumns(10);
		
		btnNewButton = new JButton("Scelta");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(448, 71, 84, 27);
		contentPane.add(btnNewButton);
		
		lblNewLabel_2 = new JLabel("Descrizione ");
		lblNewLabel_2.setBounds(82, 159, 69, 12);
		contentPane.add(lblNewLabel_2);
		
		textArea = new JTextArea();
		textArea.setBounds(158, 128, 374, 122);
		contentPane.add(textArea);
		
		btnNewButton_1 = new JButton("Invia ");
		btnNewButton_1.setBounds(185, 261, 144, 36);
		contentPane.add(btnNewButton_1);
		
		btnNewButton_2 = new JButton("Annulla");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_2.setBounds(380, 261, 120, 36);
		contentPane.add(btnNewButton_2);

	}
}
