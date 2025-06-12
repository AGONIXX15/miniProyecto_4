package view.battle.gui;

import org.jfree.chart.*;
import org.jfree.chart.plot.*;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.*;
import javax.swing.*;
import java.awt.*;

public class StatisticsGraph extends JFrame {

    private JPanel graphsPanel;

    public StatisticsGraph() {
        setTitle("Daño por combate");
        setSize(1200, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        graphsPanel = new JPanel();
        graphsPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20)); // para que acomode bien
        add(new JScrollPane(graphsPanel)); // scroll si hay muchas gráficas

        // Datos de prueba
        int[][] dañosPokemon1 = {
                {30, 40, 35},     // combate 1
                {20, 25},         // combate 2
                {15, 18, 22, 30}  // combate 3
        };

        int[][] dañosPokemon2 = {
                {25, 38, 40},
                {30, 10},
                {20, 15, 20, 10}
        };

        for (int i = 0; i < dañosPokemon1.length; i++) {
            graphsPanel.add(crearGrafica(dañosPokemon1[i], dañosPokemon2[i], "Combate " + (i + 1)));
        }

        setVisible(true);
    }

    private ChartPanel crearGrafica(int[] dañoP1, int[] dañoP2, String titulo) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (int i = 0; i < dañoP1.length; i++) {
            dataset.addValue(dañoP1[i], "pokemon1", "Turno " + (i + 1));
            dataset.addValue(dañoP2[i], "pokemon2", "Turno " + (i + 1));
        }

        JFreeChart chart = ChartFactory.createBarChart(
                titulo,        // Título
                "Turno",       // Eje X
                "Daño",        // Eje Y
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false
        );

        CategoryPlot plot = chart.getCategoryPlot();
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setSeriesPaint(0, Color.RED);   // pokemon1
        renderer.setSeriesPaint(1, Color.BLUE);  // pokemon2

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(350, 300));
        chartPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        return chartPanel;
    }

    public static void main(String[] args) {
        new StatisticsGraph();
    }
}
