package game;

import game.listener.GameKeyListener;
import game.screen.GameScreenPanel;
import game.screen.MenuScreenPanel;
import game.util.Dictionary;
import game.util.GameState;

import java.awt.BorderLayout;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.util.List;

import javax.swing.JFrame;

public class GUI extends JFrame {
	public GameState state;
	public Point gameRange;
	
	public MenuScreenPanel menuScreenPanel;
	public GameScreenPanel gameScreenPanel;

	public List<String> dictionary;

	public GUI() {
		this.setTitle("typert");
		this.state = GameState.MENU;
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.getContentPane().setLayout(new BorderLayout());
		
		this.addKeyListener(new GameKeyListener(this));
		
		this.setSize(700, 500);
		// makeFullScreen();
		
		gameRange = new Point(getWidth(), (int)(getHeight()*.8));
		menuScreenPanel = new MenuScreenPanel(this);
		updateScreen();

		dictionary = Dictionary.readTextFile();
	}

	private void makeFullScreen() {
		GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice device = env.getDefaultScreenDevice();
		this.setUndecorated(true);
		device.setFullScreenWindow(this);
	}

	public void updateScreen() {
		System.out.println("M: UpdateScreen()");
		this.getContentPane().removeAll();

		switch (this.state) {
			case MENU:
			case PAUSED:
				System.out.println("S: Menu/Paused");
				this.menuScreenPanel.checkResumeButton();
				this.getContentPane().add(menuScreenPanel, BorderLayout.CENTER);
				break;
			case GAME_OVER:
				System.out.println("S: GameOver");
				this.getContentPane().add(gameScreenPanel, BorderLayout.CENTER);
				break;
			case PLAYING:
				System.out.println("S: Playing");
				this.getContentPane().add(gameScreenPanel, BorderLayout.CENTER);
				break;
			default:
				break;
		}
		
		// Refresh the GUI
		this.revalidate();
		this.repaint();
	}
}
