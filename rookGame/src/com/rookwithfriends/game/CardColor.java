package com.rookwithfriends.game;

public enum CardColor{
	red(0),
	black(1),
	yellow(2),
	green(3),
	white(4);
	
	private int value;    
	
	private CardColor(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
}
