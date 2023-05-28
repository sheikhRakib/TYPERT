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
    private WordSpeed speed;
    public Timer wordSpeedTimer;
    private GUI gui;

    public Word(String name, GUI gui) {
        super(name);
        this.gui = gui;

        int fontSize = (int) (gui.getWidth() * 0.020);

        setFont(new Font("", Font.PLAIN, fontSize));
        setSize(getPreferredSize());
        setLocation((int) -getWidth(), new Random().nextInt(gui.gameRange.y));

        speed = getRandomSpeed();
        wordSpeedTimer = new Timer(speed.getValue(), this);
        wordSpeedTimer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(gui.state != GameState.PLAYING) return;

        int newX = getX();
        if (newX + getWidth() < getParent().getWidth()) {
            newX = getX() + 1;
            setLocation(newX, getY());
        } else {
            gui.state = GameState.GAME_OVER;
            gui.gameOverScreen = new GameOverScreen(6);
            gui.updateScreen();
        }
    }

    public void removeWordFromScreen() {
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
        WordSpeed[] speeds = WordSpeed.values();
        int index = new Random().nextInt(speeds.length);
        return speeds[index];
    }
}
