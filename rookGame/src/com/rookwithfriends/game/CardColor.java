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
	
	//This method preserves the Enum Type -- Created by Ethan, and Josh -- Refactor when not Cmd Line Based
	public static CardColor returnColor(String color)
	{
		CardColor theColor = null;
		
		if (color == "RED"){
			theColor = red;
		}
		else if(color == "BLACK"){
			theColor = black;
		}
		else if(color == "YELLOW"){
			theColor = yellow;
		}
		else if(color == "GREEN"){
			theColor = green;
		}
		else if(color == "WHITE"){
			theColor = white;
		}
		return theColor;
	}
	
	
}//Class End
