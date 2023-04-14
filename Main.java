import java.awt.EventQueue;

import game.GUI;

class Main {
    public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
            try {
                GUI gui = new GUI();
                gui.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}