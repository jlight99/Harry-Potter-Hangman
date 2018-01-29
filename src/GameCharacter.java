class GameCharacter {
	private String name;
	private String house;
	private String gender;

	GameCharacter(String name, String house, String gender) {
		this.name = name;
		this.house = house;
		this.gender = gender;
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