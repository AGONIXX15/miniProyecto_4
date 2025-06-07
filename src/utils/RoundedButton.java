package utils;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class RoundedButton extends JButton {
    private final int arcWidth;
    private final int arcHeight;
    private final Color backgroundColor;

    public RoundedButton(String text, Color backgroundColor, int arc) {
        super(text);
        this.backgroundColor = backgroundColor;
        this.arcWidth = arc;
        this.arcHeight = arc;

        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setFocusPainted(false);
        setForeground(Color.WHITE); // letras blancas
        setFont(getFont().deriveFont(Font.BOLD, 16));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        Shape round = new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), arcWidth, arcHeight);

        g2.setColor(backgroundColor);
        g2.fill(round);

        // Clip para evitar que se pinte fuera del área redondeada
        g2.setClip(round);
        super.paintComponent(g2);
        g2.dispose();
    }
}
