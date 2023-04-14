package game;

import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {
	public static final String TITLE = "typert";
	public GameState state;

	public GUI() {
		this.state = GameState.MENU;
		this.setTitle(TITLE);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Change the layout from GridLayout to BorderLayout
		this.getContentPane().setLayout(new BorderLayout());

		// Set the frame to full screen
		GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice device = env.getDefaultScreenDevice();
		this.setUndecorated(true);
		device.setFullScreenWindow(this);
		
		// Get the screen size as a Dimension object
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		// Add the MenuPanel
		MenuPanel menuPanel = new MenuPanel(this);
		this.getContentPane().add(menuPanel, BorderLayout.CENTER);
	}
}
