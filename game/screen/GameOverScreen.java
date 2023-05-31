package game.screen;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import game.GUI;
import game.util.GameState;

public class GameOverScreen extends JPanel {
    private JLabel gameOverLabel;
    private JLabel scoreLabel;
    private JButton restartButton;

    public GameOverScreen(GUI gui, int score) {
        setBackground(Color.WHITE);
        setLayout(null);

        gameOverLabel = new JLabel("Game Over");
        gameOverLabel.setFont(new Font("Arial", Font.BOLD, 30));
        gameOverLabel.setBounds(100, 100, 200, 50);
        add(gameOverLabel);

        scoreLabel = new JLabel("Your Score: " + score);
        scoreLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        scoreLabel.setBounds(150, 200, 200, 30);
        add(scoreLabel);

        restartButton = new JButton("Restart");
        restartButton.setFont(new Font("Arial", Font.PLAIN, 16));
        restartButton.setBounds(150, 250, 100, 30);
        add(restartButton);
        restartButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if ( gui.state != GameState.GAME_OVER) return;

                gui.gameScreen.gameSubPanel.clearList();
                gui.state = GameState.PLAYING;
                gui.gameScreen = new GameScreen(gui);
                gui.updateScreen();
            }

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}
            
            @Override
            public void mouseEntered(MouseEvent e) {}
            
            @Override
            public void mouseExited(MouseEvent e) {}
        });
    }
}