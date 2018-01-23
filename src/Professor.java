class Professor extends Professional {
	private String subject;
	private boolean headOfHouse;

	public Professor(String name, String house, String gender, String position, String subject, boolean headOfHouse) {
		super(name, house, gender, position);
		this.subject = subject;
		this.headOfHouse = headOfHouse;
	}

	public String getSubject() {
		return subject;
	}

	public boolean getHead() {
		return headOfHouse;
	}
}