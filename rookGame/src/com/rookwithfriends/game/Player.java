package com.rookwithfriends.game;
import java.util.*;


public class Player {
	//Declare Class Members//
	private int playerID;
	private int playerBid;
	private int handID;
	
	//CardSet Objects For Player//
	private CardSet playerHand;
	private CardSet cardsWon;
	
	public Player(int id)
	{
		this.playerID = id;
		this.playerHand = new CardSet();
		this.cardsWon = new CardSet();
	}
	public Player()
	{
		this.playerHand = new CardSet();
		this.cardsWon = new CardSet();
		this.playerID=0;
	}

	//selectCard(); -- Makes use of CardSet method getCard to return card in playerHand at specific index
	//Should this ask the user for the card?
	public Card selectCard(int index){
		
		return this.playerHand.getCard(index);
		
	}
	
	//combineHand(); -- This method allows the user to combine cards from the Kitty into playerHand
	//NEEDS REFACTORING//
	
	public void combineHand(CardSet theKitty){
		for(int i=0; i < theKitty.size() ; i++){
			this.playerHand.addCard(theKitty.getCard(i)); //CardSet needs a get method to retrieve entire ArrayList
		}
	}
	
	
	//Class Setters and Getters for Player//
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
	
	public int setBid(int currentBid)
	{
		//Scanner read = new Scanner(System.in);
		//System.out.println("Current bid is" + currentBid);
		//System.out.println("Enter 1 to increase, enter 2 to pass");
		
		//int answer=read.nextInt();
		//if(answer==1)
		//{
			//get new bid
			//System.out.println("What is your new bid. Must be a multiple of 5");
			//int bid=read.nextInt();
			//test if multiple of 5 and greater than currentBid
			//if(bid%5==0 && bid>currentBid)
			//{
			//	currentBid=bid;
			//}
		//}
		//read.close();
		return currentBid;
	}
	
	public void printHand()
	{
		System.out.println(playerHand);
	}
	

}