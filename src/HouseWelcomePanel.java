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
	private Graphics gTemp;
	protected JButton jbtMotto = new JButton();

	HouseWelcomePanel(GameData gameData) {
		super(gameData);		

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
	
	private String getMotto() {
		if (gameData.getUserHouse().equals("GRYFFINDOR")) {
			return "Our daring, nerve, and chivalry set us Gryffindors apart!";
		} else if (gameData.getUserHouse().equals("HUFFLEPUFF")) {
			return "Us Hufflepuffs are just and loyal and unafraid of toil!";
		} else if (gameData.getUserHouse().equals("RAVENCLAW")) {
			return "Wit beyond measure is man's greatest treasure!";
		} else if (gameData.getUserHouse().equals("SLYTHERIN")) {
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

	private void drawBackground(Graphics gTemp) {	
		Image gryffindorImage = new ImageIcon("resources/Gryffindor.png").getImage();
		Image gryffindorRedImage = new ImageIcon("resources/GryffindorRed.jpg").getImage();
		Image ravenclawImage = new ImageIcon("resources/Ravenclaw.png").getImage();
		Image ravenclawBlueImage = new ImageIcon("resources/RavenclawBlue.jpg").getImage();
		Image hufflepuffImage = new ImageIcon("resources/Hufflepuff.png").getImage();
		Image hufflepuffYellowImage = new ImageIcon("resources/HufflepuffYellow.jpg").getImage();
		Image slytherinImage = new ImageIcon("resources/Slytherin.png").getImage();
		Image slytherinGreenImage = new ImageIcon("resources/SlytherinGreen.jpg").getImage();

		Image houseImage = null;
		Image backgroundImage = null;
		Color fontColour = null;
		String houseName = null;
		
		if (gameData.getUserHouse().equals("GRYFFINDOR")) {
			houseImage = gryffindorImage;
			backgroundImage = gryffindorRedImage;
			fontColour = new Color(218, 165, 32); 
			houseName = "Gryffindor";
		} else if (gameData.getUserHouse().equals("HUFFLEPUFF")) {
			houseImage = hufflepuffImage; 
			backgroundImage = hufflepuffYellowImage;
			fontColour = Color.BLACK;
			houseName = "Hufflepuff";
		} else if (gameData.getUserHouse().equals("RAVENCLAW")) {
			houseImage = ravenclawImage;
			backgroundImage = ravenclawBlueImage;
			fontColour = new Color(184, 115, 51);
			houseName = "Ravenclaw";	
		} else if (gameData.getUserHouse().equals("SLYTHERIN")) {
			houseImage = slytherinImage;
			backgroundImage = slytherinGreenImage;
			fontColour = Color.LIGHT_GRAY;
			houseName = "Slytherin";
		} 

		if (gameData.getUsername() == null) {
			gameData.setUsername("He Who Must Not Be Named");
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
		if (fm.stringWidth(gameData.getUsername()) / fm.stringWidth("a") > 15) {
			longName = true;
			gTemp.setFont(new Font("TimesRoman", Font.ITALIC, 15));
			FontMetrics fm2 = gTemp.getFontMetrics();
			xUsername = 240 + 120 - fm2.stringWidth(gameData.getUsername()) / 2;
		} else {
			xUsername = 240 + 120 - fm.stringWidth(gameData.getUsername()) / 2;
		}

		gTemp.drawString(gameData.getUsername() + "!", xUsername, 160);
	}

	protected void proceed() {
		super.proceed();
		GuessWordPanel guessPanel = new GuessWordPanel(gameData);
		frame.add(guessPanel);
		guessPanel.jbtGuessChar.requestFocus();
	}
}
