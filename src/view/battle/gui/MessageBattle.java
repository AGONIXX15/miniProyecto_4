package view.battle.gui;

import utils.CustomFont;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.Queue;

public class MessageBattle extends JPanel {
    private JTextArea textArea;
    private String fullMessage = null;
    private CardLayout card;
    private int charIndex = 0;
    final private int speed = 15;
    private Queue<String> messageQueue = new LinkedList<>();
    private boolean isShowingMessage = false;

    /**
     * este es el constructor de la clase de mensajes para nuestro juego
     */
    public MessageBattle() {
        card = new CardLayout();
        setLayout(card);
        setPreferredSize(new Dimension(300, 300));
        textArea = new JTextArea();
        textArea.setFont(CustomFont.loadfont(20));
        textArea.setForeground(Color.WHITE);
        textArea.setOpaque(false);
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setPreferredSize(new Dimension(280, 120));

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(null);
        scrollPane.setPreferredSize(new Dimension(280, 120));

        add(scrollPane, "textArea");
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        add(panel, "vacio");
        setOpaque(false);
    }

    public void enqueueMessage(String newMessage) {
        messageQueue.add(newMessage);
        if (!isShowingMessage) {
            showNextMessage();
        }
    }

    private void showNextMessage() {
        if (messageQueue.isEmpty()) {
            isShowingMessage = false;
            return;
        }

        isShowingMessage = true;
        fullMessage = messageQueue.poll();
        textArea.setText("");
        charIndex = 0;

        Timer timer = new Timer(speed, e -> {
            if (charIndex < fullMessage.length()) {
                textArea.append(String.valueOf(fullMessage.charAt(charIndex)));
                charIndex++;
            } else {
                ((Timer) e.getSource()).stop();
                // Espera un poco antes de mostrar el siguiente mensaje
                new Timer(200, evt -> {
                    ((Timer) evt.getSource()).stop();
                    showNextMessage();
                }).start();
            }
        });
        timer.start();
    }

    public CardLayout getCard() {
        return card;
    }
}
