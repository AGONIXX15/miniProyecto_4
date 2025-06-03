package view.battle.gui;
import models.Attack;
import models.AttackFactory;
import utils.CustomFont;

import javax.swing.*;
import java.awt.*;

public class PokemonAttacksButtons extends JPanel {
    private boolean touched;

    public PokemonAttacksButtons(Attack[]attacks) {
        setPreferredSize(new Dimension(400, 400));
        GridLayout grid = new GridLayout(2,2);
        grid.setVgap(10);
        grid.setHgap(10);
        this.touched = false;
        setLayout(new GridLayout(2,2));
        for(int i = 0; i < attacks.length; i++) {
            JButton button = new JButton(String.format("%s", attacks[i].getName()));
            button.setFont(CustomFont.loadfont(20)); // Tamaño 16, negrita
            button.setForeground(Color.WHITE);                // Letras blancas
            button.setBackground(new Color(173, 216, 230));
            int index = i;
            button.setPreferredSize(new Dimension(150, 150));
            button.addActionListener(e -> {
                if(touched){
                    return;
                }

                touched = true;
               // System.out.println("ataque escogido " + attacks[index] + " " + index);
                // attack = index;
                BattlePokemonGUI.getInstance().makeDamage(index);
                touched = false;
            });

            add(button);
        }

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Pokemon Attacks");
        frame.setSize(1000,1000);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Attack []attacks = new Attack[]{
                AttackFactory.VINE_WHIP, AttackFactory.SOLAR_BEAM, AttackFactory.RAZOR_LEAF, AttackFactory.TACKLE
        };
        PokemonAttacksButtons panel = new PokemonAttacksButtons(attacks);
        panel.setBounds(100,100,300,300);
        JLabel label = new JLabel("algo");
        label.setBounds(200,500,100,100);
        frame.add(panel);
        frame.add(label);
        frame.setVisible(true);
    }
}
