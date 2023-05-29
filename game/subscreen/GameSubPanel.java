package game.subscreen;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.Timer;

import game.GUI;
import game.object.Word;
import game.util.GameState;

public class GameSubPanel extends JPanel {
    private List<Word> words;

    public GameSubPanel(GUI gui) {
        setBorder(BorderFactory.createLoweredBevelBorder());
        setLayout(null);

        words = new ArrayList<>();

        Random random = new Random();

        new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(gui.state != GameState.PLAYING) return;

                String randomWord = gui.dictionary.get(random.nextInt(gui.dictionary.size()));
                Word word = new Word(randomWord, gui);
                add(word);
                words.add(word);
                
                // printWords();

                revalidate();
                repaint();
            }
        }).start();
    }


    private void printWords() {
        for (Word word : words) {
            System.out.print(word.getText() + ", ");
        }
        System.out.println();
    }

    public boolean matchWord(String inputWord) {
        for (Word word : words) {
            if(inputWord.equals(word.getText())) {
                word.delete();
                return true;
            }
        }
        return false;
    }
}
