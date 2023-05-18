package game.object;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.Timer;

import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import game.GUI;

public class Word extends JLabel implements ActionListener {
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

        point = new Point((int) -this.oDimension.getWidth(), random.nextInt(gui.gameRange.y));
        setLocation(point);
        setSize(oDimension);

        objectMoveTimer = new Timer(30, this);
        objectMoveTimer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int newX = getX();
        if (newX + this.getWidth() < getParent().getWidth()) {
            newX = getX() + 1;
            setLocation(newX, getY());
        } else {
            removeWordFromScreen();
        }
    }

    public void removeWordFromScreen() {
        objectMoveTimer.stop();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Container parent = getParent();
                if (parent != null) {
                    parent.remove(Word.this);
                    parent.revalidate();
                    parent.repaint();
                }
            }
        });
    }

}
