package game.object;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.util.Random;
import java.util.Timer;

import javax.swing.JLabel;

import game.GUI;


public class Word extends JLabel{
    private String name;
    private Point point;
    private Random random;

    private Dimension oDimension;

    public Timer objectMoveTimer; // object moving time

        // Constructor of Game Object
    public Word(String name, GUI gui) {
            super(name);
            int fontSize = (int) (gui.getWidth() * 0.020); 
            setFont(new Font("", Font.PLAIN, fontSize)); 

            random = new Random();
            
            this.name = name;
            oDimension = getPreferredSize();
            
            point = new Point(random.nextInt(gui.gameRange.x), random.nextInt(gui.gameRange.y));
            setLocation(point);
            setSize(oDimension);
        }
}
