class Student extends GameCharacter {
	private int year;

	public Student(String name, String house, String gender, int year) {
		super(name, house, gender);
		this.year = year;
	}

	public int getYear() {
		return year;
	}
	
	public String getQuidditchHint() {
		return "Hint: this person does not play Quidditch";
	}
}