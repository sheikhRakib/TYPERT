package game;

import game.screen.GameOverScreen;
import game.screen.GameScreen;
import game.screen.MenuScreen;
import game.util.Dictionary;
import game.util.GameState;

import java.awt.BorderLayout;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.JFrame;

public class GUI extends JFrame {
	public GameState state;
	public Point gameRange;
	
	public List<String> dictionary;

	public MenuScreen menuScreen;
	public GameScreen gameScreen;
	public GameOverScreen gameOverScreen;
	
	
	public GUI() {
		dictionary = Dictionary.readTextFile();

		this.setTitle("typert");
		this.state = GameState.MENU;
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(new BorderLayout());
		
		KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {
			@Override
			public boolean dispatchKeyEvent(KeyEvent e) {
				if (e.getID() == KeyEvent.KEY_TYPED) {
					keyboardController(Character.toLowerCase(e.getKeyChar()));
				}
				return false; // Return false to allow the event to be dispatched to the focused component
			}
		});

		this.setSize(700, 500);
		// makeFullScreen();
		
		gameRange = new Point(getWidth(), (int)(getHeight()*.8));
		menuScreen = new MenuScreen(this);
		updateScreen();
		System.out.println("Range: ("+gameRange.getX()+", "+gameRange.getY()+")");
	}

	private void makeFullScreen() {
		GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice device = env.getDefaultScreenDevice();
		this.setUndecorated(true);
		device.setFullScreenWindow(this);
	}

	public void updateScreen() {
		this.getContentPane().removeAll();

		switch (this.state) {
			case MENU:
			case PAUSED:
				this.menuScreen.checkResumeButton();
				this.getContentPane().add(menuScreen, BorderLayout.CENTER);
				break;
			case GAME_OVER:
				this.getContentPane().add(gameOverScreen, BorderLayout.CENTER);
				break;
			case PLAYING:
				this.getContentPane().add(gameScreen, BorderLayout.CENTER);
				break;
			default:
				break;
		}
		
		// Refresh the GUI
		this.revalidate();
		this.repaint();
	}

	private void keyboardController(char key) {
		switch (key) {
			case KeyEvent.VK_ESCAPE:
				if (state == GameState.PLAYING) {
					state = GameState.PAUSED;
				} else if (state == GameState.PAUSED) {
					state = GameState.PLAYING;
				}
				break;
			case KeyEvent.VK_SPACE:
			case KeyEvent.VK_ENTER:
				gameScreen.scoreSubPanel.submitInputText();
				break;
			default:
				gameScreen.scoreSubPanel.updateInputText(key);
				break;
		}

		updateScreen();
	}
}
