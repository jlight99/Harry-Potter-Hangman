import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.border.Border;
import java.util.*;

public class SortingCeremony extends JFrame {
	private GameData gameData;

	public static void main(String[] args) {
		final GameData gameData = new GameData();
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				SortingCeremony game = new SortingCeremony(gameData);
				game.setVisible(true);
			}
		});
	}

	public SortingCeremony(GameData gameData) {
		this.gameData = gameData;
		initUI();
	}

	private void initUI() {
		InitiationPanel initiationPanel = new InitiationPanel(gameData);
		add(initiationPanel);

		setTitle("Harry Potter Hangman");
		setSize(500, 300);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}
}
