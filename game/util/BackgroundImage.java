package game.util;

import java.awt.Image;
import javax.swing.ImageIcon;
import game.GUI;

public class BackgroundImage extends ImageIcon {

    public BackgroundImage(GUI gui) {
        super(new ImageIcon(BackgroundImage.class.getResource("/props/GameBackground.png")).getImage()
                .getScaledInstance(gui.getWidth(), gui.getHeight(), Image.SCALE_SMOOTH));
    }
}
