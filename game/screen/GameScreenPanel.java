package game.screen;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JPanel;

import game.GUI;
import game.subscreen.GameSubPanel;
import game.subscreen.ScoreSubPanel;

public class GameScreenPanel extends JPanel {
    public GameSubPanel gameSubPanel;
    public ScoreSubPanel scoreSubPanel;


    public GameScreenPanel(GUI gui) {
        setBackground(Color.LIGHT_GRAY);
        setLayout(new BorderLayout());

        // Add the GameSubPanel as a subpanel
        gameSubPanel = new GameSubPanel(gui);
        add(gameSubPanel, BorderLayout.CENTER);
        System.out.println(gameSubPanel.getWidth());

        // Add the ScorePanel as a subpanel
        scoreSubPanel = new ScoreSubPanel(gui);
        add(scoreSubPanel, BorderLayout.SOUTH);
    }

}
