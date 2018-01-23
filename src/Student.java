class Student extends Character {
	private int year;

	public Student(String name, String house, String gender, int year) {
		super(name, house, gender);
		this.year = year;
	}

	public int getYear() {
		return year;
	}
}