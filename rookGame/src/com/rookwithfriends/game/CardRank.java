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
	
	//This method preserves the Enum Type -- Created by Ethan, and Josh -- Refactor when not Cmd Line Based
	 public static CardRank returnRank(double val)
		{
			int num;
			
		 	CardRank theValue = null;
			if (val == 10.5){
				 num = 15;
			}
			else{
				 num = (int) val;
			}
			
		  switch (num) {
            case 1:  theValue = one;
                     break;
            case 2:  theValue = two;
                     break;
            case 3:  theValue = three;
                     break;
            case 4:  theValue = four;
                     break;
            case 5:  theValue = five;
                     break;
            case 6:  theValue = six;
                     break;
            case 7:  theValue = seven;
                     break;
            case 8:  theValue = eight;
                     break;
            case 9:  theValue = nine;
                     break;
            case 10: theValue = ten;
                     break;
            case 11: theValue = eleven;
                     break;
            case 12: theValue = thirteen;
                     break;
            case 13: theValue = fourteen;
            	     break;
            case 14: theValue = fifteen;
            		 break;
            case 15: theValue = rook;
   		 			 break;
        }
}
