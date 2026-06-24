package gui.notifica;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;

public class NotificheGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NotificheGUI frame = new NotificheGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public NotificheGUI() {
		super("Centro notifiche");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 621, 462);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNotInviate=new JButton("Notifiche Inviate");
		JButton btnNotRicevute=new JButton("Notifiche Ricevute");
		JButton btnRichieste=new JButton("Inviti");		
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(154, 10, 330, 20);
		menuBar.add(btnRichieste);
		menuBar.add(btnNotInviate);
		menuBar.add(btnNotRicevute);
		
		contentPane.add(menuBar);
		
		JButton btnNewButton = new JButton("CreaNotifica");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(256, 340, 98, 22);
		contentPane.add(btnNewButton);
		
		table = new JTable();
		table.setBounds(60, 41, 495, 294);
		contentPane.add(table);

	}
}
