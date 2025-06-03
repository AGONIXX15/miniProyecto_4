package view.battle.gui;

import battle.BattleTrainer;
import controllers.ControllerBattle;
import models.Pokemon;
import utils.ReproduceSound;
import utils.CustomFont;
import view.battle.*;

import javax.imageio.ImageIO;
import java.net.URL;
import java.io.IOException;

import javax.swing.*;
import java.awt.*;

public class BattlePokemonGUI extends JFrame implements ViewBattle {
    public int index1, index2;
    private CardLayout cards_menu, cards_buttons;
    private JPanel choose_menu, buttonsPanel;
    private JLayeredPane mainPanel;
    private PokemonStatusBar bar1, bar2;
    private PokemonAttacksButtons attacksButtons1, attacksButtons2;
    private MessageBattle messageBattle;
    private JLabel labelPokemon1, labelPokemon2, turnoLabel;
    private ReproduceSound sound;

    private static BattlePokemonGUI instance;
    private boolean trainer1Active;
    private ControllerBattle controllerBattle;

    public static BattlePokemonGUI getInstance() {
        return instance;
    }

    // tratar de acomodar las posiciones para windows
    public BattlePokemonGUI(ControllerBattle controllerBattle) {
        this.controllerBattle = controllerBattle;
        instance = this;
        trainer1Active = true;
        setDefaultCloseOperation(EXIT_ON_CLOSE);


        messageBattle = new MessageBattle();
        messageBattle.getCard().show(messageBattle, "vacio");
        // acomodar esto
        messageBattle.setBounds(1000, 600, 200, 200);

        mainPanel = new JLayeredPane();
        mainPanel.setSize(1000, 1000);

        turnoLabel = new JLabel("");
        turnoLabel.setBounds(100, 700, 600, 80);
        turnoLabel.setFont(CustomFont.loadfont(50));
        turnoLabel.setForeground(Color.WHITE);
        mainPanel.add(turnoLabel);
        setSize(1000, 1000);


        choose_menu = new JPanel();
        choose_menu.setPreferredSize(new Dimension(500, 500));
        choose_menu.setOpaque(false);
        cards_menu = new CardLayout();
        choose_menu.setLayout(cards_menu);
        choose_menu.add(new ShowPokemons(controllerBattle.trainer1), "trainer1");
        choose_menu.add(new ShowPokemons(controllerBattle.trainer2), "trainer2");
        JPanel p2 = new JPanel();
        p2.setOpaque(false);
        choose_menu.add(p2, "vacio");
        choose_menu.setBounds(100, 100, 500, 500);
        choose_menu.setVisible(true);


        cards_buttons = new CardLayout();

        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(cards_buttons);
        buttonsPanel.add(p2, "vacio");
        cards_buttons.show(buttonsPanel, "vacio");
        buttonsPanel.setVisible(true);


        mainPanel.add(choose_menu, Integer.valueOf(1));
        mainPanel.add(messageBattle, Integer.valueOf(1));
        setVisible(true);
        setLayout(null);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();


        ImageIcon battleBackground = new ImageIcon("resources/images/battleBackground.jpg");
        Image scaledImage = battleBackground.getImage().getScaledInstance(
                screenSize.width, screenSize.height, Image.SCALE_SMOOTH);
        ImageIcon scaledBackground = new ImageIcon(scaledImage);

        JLabel backgroundLabel = new JLabel(scaledBackground);
        backgroundLabel.setBounds(0, 0, screenSize.width, screenSize.height);

        mainPanel.add(backgroundLabel, Integer.valueOf(0));


        setContentPane(mainPanel);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        //reproducir sonido
        sound = new ReproduceSound();
        sound.loadSound("sounds/Voicy_Pokemon GO OST_ Battle.wav");
        sound.loopSound();
    }

    @Override
    public void start(){
        cards_menu.show(choose_menu, "trainer1");
    }

    public void chooseAgain() {
        removeThings();
        if (BattleTrainer.trainerHasLost(controllerBattle.trainer1)) {
            JOptionPane.showMessageDialog(null, String.format("gano el entrenador %s", controllerBattle.trainer2.getNameTrainer()));
            setVisible(false);
            sound.stopSound();
            System.exit(0);
        }
        if (BattleTrainer.trainerHasLost(controllerBattle.trainer2)) {
            JOptionPane.showMessageDialog(null, String.format("gano el entrenador %s", controllerBattle.trainer1.getNameTrainer()));
            setVisible(false);
            sound.stopSound();
            System.exit(0);
        }
        ((ShowPokemons) choose_menu.getComponent(0)).update();
        ((ShowPokemons) choose_menu.getComponent(1)).update();
        choose_menu.setVisible(true);

        index1 = -1;
        index2 = -1;
        System.out.println(trainer1Active);
        cards_menu.show(choose_menu, "trainer1");
    }

    public void updateLabel() {
        turnoLabel.setText(String.format("turno de: %s", (controllerBattle.getTurn()) ? controllerBattle.trainer1.getNameTrainer() : controllerBattle.trainer2.getNameTrainer()));
    }

    public void chooseTrainerPokemon() {
        if (!trainer1Active) {
            index2 = ((ShowPokemons) choose_menu.getComponent(1)).getChoose();
            System.out.println(index2);
            trainer1Active = true;
            choose_menu.setVisible(false);
            controllerBattle.startCombat(index1, index2);
            setThings();
            return;
        }
        index1 = ((ShowPokemons) choose_menu.getComponent(0)).getChoose();
        System.out.println(index1);
        cards_menu.show(choose_menu, "trainer2");
        trainer1Active = false;
    }

    public void pokemonBars() {
        Pokemon pokemon1 = controllerBattle.getPokemon1();
        Pokemon pokemon2 = controllerBattle.getPokemon2();
        bar1 = new PokemonStatusBar(pokemon1.getName(), pokemon1.getHealth(), pokemon1.getHealthMax());
        bar1.setBounds(1000, 100, 300, 80);
        bar2 = new PokemonStatusBar(pokemon2.getName(), pokemon2.getHealth(), pokemon2.getHealthMax());
        bar2.setBounds(150, 400, 300, 80);


        mainPanel.add(bar1, Integer.valueOf(2));
        mainPanel.add(bar2, Integer.valueOf(2));
    }

    @Override
    public void sendMessage(String message) {
        messageBattle.getCard().show(messageBattle, "textArea");
        messageBattle.enqueueMessage(message);
        messageBattle.revalidate();
        messageBattle.repaint();
    }

    public void makeDamage(int index) {
        boolean tempTurn = controllerBattle.getTurn();
        controllerBattle.processAttack(index);
        if(tempTurn){
            bar2.setHp(controllerBattle.getPokemon2().getHealth());
        } else {
            bar1.setHp(controllerBattle.getPokemon1().getHealth());
        }
        if(controllerBattle.hasFinish()){
            chooseAgain();
            return;
        }
        updateThings();
    }

    public void setAttacksButtons() {
        attacksButtons1 = new PokemonAttacksButtons(controllerBattle.getPokemon1().getAttacks());
        attacksButtons2 = new PokemonAttacksButtons(controllerBattle.getPokemon2().getAttacks());
        buttonsPanel.add(attacksButtons1, "attacks1");
        buttonsPanel.add(attacksButtons2, "attacks2");
        buttonsPanel.setBounds(600, 600, 400, 200);
        buttonsPanel.setVisible(true);
        mainPanel.add(buttonsPanel, Integer.valueOf(2));
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    public void updateAttacksButtons() {
        if (controllerBattle.getTurn()) {
            cards_buttons.show(buttonsPanel, "attacks1");
        } else {
            cards_buttons.show(buttonsPanel, "attacks2");
        }
    }

    public void setThings() {
        pokemonBars();
        setAttacksButtons();
        addPokemonImages();
        updateThings();
    }

    public void updateThings() {
        updateLabel();
        updateAttacksButtons();
    }

    /**
     * funcion para borrar barras de vida, imagenes de pokemon etc y volver a escoger
     */
    public void removeThings() {
        mainPanel.remove(bar1);
        mainPanel.remove(bar2);
        mainPanel.remove(attacksButtons1);
        mainPanel.remove(attacksButtons2);
        buttonsPanel.setVisible(false);
        mainPanel.revalidate();
        mainPanel.repaint();
        if (labelPokemon1 != null) mainPanel.remove(labelPokemon1);
        if (labelPokemon2 != null) mainPanel.remove(labelPokemon2);
    }

    private JLabel createPokemonImageLabel(String imageUrl, int x, int y, int width, int height) {
        try {
            URL url = new URL(imageUrl);
            Image image = ImageIO.read(url);
            Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            JLabel label = new JLabel(new ImageIcon(scaledImage));
            label.setBounds(x, y, width, height);
            return label;
        } catch (IOException e) {
            e.printStackTrace();
            return new JLabel("No se pudo cargar imagen");
        }
    }

    public void addPokemonImages() {
        if (labelPokemon1 != null) mainPanel.remove(labelPokemon1);
        if (labelPokemon2 != null) mainPanel.remove(labelPokemon2);


        labelPokemon1 = createPokemonImageLabel(controllerBattle.getPokemon1().getImagenUrl(), 1050, 200, 200, 200); // lado izquierdo
        labelPokemon2 = createPokemonImageLabel(controllerBattle.getPokemon2().getImagenUrl(), 350, 400, 200, 200); // lado derecho

        mainPanel.add(labelPokemon1, Integer.valueOf(2));
        mainPanel.add(labelPokemon2, Integer.valueOf(2));
    }
}