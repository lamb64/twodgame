package twodgame;

import java.awt.Component;

import javax.swing.JFrame;

public class start {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setTitle("2d game");

		GameFanel GameFanel = new GameFanel();
		window.add(GameFanel);

		window.pack();

		window.setLocationRelativeTo(null);
		window.setVisible(true);

		GameFanel.startGameThread();

	}

}
