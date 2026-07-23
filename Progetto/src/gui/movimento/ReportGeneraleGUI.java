package gui.movimento;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartMouseEvent;
import org.jfree.chart.ChartMouseListener;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.entity.ChartEntity;
import org.jfree.chart.entity.PieSectionEntity;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.awt.event.ActionEvent;
import control.MovimentoController;
import gui.utente.HomeGUI;

public class ReportGeneraleGUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private MovimentoController controller;

    private JPanel contentPane;
    private JLabel titolo;
    private JLabel totaleSpeso;
    private JButton tornaHome;
    private JButton btnVisualizzaDettagli;
    
    private PiePlot plot;
    DefaultPieDataset dataset = new DefaultPieDataset();
    JFreeChart graficoTorta;
    private String spicchioSelzionato=null;

    public ReportGeneraleGUI(MovimentoController controller) {
    	
		super("Resoconto Delle Spese");
    	this.controller=controller;    	
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null); 
        
        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE); 
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        JLabel titolo = new JLabel("Report generale", SwingConstants.CENTER);
        titolo.setFont(new Font("Arial", Font.BOLD, 28));
        titolo.setBounds(300, 30, 400, 40);
        contentPane.add(titolo);

        JLabel totaleSpeso = new JLabel("Totale speso: 350.00 €", SwingConstants.CENTER);
        totaleSpeso.setFont(new Font("Arial", Font.PLAIN, 18));
        totaleSpeso.setBounds(300, 80, 400, 30);
        contentPane.add(totaleSpeso);

        JButton tornaHome = new JButton("TORNA HOME");
        tornaHome.setFont(new Font("Arial", Font.BOLD, 14));
        tornaHome.setBounds(539, 581, 200, 40);
        contentPane.add(tornaHome);
        
        JButton btnVisualizzaDettagli = new JButton("VISUALIZZA DETTAGLI");
        btnVisualizzaDettagli.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        	}
        });
        btnVisualizzaDettagli.setFont(new Font("Arial", Font.BOLD, 14));
        btnVisualizzaDettagli.setBounds(250, 581, 200, 40);
        contentPane.add(btnVisualizzaDettagli);

        tornaHome.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controller.btn_reportGenerale_tornaHome();
            }
        }); 
            DefaultPieDataset dataset = new DefaultPieDataset();
            
            this.graficoTorta = ChartFactory.createPieChart(
                    "Grafico delle spese per Gruppo", 
                    dataset,                    
                    true,                       
                    true,                       
                    false                       
            );

            plot = (PiePlot) graficoTorta.getPlot();
            ChartPanel pannelloGrafico = new ChartPanel(graficoTorta);
            pannelloGrafico.addChartMouseListener(new ChartMouseListener() {
                @Override
                public void chartMouseClicked(ChartMouseEvent event) {
                    ChartEntity entity = event.getEntity();
                    
                    for (Object key : dataset.getKeys()) {
                        plot.setExplodePercent((Comparable) key, 0.0);
                    }

                    if (entity instanceof PieSectionEntity) {
                        PieSectionEntity spicchio = (PieSectionEntity) entity;
                        spicchioSelzionato = spicchio.getSectionKey().toString();
                        
                        plot.setExplodePercent(spicchio.getSectionKey(), 0.20);
                    } else {
                        spicchioSelzionato = null;
                    }
                }

                @Override
                public void chartMouseMoved(ChartMouseEvent event) {}
            });
            pannelloGrafico.setBounds(217,121,551,430);
            contentPane.add(pannelloGrafico);
            
            
            
    }
    
    public void aggiornaReport(HashMap<Object,Double> map) {
    	if(map==null) {
    		return;
    	}
    	this.dataset=new DefaultPieDataset();
    	
    	for(Map.Entry<Object,Double> entry:map.entrySet()) {
    		Object gruppo=entry.getKey();
    		Double totale=entry.getValue();
    		dataset.setValue((String)gruppo, totale);
    	}

    }
    
    
}