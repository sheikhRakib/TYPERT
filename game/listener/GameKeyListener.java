package game.listener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import game.GUI;
import game.util.GameState;

public class GameKeyListener implements KeyListener {
    private GUI gui;

    public GameKeyListener(GUI gui) {
        this.gui = gui;
    }
    @Override
    public void keyTyped(KeyEvent e) {
        char key = e.getKeyChar();
        
        switch (key) {
            case KeyEvent.VK_ESCAPE:
                if(gui.state == GameState.PLAYING) {
                    gui.state = GameState.PAUSED;
                } else if (gui.state == GameState.PAUSED) {
                    gui.state = GameState.PLAYING;
                }
                break;        
            default:
                gui.gameScreenPanel.scoreSubPanel.updateInputText(e);
                break;
        }
        gui.updateScreen();
    }

    @Override
    public void keyPressed(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}
}
