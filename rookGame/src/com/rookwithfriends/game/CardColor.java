package com.rookwithfriends.game;

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
	
	//This method preserves the Enum Type -- Created by Ethan, and Josh -- Refactor when not Cmd Line Based
		public static CardColor returnColor(String color)
		{
			CardColor theColor = null;
			
			if (color.equals("RED")){
				theColor = red;
			}
			else if(color.equals("BLACK")){
				theColor = black;
			}
			else if(color.equals("YELLOW")){
				theColor = yellow;
			}
			else if(color.equals("GREEN")){
				theColor = green;
			}
			return theColor;
		}
}
