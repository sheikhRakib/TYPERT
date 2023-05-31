package game.subscreen;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
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
    private static final int ZONE_SIZE = 40;

    public GameSubPanel(GUI gui) {
        setBorder(BorderFactory.createLoweredBevelBorder());
        setLayout(null);

        words = new ArrayList<>();

        Random random = new Random();

        new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (gui.state != GameState.PLAYING)
                    return;

                String randomWord = gui.dictionary.get(random.nextInt(gui.dictionary.size()));
                Word word = new Word(randomWord, gui);

                // Adjust the word's Y location to fit in a zone
                int zone = random.nextInt(gui.gameRange.y / ZONE_SIZE);
                word.setLocation(word.getX(), zone * ZONE_SIZE);

                // Verify if the zone is already occupied
                for (Word existingWord : words) {
                    if (existingWord.getY() / ZONE_SIZE == zone) {
                        return;
                    }
                }

                add(word);
                words.add(word);

                revalidate();
                repaint();
            }
        }).start();
    }


    public void clearList() {
        for (Word word : words) {
            word.delete();
        }
    }

    public boolean matchWord(String inputWord) {
        if(inputWord.equals("bang")) {
            for (Word word : words) {
                word.delete();
            }
            return true;
        }
        Iterator<Word> wordIterator = words.iterator();
        while (wordIterator.hasNext()) {
            Word word = wordIterator.next();
            if (inputWord.equals(word.getText())) {
                word.delete();
                wordIterator.remove(); // Safely remove the word from the list
                return true;
            }
        }
        return false;
    }
}
