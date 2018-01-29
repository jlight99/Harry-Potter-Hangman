import java.util.ArrayList;

class GameData {
	private String username;
	private String userHouse;
	private GameCharacter chosenOne;
	private boolean allGuessed;
	private int guessMode = 0;
	public static final int GUESS_CHARACTER = 0;
	public static final int GUESS_HOUSEMATE = 1;
	//public static final int GUESS_STUDENT = 2;
	//public static final int GUESS_PROFESSOR = 3;
	public static final int GUESS_QUIDDITCHPLAYER = 4;
	private int points = 0;
	
	void setUsername(String username) {
		this.username = username;
	}
	
	String getUsername() {
		return this.username;
	}
	
	void setUserHouse(String userHouse) {
		this.userHouse = userHouse;
	}
	
	String getUserHouse() {
		return this.userHouse;
	}
	
	void setChosenOne(GameCharacter chosenOne) {
		this.chosenOne = chosenOne;
	}
	
	GameCharacter getChosenOne() {
		return this.chosenOne;
	}
	
	void setAllGuessed(boolean allGuessed) {
		this.allGuessed = allGuessed;
	}
	
	boolean getAllGuessed() {
		return this.allGuessed;
	}
	
	void setGuessMode(int guessMode) {
		this.guessMode = guessMode;
	}
	
	int getGuessMode() {
		return this.guessMode;
	}
	
	void setPoints(int points) {
		this.points = points;
	}
	
	int getPoints() {
		return this.points;
	}
	
	GameCharacter determineChosenOne() {
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
