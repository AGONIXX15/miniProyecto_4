package view;

import controllers.ControllerTrainer;
import utils.ReproduceSound;
import utils.CustomFont;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    static ReproduceSound reproduceSound;

    public MainFrame() {

        setTitle("Batalla Pokémon");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximiza
        setUndecorated(false);
        setLocationRelativeTo(null); // Centra la ventana en la pantalla

        //componentes
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel titulo = new JLabel("POKÉMON: Torneo de Leyendas");
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        titulo.setFont(CustomFont.loadfont(100f));
        titulo.setForeground(Color.WHITE);

        JLabel mensaje = new JLabel("<html><div style='width: 100%; text-align: center;'>"
                + "¡Bienvenido a la Liga Pokémon definitiva!<br>"
                + "¿Estás listo para convertirte en un Maestro Pokémon o vas a huir como un Magikarp asustado?"
                + "</div></html>");
        mensaje.setFont(CustomFont.loadfont(30f));
        mensaje.setForeground(Color.WHITE);
        mensaje.setAlignmentX(Component.CENTER_ALIGNMENT);
        mensaje.setMaximumSize(new Dimension(800, 150));

        ImageIcon imageHome = new ImageIcon("resources/images/botonInicio.png");
        JButton begin = new JButton(imageHome);
        begin.setAlignmentX(Component.CENTER_ALIGNMENT);
        begin.setFocusPainted(false);
        begin.setBorderPainted(false);
        begin.setContentAreaFilled(false);
        begin.setOpaque(false);

        // Establece el tamaño igual al de la imagen
        begin.setPreferredSize(new Dimension(imageHome.getIconWidth(), imageHome.getIconHeight()));

        begin.addActionListener(e -> {
            setVisible(false);
            ViewTrainer viewTrainer = new ViewTrainer();
            ControllerTrainer controller = ControllerTrainer.getInstance();
            controller.setViewI(viewTrainer);
        });

        ImageIcon trainer1 = new ImageIcon("resources/images/fondo3.jpg");
        Image image = trainer1.getImage().getScaledInstance(400, 400, Image.SCALE_SMOOTH);
        JLabel imagenLabel = new JLabel(new ImageIcon(image));


        panel.add(Box.createVerticalStrut(90)); // Espacio arriba del título
        panel.add(titulo);
        panel.add(Box.createVerticalStrut(30));
        panel.add(mensaje);
        panel.add(Box.createVerticalStrut(30));
        panel.add(begin);


        ImageIcon backgroundImage = new ImageIcon("resources/images/fondo3.jpg");
        Image scaledImage = backgroundImage.getImage().getScaledInstance(
                Toolkit.getDefaultToolkit().getScreenSize().width,
                Toolkit.getDefaultToolkit().getScreenSize().height,
                Image.SCALE_SMOOTH
        );
        JLabel backgroundLabel = new JLabel(new ImageIcon(scaledImage));
        backgroundLabel.setLayout(new BorderLayout());
        backgroundLabel.add(panel, BorderLayout.CENTER);
        setContentPane(backgroundLabel);

        setVisible(true); // Hace visible la ventana

        reproduceSound= new ReproduceSound();
        reproduceSound.loadSound("sounds/Voicy_Pokemon GO Original Sounds_ GOCHA.wav");
        reproduceSound.playSound();
    }
}

