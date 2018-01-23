
class Letter {
	private int x;
	private int y;
	private int idx;
	private char c;
	private boolean guessed = false;

	public Letter(char c, int idx) {
		this.c = c;
		this.idx = idx;
	}

	int getX() {
		return this.x;
	}

	void setX(int new_x) {
		this.x = new_x;
	}

	int getY() {
		return this.y;
	}

	void setY(int new_y) {
		this.y = new_y;
	}

	int getIdx() {
		return this.idx;
	}

	void setIdx(int new_idx) {
		this.idx = new_idx;
	}

	char getChar() {
		return this.c;
	}

	void setChar(char new_char) {
		this.c = new_char;
	}

	boolean getGuessed() {
		return this.guessed;
	}

	void setGuessed(boolean new_guessed) {
		this.guessed = new_guessed;
	}
}
