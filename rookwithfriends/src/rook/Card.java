package rook;

public class Card {
	CardColor color;
	CardRank rank;
	int id;
	
	public Card(){
		color=red;
		rank=one;
		id=1;
	}
	
	public Card(CardColor Color, CardRank Rank, int ID){
		color=Color;
		rank=Rank;
		id=ID;
		
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
