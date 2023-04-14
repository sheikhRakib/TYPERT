package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

public class GUI extends JFrame {
	public static final String TITLE = "typert";
	public GameState state;
	GameScreenPanel gameScreenPanel;

	public GUI() {
		this.setTitle(TITLE);
		this.state = GameState.MENU;
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Change the layout from GridLayout to BorderLayout
		this.getContentPane().setLayout(new BorderLayout());

		// Set the frame to full screen
		GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice device = env.getDefaultScreenDevice();
		this.setUndecorated(true);
		device.setFullScreenWindow(this);

		showMenuPanel();
		setupEscKeyBinding();
	}

	public void showMenuPanel() {
		// Remove the current menu panel
		getContentPane().removeAll();

		// Add the MenuPanel
		MenuPanel menuPanel = new MenuPanel(this);
		this.getContentPane().add(menuPanel, BorderLayout.CENTER);

		// Refresh the GUI
		revalidate();
		repaint();
	}

	public void showGamePanel() {
		// Remove the current menu panel
		getContentPane().removeAll();

		// Add the game panel

		if(this.state == GameState.MENU) gameScreenPanel = new GameScreenPanel(this);
		this.getContentPane().add(gameScreenPanel, BorderLayout.CENTER);

		// Refresh the GUI
		revalidate();
		repaint();
	}

	private void setupEscKeyBinding() {
		Action escapeAction = new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(state != GameState.MENU)
					state = (state == GameState.PLAYING) ? GameState.PAUSED : GameState.PLAYING;

				if (state == GameState.PAUSED) showMenuPanel();
				if (state == GameState.PLAYING) showGamePanel();
			}
		};

		String escapeKey = "ESCAPE";
		KeyStroke escapeKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);

		getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(escapeKeyStroke, escapeKey);
		getRootPane().getActionMap().put(escapeKey, escapeAction);
	}
}
