import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.border.Border;
import java.util.*;

public class SortingCeremony extends JFrame {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				SortingCeremony game = new SortingCeremony();
				game.setVisible(true);
			}
		});
	}

	public SortingCeremony() {
		initUI();
	}

	private void initUI() {
		InitiationPanel initiationPanel = new InitiationPanel();
		add(initiationPanel);

		setTitle("Harry Potter Hangman");
		setSize(500, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}
}