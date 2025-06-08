package view.battle.gui;

import exceptions.PokemonWeakenedException;
import models.Trainer;
import utils.CustomFont;
import view.utils.Pokedex;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class ShowPokemons extends JScrollPane {
    private Trainer trainer;
    private int choose = -1;
    private JLabel []labelInfos;

    public ShowPokemons(Trainer trainer) {
        setPreferredSize(new Dimension(600, 600));
        this.trainer = trainer;
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        labelInfos = new JLabel[trainer.getTeamArray().length];
        JLabel label = new JLabel(String.format("Escoge un Pokemon %s", trainer.getNameTrainer() + ":"));
        label.setForeground(Color.WHITE);
        label.setFont(CustomFont.loadfont(20f));
        panel.add(label);

        for (int i = 0; i < trainer.getTeamArray().length; i++) {
            JPanel pokemonPanel = new JPanel(new BorderLayout());

            pokemonPanel.setBorder(BorderFactory.createLineBorder(Color.blue, 2));
            pokemonPanel.setBackground(new Color(178, 235, 242));
            pokemonPanel.setPreferredSize(new Dimension(450, 80));
            pokemonPanel.setBorder(BorderFactory.createLineBorder(new Color(33, 150, 243), 2));

            // Imagen desde Pokedex
            Integer id = Pokedex.pokedex.get(trainer.getTeamArray()[i].getName());
            if (id != null) {
                String imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" + id + ".png";
                try {
                    URL url = new URL(imageUrl);
                    Image image = ImageIO.read(url);
                    Image scaledImage = image.getScaledInstance(75, 75, Image.SCALE_SMOOTH);
                    JLabel iconLabel = new JLabel(new ImageIcon(scaledImage));
                    pokemonPanel.add(iconLabel, BorderLayout.WEST);
                } catch (Exception ex) {
                    System.out.println("Error cargando imagen para " + trainer.getTeamArray()[i].getName() + ": " + ex.getMessage());
                }
            }

            // Info del Pokémon
            JLabel infoLabel = new JLabel("Nombre: " + trainer.getTeamArray()[i].getName() + ", Tipo: " + trainer.getTeamArray()[i].getType() + ", Vida: " + trainer.getTeamArray()[i].getHealth());
            labelInfos[i] = infoLabel;
            infoLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
            infoLabel.setForeground(Color.BLACK);
            pokemonPanel.add(infoLabel, BorderLayout.CENTER);


            // Botón "Escoger"
            JButton botonElegir = new JButton("Seleccionar");
            botonElegir.setPreferredSize(new Dimension(110, 30));
            botonElegir.setFont(CustomFont.loadfont(15));
            int index = i;
            botonElegir.addActionListener(e -> {
                try {
                    if (!trainer.getTeamArray()[index].isAlive()) {
                        throw new PokemonWeakenedException("No se puede escoger un Pokémon muerto. Escoge uno de nuevo.");
                    }
                    this.choose = index;
                    BattlePokemonGUI.getInstance().chooseTrainerPokemon();
                } catch (PokemonWeakenedException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            });
            pokemonPanel.add(botonElegir, BorderLayout.EAST);

            panel.add(Box.createRigidArea(new Dimension(0, 5)));
            panel.add(pokemonPanel);
        }

        setViewportView(panel);
        setOpaque(false);
        getViewport().setOpaque(false);
        setBorder(null);
        setPreferredSize(new Dimension(500, 400));
        setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_ALWAYS);
        getVerticalScrollBar().setUnitIncrement(16);
    }

    public void update(){
        for(int i = 0; i < trainer.getTeamArray().length; i++){
            labelInfos[i].setText("Nombre: " + trainer.getTeamArray()[i].getName() + ", Tipo: " + trainer.getTeamArray()[i].getType() + ", Vida: " + trainer.getTeamArray()[i].getHealth());
        }
    }

    public int  getChoose() {
        if (choose == -1) {
            System.out.println("no ha sido seleccionado");
        }
        return choose;
    }

}
