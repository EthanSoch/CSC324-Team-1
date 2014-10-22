package Rook;

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

	public void Sort() {
		Collections.sort(cardSet, new Comparator<Card>() {
			@Override
			public int compare(Card card1, Card card2) {
				if(card1.getColor().getValue()==card2.getColor().getValue())
					return card1.getRank().getValue() - card2.getRank().getValue();
				else
					return card1.getColor().getValue()-card2.getRank().getValue();
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
}
