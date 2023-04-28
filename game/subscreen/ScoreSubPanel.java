package game.subscreen;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import game.GUI;
import game.util.GameState;

public class ScoreSubPanel extends JPanel implements ActionListener {
    private GUI gui;

    private JButton pauseButton;
    private JLabel timeLabel;
    private JLabel scoreLabel;
    private JLabel accuracyLabel;
    private JLabel iWordLabel;

    private int score;
    private double accuracy;
    private int timer;

    private Timer updateTimer;

    public ScoreSubPanel(GUI gui) {
        this.gui = gui;
        this.timer = 0;
        this.score = 0;
        this.accuracy = 1;

        int fontSize = (int) (gui.getWidth() * 0.025); // Adjust the 0.025 value to change the proportion
        Font scoreObjectFont = new Font("Times New Roman", Font.ITALIC, fontSize);

        // Set BoxLayout with LINE_AXIS
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));

        pauseButton = new JButton("Pause");
        pauseButton.setFont(scoreObjectFont);
        add(pauseButton);

        add(Box.createHorizontalStrut(15)); // Add space between components

        timeLabel = new JLabel("Time: 0:00");
        timeLabel.setFont(scoreObjectFont);
        add(timeLabel);

        add(Box.createHorizontalStrut(15));

        scoreLabel = new JLabel("Score: " + score);
        scoreLabel.setFont(scoreObjectFont);
        add(scoreLabel);

        add(Box.createHorizontalStrut(10));

        accuracyLabel = new JLabel("Accuracy: " + accuracy + "%");
        accuracyLabel.setFont(scoreObjectFont);
        add(accuracyLabel);

        add(Box.createHorizontalStrut(10));

        JPanel wordPanel = new JPanel();

        JLabel lblWord = new JLabel("Word: ");
        lblWord.setFont(scoreObjectFont);
        wordPanel.add(lblWord);

        iWordLabel = new JLabel();
        iWordLabel.setFont(new Font(Font.SERIF, Font.BOLD, fontSize));
        wordPanel.add(iWordLabel);

        add(wordPanel);

        // Create and start the timer
        updateTimer = new Timer(1000, this); // Update every 1000 ms or 1 second
        updateTimer.start();
    }

    private void updateTime() {
        if (gui.state == GameState.PLAYING) {
            timer++;
            if (timer % 60 < 10)
                timeLabel.setText("Time: " + timer / 60 + ":0" + timer % 60);
            else
                timeLabel.setText("Time: " + timer / 60 + ":" + timer % 60);
            timeLabel.repaint();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == updateTimer) {
            updateTime();
        }
    }
    public void submitInputText() {
        if (gui.state == GameState.PLAYING && !iWordLabel.getText().isEmpty()) {
            System.out.println(iWordLabel.getText());
            iWordLabel.setText("");
            // frame.gamePanel.matchWord();
            // inputWord = null;
            iWordLabel.repaint();
        }
    }
    public void updateInputText(KeyEvent event) {
        char key = event.getKeyChar();
        key = Character.toLowerCase(key);

        iWordLabel.setText(iWordLabel.getText() + key);
        iWordLabel.repaint();
    }
}
