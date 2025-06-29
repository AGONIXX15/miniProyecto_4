package view.battle.gui.components;
import models.pokemon.Attack;
import utils.CustomFont;
import utils.RoundedButton;
import view.battle.gui.BattlePokemonGUI;

import javax.swing.*;
import java.awt.*;

public class PokemonAttacksButtons extends JPanel {
    private boolean touched;

    public PokemonAttacksButtons(Attack[]attacks) {
        setPreferredSize(new Dimension(400, 400));
        setOpaque(false);
        GridLayout grid = new GridLayout(2,2);
        grid.setVgap(10);
        grid.setHgap(10);
        this.touched = false;
        setLayout(new GridLayout(2, 2, 10, 10));
        for(byte i = 0; i < attacks.length; i++) {
            RoundedButton button = new RoundedButton(attacks[i].getName(), new Color(200, 230, 201), 30);
            button.setFont(CustomFont.loadfont(20));
            button.setBorder(BorderFactory.createLineBorder(new Color(56, 142, 60), 2));
            byte index = i;
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
