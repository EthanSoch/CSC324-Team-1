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
}
