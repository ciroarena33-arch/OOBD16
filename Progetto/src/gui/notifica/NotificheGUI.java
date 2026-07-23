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
import javax.swing.JTabbedPane;
import control.NotificaController;

public class NotificheGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private NotificaController controller;

	private JPanel contentPane;
	private JTable table, table2, table3;
	private JTabbedPane tabbedPane;
	private JButton btnNewButton;
	private JPanel inviti;
	private JPanel notRicevute;
	private JPanel notInviate;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NotificheGUI frame = new NotificheGUI(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public NotificheGUI(NotificaController controller) {
		this.controller = controller;

		setTitle("Notifiche");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setSize(620, 460);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnNewButton = new JButton("CreaNotifica");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.btn_notifiche_creaNotifica();
			}
		});
		btnNewButton.setBounds(230, 340, 124, 22);
		contentPane.add(btnNewButton);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);
		tabbedPane.setBounds(60, 41, 495, 288);
		contentPane.add(tabbedPane);
		
		inviti = new JPanel();
		table = new JTable();
		table.setBounds(60, 41, 495, 294);
		inviti.add(table);
		
		notRicevute = new JPanel();
		table2 = new JTable();
		table2.setBounds(60, 41, 495, 294);
		notRicevute.add(table2);
		
		notInviate = new JPanel();
		table3 = new JTable();
		table3.setBounds(60, 41, 495, 294);
		notInviate.add(table3);
		
		tabbedPane.add("Inviti", inviti);
		tabbedPane.add("Notifiche Inviate", notInviate);
		tabbedPane.add("Notifiche Ricevute", notRicevute);

	}
}
