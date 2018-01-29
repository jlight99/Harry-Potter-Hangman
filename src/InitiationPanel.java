import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;

class InitiationPanel extends GamePanel {
	private Graphics gTemp;
	private JTextField jtfName;
	private String welcomeMessage = "Welcome to the Sorting Ceremony!";

	InitiationPanel(GameData gameData) {
		super(gameData);

		jtfName = new JTextField("Please print your name");
		jtfName.setBounds(145, 150, 194, 28);

		JButton jbtProceed = new JButton("Proceed onwards!");
		jbtProceed.setBounds(158, 187, 163, 31);

		add(jtfName);
		add(jbtProceed);

		jtfName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					InitiationPanel.this.getUsername(jtfName.getText());
					repaint();
				}
			}
		});

		jbtProceed.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				proceed();
			}
		});

		jbtProceed.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				proceed();
			}
		});
	}
	
	private void getUsername(String name) {
		gameData.setUsername(name);
		welcomeMessage = "Welcome, " + gameData.getUsername() + "!";
	}

	protected void proceed() {
		super.proceed();
		SortingPanel sortingPanel = new SortingPanel(gameData);
		frame.add(sortingPanel);
		sortingPanel.jbtOk.requestFocus();
	}

	@Override 
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		gTemp = g.create();
		doDrawing(gTemp);
	}

	private void doDrawing(Graphics gTemp) {
		Image parchment = new ImageIcon("resources/Parchment.jpg").getImage();
		gTemp.drawImage(parchment, 0, 0, this);
		gTemp.setFont(new Font("TimesRoman", Font.ITALIC, 24)); 

		FontMetrics fm = gTemp.getFontMetrics();
		int x = this.getWidth() / 2 - fm.stringWidth(welcomeMessage) / 2;
		int y = this.getHeight() / 5;

		gTemp.drawString(welcomeMessage, x, y);
	}
}
