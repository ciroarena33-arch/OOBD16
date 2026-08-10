package gui.movimento;

import javax.swing.*;
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

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.awt.event.ActionEvent;
import control.MovimentoController;

public class ReportGeneraleGUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private MovimentoController controller;

    private JPanel contentPane;
    private JLabel titolo;
    private JLabel totaleSpeso;
    private JButton tornaHome;
    private JButton btnVisualizzaDettagli;
    
    private PiePlot plot;
    private DefaultPieDataset dataset = new DefaultPieDataset();
    private JFreeChart graficoTorta;
    private ChartPanel pannelloGrafico;
    private int indiceSpicchioSelezionato = -1;
    private String spicchioSelezionato = null;

    private ArrayList<Object> mappaLookup = new ArrayList<>();

    public ReportGeneraleGUI(MovimentoController controller) {
        super();
        setTitle("Resoconto delle Spese");
        this.controller = controller;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setResizable(false);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBackground(new Color(245, 245, 250));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        JLabel titolo = new JLabel("Report Generale Spese", SwingConstants.CENTER);
        titolo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titolo.setForeground(new Color(30, 41, 59));
        titolo.setBounds(0, 15, 800, 36);
        contentPane.add(titolo);

        JLabel totaleSpeso = new JLabel("Distribuzione Spese per Gruppo", SwingConstants.CENTER);
        totaleSpeso.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        totaleSpeso.setForeground(new Color(100, 116, 139));
        totaleSpeso.setBounds(0, 52, 800, 22);
        contentPane.add(totaleSpeso);

        JButton btnVisualizzaDettagli = new JButton("VISUALIZZA DETTAGLI");
        btnVisualizzaDettagli.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnVisualizzaDettagli.setBackground(new Color(60, 120, 216));
        btnVisualizzaDettagli.setForeground(Color.WHITE);
        btnVisualizzaDettagli.setFocusPainted(false);
        btnVisualizzaDettagli.setBounds(180, 500, 210, 38);
        btnVisualizzaDettagli.addActionListener(e -> {
            if (spicchioSelezionato != null) {
                Object oggettoSelezionato = getOggettoDaSpicchioSelezionato(spicchioSelezionato);
                if (oggettoSelezionato != null) {
                    controller.gestisciSelezione(oggettoSelezionato);
                } else {
                    JOptionPane.showMessageDialog(null, "Errore nel recupero dell'elemento.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Seleziona prima una fetta dal grafico!");
            }
        });
        contentPane.add(btnVisualizzaDettagli);

        JButton tornaHome = new JButton("TORNA HOME");
        tornaHome.setFont(new Font("Segoe UI", Font.BOLD, 13));
        tornaHome.setBackground(new Color(110, 120, 135));
        tornaHome.setForeground(Color.WHITE);
        tornaHome.setFocusPainted(false);
        tornaHome.setBounds(410, 500, 210, 38);
        tornaHome.addActionListener(e -> controller.btn_reportGenerale_tornaHome());
        contentPane.add(tornaHome);

        this.graficoTorta = ChartFactory.createPieChart(
                "Grafico delle spese per Gruppo",
                this.dataset,
                true,
                true,
                false
        );

        plot = (PiePlot) graficoTorta.getPlot();
        plot.setBackgroundPaint(new Color(245, 245, 250));
        plot.setOutlineVisible(false);

        pannelloGrafico = new ChartPanel(graficoTorta);
        pannelloGrafico.addChartMouseListener(new ChartMouseListener() {
            @Override
            public void chartMouseClicked(ChartMouseEvent event) {
                ChartEntity entity = event.getEntity();
                for (Object key : dataset.getKeys()) {
                    plot.setExplodePercent((Comparable<?>) key, 0.0);
                }
                if (entity instanceof PieSectionEntity) {
                    PieSectionEntity spicchio = (PieSectionEntity) entity;
                    indiceSpicchioSelezionato = spicchio.getSectionIndex();
                    spicchioSelezionato = spicchio.getSectionKey().toString();
                    plot.setExplodePercent(spicchio.getSectionKey(), 0.20);
                } else {
                    indiceSpicchioSelezionato = -1;
                    spicchioSelezionato = null;
                }
            }

            @Override
            public void chartMouseMoved(ChartMouseEvent event) {}
        });
        pannelloGrafico.setBounds(150, 85, 500, 390);
        contentPane.add(pannelloGrafico);
    }

    public void aggiornaReport(HashMap<Object,Double> map) {
        if (map == null) return;
        this.dataset.clear();
        this.mappaLookup.clear();
        for (Map.Entry<Object,Double> entry : map.entrySet()) {
            Object p = entry.getKey();
            mappaLookup.add(p);
            dataset.setValue(p.toString(), entry.getValue());
        }
        if (pannelloGrafico != null) {
            pannelloGrafico.revalidate();
            pannelloGrafico.repaint();
        }
    }

    private Object getOggettoDaSpicchioSelezionato(String chiave) {
        if (chiave == null) return null;
        for (Object obj : mappaLookup) {
            if (obj.toString().equals(chiave)) {
                return obj;
            }
        }
        return null;
    }
}
