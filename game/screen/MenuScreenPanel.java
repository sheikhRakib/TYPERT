package game.screen;

import java.awt.*;
import java.awt.event.MouseEvent;

import javax.swing.*;

import game.GUI;
import game.listener.GameMouseListener;
import game.util.BackgroundImage;
import game.util.GameState;

public class MenuScreenPanel extends JLabel {
    private GUI gui;
    private JLabel resumeLabel;
    private JLabel newGameLabel;
    private JLabel highScoreLabel;
    private JLabel exitLabel;

    private GameMouseListener gameMouseListener;

    public MenuScreenPanel(GUI gui) {
        this.gui = gui;
        this.gameMouseListener = new GameMouseListener(gui);

        this.setLayout(null);
        int fontSize = (int) (gui.getWidth() * 0.04); // Adjust the 0.03 value to change the proportion
        Font labelFont = new Font("Baskerville", Font.HANGING_BASELINE, fontSize);

        this.setIcon(new BackgroundImage(gui));

        resumeLabel = new JLabel("Resume");
        resumeLabel.setFont(labelFont);
        resumeLabel.setSize(resumeLabel.getPreferredSize());
        resumeLabel.addMouseListener(gameMouseListener);
        
        newGameLabel = new JLabel("New Game");
        newGameLabel.setFont(labelFont);
        newGameLabel.setSize(newGameLabel.getPreferredSize());
        newGameLabel.addMouseListener(gameMouseListener);
        
        highScoreLabel = new JLabel("Score");
        highScoreLabel.setFont(labelFont);
        highScoreLabel.setSize(highScoreLabel.getPreferredSize());
        highScoreLabel.addMouseListener(gameMouseListener);
        
        exitLabel = new JLabel("Exit");
        exitLabel.setFont(labelFont);
        exitLabel.setSize(exitLabel.getPreferredSize());
        exitLabel.addMouseListener(gameMouseListener);
        
        this.add(resumeLabel);
        this.add(newGameLabel);
        this.add(highScoreLabel);
        this.add(exitLabel);
        
        resumeLabel.setLocation(gui.getWidth()/2 - (resumeLabel.getWidth()/2), gui.getHeight()/2 - 100);
        newGameLabel.setLocation(gui.getWidth()/2 - (newGameLabel.getWidth()/2), resumeLabel.getY() + resumeLabel.getHeight() + 10);
        highScoreLabel.setLocation(gui.getWidth()/2 - (highScoreLabel.getWidth()/2), newGameLabel.getY() + newGameLabel.getHeight() + 10);
        exitLabel.setLocation(gui.getWidth()/2 - (exitLabel.getWidth()/2), highScoreLabel.getY() + highScoreLabel.getHeight() + 10); 
    }

    public void checkResumeButton() {
        resumeLabel.setVisible(false);
        if (gui.state != GameState.MENU) {
            resumeLabel.setVisible(true);
        }
    }

    public void mouseClicked(MouseEvent e) {
        Object source = e.getSource();

        if (source == resumeLabel) {
            gui.state = GameState.PLAYING;
        } else if (source == newGameLabel) {
            gui.state = GameState.PLAYING;
            gui.gameScreenPanel = new GameScreenPanel(gui);
        } else if (source == highScoreLabel) {
            gui.state = GameState.PAUSED;
            System.out.println("High Score");
        } else if (source == exitLabel) {
            System.exit(0);
        }
        gui.updateScreen();
    }

    public void mouseEntered(MouseEvent e) {
        Object source = e.getSource();

        if (source == resumeLabel) {
            resumeLabel.setForeground(Color.WHITE);
        } else if (source == newGameLabel) {
            newGameLabel.setForeground(Color.WHITE);
        } else if (source == highScoreLabel) {
            highScoreLabel.setForeground(Color.WHITE);
        } else if (source == exitLabel) {
            exitLabel.setForeground(Color.RED);
        }
    }

    public void mouseExited() {
        resumeLabel.setForeground(Color.BLACK);
        newGameLabel.setForeground(Color.BLACK);
        highScoreLabel.setForeground(Color.BLACK);
        exitLabel.setForeground(Color.BLACK);
    }
}
