class Character {
	private String name;
	private String house;
	private String gender;

	Character(String n, String h, String g) {
		name = n;
		house = h;
		gender = g;
	}

	String getName() {
		return this.name;
	}

	String getHouse() {
		return this.house;
	}

	String getGender() {
		return this.gender;
	}
}