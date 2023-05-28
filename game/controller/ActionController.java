package game.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import game.GUI;
import game.util.GameState;

public class ActionController implements KeyListener, MouseListener, ActionListener{
    private GUI gui;

    public ActionController(GUI gui) {
        this.gui = gui;
    }


    @Override
    public void actionPerformed(ActionEvent e) { }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (gui.state == GameState.MENU || gui.state == GameState.PAUSED) {
            gui.menuScreen.mouseClicked(e);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) { }

    @Override
    public void mouseReleased(MouseEvent e) { }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (gui.state == GameState.MENU || gui.state == GameState.PAUSED) {
            gui.menuScreen.mouseEntered(e);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (gui.state == GameState.MENU || gui.state == GameState.PAUSED) {
            gui.menuScreen.mouseExited();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        char key = Character.toLowerCase(e.getKeyChar());

        switch (key) {
            case KeyEvent.VK_ESCAPE:
                if (gui.state == GameState.PLAYING) {
                    gui.state = GameState.PAUSED;
                } else if (gui.state == GameState.PAUSED) {
                    gui.state = GameState.PLAYING;
                }
                break;
            case KeyEvent.VK_SPACE:
            case KeyEvent.VK_ENTER:
                gui.gameScreen.scoreSubPanel.submitInputText();
                break;
            default:
                gui.gameScreen.scoreSubPanel.updateInputText(key);
                break;
        }
        gui.updateScreen();
    }

    @Override
    public void keyPressed(KeyEvent e) { }

    @Override
    public void keyReleased(KeyEvent e) { }
    
}
