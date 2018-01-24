import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;

class GuessWordPanel extends GamePanel {
	private Graphics gTemp;
	private Image parchmentImage = new ImageIcon("resources/Parchment.jpg").getImage();
	public static GameCharacter chosenOne; 
	private String word;
	private int wordLength;
	private int spaceIdx;
	private Letter[] letters;
	ArrayList<Character> guessedBank = new ArrayList<Character>();
	ArrayList<Character> wrongGuessedBank = new ArrayList<Character>();
	private boolean guessed;
	private int totalGuesses;
	private int wrongGuesses;
	public static final int INITIAL = 0;
	public static final int BEFORE_GUESSING = 1;
	public static final int WHILE_GUESSING = 2;
	public static final int ALL_GUESSED = 3;
	private int paintOption = INITIAL;
	JButton jbtOK;
	JButton jbtGuessChar = new JButton("Guess anyone!");
	JButton jbtGuessMate = new JButton("Guess a Housemate!");
	//JButton jbtGuessStud = new JButton("Guess a student!");
	//JButton jbtGuessProf = new JButton("Guess a professor!");
	JButton jbtGuessQuid = new JButton("Guess a Quidditch player!");
	private String guess;
	private int guessIdx;
	private String message;
	private boolean guessesLeft = true;
	public static boolean allGuessed = false;
	public static int points = 0;
	public static int guessMode = 0;
	public static final int GUESS_CHARACTER = 0;
	public static final int GUESS_HOUSEMATE = 1;
	//public static final int GUESS_STUDENT = 2;
	//public static final int GUESS_PROFESSOR = 3;
	public static final int GUESS_QUIDDITCHPLAYER = 4;
	JButton jbtProceed = new JButton();
	int idx = 0;
	boolean repeatedGuess = false;

	public GuessWordPanel() {
		setLayout(null);

		jbtGuessChar.setBounds(172, 135, 160, 31);
		jbtGuessMate.setBounds(150, 180, 196, 31);
		//jbtGuessStud.setBounds(150, 180, 120, 31);
		//jbtGuessProf.setBounds(100, 220, 120, 31);
		jbtGuessQuid.setBounds(132, 225, 235, 31);
		
		add(jbtGuessChar);
		add(jbtGuessMate);
		//add(jbtGuessStud);
		//add(jbtGuessProf);
		add(jbtGuessQuid);

		jbtGuessChar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				guessCharPressed();
			}
		});

		jbtGuessChar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					guessCharPressed();
				}
			}
		});
	
		jbtGuessMate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				guessMatePressed();
			}
		});

		jbtGuessMate.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					guessMatePressed();
				}
			}
		});
		/*
		jbtGuessStud.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				guessStudPressed();
			}
		});

		jbtGuessStud.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					guessStudPressed();
				}
			}
		});
		
		jbtGuessProf.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				guessProfPressed();
			}
		});

		jbtGuessProf.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					guessProfPressed();
				}
			}
		});
		*/
		jbtGuessQuid.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				guessQuidPressed();
			}
		});

		jbtGuessQuid.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					guessQuidPressed();
				}
			}
		});
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		gTemp = g.create();
		gTemp.drawImage(parchmentImage, 0, 0, this);
	
		if (guessesLeft == false) {
			proceed();
		} else if (paintOption == INITIAL) {
			drawBackground(gTemp);
		} else if (paintOption == BEFORE_GUESSING) {
			drawPrompt(gTemp);
			drawPromptInteraction(gTemp);
		} else if (paintOption == WHILE_GUESSING) {
			drawPrompt(gTemp);
			drawPromptInteraction(gTemp);
			drawGuessedLetters(gTemp);
			drawWrongGuesses(gTemp);
			
			if (wrongGuessedBank.size() >= 3) {
				drawHint(gTemp);
			}
			
		} else if (paintOption == ALL_GUESSED) {
			drawPrompt(gTemp);
			addProceedButton();
			drawGuessedLetters(gTemp);
			drawWrongGuesses(gTemp);
		}
	}

	public void drawBackground(Graphics gTemp) {
		gTemp.setFont(new Font("TimesRoman", Font.ITALIC, 20));

		String time = "Time to meet everyone!";
		String alreadyKnow = "Let's see how many people you already know...";

		FontMetrics fm = gTemp.getFontMetrics();
		int x1 = 250 - fm.stringWidth(time) / 2;
		int y1 = 50;
		int x2 = 250 - fm.stringWidth(alreadyKnow) / 2;
		int y2 = 80;

		gTemp.drawString(time, x1, y1);
		gTemp.drawString(alreadyKnow, x2, y2);
	}

	public void vanishButtons() {
		jbtGuessChar.setVisible(false);
		jbtGuessMate.setVisible(false);
		//jbtGuessStud.setVisible(false);
		//jbtGuessProf.setVisible(false);
		jbtGuessQuid.setVisible(false);
	}
	
	public void getWord() {
		chosenOne = getChosenOne();
		word = chosenOne.getName().toUpperCase();
		wordLength = word.length();
		letters = new Letter[wordLength];
		
		for (int i = 0; i < wordLength; i++) {
			char currentChar = word.charAt(i);

			if (currentChar == ' ') {
				spaceIdx = i;
			} 
			
			letters[i] = new Letter(currentChar, i);
		}
	}
	
	public void guessCharPressed() {
		guessMode = GUESS_CHARACTER;
		getWord();
		vanishButtons();
		paintOption = BEFORE_GUESSING;
		repaint();
	}
	
	public void guessMatePressed() {
		guessMode = GUESS_HOUSEMATE;
		getWord();
		vanishButtons();
		paintOption = BEFORE_GUESSING;
		repaint();
	}
/*	
	public void guessStudPressed() {
		guessMode = GUESS_STUDENT;
		getWord();
		vanishButtons();
		paintOption = BEFORE_GUESSING;
		repaint();
	}
	
	public void guessProfPressed() {
		guessMode = GUESS_PROFESSOR;
		getWord();
		vanishButtons();
		paintOption = BEFORE_GUESSING;
		repaint();		
	}
*/
	public void guessQuidPressed() {
		guessMode = GUESS_QUIDDITCHPLAYER;
		getWord();
		vanishButtons();
		paintOption = BEFORE_GUESSING;
		repaint();
	}
	
	public void drawPrompt(Graphics gTemp) {
		gTemp.setFont(new Font("TimesRoman", Font.ITALIC, 20));

		drawGuessMessage();
		drawLetterSlots();	
	}
	
	public void drawPromptInteraction(Graphics gTemp) {
		JTextField jtfGuess = new JTextField();
		JButton jbtGuess = new JButton();
		
		addGuessTextfield(jtfGuess);
		addGuessButton(jbtGuess, jtfGuess);
		
		jtfGuess.requestFocus();
	}
	
	public void drawGuessMessage() {
		FontMetrics fm = gTemp.getFontMetrics();
		
		String guess = "Can you guess the name of this young " + GuessWordPanel.chosenOne.getGender() + "?";
		int x1 = 250 - fm.stringWidth(guess) / 2;
		int y1 = 65;

		gTemp.drawString(guess, x1, y1);
	}
	
	public void drawLetterSlots() {
		int x2 = 500 / 2 - 19 * wordLength + 9 * (wordLength - 1) / 2 + 8;
		int y2 = 136;		

		for (int j = 0; j < wordLength; j++) {
			if (j == spaceIdx) {
				x2 += 14;
			} else {
				gTemp.drawLine(x2, y2, x2 + 19, y2);
				letters[j].setX(x2 + 3);
				letters[j].setY(y2 - 2);

				x2 += 29;
			}
		}
	}
	
	public void addGuessTextfield(JTextField jtfGuess) {
		jtfGuess.setText("Enter your guess here!");
		jtfGuess.setBounds(100, 230, 200, 30);
		
		add(jtfGuess);

		jtfGuess.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					GuessWordPanel.this.guess = jtfGuess.getText();
					GuessWordPanel.this.verifyGuess();
				}
			}
		});
	}
	
	public void addGuessButton(JButton jbtGuess, JTextField jtfGuess) {
		jbtGuess.setText("Guess!");
		jbtGuess.setBounds(300, 230, 100, 30);

		add(jbtGuess);

		jbtGuess.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GuessWordPanel.this.guess = jtfGuess.getText();
				GuessWordPanel.this.verifyGuess();
			}
		});
	}
	
	public void addProceedButton() {
		jbtProceed.setText("I know I am");
		jbtProceed.setBounds(240, 160, 139, 27);
		
		add(jbtProceed);
		
		jbtProceed.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GuessWordPanel.this.proceed();
			}
		});
	}

	public void updateGuessedBank(Character c) {
		int i;
		
		for (i = 0; i < guessedBank.size(); i++) {
			if (guessedBank.get(i).charValue() == c.charValue()) {
				repeatedGuess = true;
				break;
			}
		}
		
		if (!repeatedGuess || guessedBank.size() == 0) {
			guessedBank.add(c);
		}
	}
	
	public void verifyGuess() {
		totalGuesses++;
		guessed = false;
		guess = guess.toUpperCase();
		if (guess.equals(word)) {
			for (int i = 0; i < wordLength; i++) {
				letters[i].setGuessed(true);
			}
			message = "Impressive!";
			allGuessed = true;
	
			paintOption = ALL_GUESSED;
			repaint();
		} else {
			char guessedChar = guess.charAt(0);
			updateGuessedBank(new Character(guessedChar));
			
			if (repeatedGuess) {
				message = "You've already guessed this!";
				repaint();
				repeatedGuess = false;//reset value of repeatedGuess
			} else {
				int i;
				for (i = 0; i < wordLength; i++) {
					if (guessedChar == letters[i].getChar()) {
						letters[i].setGuessed(true);
						guessed = true;
					}
				}
	
				if (guessed) {
					message = "Nice one!";
				} else {
					wrongGuessedBank.add(new Character(guessedChar));
					message = "That is incorrect";
					wrongGuesses++;
				}	
	
				checkAllGuessed();
			}
			paintOption = WHILE_GUESSING;
			repaint();
		}
	}

	public void checkAllGuessed() {
		int j;
		for (j = 0; j < wordLength; j++) {
			if (letters[j].getGuessed() == false && j != spaceIdx) {
				break;
			}
		}

		if (j == wordLength) {
			allGuessed = true;
			proceed();
		}
	} 

	public void drawGuessedLetters(Graphics gTemp) {
		FontMetrics fm = gTemp.getFontMetrics();
		int x1 = getWidth() / 2 - fm.stringWidth(message) / 2;
		
		if (allGuessed) {
			x1 -= 80;
		}

		gTemp.drawString(message, x1, 180);

		Graphics gTemp2 = gTemp.create();
		gTemp2.setFont(new Font("TimesRoman", 0, 20));
		for (int i = 0; i < wordLength; i++) {
			if (letters[i].getGuessed() == true) {
				gTemp2.drawString(letters[i].getChar() + "", letters[i].getX(), letters[i].getY());
			}
		}
	}

	public void drawHint(Graphics gTemp) {
		gTemp.setFont(new Font("TimesRoman", Font.ITALIC, 16));
		FontMetrics fm = gTemp.getFontMetrics();
		
		String hintMessage = ((Student) chosenOne).getQuidditchHint();
		int x1 = getWidth() / 2 - fm.stringWidth(hintMessage) / 2;
		
		gTemp.drawString(hintMessage, x1, 100);
	}
	
	public void drawWrongGuesses(Graphics gTemp) {
		int x = 136;
		int y = 217;
		for (int i = 0; i < wrongGuessedBank.size(); i++) {
			gTemp.setColor(Color.BLACK);
			gTemp.drawString(wrongGuessedBank.get(i).charValue() + "", x, y);
			gTemp.setColor(Color.RED);
			gTemp.drawLine(x - 2, y - 10, x + 15, y - 10);
			x += 32;
		}
	
		gTemp.setColor(Color.BLACK);
		
		switch (wrongGuessedBank.size()) {
			case 1:
				gTemp.drawOval(442, 150, 30, 30);
				break;
			case 2:
				gTemp.drawOval(442, 150, 30, 30);
				gTemp.drawLine(457, 180, 457, 211);
				break;
			case 3:
				gTemp.drawOval(442, 150, 30, 30);
				gTemp.drawLine(457, 180, 457, 211);
				gTemp.drawLine(440, 183, 457, 198);
				break;
			case 4:
				gTemp.drawOval(442, 150, 30, 30);
				gTemp.drawLine(457, 180, 457, 211);
				gTemp.drawLine(440, 183, 457, 198);
				gTemp.drawLine(457, 198, 474, 183);
				break;
			case 5:
				gTemp.drawOval(442, 150, 30, 30);
				gTemp.drawLine(457, 180, 457, 211);
				gTemp.drawLine(440, 183, 457, 198);
				gTemp.drawLine(457, 198, 474, 183);
				gTemp.drawLine(457, 211, 440, 225);
				break;
			case 6:
				gTemp.drawOval(442, 150, 30, 30);
				gTemp.drawLine(457, 180, 457, 211);
				gTemp.drawLine(440, 183, 457, 198);
				gTemp.drawLine(457, 198, 474, 183);
				gTemp.drawLine(457, 211, 440, 225);
				gTemp.drawLine(457, 211, 474, 225);
				break;
			case 7:
				gTemp.drawOval(442, 150, 30, 30);
				gTemp.drawLine(457, 180, 457, 211);
				gTemp.drawLine(440, 183, 457, 198);
				gTemp.drawLine(457, 198, 474, 183);
				gTemp.drawLine(457, 211, 440, 225);
				gTemp.drawLine(457, 211, 474, 225);
				gTemp.drawLine(440, 183, 435, 170);
				guessesLeft = false;
		}	
	}

	public void proceed() {
		super.proceed();
		PlayAgainPanel playAgainPanel = new PlayAgainPanel();
		frame.add(playAgainPanel);
	}	

	public GameCharacter getChosenOne() {
		ArrayList<GameCharacter> characterBank = new ArrayList<GameCharacter>();
		GameCharacter cChang = new QuidditchPlayer("Cho Chang", "Ravenclaw", "witch", 2, "Seeker");
		characterBank.add(cChang);
		GameCharacter lLovegood = new Student("Luna Lovegood", "Ravenclaw", "witch", 1);
		characterBank.add(lLovegood);
		GameCharacter tBoot = new Student("Terry Boot", "Ravenclaw", "wizard", 1);
		characterBank.add(tBoot);
		GameCharacter mCorner = new Student("Michael Corner", "Ravenclaw", "wizard", 1);
		characterBank.add(mCorner);
		GameCharacter rDavies = new QuidditchPlayer("Roger Davies", "Ravenclaw", "wizard", 3, "Chaser");
		characterBank.add(rDavies);
		GameCharacter hPotter = new QuidditchPlayer("Harry Potter", "Gryffindor", "wizard", 1, "Seeker");
		characterBank.add(hPotter);
		GameCharacter rWeasley = new QuidditchPlayer("Ron Weasley", "Gryffindor", "wizard", 1, "Keeper");
		characterBank.add(rWeasley);
		GameCharacter hGranger = new Student("Hermione Granger", "Gryffindor", "witch", 1);
		characterBank.add(hGranger);
		GameCharacter ginWeasley = new QuidditchPlayer("Ginny Weasley", "Gryffindor", "witch", 0, "Chaser");
		characterBank.add(ginWeasley);
		GameCharacter nLongbottom = new Student("Neville Longbottom", "Gryffindor", "wizard", 1);
		characterBank.add(nLongbottom);
		GameCharacter kBell = new QuidditchPlayer("Katie Bell", "Gryffindor", "witch", 2, "Chaser");
		characterBank.add(kBell);
		GameCharacter aSpinnet = new QuidditchPlayer("Alicia Spinnet", "Gryffindor", "witch", 3, "Chaser");
		characterBank.add(aSpinnet);
		GameCharacter aJohnson = new QuidditchPlayer("Angelina Johnson", "Gryffindor", "witch", 3, "Chaser");
		characterBank.add(aJohnson);
		GameCharacter oWood = new QuidditchPlayer("Oliver Wood", "Gryffindor", "wizard", 5, "Chaser");
		characterBank.add(oWood);
		GameCharacter fWeasley = new QuidditchPlayer("Fred Weasley", "Gryffindor", "wizard", 3, "Beater");
		characterBank.add(fWeasley);
		GameCharacter geoWeasley = new QuidditchPlayer("George Weasley", "Gryffindor", "wizard", 3, "Beater");
		characterBank.add(geoWeasley);
		GameCharacter pWeasley = new Student("Percy Weasley", "Gryffindor", "wizard", 5);
		characterBank.add(pWeasley);
		GameCharacter eMacmillan = new Student("Ernie Macmillan", "Hufflepuff", "wizard", 1);
		characterBank.add(eMacmillan);
		GameCharacter hAbbott = new Student("Hannah Abbott", "Hufflepuff", "witch", 1);
		characterBank.add(hAbbott);
		GameCharacter sBones = new Student("Susan Bones", "Hufflepuff", "witch", 1);
		characterBank.add(sBones);
		GameCharacter cDiggory = new QuidditchPlayer("Cedric Diggory", "Hufflepuff", "wizard", 4, "Seeker");
		characterBank.add(cDiggory);
		GameCharacter zSmith = new QuidditchPlayer("Zacharias Smith", "Hufflepuff", "wizard", 1, "Chaser");
		characterBank.add(zSmith);
		GameCharacter dMalfoy = new QuidditchPlayer("Draco Malfoy", "Slytherin", "wizard", 1, "Seeker");
		characterBank.add(dMalfoy);
		GameCharacter pParkinson = new Student("Pansy Parkinson", "Slytherin", "witch", 1);
		characterBank.add(pParkinson);
		GameCharacter aGreengrass = new Student("Astoria Greengrass", "Slytherin", "witch", 0);
		characterBank.add(aGreengrass);
		GameCharacter tNott = new Student("Theodore Nott", "Slytherin", "wizard", 1);
		characterBank.add(tNott);
		GameCharacter bZabini = new Student("Blaise Zabini", "Slytherin", "wizard", 1);
		characterBank.add(bZabini);
		GameCharacter mFlint = new QuidditchPlayer("Marcus Flint", "Slytherin", "wizard", 6, "Chaser");
		characterBank.add(mFlint);
		
		ArrayList<GameCharacter> gryffindorBank = new ArrayList<GameCharacter>();
		ArrayList<GameCharacter> hufflepuffBank = new ArrayList<GameCharacter>();
		ArrayList<GameCharacter> slytherinBank = new ArrayList<GameCharacter>();
		ArrayList<GameCharacter> ravenclawBank = new ArrayList<GameCharacter>();
		ArrayList<QuidditchPlayer> quidditchBank = new ArrayList<QuidditchPlayer>();

		for (int i = 0; i < characterBank.size(); i++) {
			GameCharacter currentCharacter = characterBank.get(i);
			if (currentCharacter.getHouse().equals("Gryffindor")) {
				gryffindorBank.add(currentCharacter);
			} else if (currentCharacter.getHouse().equals("Hufflepuff")) {
				hufflepuffBank.add(currentCharacter);
			} else if (currentCharacter.getHouse().equals("Ravenclaw")) {
				ravenclawBank.add(currentCharacter);
			} else if (currentCharacter.getHouse().equals("Slytherin")) {
				slytherinBank.add(currentCharacter);
			}

			if (currentCharacter instanceof QuidditchPlayer) {
				quidditchBank.add((QuidditchPlayer) currentCharacter);
			}
		}

		if (guessMode == GUESS_CHARACTER) {
			return characterBank.get((int) (Math.random() * characterBank.size()));
		} else if (guessMode == GUESS_HOUSEMATE) {
			if (SortingPanel.userHouse.equals("GRYFFINDOR")) {
				return gryffindorBank.get((int) (Math.random() * gryffindorBank.size()));
			} else if (SortingPanel.userHouse.equals("HUFFLEPUFF")) {
				return hufflepuffBank.get((int) (Math.random() * hufflepuffBank.size()));
			} else if (SortingPanel.userHouse.equals("RAVENCLAW")) {
				return ravenclawBank.get((int) (Math.random() * ravenclawBank.size()));
			} else if (SortingPanel.userHouse.equals("SLYTHERIN")) {
				return slytherinBank.get((int) (Math.random() * slytherinBank.size()));
			} else {
				return characterBank.get(0);
			}
		} else if (guessMode == GUESS_QUIDDITCHPLAYER) {
			return quidditchBank.get((int) (Math.random() * quidditchBank.size()));
		} else {
			return characterBank.get(0);
		}
	}
}
