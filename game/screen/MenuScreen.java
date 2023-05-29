package game.screen;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

import game.GUI;
import game.util.BackgroundImage;
import game.util.GameState;

public class MenuScreen extends JLabel implements MouseListener{
    private GUI gui;
    private JLabel resumeLabel;
    private JLabel newGameLabel;
    private JLabel highScoreLabel;
    private JLabel exitLabel;

    public MenuScreen(GUI gui) {
        this.gui = gui;

        this.setLayout(null);
        int fontSize = (int) (gui.getWidth() * 0.04); // Adjust the 0.03 value to change the proportion
        Font labelFont = new Font("Baskerville", Font.HANGING_BASELINE, fontSize);
        this.setIcon(new BackgroundImage(gui));

        resumeLabel = new JLabel("Resume");
        resumeLabel.setFont(labelFont);
        resumeLabel.setSize(resumeLabel.getPreferredSize());
        resumeLabel.addMouseListener(this);
        
        newGameLabel = new JLabel("New Game");
        newGameLabel.setFont(labelFont);
        newGameLabel.setSize(newGameLabel.getPreferredSize());
        newGameLabel.addMouseListener(this);
        
        highScoreLabel = new JLabel("Score");
        highScoreLabel.setFont(labelFont);
        highScoreLabel.setSize(highScoreLabel.getPreferredSize());
        highScoreLabel.addMouseListener(this);
        
        exitLabel = new JLabel("Exit");
        exitLabel.setFont(labelFont);
        exitLabel.setSize(exitLabel.getPreferredSize());
        exitLabel.addMouseListener(this);
        
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
        if (gui.state == GameState.PAUSED) {
            resumeLabel.setVisible(true);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseClicked(MouseEvent e) {
        if (gui.state != GameState.MENU && gui.state != GameState.PAUSED)
            return;

        Object source = e.getSource();

        if (source == resumeLabel) {
            gui.state = GameState.PLAYING;
        } else if (source == newGameLabel) {
            gui.state = GameState.PLAYING;
            gui.gameScreen = new GameScreen(gui);
        } else if (source == highScoreLabel) {
            gui.state = GameState.SCORE_MENU;
            System.out.println("High Score");
        } else if (source == exitLabel) {
            System.exit(0);
        }
        gui.updateScreen();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (gui.state != GameState.MENU && gui.state != GameState.PAUSED)
            return;

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

    @Override
    public void mouseExited(MouseEvent e) {
        if(gui.state != GameState.MENU && gui.state != GameState.PAUSED) return;

        resumeLabel.setForeground(Color.BLACK);
        newGameLabel.setForeground(Color.BLACK);
        highScoreLabel.setForeground(Color.BLACK);
        exitLabel.setForeground(Color.BLACK);
    }
}
