package game;

import game.listener.GameKeyListener;
import game.screen.GameScreenPanel;
import game.screen.MenuScreenPanel;
import game.util.GameState;

import java.awt.BorderLayout;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
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

		dictionary = readTextFile("props/dictionary.txt");
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

	public List<String> readTextFile(String filePath) {
		List<String> words = new ArrayList<>();

		try (BufferedReader br = Files.newBufferedReader(Paths.get(filePath))) {
			String line;
			while ((line = br.readLine()) != null) {
				words.add(line.toLowerCase());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return words;
	}
}
