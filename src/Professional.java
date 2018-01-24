class Professional extends GameCharacter {
	private String position;

	public Professional(String name, String house, String gender, String position) {
		super(name, house, gender);
		this.position = position;
	}

	public String getPosition() {
		return position;
	}
}