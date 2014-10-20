import java.util.*;

import javax.smartcardio.Card;
public class CardSet
{
	private int score;
	private ArrayList<Card> cardSet;
	private int handID;
	
	public CardSet()
	{
		cardSet = new ArrayList<Card> ();
		score=0;
		handID=0;
	}
	
	public void Shuffle()
	{
		Collections.shuffle(cardSet);
	}
	
	public void Sort()
	{
		Collections.sort(cardSet, new Comparator<Card>() {
	        @Override public int compare(Card card1, Card card2) {
	            return card1.getNum()-card2.getNum();
	        }
		}
		
		Collections.sort(cardSet, new Comparator<Card>() {
	        @Override public int compare(Card card1, Card card2) {
	            return card1.getColor()-card2.getColor();
	        }
		}
		
	}
	
	public void addCard(Card newCard)
	{
		cardSet.add(newCard);
	}
	
	public Card getCard(int index)
	{
		return cardSet.get(index);
	}
	
	public void setID(int newID)
	{
		handID = newID;
	}
	
	public int getID()
	{
		return handID;
	}
	
	public void calcScore()
	{
		for(int i=0;i<cardSet.size();i++)
		{
			switch(cardSet.get(i).cardNum)
			{
			case (1):
				score+=15;
				break;
			case (5):
				score+=5;
				break;
			case (10):
				score+=10;
				break;
			case (14):
				score+=10;
				break;
			case (10.5):
				score+=20;
				break
			default:
				break;
			}
		}
	}
	
	public int getScore()
	{
		return score;
	}
	
	
}