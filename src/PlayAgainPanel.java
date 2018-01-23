import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

class PlayAgainPanel extends GamePanel {
	Graphics gTemp;
	int paint = -1;
	private Image parchmentImage = new ImageIcon("/resources/Parchment.jpg").getImage();
	JButton jbtHi;
	JButton jbtPlayAgain;
	JButton jbtQuit;
	JButton jbtAwesome;

	public PlayAgainPanel() {
		setLayout(null);

		if (GuessWordPanel.allGuessed == true) {
			paint = 0;
			repaint();

			jbtAwesome = new JButton("I know I'm awesome");
			jbtAwesome.setBounds(96, 200, 310, 30);
	
			jbtAwesome.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					PlayAgainPanel.this.paint = 2;
					PlayAgainPanel.this.jbtAwesome.setVisible(false);
					repaint();
				}
			});					

			add(jbtAwesome);
		} else if (GuessWordPanel.allGuessed == false) {
			paint = 1;
			repaint();

			jbtHi = new JButton("Hey, nice to meet you! Sorry - gotta run!");
			jbtHi.setBounds(79, 200, 340, 30);

			jbtHi.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					PlayAgainPanel.this.paint = 2;
					PlayAgainPanel.this.jbtHi.setVisible(false);
					repaint();
				}
			});

			jbtHi.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						PlayAgainPanel.this.paint = 2;
						PlayAgainPanel.this.jbtHi.setVisible(false);
						repaint();
					}
				}
			});

			add(jbtHi);
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);	
		gTemp = g.create();
		if (paint == 0) {
			drawWow(gTemp);
		} else if (paint == 1) {
			drawVoldemort(gTemp);
		} else if (paint == 2) {
			drawPlayAgain(gTemp);
		} else if (paint == 3) {
			quit(gTemp);
		}
	}

	public void drawVoldemort(Graphics gTemp) {
		gTemp.setFont(new Font("TimesRoman", Font.BOLD, 20));
		gTemp.drawImage(parchmentImage, 0, 0, this);

		String uhOh = "Uh oh... a fully-formed Voldemort";
		String incompetence = "has been risen by your incompetence!";
		String before = "Before you run for your life though, meet ";
		FontMetrics fm = gTemp.getFontMetrics();
		
		int x1 = getWidth() / 2 - fm.stringWidth(uhOh) / 2;
		int y1 = 30;
		
		int x2 = getWidth() / 2 - fm.stringWidth(incompetence) / 2;
		int y2 = 55;

		int x3 = getWidth() / 2 - fm.stringWidth(before) / 2;
		int y3 = 80;

		gTemp.drawString(uhOh, x1, y1);
		gTemp.drawString(incompetence, x2, y2);
		gTemp.drawString(before, x3, y3);

		gTemp.setFont(new Font("TimesRoman", Font.ITALIC, 30));
		FontMetrics fm2 = gTemp.getFontMetrics();

		int x4 = getWidth() / 2 - fm2.stringWidth(GuessWordPanel.chosenOne.getName()) / 2;
		int y4 = getHeight() / 2;

		gTemp.drawString(GuessWordPanel.chosenOne.getName(), x4, y4);
	}

	public void drawWow(Graphics gTemp) {
		gTemp.setFont(new Font("TimesRoman", Font.ITALIC, 18));
		gTemp.drawImage(parchmentImage, 0, 0, this);

		String met = "It looks like you've already met " + GuessWordPanel.chosenOne.getName() + "!";
		String well = "Well done!";
		GuessWordPanel.points++;

		FontMetrics fm = gTemp.getFontMetrics();

		int x1 = getWidth() / 2 - fm.stringWidth(met) / 2;
		int y1 = 100;

		int x2 = getWidth() / 2 - fm.stringWidth(well) / 2;
		int y2 = 136;

		gTemp.drawString(met, x1, y1);
		gTemp.drawString(well, x2, y2);
	}

	public void drawPlayAgain(Graphics gTemp) {
		Image characters = new ImageIcon("/resources/Characters.jpg").getImage();
		gTemp.drawImage(characters, 0, 0, 500, 300, this);

		Border pointsBorder = BorderFactory.createLineBorder(Color.LIGHT_GRAY, 5);

		JLabel jlblPoints = new JLabel("Total points: " + GuessWordPanel.points);
		jlblPoints.setBorder(pointsBorder);
		jlblPoints.setFont(new Font("TimesRoman", Font.ITALIC, 18));
		jlblPoints.setBackground(Color.BLUE);
		jlblPoints.setForeground(Color.LIGHT_GRAY);
		jlblPoints.setBounds(180, 60, 154, 30);
		jlblPoints.setOpaque(true);
		add(jlblPoints);

		jbtPlayAgain = new JButton("Guess again!");
		jbtPlayAgain.setBounds(136, 200, 150, 30);

		jbtPlayAgain.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PlayAgainPanel.this.playAgain();
			}
		});

		jbtPlayAgain.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					PlayAgainPanel.this.playAgain();
				}
			}
		});

		jbtQuit = new JButton("Quit");
		jbtQuit.setBounds(300, 200, 90, 30);

		jbtQuit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PlayAgainPanel.this.paint = 3;
				PlayAgainPanel.this.repaint();
			}
		});

		jbtQuit.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					PlayAgainPanel.this.paint = 3;
					PlayAgainPanel.this.repaint();
				}
			}
		});

		add(jbtPlayAgain);
		add(jbtQuit);
	}

	public void playAgain() {
		JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
		this.setVisible(false);
		GuessWordPanel guessWordPanel = new GuessWordPanel();
		frame.add(guessWordPanel);
		guessWordPanel.allGuessed = false;
	}

	public void quit(Graphics gTemp) {
		JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
		frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
	}
}
