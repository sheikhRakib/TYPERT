package game.screen;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameOverScreen extends JPanel {
    private JLabel gameOverLabel;
    private JLabel scoreLabel;
    private JButton restartButton;

    public GameOverScreen(int score) {
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
        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle restart logic here
            }
        });
        add(restartButton);
    }
}