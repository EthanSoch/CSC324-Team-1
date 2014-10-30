package com.rookwithfriends.game;

public enum CardRank {
	one(1),
	two(2),
	three(3),
	four(4),
	five(5),
	six(6),
	seven(7),
	eight(8),
	nine(9),
	ten(10),
	eleven(11),
	twelve(12),
	thirteen(13),
	fourteen(14),
	fifteen(15),
	rook(10.5);
	
	private double value;    
	
	private CardRank(double value) {
		this.value = value;
	}
	
	public double getValue() {
		return value;
	}
}
