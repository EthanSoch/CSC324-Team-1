package com.rookwithfriends.game;

import java.io.Serializable;
import java.io.StringWriter;
import java.util.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlRootElement;

import org.eclipse.persistence.jaxb.MarshallerProperties;


public class CardSet extends ArrayList<Card>{
	private static final long serialVersionUID = 3456263502636499019L;
	private int handID;

	public CardSet() {
		handID = 0;
	}

	public void Shuffle() {
		Collections.shuffle((List<Card>)this);
	}

	// Sort the cards according to color and number
	// Only should be used for ordering hands without regards to a specific
	// color
	// For ordering with regard to a trump color, use findWinningCard(CardColor
	// card)
	public void Sort() {
		// use a custom comparator to compare cards
		Collections.sort(this, new Comparator<Card>() {
			@Override
			public int compare(Card card1, Card card2) {
				// if colors are the same, compare numbers
				if (card1.getColor().getValue() == card2.getColor().getValue()) {
					if (card1.getRank().getValue() - card2.getRank().getValue() < 0) {
						return -1;
					} else if (card1.getRank().getValue()
							- card2.getRank().getValue() == 0) {
						return 0;
					} else {
						return 1;
					}
				} else
				// if colors are different, compare colors
				{
					if (card1.getColor().getValue()
							- card2.getColor().getValue() < 0) {
						return -1;
					} else if (card1.getColor().getValue()
							- card2.getColor().getValue() == 0) {
						return 0;
					} else {
						return 1;
					}
				}
			}
		});
	}

	public void setID(int newID) {
		handID = newID;
	}

	public int getID() {
		return handID;
	}

	public int getScore() {
		int score = 0;

		for (Card card : this) {
			switch (card.getRank()) {
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

	// finds the highest card of a cardset with regards to a trump color
	public Card findWinningCard(CardColor trump) {
		Card highCard = get(0);
		for (Card currentCard : this) {
			if(currentCard.getColor()==CardColor.white)
			{
				currentCard.setColor(trump);
			}
			if (highCard.getColor().getValue() == trump.getValue() && currentCard.getColor().getValue() == trump.getValue()) {
				if (highCard.getRank().getValue() < currentCard.getRank().getValue()) {
					highCard = currentCard;
				}
			} else if (highCard.getColor().getValue() != trump.getValue() && currentCard.getColor().getValue() == trump.getValue()) {
				highCard = currentCard;
			}
			else if(highCard.getColor().getValue() != trump.getValue() && currentCard.getColor().getValue() != trump.getValue()) {
					if (highCard.getRank().getValue() < currentCard.getRank().getValue()) {
						highCard = currentCard;
					}
			}
		}
		return highCard;
	}
	
	public Card front(){
		return get(0);
	}
	
	public void pop(){
		remove(0);
	}
	
	public String toJSON(){
		
		try{
			// Create a JaxBContext
			JAXBContext jc = JAXBContext.newInstance(Card.class);
	
			// Create the Marshaller Object using the JaxB Context
			Marshaller marshaller = jc.createMarshaller();
			
			// Set the Marshaller media type to JSON or XML
			marshaller.setProperty(MarshallerProperties.MEDIA_TYPE, "application/json");
			
			// Set it to true if you need to include the JSON root element in the JSON output
			marshaller.setProperty(MarshallerProperties.JSON_INCLUDE_ROOT, true);
			
			// Set it to true if you need the JSON output to formatted
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			
			StringWriter stringWriter = new StringWriter();
			
			// Marshal the employee object to JSON and print the output to console
			marshaller.marshal(this, stringWriter);
			
			return stringWriter.toString();
		}
		catch(JAXBException e){
			System.err.println("toJson error\n" + e.getMessage());
		}
		
		return "error";
		
	}
	
	public String toString()
	{
		String string = "";
		for(int i = 0;i<size();i++)
		{
			string+="Card ["+i+"]: "+ get(i).toString() + "\n";
		}
		return string;
	}
	
	
}
