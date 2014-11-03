package com.rookwithfriends.game;

public class Player {
	// Declare Class Members//
	private int playerID;
	private int playerBid;
	private int handID;
	private boolean hasPassed;

	// CardSet Objects For Player//
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
		//Scanner read = new Scanner(System.in);
		String cardColor = null;
		String colorUp;
		double theVal;
		boolean correctInput = false;
		boolean cardNotFound = false;
		Card theCard = null;

		do {

			// Keep prompting for input until a string has been entered
			do {
				//System.out.println("Please enter color of the card that you want.\n");
				cardColor = "RED";//read.next();
				colorUp = cardColor.toUpperCase();

				switch (colorUp) {
				case "RED":
				case "BLACK":
				case "YELLOW":
				case "WHITE":
					correctInput = true;
					break;
				}

			} while (!correctInput);

			//System.out.println("Please enter value of the card that you want. (Must be a double)\n");
			theVal = 5;//read.nextDouble();

			CardRank cardVal = CardRank.returnRank(theVal);
			CardColor theCardColor = CardColor.returnColor(colorUp);

			for (int i = 0; i < playerHand.size(); i++) {
				if (playerHand.get(i).getColor() == theCardColor
						&& playerHand.get(i).getRank() == cardVal) {
					theCard = playerHand.get(i);
				} else {
					cardNotFound = true;
				}

			}
		} while (cardNotFound);

		//read.close();

		return theCard;
	}

	public int setBid(int currentBid) {
		/*Scanner read = new Scanner(System.in);
		System.out.println("Current bid is " + currentBid + ".\n");
		System.out.println("Enter 1 to increase, enter 2 to pass.\n");*/

		int answer = 1;//read.nextInt();
		if (answer == 1) {
			//System.out.println("What is your new bid. Must be a multiple of 5.\n");

			int bid = 5;//read.nextInt();
			if (bid % 5 == 0 && bid > currentBid) {
				currentBid = bid;
				//System.out.println("Thank you, your bid has been set. New bid is: "
								//+ currentBid + "\n");
			}
		}
		else
		{
			hasPassed=true;
		}
		// read.close();
		return currentBid;
	}

	public void printHand() {
		System.out.println(playerHand);
	}

}
