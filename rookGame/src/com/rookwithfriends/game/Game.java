package com.rookwithfriends.game;
import java.util.*;


public class Game 
{
	//Declare Class Members//
	private int playerID;
	private int gameBid;
	private int trump;
	public CardSet allDeck, allDeckCopy, deckp1, deckp2, deckp3, deckp4, kitty;
	public Card c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16,
		c17, c18, c19, c20, c21, c22, c23, c24, c25, c26, c27, c28, c29, c30, c31, c32,
		c33, c34, c35, c36, c37, c38, c39, c40, c41, c42, c43, c44, c45, temp;
	public Player player1;
	public Player player2;
	public Player player3;
	public Player player4;
	
	//vector of all players
	//public Player bidWinner
	//need to add the kitty, all, and center hands

	
	public Game(int playerId1, int playerId2, int playerId3, int playerId4)
	{
		
		this.player1 = new Player(playerId1);
		this.player2 = new Player(playerId2);
		this.player3 = new Player(playerId3);
		this.player4 = new Player(playerId4);
		
		playGame();

	}
	
	
	//Let the Games Begin
	private void playGame()
	{
		//Step 1 -- Shuffle Cards
		//create Deck that holds all cards
		allDeck = new CardSet();
		c1 = new Card(CardColor.red, CardRank.one, 0);
		c2 = new Card(CardColor.red, CardRank.five, 0);
		c3 = new Card(CardColor.red, CardRank.six, 0);
		c4 = new Card(CardColor.red, CardRank.seven, 0);
		c5 = new Card(CardColor.red, CardRank.eight, 0);
		c6 = new Card(CardColor.red, CardRank.nine, 0);
		c7 = new Card(CardColor.red, CardRank.ten, 0);
		c8 = new Card(CardColor.red, CardRank.eleven, 0);
		c9 = new Card(CardColor.red, CardRank.twelve, 0);
		c10 = new Card(CardColor.red, CardRank.thirteen, 0);
		c11 = new Card(CardColor.red, CardRank.fourteen, 0);
		
		c12 = new Card(CardColor.green, CardRank.one, 0);
		c13 = new Card(CardColor.green, CardRank.five, 0);
		c14 = new Card(CardColor.green, CardRank.six, 0);
		c15 = new Card(CardColor.green, CardRank.seven, 0);
		c16 = new Card(CardColor.green, CardRank.eight, 0);
		c17 = new Card(CardColor.green, CardRank.nine, 0);
		c18 = new Card(CardColor.green, CardRank.ten, 0);
		c19 = new Card(CardColor.green, CardRank.eleven, 0);
		c20 = new Card(CardColor.green, CardRank.twelve, 0);
		c21 = new Card(CardColor.green, CardRank.thirteen, 0);
		c22 = new Card(CardColor.green, CardRank.fourteen, 0);
		
		c23 = new Card(CardColor.black, CardRank.one, 0);
		c24 = new Card(CardColor.black, CardRank.five, 0);
		c25 = new Card(CardColor.black, CardRank.six, 0);
		c26 = new Card(CardColor.black, CardRank.seven, 0);
		c27 = new Card(CardColor.black, CardRank.eight, 0);
		c28 = new Card(CardColor.black, CardRank.nine, 0);
		c29 = new Card(CardColor.black, CardRank.ten, 0);
		c30 = new Card(CardColor.black, CardRank.eleven, 0);
		c31 = new Card(CardColor.black, CardRank.twelve, 0);
		c32 = new Card(CardColor.black, CardRank.thirteen, 0);
		c33 = new Card(CardColor.black, CardRank.fourteen, 0);
		
		c34 = new Card(CardColor.yellow, CardRank.one, 0);
		c35 = new Card(CardColor.yellow, CardRank.five, 0);
		c36 = new Card(CardColor.yellow, CardRank.six, 0);
		c37 = new Card(CardColor.yellow, CardRank.seven, 0);
		c38 = new Card(CardColor.yellow, CardRank.eight, 0);
		c39 = new Card(CardColor.yellow, CardRank.nine, 0);
		c40 = new Card(CardColor.yellow, CardRank.ten, 0);
		c41 = new Card(CardColor.yellow, CardRank.eleven, 0);
		c42 = new Card(CardColor.yellow, CardRank.twelve, 0);
		c43 = new Card(CardColor.yellow, CardRank.thirteen, 0);
		c44 = new Card(CardColor.yellow, CardRank.fourteen, 0);
		c45 = new Card(CardColor.white, CardRank.rook, 0);
		
		allDeck.addCard(c1);
		allDeck.addCard(c2);
		allDeck.addCard(c3);
		allDeck.addCard(c4);
		allDeck.addCard(c5);
		allDeck.addCard(c6);
		allDeck.addCard(c7);
		allDeck.addCard(c8);
		allDeck.addCard(c9);
		allDeck.addCard(c10);
		allDeck.addCard(c11);
		allDeck.addCard(c12);
		allDeck.addCard(c13);
		allDeck.addCard(c14);
		allDeck.addCard(c15);
		allDeck.addCard(c16);
		allDeck.addCard(c17);
		allDeck.addCard(c18);
		allDeck.addCard(c19);
		allDeck.addCard(c20);
		allDeck.addCard(c21);
		allDeck.addCard(c22);
		allDeck.addCard(c23);
		allDeck.addCard(c24);
		allDeck.addCard(c25);
		allDeck.addCard(c26);
		allDeck.addCard(c27);
		allDeck.addCard(c28);
		allDeck.addCard(c29);
		allDeck.addCard(c30);
		allDeck.addCard(c31);
		allDeck.addCard(c32);
		allDeck.addCard(c33);
		allDeck.addCard(c34);
		allDeck.addCard(c35);
		allDeck.addCard(c36);
		allDeck.addCard(c37);
		allDeck.addCard(c38);
		allDeck.addCard(c39);
		allDeck.addCard(c40);
		allDeck.addCard(c41);
		allDeck.addCard(c42);
		allDeck.addCard(c43);
		allDeck.addCard(c44);
		allDeck.addCard(c45);

			//instantiate hand with all cards
		allDeck.Shuffle();
		allDeckCopy = allDeck; // for when we reset cards
		
		//Step 2 -- Deal Cards
		//player 1 deal hand
		for(int i=0; i<10; i++) 
		{
			moveCard(deckp1, allDeck, i, 1);// 1 is cardID for player 1, i is index of card
		}
		deckp1.Sort();
		player1.setPlayerHand(deckp1);
		
		
		//player 2 deal hand
		for(int i=0; i<10; i++) 
		{
			moveCard(deckp2, allDeck, i, 2);
		}
		deckp2.Sort();
		player2.setPlayerHand(deckp2);
		
		
		//player 3 deal hand
		for(int i=0; i<10; i++) 
		{
			moveCard(deckp3, allDeck, i, 3);
		}
		deckp3.Sort();
		player3.setPlayerHand(deckp3);
		
		
		//player 4 deal hand
		for(int i=0; i<10; i++) 
		{
			moveCard(deckp4, allDeck, i, 4);
		}
		deckp4.Sort();
		player4.setPlayerHand(deckp4);
		
		
		//kitty deal hand
		for(int i=0; i<5; i++) 
		{
			moveCard(kitty, allDeck, i, 5);
		}
		kitty.Sort();
		
		System.out.println("Player 1: ");
		player1.printHand();
		
		System.out.println("Player 2: ");
		player2.printHand();
		
		System.out.println("Player 3: ");
		player3.printHand();
		
		System.out.println("Player 4: ");
		player4.printHand();
		
		System.out.println(kitty);
		
			//deal(); -- Is this done with instantiation of each player?
			//ex: for(i 0 thru 9 : player1.getHand().addCard(all.getCard(i))
			//do for 10-19,20-29,30-39 for p2-p4. Add 40-44 to kitty
		
		//Step 3 -- Sort Each Hand
			//player1.getHand().sortHand() 
		    //player2-4 "
		//Step 3.5 -- Display each hand
			//player1.printHand()
			//player2-4 "
		//Step 4 -- Round of bidding?
		//do{
			//need to update current bidWinner after every bid.
			//tempbid=player1.setBid(gameBid) -- Method needed?
			//if(tempbid>gameBid) {
			//	bidWinner=player1;
			//	gameBid=tempbid; }  Can this be put in a method checkBid(tempBid,player)
		    
		//}while(currentbid<200 and not everyone passes)
		
		//Step 4 -- Find winner of bid - Pass control unto them?
			//Player bidWinner is a public variable -- Create Gameboard, add needed methods. 
			/*
			 
			 bidWinner.combineHand(kitty)
			 
			 */
			
		
	}
	
	
	
  //Accessors and Mutators for Game
	public void moveCard(CardSet a, CardSet b, int i, int cardID)
	{
		temp = b.getCard(i);
		temp.setId(cardID);
		a.addCard(temp);
		b.discardCard(i);
	}
	
	public int getPlayerID() {
		return playerID;
	}

	public void setPlayerID(int playerID) {
		this.playerID = playerID;
	}

	public int getGameBid() {
		return gameBid;
	}

	public void setGameBid(int gameBid) {
		this.gameBid = gameBid;
	}

	public int getTrump() {
		return trump;
	}

	public void setTrump(int trump) {
		this.trump = trump;
	}



}
