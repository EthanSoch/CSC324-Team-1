package com.rookwithfriends.game;

public class Card {
	CardColor color;
	CardRank rank;
	int id;
	
	public Card(CardColor color, CardRank rank, int id){
		this.color = color;
		this.rank = rank;
		this.id = id;
	}

	public CardColor getColor() {
		return color;
	}

	public void setColor(CardColor color) {
		this.color = color;
	}

	public CardRank getRank() {
		return rank;
	}

	public void setRank(CardRank rank) {
		this.rank = rank;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	@Override
	public boolean equals(Object card){
		if(!(card instanceof Card))
			
			return  false;
		else
		{
			Card objCard = (Card)card;
			return color == objCard.getColor() && rank == objCard.getRank();
		}
	}
	
	
	public String toString()
	{
		String temp = "";
		if(color.name()=="white")
		{
			temp+="Rook";
		}
		else
		{
			temp+=color.name();
			temp+=" ";
			if(rank.getValue()==15)
			{
				temp+="1";
			}
			else
			{
				temp+=rank.getValue();
			}
		}
		
		return temp; 
	}
}
