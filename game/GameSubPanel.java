package game;

import javax.swing.*;

public class GameSubPanel extends JPanel {

    private GUI gui;

    public GameSubPanel(GUI gui) {
        this.gui = gui;
        setBorder(BorderFactory.createLoweredBevelBorder());

    }
}
