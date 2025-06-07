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
                BattlePokemonGUI.getInstance().makeDamage(index);
                touched = false;
            });

            add(button);
        }

    }


}
