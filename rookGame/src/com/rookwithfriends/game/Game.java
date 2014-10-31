package com.rookwithfriends.game;

public class Game {
	// Declare Class Members//
	private int gameBid, trump;
	public CardSet allDeck, centerDeck, kitty;
	public Player[] players;
	private Player bidWinner;

	// vector of all players
	// public Player bidWinner
	// need to add the kitty, all, and center hands

	public Game(int playerId1, int playerId2, int playerId3, int playerId4) {
		players = new Player[4];
		players[0] = new Player(playerId1);
		players[1] = new Player(playerId2);
		players[2] = new Player(playerId3);
		players[3] = new Player(playerId4);
		
		/*for(int i = 0 ; i < players.length ; i++){
			players[i] = new Player();
			System.out.println("Player["+i+"]"+" created.");
		}*/
	}

	public void startGame() {
		System.out.println("Starting Game\n");
		// Step 1 -- Shuffle Cards
		// create Deck that holds all cards
		createDeck(); // Moved the code that instantiates allDeck to separate
						// method

		// instantiate hand with all cards
		allDeck.Shuffle();

		// Step 2 -- Deal & Sort Cards
		dealHands(); // Moved the code that deals hands into separate method

		// Step 3 -- Print all the hands out
		System.out.println("Here are the hands\n");
		printHands(); // Moved the print hands into a different method
		
		int tempbid;
		// Step 4 -- Round of bidding?
		do{		 
			 for(int i = 0 ; i < players.length ; i++){
				 if (gameBid >=200){
					 break;
				 }
				 System.out.println("Player[" + i + "]: It's your turn to bid.\n");
				  tempbid = players[i].setBid(gameBid);		 
					 if(tempbid > gameBid){
						 bidWinner=players[i];
						 gameBid=tempbid; 
					 }
			 }
		 
		 } while(gameBid < 200);

		// Step 4 -- Find winner of bid - Pass control unto them?
		// Player bidWinner is a public variable -- Create Gameboard, add needed
		// methods.
		
		System.out.println("The winner of the bid was: " + bidWinner.getPlayerID());
		bidWinner.combineHand(kitty);
		bidWinner.getPlayerHand().Sort();
		System.out.println("\nThe winners hand is: ");
		bidWinner.printHand();

	}

	// Methods for Game
	public int getGameBid() {
		return gameBid;
	}

	public CardSet getAllDeck() {
		return allDeck;
	}

	public void setAllDeck(CardSet allDeck) {
		this.allDeck = allDeck;
	}

	public CardSet getCenterDeck() {
		return centerDeck;
	}

	public void setCenterDeck(CardSet centerDeck) {
		this.centerDeck = centerDeck;
	}

	public CardSet getKitty() {
		return kitty;
	}

	public void setKitty(CardSet kitty) {
		this.kitty = kitty;
	}

	public Player[] getPlayers() {
		return players;
	}

	public void setPlayers(Player[] players) {
		this.players = players;
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

	public void printHands() {
		for (Player player : players) {
			System.out.println("\nPlayer " + player.getPlayerID() + ": " );
			
			player.printHand();
		}
		System.out.println("The Kitty: ");
		System.out.println(kitty);
	}

	public void createDeck() {
		allDeck = new CardSet();

		for (CardColor color : CardColor.values()) {
			for (CardRank rank : CardRank.values()) {
				if (rank != CardRank.rook) {
					allDeck.add(new Card(color, rank, 0));
				}
			}
		}

		allDeck.add(new Card(CardColor.black, CardRank.rook, 0));
	}

	public void dealHands() {
		for (Player player : players) {
			CardSet hand = new CardSet();

			for (int i = 0; i < 10; i++) {
				hand.add(allDeck.front());
				allDeck.pop();
			}

			hand.Sort();
			player.setPlayerHand(hand);
		}

		kitty = new CardSet();
		
		// kitty deal hand
		for (int i = 0; i < 5; i++) {
			kitty.add(allDeck.front());
			allDeck.pop();
		}
		kitty.Sort();
		System.out.println("Hands have been created, and sorted.\n");
	}

}
