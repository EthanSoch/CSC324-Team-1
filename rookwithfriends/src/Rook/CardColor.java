package Rook;

public enum CardColor{
	red(0),
	black(1),
	yellow(2),
	green(3);
	
	private int value;    
	
	private CardColor(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
}
