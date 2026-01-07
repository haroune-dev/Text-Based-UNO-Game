package enumTypes;

public enum Number {
	ZERO(0),
	ONE(1),
	TWO(2),
	THREE(3),
	FOUR(4),
	FIVE(5),
	SIX(6),
	SEVEN(7),
	EIGHT(8),
	NINE(9);
	
	private int value;
	Number(int value){
		this.value=value;
	}
	
	@Override
	public String toString() {
		return String.valueOf(value);
	}
}
