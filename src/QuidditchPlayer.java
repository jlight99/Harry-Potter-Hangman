class QuidditchPlayer extends Student {
	private String quidPosition;

	public QuidditchPlayer(String name, String house, String gender, int year, String quidPosition) {
		super(name, house, gender, year);//is this call necessary? won't it automatically call super?
		this.quidPosition = quidPosition;
	}

	public String getQuidPosition() {
		return quidPosition;
	}
	
	@Override
	public String getQuidditchHint() {
		return "Hint: this person plays " + quidPosition + " for their House team";
	}
}