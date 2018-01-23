import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

class SortingPanel extends GamePanel {
	Graphics gTemp;
	Image sortingBackground = new ImageIcon("/resources/SortingHatBackground.jpg").getImage();
	public static int paintTimes = 0;
	JButton jbtOk;
	String choice;
	public static String userHouse;

	public SortingPanel() {
		setLayout(null);
		setFocusable(true);


		jbtOk = new JButton("Ok, let's do it!");
		jbtOk.setBounds(198, 157, 188, 35);
		jbtOk.setFocusable(true);

		jbtOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				jbtOk.setVisible(false);
				paintTimes = 1;
				repaint();
			}
		});

		jbtOk.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				jbtOk.setVisible(false);
				paintTimes = 1;
				repaint();
			}
		});

		add(jbtOk);

		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				char code = (e.getKeyChar() + "").toUpperCase().charAt(0);
				if (code == 'G') {
					userHouse = "GRYFFINDOR";
				} else if (code == 'H') {
					userHouse = "HUFFLEPUFF";
				} else if (code == 'R') {
					userHouse = "RAVENCLAW";
				} else if (code == 'S') {
					userHouse = "SLYTHERIN";
				} else if (code == '?') {
					paintTimes = 2;
					repaint();
				}
			
				if (userHouse != null) {
					proceed();
				}
			}
		});

		addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();

				if (x <= 127 || (x <= 191 && y >= 184 && y <= 211)) {
					SortingPanel.paintTimes = 2;
					SortingPanel.this.repaint();
				} else if (x >= 260 && x < 355) {
					if (y >= 20 && y <= 115) {
						userHouse = "GRYFFINDOR";
					} else if (y >= 125 && y <= 235) {
						userHouse = "HUFFLEPUFF";
					}
				} else if (x > 355 && x <= 460) {
					if (y >= 20 && y <= 115) {
						userHouse = "SLYTHERIN";
					} else if (y >= 125 && y <= 235) {
						userHouse = "RAVENCLAW";
					}
				}

				if (userHouse != null) {
					proceed();
				}
			}
		
			@Override public void mouseEntered(MouseEvent e) {}
			@Override public void mouseExited(MouseEvent e) {}
			@Override public void mousePressed(MouseEvent e) {}
			@Override public void mouseReleased(MouseEvent e) {}
		});

	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		gTemp = g.create();
		if (paintTimes == 0) {
			drawItsTime(gTemp);
		} else if (paintTimes == 1) {
			drawChoice(gTemp);
		} else if (paintTimes == 2) {
			drawHat(gTemp);
		} 
	}

	private void drawItsTime(Graphics gTemp) {
		gTemp.drawImage(sortingBackground, 0, 0, 500, 300, this);
		gTemp.setFont(new Font("TimesRoman", Font.ITALIC, 28));
		gTemp.setColor(new Color(238, 188, 98));

		String message = "Time for the Sorting!";

		FontMetrics fm = gTemp.getFontMetrics();
		int x = this.getWidth() / 5 * 3 - fm.stringWidth(message) / 2;
		int y = this.getHeight() / 6;

		gTemp.drawString(message, x, y);
	}

	private void drawChoice(Graphics gTemp) {
		Image emblem = new ImageIcon("/resources/Emblem.png").getImage();
		gTemp.drawImage(sortingBackground, 0, 0, 500, 300, this);
		gTemp.drawImage(emblem, 259, 19, 211, 225, this); 
		
		String message1 = "Click the Hat to be Sorted,";
		String message2 = "or a crest to choose your own House!";

		gTemp.setFont(new Font("TimesRoman", Font.ITALIC, 18));
		gTemp.setColor(new Color(238, 188, 98));

		FontMetrics fm = gTemp.getFontMetrics();
		int x1 = this.getWidth() / 5 * 3 - fm.stringWidth(message1) / 2;
		int y1 = this.getHeight() / 9 * 8;

		int x2 = this.getWidth() / 5 * 3 - fm.stringWidth(message2) / 2;
		int y2 = y1 + 21;

		gTemp.drawString(message1, x1, y1);
		gTemp.drawString(message2, x2, y2);
	}

	private void drawHat(Graphics gTemp) {
		Image imgPartyPopper = new ImageIcon("/resources/PartyPoppers.png").getImage().getScaledInstance(45, -1, 0);
		JButton jbtParty = new JButton(new ImageIcon(imgPartyPopper));
		jbtParty.setBounds(400, 200, 45, 45);

		jbtParty.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				proceed();
			}
		});

		jbtParty.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					proceed();
				}
			}
		});

		add(jbtParty);

		gTemp.drawImage(sortingBackground, 0, 0, 630, 370, this);
		Image speechBubbleImage = new ImageIcon("/resources/SpeechBubble.png").getImage();
		gTemp.drawImage(speechBubbleImage, 129, 20, 300, 200, this);

		String[] houses = {"RAVENCLAW", "GRYFFINDOR", "HUFFLEPUFF", "SLYTHERIN"};
		userHouse = houses[(int) (Math.random() * 4)];

		FontMetrics fm = gTemp.getFontMetrics();
		int x = 137 + (400 - 180) / 2 - fm.stringWidth(userHouse + "!") / 2;
		int y = 120;

		gTemp.setFont(new Font("TimesRoman", Font.BOLD, 24));
		gTemp.drawString(userHouse + "!", x, y);

	}

	public void proceed() {
		super.proceed();	
		HouseWelcomePanel hwPanel = new HouseWelcomePanel();
		frame.add(hwPanel);
		hwPanel.jbtMotto.requestFocus();
	}
}
