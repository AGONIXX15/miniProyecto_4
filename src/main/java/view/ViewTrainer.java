package view;

import controllers.ControllerBattle;
import controllers.ControllerTrainer;
import view.battle.gui.components.SaveViewGui;
import models.pokemon.Pokemon;
import models.trainer.Trainer;
import utils.ReproduceSound;
import utils.CustomFont;

import javax.imageio.ImageIO;
import java.net.URL;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import javax.swing.*;

import utils.RoundedButton;
import utils.Pokedex;
import view.battle.gui.BattlePokemonGUI;

/**
 * @author Sebastian Devia
 * interfaz grafica del menu de escoger entrenadores
 */
public class ViewTrainer extends JFrame implements ViewTrainerInterface {
    String nombre1, nombre2;
    JPanel contenedor;
    JLabel entrenador1, entrenador2, textoBienvenida;
    JButton iniciarBatalla, mostrarEquipo, asignarEntreadores,cambiarConsola,cargarJuego;
    TextField entrenador1Texto, entrenador2Texto;
    public boolean entreadoresIntroduccidos, asignacionDeEquipos = false;
    public boolean tried;
    private ControllerTrainer controller;

    public ViewTrainer(ControllerTrainer controller) {
        this.controller = controller;
        if(this.controller.trainer1 != null) {
            nombre1 = this.controller.trainer1.getNameTrainer();
            nombre2 = this.controller.trainer2.getNameTrainer();
        }
        tried = false;
        setTitle("Seleccionar Entrenadores");


        UIManager.put("OptionPane.messageFont", CustomFont.loadfont(20));
        UIManager.put("OptionPane.buttonFont", CustomFont.loadfont(18));
        UIManager.put("Button.background", new Color(120, 180, 240));
        UIManager.put("Button.foreground", Color.BLACK);


        ImageIcon fondoIcon, fondoBotonSeleccionar, fondoBotonAsignar, fondoBotonBatalla;

        fondoIcon = new ImageIcon("src/resources/images/fondoSeleccion.png");
        fondoBotonSeleccionar = new ImageIcon("src/resources/images/fondoBotonSeleccionar3.png");
        fondoBotonAsignar = new ImageIcon("src/resources/images/fondoBotonAsignar.png");
        fondoBotonBatalla = new ImageIcon("src/resources/images/fondoBotonBatalla.png");
        JLabel fondo = new JLabel(fondoIcon);
        fondo.setBounds(0, 0, fondoIcon.getIconWidth(), fondoIcon.getIconHeight());
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        contenedor = new JPanel();
        contenedor.setLayout(null);
        contenedor.add(fondo);
        setContentPane(fondo);

        // Labels y TextFields
        textoBienvenida = new JLabel("Ingresa los entrenadores y selecciona equipos");

        textoBienvenida.setFont(CustomFont.loadfont(55f));
        textoBienvenida.setForeground(Color.WHITE);
        textoBienvenida.setBounds(280, 10, 1500, 50);
        fondo.add(textoBienvenida);

        entrenador1 = new JLabel("Entrenador 1:");
        entrenador1.setFont(CustomFont.loadfont(40f));
        entrenador1.setForeground(Color.BLACK);
        entrenador1.setBounds(300, 300, 400, 30);
        fondo.add(entrenador1);

        entrenador1Texto = new TextField();
        entrenador1Texto.setBounds(250, 350, 300, 30);
        entrenador1Texto.setText(nombre1);
        fondo.add(entrenador1Texto);

        entrenador2 = new JLabel("Entrenador 2:");
        entrenador2.setFont(CustomFont.loadfont(40f));
        entrenador2.setForeground(Color.BLACK);
        entrenador2.setBounds(950, 300, 400, 30);
        fondo.add(entrenador2);

        entrenador2Texto = new TextField();
        entrenador2Texto.setBounds(900, 350, 300, 30);
        entrenador2Texto.setText(nombre2);
        fondo.add(entrenador2Texto);

        // Botones
        asignarEntreadores = new JButton("Ingresar entrenadores", fondoBotonSeleccionar);
        asignarEntreadores.setBounds(600, 500, 300, 60);
        fondo.add(asignarEntreadores);

        mostrarEquipo = new JButton("Asignar y Mostrar Equipo", fondoBotonAsignar);
        mostrarEquipo.setBounds(600, 600, 300, 60);
        fondo.add(mostrarEquipo);

        iniciarBatalla = new JButton("Iniciar Batalla", fondoBotonBatalla);
        iniciarBatalla.setBounds(600, 700, 300, 60);
        fondo.add(iniciarBatalla);

        RoundedButton cargarJuego = new RoundedButton("Cargar partida",new Color(33, 150, 243), 30);
        cargarJuego.setFont(CustomFont.loadfont(35f));
        cargarJuego.setBounds(1200, 720, 300, 60);
        cargarJuego.addActionListener(e -> {
            if (SaveViewGui.load()){
                dispose();
                MainFrame.reproduceSound.stopSound();
            }

        });
        fondo.add(cargarJuego);





        RoundedButton cambiarConsola = new RoundedButton("Cambiar Vista", new Color(33, 150, 243), 30);
        cambiarConsola.setFont(CustomFont.loadfont(35));
        cambiarConsola.setBorder(BorderFactory.createLineBorder(new Color(0, 10, 94), 2));
        cambiarConsola.setBounds(30, 720, 300, 60);
        fondo.add(cambiarConsola);


        cambiarConsola.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);

                ViewTrainerConsole viewConsola = new ViewTrainerConsole(controller);
                controller.setViewI(viewConsola);
                viewConsola.mostrarMenu();
            }
        });
        asignarEntreadores.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AlertasignarEquipo();
            }
        });

        mostrarEquipo.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (entreadoresIntroduccidos || controller.trainer1!=null) {
                    asignacionDeEquipos = true;
                    mostrarEquipo();
                    return;
                }
                JOptionPane.showMessageDialog(null, "Por favor,Ingrese los entrenadores","Error", JOptionPane.ERROR_MESSAGE);
            }

        });
        setVisible(true);

        iniciarBatalla.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println(controller.trainer1);
                if (controller.trainer1 != null || (entreadoresIntroduccidos && asignacionDeEquipos)) {
                    tried = true;
                    if(MainFrame.reproduceSound != null){
                        MainFrame.reproduceSound.stopSound();
                    }
                    // parar sonido de inicio
                    ReproduceSound reproduceSound = new ReproduceSound();
                    reproduceSound.loadSound("sounds/ready-fight-37973.wav");
                    reproduceSound.playSound();
                    Timer t = new Timer(1000, event -> {
                        setVisible(false);
                        ControllerBattle controllerBattle = new ControllerBattle(controller.trainer1, controller.trainer2, controller.getRandom());
                        BattlePokemonGUI view = new BattlePokemonGUI(controllerBattle);
                        controllerBattle.setViewBattle(view);
                        dispose();
                        controllerBattle.startBattle();
                    });
                    t.setRepeats(false);
                    t.start();
                    return;
                }
                if (!tried) {
                    JOptionPane.showMessageDialog(null, "Ingrese los entrenadores y asigne su equipo", "Error", JOptionPane.ERROR_MESSAGE);

                }

            }
        });


    }

    //al darle al boton de ingresar entrenadores
    public void AlertasignarEquipo() {
        nombre1 = entrenador1Texto.getText();
        nombre2 = entrenador2Texto.getText();
        if (!nombre1.isEmpty() && !nombre2.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Entrenadores ingresados");
            entreadoresIntroduccidos = true;
            return;
        }
        JOptionPane.showMessageDialog(null, "Introduce los entrenadores","Error", JOptionPane.ERROR_MESSAGE);
        entreadoresIntroduccidos = false;
    }

    public LinkedList<JPanel> getTeamPanel(Trainer trainer, Color colorBorder, Color colorBackground){
        LinkedList<JPanel> panels = new LinkedList<>();
        for (Pokemon p : trainer.getTeamArray()) {
            JPanel pokemonPanel = new JPanel(new BorderLayout());
            pokemonPanel.setBorder(BorderFactory.createLineBorder(colorBorder, 2)); // Borde negro
            pokemonPanel.setBackground(colorBackground);
            pokemonPanel.setPreferredSize(new Dimension(470, 70));

            try {
                String imageUrl = Pokedex.getUrlByName(p.getName());
                URL url = new URL(imageUrl);
                Image imagen = ImageIO.read(url);
                Image escalaImagen = imagen.getScaledInstance(75, 75, Image.SCALE_SMOOTH);
                JLabel iconLabel = new JLabel(new ImageIcon(escalaImagen));
                pokemonPanel.add(iconLabel, BorderLayout.WEST);
            } catch (Exception ex) {
                System.out.println("Error cargando imagen para " + p.getName() + ": " + ex.getMessage());
            }

            JLabel infoLabel = new JLabel("Nombre: " + p.getName() + " Tipo: " + p.getType() + " Vida: " + p.getHealth()+" Velocidad: "+p.getSpeed());
            infoLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
            infoLabel.setForeground(Color.BLACK);
            pokemonPanel.add(infoLabel, BorderLayout.CENTER);
            panels.addLast(pokemonPanel);
        }
        return panels;

    }

    public void mostrarEquipo() {

        JPanel panel = new JPanel(new GridLayout(1, 2));

        //mostrar equipo de entranador1
        JPanel panel1 = new JPanel();
        this.controller.introducirTrainers(entrenador1Texto.getText(), entrenador2Texto.getText());

        panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
        JLabel textoEquipo = new JLabel("Equipo de " +nombre1 + ":");
        textoEquipo.setFont(CustomFont.loadfont(18));
        panel1.add(textoEquipo);

        for(JPanel p: getTeamPanel(this.controller.trainer1, Color.blue,new Color(33, 150, 243))){
            panel1.add(Box.createRigidArea(new Dimension(10, 3)));
            panel1.add(p);
        }

        //mostrar equipo de entranador2
        JPanel panel2 = new JPanel();
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
        JLabel textoEquipo2 = new JLabel("Equipo de " + nombre2+ ":");
        textoEquipo2.setFont(CustomFont.loadfont(18));
        panel2.add(textoEquipo2);

        for(JPanel p: getTeamPanel(this.controller.trainer2, Color.red,new Color(205, 64, 64))){
            panel2.add(Box.createRigidArea(new Dimension(10, 3)));
            panel2.add(p);
        }

        panel.add(panel1);
        panel.add(panel2);

        JOptionPane.showMessageDialog(this, panel, "Equipos", JOptionPane.PLAIN_MESSAGE);
    }

    @Override
    public void mostrarMenu() {
        this.setVisible(true);
    }

    @Override
    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }
}
