package view.battle.gui;

import org.jfree.chart.*;
import org.jfree.chart.plot.*;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.*;
import view.utils.Pair;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class StatisticsGraph extends JFrame {

    private JPanel graphsPanel;

    public StatisticsGraph(Pair<LinkedList<Integer>[], LinkedList<Integer>[]> damageTrainers) {
        LinkedList<Integer> []damageTrainer1 = damageTrainers.first;
        LinkedList<Integer> []damageTrainer2 = damageTrainers.second;
        if(damageTrainer1.length != 3 || damageTrainer2.length != 3) {
            throw new IllegalArgumentException("the size of damage trainers must be 3xn");
        }
        setTitle("Daño por combate");
        setSize(1200, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        graphsPanel = new JPanel();
        graphsPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20)); // para que acomode bien
        add(new JScrollPane(graphsPanel)); // scroll si hay muchas gráficas

        // Datos de prueba

        for (int i = 0; i < 3; ++i) {
            graphsPanel.add(crearGrafica(damageTrainer1[i], damageTrainer2[i], "Combate " + (i + 1)));
        }

        setVisible(true);
    }

    private ChartPanel crearGrafica(LinkedList<Integer> dañoP1, LinkedList<Integer> dañoP2, String titulo) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        int index = 1;
        for(Integer damage: dañoP1){
            dataset.addValue(damage, "pokemon1", "Turno " + index);
            ++index;
        }
        index = 1;
        for(Integer damage: dañoP2){
            dataset.addValue(damage, "pokemon2", "Turno " + index);
            ++index;
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
}
