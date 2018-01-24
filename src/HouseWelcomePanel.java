import java.awt.Color;
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

class HouseWelcomePanel extends GamePanel {
	Graphics gTemp;
	JButton jbtMotto = new JButton();

	public HouseWelcomePanel() {
		setLayout(null);		

		jbtMotto.setText(getMotto());
		jbtMotto.setBounds(29, 274, 435, 25);
		jbtMotto.setOpaque(false);

		add(jbtMotto);
		
		jbtMotto.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				proceed();
			}
		});

		jbtMotto.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				proceed();
			}
		});
	}
	
	public String getMotto() {
		if (SortingPanel.userHouse.equals("GRYFFINDOR")) {
			return "Our daring, nerve, and chivalry set us Gryffindors apart!";
		} else if (SortingPanel.userHouse.equals("HUFFLEPUFF")) {
			return "Us Hufflepuffs are just and loyal and unafraid of toil!";
		} else if (SortingPanel.userHouse.equals("RAVENCLAW")) {
			return "Wit beyond measure is man's greatest treasure!";
		} else if (SortingPanel.userHouse.equals("SLYTHERIN")) {
			return "We Slytherins use any means to achieve our ends!";
		} else {
			return "Invalid user house";
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		gTemp = g.create();
		drawBackground(gTemp);
	}

	public void drawBackground(Graphics gTemp) {	
		Image gryffindorImage = new ImageIcon("resources/Gryffindor.png").getImage();
		Image gryffindorRedImage = new ImageIcon("resources/GryffindorRed.jpg").getImage();
		Image ravenclawImage = new ImageIcon("resources/Ravenclaw.png").getImage();
		Image ravenclawBlueImage = new ImageIcon("resources/RavenclawBlue.jpg").getImage();
		Image hufflepuffImage = new ImageIcon("resources/Hufflepuff.png").getImage();
		Image hufflepuffYellowImage = new ImageIcon("resources/HufflepuffYellow.jpg").getImage();
		Image slytherinImage = new ImageIcon("resources/Slytherin.png").getImage();
		Image slytherinGreenImage = new ImageIcon("resources/SlytherinGreen.jpg").getImage();
/*
		Image houseImage = hufflepuffImage;
		Image backgroundImage = slytherinGreenImage;
		Color fontColour = Color.RED;
		String houseName = "Ravenclaw";
*/	 
		Image houseImage = null;
		Image backgroundImage = null;
		Color fontColour = null;
		String houseName = null;
		
		if (SortingPanel.userHouse.equals("GRYFFINDOR")) {
			houseImage = gryffindorImage;
			backgroundImage = gryffindorRedImage;
			fontColour = new Color(218, 165, 32); 
			houseName = "Gryffindor";
		} else if (SortingPanel.userHouse.equals("HUFFLEPUFF")) {
			houseImage = hufflepuffImage; 
			backgroundImage = hufflepuffYellowImage;
			fontColour = Color.BLACK;
			houseName = "Hufflepuff";
		} else if (SortingPanel.userHouse.equals("RAVENCLAW")) {
			houseImage = ravenclawImage;
			backgroundImage = ravenclawBlueImage;
			fontColour = new Color(184, 115, 51);
			houseName = "Ravenclaw";	
		} else if (SortingPanel.userHouse.equals("SLYTHERIN")) {
			houseImage = slytherinImage;
			backgroundImage = slytherinGreenImage;
			fontColour = Color.LIGHT_GRAY;
			houseName = "Slytherin";
		} 

		if (InitiationPanel.username == null) {
			InitiationPanel.username = "He Who Must Not Be Named";
		}

		gTemp.drawImage(backgroundImage, 0, -100, 530, 400, this);
		gTemp.drawImage(houseImage, 10, 10, 240, 270, this);
		gTemp.setFont(new Font("TimesRoman", Font.ITALIC, 28));
		gTemp.setColor(fontColour);
	
		FontMetrics fm = gTemp.getFontMetrics();
		int xWelcome = 240 + 120 - fm.stringWidth("Welcome to ") / 2;
		int xHouse = 360 - fm.stringWidth(houseName) / 2;

		gTemp.drawString("Welcome to ", xWelcome, 100);
		gTemp.drawString(houseName + ",", xHouse, 130);

		int xUsername;

		boolean longName = false;
		if (fm.stringWidth(InitiationPanel.username) / fm.stringWidth("a") > 15) {
			longName = true;
			gTemp.setFont(new Font("TimesRoman", Font.ITALIC, 15));
			FontMetrics fm2 = gTemp.getFontMetrics();
			xUsername = 240 + 120 - fm2.stringWidth(InitiationPanel.username) / 2;
		} else {
			xUsername = 240 + 120 - fm.stringWidth(InitiationPanel.username) / 2;
		}

		gTemp.drawString(InitiationPanel.username + "!", xUsername, 160);
	}

	public void proceed() {
		super.proceed();
		GuessWordPanel guessPanel = new GuessWordPanel();
		frame.add(guessPanel);
		guessPanel.jbtGuessChar.requestFocus();
	}
}