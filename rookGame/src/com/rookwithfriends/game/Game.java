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
			//shuffle(); -- Static?
		
		//Step 2 -- Deal Cards
			//deal(); -- Is this done with instantiation of each player?
		
		//Step 3 -- Sort Each Hand
			//player1.sortHand() -- Method needed?
		    //player2.sortHand() -- Method needed?
		    //player3.sortHand() -- Method needed?
		    //player4.sortHand() -- Method needed?
		
		//Step 4 -- Round of bidding?
			//player1.setBid() -- Method needed?
		    //player2.setBid() -- Method needed?
		    //player3.setBid() -- Method needed?
		    //player4.setBid() -- Method needed?
		
		//Step 4 -- Find winner of bid - Pass control unto them?
			//int winner = bid.getBidWinner() -- Create Gameboard, add needed methods. 
			/*
			 
			 if(winner == playerId1){
			 	//Give winner kitty
			 	 * combineHand()??
			 	 }
			 else if{winner == playerId2)....
			 
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
