package view.battle.gui.components;

import controllers.ControllerBattle;

import javax.swing.*;
import java.awt.*;


public class ShowHistory {

    public static void showHistoryGUI(ControllerBattle controller) {
        // Crear frame
        JFrame frame = new JFrame("Historial de la Batalla");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null); // Centrado en pantalla

        // Crear área de texto con fuente estilo consola
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        textArea.setBackground(new Color(245, 245, 245));
        textArea.setForeground(Color.DARK_GRAY);

        // Obtener historial del controlador
        String historial = controller.getHistory().ShowAllPila();

        // Mostrar mensaje en caso de que esté vacío
        if (historial.isEmpty()) {
            textArea.setText("No hay movimientos registrados en esta batalla.");
        } else {
            textArea.setText(historial);
        }

        // Envolver en scroll
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

        // Botón para cerrar
        JButton cerrar = new JButton("Cerrar");
        cerrar.addActionListener(e -> System.exit(0));

        //boton para mostrar grafica de estatisticas
        JButton Graph = new JButton("Mostrar Estadisticas");

        Graph.addActionListener(e -> {
            StatisticsGraph statsGraph = new StatisticsGraph(controller);
        });

        // Panel inferior para el botón
        JPanel bottomPanel = new JPanel();
        bottomPanel.add(cerrar);
        bottomPanel.add(Graph);

        // Añadir componentes al frame
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        // Mostrar ventana
        frame.setVisible(true);
    }
}
