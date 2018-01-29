import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;

class GuessWordPanel extends GamePanel {
	private Graphics gTemp;
	private Image parchmentImage = new ImageIcon("resources/Parchment.jpg").getImage();
	public static final int INITIAL = 0;
	public static final int BEFORE_GUESSING = 1;
	public static final int WHILE_GUESSING = 2;
	public static final int ALL_GUESSED = 3;
	private int paintOption = INITIAL;
	private JButton jbtOK;
	protected JButton jbtGuessChar = new JButton("Guess anyone!");
	private JButton jbtGuessMate = new JButton("Guess a Housemate!");
	//JButton jbtGuessStud = new JButton("Guess a student!");
	//JButton jbtGuessProf = new JButton("Guess a professor!");
	private JButton jbtGuessQuid = new JButton("Guess a Quidditch player!");
	private JButton jbtProceed = new JButton();
	
	private String word;
	private int wordLength;
	private int spaceIdx;
	private Letter[] letters;
	ArrayList<Character> guessedBank = new ArrayList<Character>();
	ArrayList<Character> wrongGuessedBank = new ArrayList<Character>();
	private boolean guessed;
	private int totalGuesses;
	private int wrongGuesses;
	private String guess;
	private String message;
	private boolean guessesLeft = true;
	private boolean repeatedGuess = false;

	GuessWordPanel(GameData gameData) {
		super(gameData);

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

	private void drawBackground(Graphics gTemp) {
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

	private void vanishButtons() {
		jbtGuessChar.setVisible(false);
		jbtGuessMate.setVisible(false);
		//jbtGuessStud.setVisible(false);
		//jbtGuessProf.setVisible(false);
		jbtGuessQuid.setVisible(false);
	}
	
	private void getWord() {
		gameData.setChosenOne(gameData.determineChosenOne());
		word = gameData.getChosenOne().getName().toUpperCase();
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
	
	private void guessCharPressed() {
		gameData.setGuessMode(gameData.GUESS_CHARACTER);
		getWord();
		vanishButtons();
		paintOption = BEFORE_GUESSING;
		repaint();
	}
	
	private void guessMatePressed() {
		gameData.setGuessMode(gameData.GUESS_HOUSEMATE);
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
	private void guessQuidPressed() {
		gameData.setGuessMode(gameData.GUESS_QUIDDITCHPLAYER);
		getWord();
		vanishButtons();
		paintOption = BEFORE_GUESSING;
		repaint();
	}
	
	private void drawPrompt(Graphics gTemp) {
		gTemp.setFont(new Font("TimesRoman", Font.ITALIC, 20));

		drawGuessMessage();
		drawLetterSlots();	
	}
	
	private void drawPromptInteraction(Graphics gTemp) {
		JTextField jtfGuess = new JTextField();
		JButton jbtGuess = new JButton();
		
		addGuessTextfield(jtfGuess);
		addGuessButton(jbtGuess, jtfGuess);
		
		jtfGuess.requestFocus();
	}
	
	private void drawGuessMessage() {
		FontMetrics fm = gTemp.getFontMetrics();
		
		String guess = "Can you guess the name of this young " + gameData.getChosenOne().getGender() + "?";
		int x1 = 250 - fm.stringWidth(guess) / 2;
		int y1 = 65;

		gTemp.drawString(guess, x1, y1);
	}
	
	private void drawLetterSlots() {
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
	
	private void addGuessTextfield(JTextField jtfGuess) {
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
	
	private void addGuessButton(JButton jbtGuess, JTextField jtfGuess) {
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
	
	private void addProceedButton() {
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

	private void updateGuessedBank(Character c) {
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
	
	private void verifyGuess() {
		totalGuesses++;
		guessed = false;
		guess = guess.toUpperCase();
		if (guess.equals(word)) {
			for (int i = 0; i < wordLength; i++) {
				letters[i].setGuessed(true);
			}
			message = "Impressive!";
			gameData.setAllGuessed(true);
	
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

	private void checkAllGuessed() {
		int j;
		for (j = 0; j < wordLength; j++) {
			if (letters[j].getGuessed() == false && j != spaceIdx) {
				break;
			}
		}

		if (j == wordLength) {
			gameData.setAllGuessed(true);
			proceed();
		}
	} 

	private void drawGuessedLetters(Graphics gTemp) {
		FontMetrics fm = gTemp.getFontMetrics();
		int x1 = getWidth() / 2 - fm.stringWidth(message) / 2;
		
		if (gameData.getAllGuessed()) {
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

	private void drawHint(Graphics gTemp) {
		gTemp.setFont(new Font("TimesRoman", Font.ITALIC, 16));
		FontMetrics fm = gTemp.getFontMetrics();
		
		String hintMessage = ((Student) gameData.getChosenOne()).getQuidditchHint();
		int x1 = getWidth() / 2 - fm.stringWidth(hintMessage) / 2;
		
		gTemp.drawString(hintMessage, x1, 100);
	}
	
	private void drawWrongGuesses(Graphics gTemp) {
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

	protected void proceed() {
		super.proceed();
		PlayAgainPanel playAgainPanel = new PlayAgainPanel(gameData);
		frame.add(playAgainPanel);
	}	
}
