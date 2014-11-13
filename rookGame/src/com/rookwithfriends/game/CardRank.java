package com.rookwithfriends.game;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "CardRank")
@XmlEnum
public enum CardRank {
	@XmlEnumValue("1") one(15),
	@XmlEnumValue("5") five(5),
	@XmlEnumValue("6") six(6),
	@XmlEnumValue("7") seven(7),
	@XmlEnumValue("8") eight(8),
	@XmlEnumValue("9") nine(9),
	@XmlEnumValue("10") ten(10),
	@XmlEnumValue("11") eleven(11),
	@XmlEnumValue("12") twelve(12),
	@XmlEnumValue("13") thirteen(13),
	@XmlEnumValue("14") fourteen(14),
	@XmlEnumValue("10.5") rook(10.5);
	
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
			//I don't know if this has been tested, but if there are errors with just one,
			//it might have to be CardRank.one
			  
	            case 1:  theValue = one;
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
	            case 15: theValue = rook;
	   		 			 break;
	        }
	  return theValue;
	}
	 
	 
}// End of Class
