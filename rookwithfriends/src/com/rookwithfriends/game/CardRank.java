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
	public static CardRank returnRank(int val) {
		switch (val) {
		case 1:return one;
		case 5:return five;
		case 6:return six;
		case 7:return seven;
		case 8:return eight;
		case 9:return nine;
		case 10:return ten;
		case 11:return eleven;
		case 12:return thirteen;
		case 13:return fourteen;
		}
		return null;
	}
	 
	public static CardRank returnRank(double val) {
		if(val == 10.5)
			return rook;
		else
			return returnRank((int)val);
	}
}// End of Class
