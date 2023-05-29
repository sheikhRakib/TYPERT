package game.screen;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JPanel;

import game.GUI;
import game.subscreen.GameSubPanel;
import game.subscreen.ScoreSubPanel;

public class GameScreen extends JPanel {
    public GameSubPanel gameSubPanel;
    public ScoreSubPanel scoreSubPanel;

    public GameScreen(GUI gui) {
        setBackground(Color.LIGHT_GRAY);
        setLayout(new BorderLayout());

        gameSubPanel = new GameSubPanel(gui);
        add(gameSubPanel, BorderLayout.CENTER);
        
        scoreSubPanel = new ScoreSubPanel(gui);
        add(scoreSubPanel, BorderLayout.SOUTH);
    }
}
