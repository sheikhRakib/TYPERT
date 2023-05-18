package game.subscreen;

import javax.swing.*;

import game.GUI;
import game.object.Word;

public class GameSubPanel extends JPanel {
    private Word word;

    public GameSubPanel(GUI gui) {
        setBorder(BorderFactory.createLoweredBevelBorder());
        setLayout(null);

        word = new Word("sample", gui);
        add(word);
    }
}
