package game.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import game.GUI;
import game.util.GameState;

public class GameMouseListener implements MouseListener {
    private GUI gui;

    public GameMouseListener(GUI gui) {
        this.gui = gui;
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseClicked(MouseEvent e) { 
        if(gui.state == GameState.MENU || gui.state == GameState.PAUSED) {
            gui.menuScreenPanel.mouseClicked(e);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if(gui.state == GameState.MENU || gui.state == GameState.PAUSED) {
            gui.menuScreenPanel.mouseEntered(e);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if(gui.state == GameState.MENU || gui.state == GameState.PAUSED) {
            gui.menuScreenPanel.mouseExited();
        }
    }

}
