package game.object;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.Timer;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import game.GUI;
import game.screen.GameOverScreen;
import game.util.GameState;
import game.util.WordSpeed;

public class Word extends JLabel implements ActionListener {
    private WordSpeed delay;
    public Timer wordSpeedTimer;
    private GUI gui;
    private String name;

    public Word(String name, GUI gui) {
        super(name);
        this.name = name;
        this.gui = gui;

        int fontSize = (int) (gui.getWidth() * 0.020);
        setFont(new Font("", Font.PLAIN, fontSize));
        setSize(getPreferredSize());
        setLocation((int) -getWidth(), new Random().nextInt(gui.gameRange.y));

        delay = getRandomSpeed();
        wordSpeedTimer = new Timer(delay.getValue(), this);
        wordSpeedTimer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(gui.state != GameState.PLAYING) return;

        if (getX() + getWidth() < gui.gameRange.getX()) {
            setLocation(getX() + 1, getY());
            setText(name + "("+getX()+","+getY()+")");
            setSize(getPreferredSize());
        } else {
            gui.state = GameState.GAME_OVER;
            System.out.println("** Word: " +getText()+ " | position: " + getX() +"("+getX()+getWidth()+"),"+getY());
            gui.gameOverScreen = new GameOverScreen(gui, gui.gameScreen.scoreSubPanel.getScore());
            gui.updateScreen();
        }
    }

    public void delete() {
        wordSpeedTimer.stop();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Container parent = getParent();
                if (parent != null) {
                    parent.remove(Word.this);
                    parent.revalidate();
                    parent.repaint();
                }
            }
        });
    }

    private WordSpeed getRandomSpeed() {
        WordSpeed[] delays = WordSpeed.values();
        int index = new Random().nextInt(delays.length);
        return delays[index];
    }
}
