package game;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JPanel;

public class GameScreenPanel extends JPanel {
    private GUI gui;
    private GameSubPanel gameSubPanel;
    private ScoreSubPanel scoreSubPanel;


    public GameScreenPanel(GUI gui) {
        this.gui = gui;
        this.gui.state = GameState.PLAYING;
        setBackground(Color.LIGHT_GRAY);
        setLayout(new BorderLayout());

        // Add the GameSubPanel as a subpanel
        gameSubPanel = new GameSubPanel(gui);
        add(gameSubPanel, BorderLayout.CENTER);
        System.out.println(gameSubPanel.getWidth());

        // Add the ScorePanel as a subpanel
        scoreSubPanel = new ScoreSubPanel(gui);
        add(scoreSubPanel, BorderLayout.SOUTH);

        // Add your game components and logic here
    }

}
