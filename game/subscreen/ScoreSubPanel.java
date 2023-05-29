package game.subscreen;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import game.GUI;
import game.util.GameState;

public class ScoreSubPanel extends JPanel {
    private GUI gui;

    private JLabel textTimeLabel;
    private JLabel textScoreLabel;
    private JLabel textAccuracylabel;
    private JLabel textWordlabel;

    private JButton pauseButton;
    private JLabel timeLabel;
    private JLabel scoreLabel;
    private JLabel accuracyLabel;
    private JLabel wordLabel;

    private int score;
    private int totalSubmit;
    private double accuracy;
    private int time;

    private Timer clock;

    public ScoreSubPanel(GUI gui) {
        this.gui = gui;

        this.time = 0;
        this.totalSubmit = 0;
        this.score = 0;
        this.accuracy = 100;

        int fontSize = (int) (gui.getWidth() * 0.025); // Adjust the 0.025 value to change the proportion
        Font font = new Font("Times New Roman", Font.ITALIC, fontSize);

        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));

        pauseButton = new JButton("Pause");
        pauseButton.setFont(font);
        pauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (gui.state == GameState.PLAYING) {
                    gui.state = GameState.PAUSED;
                    gui.updateScreen();
                }
            }
        });

        textTimeLabel = new JLabel("Time: ");
        textTimeLabel.setFont(font);
        timeLabel = new JLabel("0:00");
        timeLabel.setFont(font);

        textScoreLabel = new JLabel("Score: ");
        textScoreLabel.setFont(font);
        scoreLabel = new JLabel(Integer.toString(score));
        scoreLabel.setFont(font);

        textAccuracylabel = new JLabel("Accuracy: ");
        textAccuracylabel.setFont(font);
        accuracyLabel = new JLabel(Double.toString(accuracy) + "%");
        accuracyLabel.setFont(font);

        JPanel wordPanel = new JPanel();
        textWordlabel = new JLabel("Word: ");
        textWordlabel.setFont(font);
        wordLabel = new JLabel();
        wordLabel.setFont(new Font(Font.SERIF, Font.BOLD, fontSize));
        
        wordPanel.add(textWordlabel);
        wordPanel.add(wordLabel);
        
        add(pauseButton);
        add(Box.createHorizontalStrut(15)); // Add space between components
        add(textTimeLabel);
        add(timeLabel);
        add(Box.createHorizontalStrut(15)); // Add space between components
        add(textScoreLabel);
        add(scoreLabel);
        add(Box.createHorizontalStrut(15)); // Add space between components
        add(textAccuracylabel);
        add(accuracyLabel);
        add(Box.createHorizontalStrut(15)); // Add space between components
        add(wordPanel);

        clock = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateClock();
            }
        }); // Update every 1000 ms or 1 second
        clock.start();
    }

    private void updateClock() {
        if (gui.state == GameState.PLAYING) {
            time++;
            if (time % 60 < 10)
                timeLabel.setText(time / 60 + ":0" + time % 60);
            else
                timeLabel.setText(time / 60 + ":" + time % 60);
            timeLabel.repaint();
        }
    }

    public void submitInputText() {
        if(gui.state != GameState.PLAYING || wordLabel.getText().isEmpty()) return;

        totalSubmit++;
        String inputWord = wordLabel.getText();
        
        boolean success = gui.gameScreen.gameSubPanel.matchWord(inputWord);
        score = success ? score+1 : score;
        
        accuracy = ((double) score / (double) totalSubmit) * 100;
        scoreLabel.setText(Integer.toString(score));
        accuracyLabel.setText((new DecimalFormat("###.##").format(accuracy)) + "%");
        wordLabel.setText("");

        scoreLabel.repaint();
        accuracyLabel.repaint();
        wordLabel.repaint();
    }
    public void updateInputText(char letter) {
        if(gui.state != GameState.PLAYING) return;

        if(letter >= 'a' && letter <='z') {
            wordLabel.setText(wordLabel.getText() + letter);
            wordLabel.repaint();
        }
    }
}
