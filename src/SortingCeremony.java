import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.border.Border;
import java.util.*;
import java.sql.*;

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

		initDBConnection();
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

	private void initDBConnection() {
		try {
			System.out.println(gameData.JDBC_DRIVER);
			Class.forName(gameData.JDBC_DRIVER);
		} catch (ClassNotFoundException ex) {
			System.out.println("Error: unable to load driver class!");
			System.exit(1);
		}

		try {
			gameData.setConnection(DriverManager.getConnection(gameData.DB_URL, gameData.USER, gameData.PASS));
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
}
