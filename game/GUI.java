package game;

import game.listener.GameKeyListener;
import game.screen.GameScreenPanel;
import game.screen.MenuScreenPanel;
import game.util.GameState;

import java.awt.BorderLayout;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.JFrame;

public class GUI extends JFrame {
	public GameState state;
	
	public MenuScreenPanel menuScreenPanel;
	public GameScreenPanel gameScreenPanel;

	public GUI() {
		this.setTitle("typert");
		this.state = GameState.MENU;
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		
		// Change the layout from GridLayout to BorderLayout
		this.getContentPane().setLayout(new BorderLayout());
		
		this.addKeyListener(new GameKeyListener(this));
		
		// this.setSize(700, 500);
		makeFullScreen();
		
		menuScreenPanel = new MenuScreenPanel(this);
		gameScreenPanel = new GameScreenPanel(this);
		updateScreen();
	}

	public void updateScreen() {
		this.getContentPane().removeAll();
		
		switch (this.state) {
			case MENU:
			case PAUSED:
				this.menuScreenPanel.checkResumeButton();
				this.getContentPane().add(menuScreenPanel, BorderLayout.CENTER);
				break;
			case PLAYING:
				this.getContentPane().add(gameScreenPanel, BorderLayout.CENTER);
				break;
			default:
				break;
		}
		// Refresh the GUI
		this.revalidate();
		this.repaint();
	}
	
	private void makeFullScreen() {
		GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice device = env.getDefaultScreenDevice();
		this.setUndecorated(true);
		device.setFullScreenWindow(this);
	}
}
