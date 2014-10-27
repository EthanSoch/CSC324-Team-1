package com.rookwithfriends.game;

public class Game {
	//Declare Class Members//
	private int playerID;
	private int gameBid;
	private int trump;
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
	private void playGame(){
		//Step 1 -- Shuffle Cards
			//instantiate hand with all cards
			//all.shuffle();
		
		//Step 2 -- Deal Cards
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
		    //tempbid=player2.setBid(gameBid) -- Method needed?
		    //checkBid(tempBid,player2)
		    //tempbid=player3.setBid(gameBid) -- Method needed?
		    //checkBid(tempBid,player3)
		    //tempbid=player4.setBid(gameBid) -- Method needed?
		    //checkBid(tempBid,player4)
		//}while(currentbid<200 and not everyone passes)
		
		//Step 4 -- Find winner of bid - Pass control unto them?
			//Player bidWinner is a public variable -- Create Gameboard, add needed methods. 
			/*
			 
			 bidWinner.combineHand(kitty)
			 
			 */
			
		
	}
	
	
	
  //Accessors and Mutators for Game
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
