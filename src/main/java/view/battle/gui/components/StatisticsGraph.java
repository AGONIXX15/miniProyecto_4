package view.battle.gui.components;

import controllers.ControllerBattle;
import org.jfree.chart.*;
import org.jfree.chart.plot.*;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.*;
import utils.Pair;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;
import java.util.LinkedList;

public class StatisticsGraph extends JFrame {

    private JPanel graphsPanel;
    private ControllerBattle controller;

    public StatisticsGraph(ControllerBattle controller) {
        this.controller = controller;
        Pair<LinkedList<Integer>[], LinkedList<Integer>[]> damageTrainers = controller.getDamageTrainers();
        LinkedList<Integer> []damageTrainer1 = damageTrainers.first;
        LinkedList<Integer> []damageTrainer2 = damageTrainers.second;
        setTitle("Daño por combate");
        setSize(1200, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        graphsPanel = new JPanel();
        graphsPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20)); // para que acomode bien
        add(new JScrollPane(graphsPanel)); // scroll si hay muchas gráficas

        // Datos de prueba
         Iterator<Pair<Byte, Byte>> iterator = controller.getSave().getTurns().iterator();
        for (int i = 0; i < damageTrainer2.length; ++i) {
            if(iterator.hasNext()){
                graphsPanel.add(crearGrafica(damageTrainer1[i], damageTrainer2[i], "Combate " + (i + 1), iterator.next()));
            }
        }

        setVisible(true);
    }

    private ChartPanel crearGrafica(LinkedList<Integer> dañoP1, LinkedList<Integer> dañoP2, String titulo, Pair<Byte, Byte> p) {
        // crear un dataset para ser llenado
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        // agarrar los nombres de los respectivos pokemones
        String pokemonName1 = controller.trainer1.getTeamArray()[p.first].getName();
        String pokemonName2 = controller.trainer2.getTeamArray()[p.second].getName();
        if (pokemonName1.equals(pokemonName2)) {
            pokemonName1 += "(A)";
            pokemonName2 += "(B)";
        }
        int index = 1;
        for(Integer damage: dañoP1){
            dataset.addValue(damage, pokemonName2, "Turno " + index);
            ++index;
        }
        index = 1;
        for(Integer damage: dañoP2){
            dataset.addValue(damage, pokemonName1, "Turno " + index);
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
