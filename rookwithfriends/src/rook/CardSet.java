package rook;

import java.util.*;

public class CardSet {
	private ArrayList<Card> cardSet;
	private int handID;

	public CardSet() {
		cardSet = new ArrayList<Card>();
		handID = 0;
	}

	public void Shuffle() {
		Collections.shuffle(cardSet);
	}
	
	//Sort the cards according to color and number
	//Only should be used for ordering hands without regards to a specific color
	//For ordering with regard to a trump color, use findWinningCard(CardColor card)
	public void Sort() {
		//use a custom comparator to compare cards
		Collections.sort(cardSet, new Comparator<Card>() {
			@Override
			public int compare(Card card1, Card card2) {
				//if colors are the same, compare numbers
				if(card1.getColor().getValue()==card2.getColor().getValue())
				{
					if(card1.getRank().getValue() - card2.getRank().getValue() <0)
					{
						return -1;
					}
					else if(card1.getRank().getValue() - card2.getRank().getValue() ==0)
					{
						return 0;
					}
					else
					{
						return 1;
					}
				}
				else
				//if colors are different, compare colors
				{
					if(card1.getColor().getValue() - card2.getColor().getValue() <0)
					{
						return -1;
					}
					else if(card1.getColor().getValue() - card2.getColor().getValue() ==0)
					{
						return 0;
					}
					else
					{
						return 1;
					}
				}
			}
		});
	}

	public void addCard(Card newCard) {
		cardSet.add(newCard);
	}

	public Card getCard(int index) {
		return cardSet.get(index);
	}

	public void setID(int newID) {
		handID = newID;
	}

	public int getID() {
		return handID;
	}

	public int calcScore() {
		int score = 0;

		for (int i = 0; i < cardSet.size(); i++) {
			switch (cardSet.get(i).getRank()) {
			case one:
				score += 15;
				break;
			case five:
				score += 5;
				break;
			case ten:
				score += 10;
				break;
			case fourteen:
				score += 10;
				break;
			case rook:
				score += 20;
				break;
			default:
				break;
			}
		}

		return score;
	}

	public int size() {
		return cardSet.size();
	}
	
	//finds the highest card of a cardset with regards to a trump color
	public Card findWinningCard(CardColor trump)
	{
		Card highCard = cardSet.get(0);
		for (Card currentCard : cardSet)
		{
			if(highCard.getColor().getValue()==trump.getValue() && currentCard.getColor().getValue() == trump.getValue())
			{
				if(highCard.getRank().getValue() < currentCard.getRank().getValue())
					highCard=currentCard;
			}
			else if(highCard.getColor().getValue()!=trump.getValue() && currentCard.getColor().getValue() == trump.getValue())
				highCard= currentCard;
			else
			{
				if(highCard.getColor().getValue()==trump.getValue() && currentCard.getColor().getValue() == trump.getValue())
				{
					if(highCard.getRank().getValue() < currentCard.getRank().getValue())
						highCard= currentCard;
				}
			}
		}
		return highCard;
	}
	
	public int getScore()
	{
		this.calcScore();
		return score;
	}
}
