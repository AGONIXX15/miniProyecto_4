package view.battle.gui;
import utils.CustomFont;

import javax.swing.*;
import java.awt.*;

public class PokemonStatusBar extends JPanel {
    public String name;
    // vida actual y maxima
    public int hp, maxHp;
    // para tener en cuenta la vida antes de recibir daño esto para hacer un efecto
    private int lastHp;
    // variable booleana que sirve como flag para mostrar el rastro
    private boolean showTrail; // variab

    int width = 200;
    int height = 40;
    int x = 10;
    int y = 40;
    public PokemonStatusBar(String name, int hp, int maxHp) {
        setVisible(true);
        setOpaque(false);
        this.name = name;
        this.hp = hp;
        this.maxHp = maxHp;
        this.lastHp = hp;
        this.showTrail = false;
    }

   public void setHp(int targetHp) {
       targetHp = Math.max(0, Math.min(maxHp, targetHp));

       this.lastHp = this.hp;
       this.showTrail = true; // true para mostrar el rastro
       int finalTargetHp = targetHp;

       // efecto de bajar vida
       Timer timer = new Timer(30, e -> {
           if (this.hp > finalTargetHp) {
               --this.hp;
               repaint();
           } else {
               ((Timer) e.getSource()).stop();
               this.showTrail = false;
               this.lastHp = this.hp;
               repaint();
            }
           });
       timer.start();
   }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setFont(CustomFont.loadfont(20f));
        g.setColor(Color.WHITE);
        g.drawString(String.format("%s %d/%d HP", name, hp, maxHp), 10,30);
        g.setColor(Color.WHITE);
        g.fillRect(10,40, width, height);
        float percentage = (float) hp / (float) maxHp;
        if (percentage >= 0.7f) {
            g.setColor(Color.GREEN);
        } else if (percentage <= 0.3f) {
            g.setColor(Color.RED);
        } else if (percentage < 0.7f) {
            g.setColor(Color.ORANGE);
        }

        // calcular el nuevo ancho en pixeles con respecto a la vida
        int new_width = (int)(width * percentage);

        g.fillRect(x, y, new_width, height);

        // se activa cuando hay un cambio en la vida y quiere recibir daño
        if (showTrail && this.lastHp > this.hp) {
            g.setColor(Color.RED);
            float old_percentage = (float) lastHp / (float) maxHp;
            int old_width = (int)(width * old_percentage);
            g.fillRect(x + new_width, y, Math.abs(old_width - new_width), height);
        }
    }

    public static void main(String []args){
        JFrame frame = new JFrame("Pokemon Status Bar");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(230,120);
        frame.setLocationRelativeTo(null);
        PokemonStatusBar bar = new PokemonStatusBar("pikachu", 100, 100);
        frame.add(bar);
        bar.setHp(50);
        frame.setVisible(true);
    }
}