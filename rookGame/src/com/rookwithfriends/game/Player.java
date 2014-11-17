package com.rookwithfriends.game;

import java.io.Serializable;
import java.io.StringWriter;
import java.util.Scanner;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import org.eclipse.persistence.jaxb.MarshallerProperties;

@XmlRootElement
public class Player implements Serializable{
	private static final long serialVersionUID = -1161562471614255655L;
	// Declare Class Members//
	private int playerID;
	private int playerBid;
	private int handID;
	private boolean hasPassed;

	// CardSet Objects For Player//
	@XmlElement(name = "hand")
	private CardSet playerHand;
	private CardSet cardsWon;

	public Player(int id) {
		this.playerID = id;
		this.playerHand = new CardSet();
		this.cardsWon = new CardSet();
		this.hasPassed=false;
	}

	public Player() {
		this.playerHand = new CardSet();
		this.cardsWon = new CardSet();
		this.playerID = 0;
		this.hasPassed=false;
	}

	// selectCard(); -- Makes use of CardSet method getCard to return card in
	// playerHand at specific index
	// Should this ask the user for the card?
	public Card selectCard(int index) {

		return this.playerHand.get(index);

	}

	// combineHand(); -- This method allows the user to combine cards from the
	// Kitty into playerHand
	// NEEDS REFACTORING//

	public void combineHand(CardSet theKitty) {
		playerHand.addAll(theKitty);
	}

	// Class Setters and Getters for Player//
	public int getPlayerBid() {
		return playerBid;
	}

	public void setPlayerBid(int playerBid) {
		this.playerBid = playerBid;
	}

	public int getPlayerID() {
		return playerID;
	}

	public void setPlayerID(int playerID) {
		this.playerID = playerID;
	}

	public int getHandID() {
		return handID;
	}

	public void setHandID(int handID) {
		this.handID = handID;
	}

	public CardSet getPlayerHand() {
		return playerHand;
	}

	public void setPlayerHand(CardSet playerHand) {
		this.playerHand = playerHand;
	}

	public CardSet getCardsWon() {
		return cardsWon;
	}

	public void setCardsWon(CardSet cardsWon) {
		this.cardsWon = cardsWon;
	}

	public boolean getHasPassed() {
		return hasPassed;
	}

	public void setHasPassed(boolean hasPassed) {
		this.hasPassed = hasPassed;
	}

	public CardColor setTrump() {
		//Scanner read = new Scanner(System.in);
		String input = null;
		String colorUp;
		boolean correctInput = false;

		// Keep prompting for input until a string has been entered
		do {
			//System.out.println("Please enter the new trump.");
			input = "RED";//read.next();

			colorUp = input.toUpperCase();

			if (colorUp.equals("RED")) {
				correctInput = true;
			} else if (colorUp.equals("BLACK")) {
				correctInput = true;
			} else if (colorUp.equals("YELLOW")) {
				correctInput = true;
			} else if (colorUp.equals("GREEN")) {
				correctInput = true;
			} else if (colorUp.equals("WHITE")) {
				correctInput = true;
			}

		} while (!correctInput);

		// Call Enum method to return the Enum cast of the input
		CardColor trump = CardColor.returnColor(colorUp);

		//read.close();
		return trump;
	}

	public Card chooseCard() {
		Scanner read = new Scanner(System.in);
		int theVal;
		Card theCard = null;
		
		theVal=0;//read.nextInt();
		if(theVal<playerHand.size())
		{
			theCard=playerHand.get(theVal);
		}

		return theCard;
	}

	public int setBid(int currentBid) {
		//Scanner read = new Scanner(System.in);
		/*System.out.println("Current bid is " + currentBid + ".\n");
		System.out.println("Enter 1 to increase, enter 2 to pass.\n");

		int answer = 1;//read.nextInt();*/
		if (currentBid != 0) {
			//System.out.println("What is your new bid. Must be a multiple of 5.\n");

			int bid = currentBid+5;//read.nextInt();
			if (bid % 5 == 0 && bid > currentBid) {
				currentBid = bid;
				//System.out.println("Thank you, your bid has been set. New bid is: "
								//+ currentBid + "\n");
			}
		}
		else //If currentBid == 0 then the bid was "passed"
		{
			hasPassed=true;
		}
		// read.close();
		return currentBid;
	}

	public void printHand() {
		System.out.println(playerHand);
	}
	
	public String toJSON(){
		
		try{
			// Create a JaxBContext
			JAXBContext jc = JAXBContext.newInstance(Player.class);
	
			// Create the Marshaller Object using the JaxB Context
			Marshaller marshaller = jc.createMarshaller();
			
			// Set the Marshaller media type to JSON or XML
			marshaller.setProperty(MarshallerProperties.MEDIA_TYPE, "application/json");
			
			// Set it to true if you need to include the JSON root element in the JSON output
			marshaller.setProperty(MarshallerProperties.JSON_INCLUDE_ROOT, false);
			
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
}
