package gui.movimento;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import control.MovimentoController;

public class SpesaUtenteGruppo extends JFrame {

	private static final long serialVersionUID = 1L;
	private MovimentoController controller;

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JLabel lblTitolo;
	private JLabel lblNomeSpesa;
	private JLabel lblDescrizione;
	private JTextArea textArea;
	private JLabel lblImporto;
	private JLabel lblDestinatario;
	private JButton btnPagaDebito;
	private JButton btnIndietro;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SpesaUtenteGruppo frame = new SpesaUtenteGruppo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public SpesaUtenteGruppo(MovimentoController controller) {
		this.controller = controller;

		setTitle("Dettaglio Spesa");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setSize(430, 290);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblTitolo = new JLabel("Dettaglio Spesa");
		lblTitolo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitolo.setFont(new Font("Arial", Font.BOLD, 18));
		lblTitolo.setBounds(110, 10, 200, 28);
		contentPane.add(lblTitolo);
		
		lblNomeSpesa = new JLabel("Nome Spesa");
		lblNomeSpesa.setBounds(110, 39, 73, 14);
		contentPane.add(lblNomeSpesa);
		
		lblDescrizione = new JLabel("Descrizione");
		lblDescrizione.setBounds(110, 64, 73, 14);
		contentPane.add(lblDescrizione);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBounds(193, 59, 179, 88);
		contentPane.add(textArea);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(193, 36, 179, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		lblImporto = new JLabel("Importo");
		lblImporto.setBounds(110, 158, 48, 14);
		contentPane.add(lblImporto);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setBounds(193, 155, 179, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		lblDestinatario = new JLabel("Destinatario");
		lblDestinatario.setBounds(110, 187, 73, 14);
		contentPane.add(lblDestinatario);
		
		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setBounds(193, 184, 179, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		btnPagaDebito = new JButton("Paga Spesa");
		btnPagaDebito.setBounds(110, 230, 104, 22);
		contentPane.add(btnPagaDebito);
		
		btnIndietro = new JButton("Indietro");
		btnIndietro.setBounds(258, 230, 104, 22);
		contentPane.add(btnIndietro);

	}
}
