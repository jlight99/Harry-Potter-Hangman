import java.sql.*;

class GameData {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/HPCHARACTERS?autoReconnect=true&useSSL=false";

	static final String USER = "root";
	static final String PASS = "beaver";

	private Connection conn;

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

	void setConnection(Connection conn) {
		this.conn = conn;
	}

	Connection getConnection() {
		return this.conn;
	}
}
