package com.rookwithfriends.game;
import java.io.Serializable;
import java.io.StringWriter;
import java.util.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.eclipse.persistence.jaxb.MarshallerProperties;

@XmlRootElement
public class Game implements Serializable{
	private static final long serialVersionUID = 6986630091662956160L;
	// Declare Class Members//
	private int gameBid=100,numPasses,totalTeam1Score=0,totalTeam2Score=0,team1Score=0,team2Score=0;
	private CardColor trump, trickColor;
	public CardSet centerDeck, kitty;
	@XmlTransient
	public CardSet allDeck;
	

	@XmlTransient
	public List<Player> players;

	public Player bidWinner, currentPlayer;
	private Boolean bettingIsDone=false;

	// vector of all players
	// public Player bidWinner
	// need to add the kitty, all, and center hands

	public Game(){
		this(0,1,2,3);
	}

	public Game(int playerId1, int playerId2, int playerId3, int playerId4) {
		
		players = new ArrayList<Player>(4);
		players.add(new Player(playerId1));
		players.add(new Player(playerId2));
		players.add(new Player(playerId3));
		players.add(new Player(playerId4));
		centerDeck=new CardSet();
		/*for(int i = 0 ; i < players.length ; i++){
			players[i] = new Player();
			System.out.println("Player["+i+"]"+" created.");
		}*/
	}

	public void startGame() {
		//System.out.println("Starting Game\n");
		// Step 1 -- Shuffle Cards
		// create Deck that holds all cards
		createDeck(); // Moved the code that instantiates allDeck to separate
						// method

		// instantiate hand with all cards
		shuffleAllDeck();

		// Step 2 -- Deal & Sort Cards
		dealHands(); // Moved the code that deals hands into separate method

		// Step 3 -- Print all the hands out
		//System.out.println("Here are the hands\n");
		//printHands(); // Moved the print hands into a different method
		/*
		numPasses=0;
		for(int i=0;!bettingIsDone;i=(i+1)%4)
		{
			currentPlayer=getPlayer(i);
			setBid(currentPlayer);
			
		}
		

		// Step 4 -- Find winner of bid - Pass control unto them?
		// Player bidWinner is a public variable -- Create Gameboard, add needed
		// methods.
		
		//System.out.println("The winner of the bid was: " + bidWinner.getPlayerID());
		bidWinner.combineHand(kitty);
		bidWinner.getPlayerHand().Sort();
		//System.out.println("\nThe winners hand is: ");
		bidWinner.printHand();
		setTrump(bidWinner);
		
		
		kitty.clear();
		for(int i=0;i<5;i++)
		{
			Card temp = bidWinner.chooseCard();
			kitty.add(temp);
			bidWinner.getPlayerHand().remove(bidWinner.getPlayerHand().indexOf(temp));
		}
		
		//bidwinner starts game
		playRound();
		*/
	}

	public void setBid(Player curPlayer) {
		int tempbid;
		// Step 4 -- Round of bidding?

		//System.out.println(curPlayer+": It's your turn to bid.\n");
		if(!curPlayer.getHasPassed())
		{
			if (gameBid >= 200) {
				bettingIsDone=true;
			}
			tempbid = curPlayer.setBid(gameBid);
			if(curPlayer.getHasPassed())
			{
				numPasses+=1;
				if(numPasses==4)
				{
					bettingIsDone=true;
				}
			}
			
			if (tempbid > gameBid) {
				bidWinner = curPlayer;
				gameBid = tempbid;
			}
		}
		

	}

	// Methods for Game
	public void shuffleAllDeck(){
		allDeck.Shuffle();
	}
	public int getGameBid() {
		return gameBid;
	}
	
	public int getTotalTeam1Score() {
		return totalTeam1Score;
	}

	public void setTotalTeam1Score(int totalTeam1Score) {
		this.totalTeam1Score = totalTeam1Score;
	}

	public int getTotalTeam2Score() {
		return totalTeam2Score;
	}

	public void setTotalTeam2Score(int totalTeam2Score) {
		this.totalTeam2Score = totalTeam2Score;
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

	public List<Player> getPlayers() {
		return players;
	}
	
	public void setPlayers(List<Player> players) {
		this.players = players;
	}
	
	public Player getPlayer(int index){
		return players.get(index);
	}

	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}

	public void setGameBid(int gameBid) {
		this.gameBid = gameBid;
	}

	public CardColor getTrump() {
		return trump;
	}


	public Player getBidWinner() {
		return bidWinner;
	}

	public void setBidWinner(Player bidWinner) {
		this.bidWinner = bidWinner;
	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	public void setCurrentPlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	public Boolean getBettingIsDone() {
		return bettingIsDone;
	}

	public void setBettingIsDone(Boolean bettingIsDone) {
		this.bettingIsDone = bettingIsDone;
	}

	public int getNumPasses() {
		return numPasses;
	}

	public void setNumPasses(int numPasses) {
		this.numPasses = numPasses;
	}
	
	public Player getPlayerById(int id){
		for(Player player : players){
			if(player.getPlayerID() == id)
				return player;
		}
		
		return null;
	}

	public void printHands() {
		for (Player player : players) {
			//System.out.println("\nPlayer " + player.getPlayerID() + ": " );
			
			player.printHand();
		}
		//System.out.println("The Kitty: ");
		//System.out.println(kitty);
	}

	public void createDeck() {
		allDeck = new CardSet();
		
		
		for (CardColor color : CardColor.values()) {
			for (CardRank rank : CardRank.values()) {
				if (rank != CardRank.rook && color != CardColor.white) {
					allDeck.add(new Card(color, rank, 0));
				}
			}
		}

		allDeck.add(new Card(CardColor.white, CardRank.rook, 0));
	}

	public void dealHands() {
		for (Player player : players) {
			CardSet hand = new CardSet();
			
			for (int i = 0; i < 10; i++) {
				allDeck.front().setId(player.getPlayerID());
				hand.add(allDeck.front());
				allDeck.pop();
			}

			hand.Sort();
			player.setPlayerHand(hand);
			player.getCardsWon().clear();
		}

		kitty = new CardSet();
		
		// kitty deal hand
		for (int i = 0; i < 5; i++) {
			kitty.add(allDeck.front());
			allDeck.pop();
		}
		kitty.Sort();
		//System.out.println("Hands have been created, and sorted.\n");
	}
	
	public void setTrump(Player winner) {

		String input = null;
		String colorUp;
		boolean correctInput = false;

		// Keep prompting for input until a string has been entered
		do {
			System.out.println("Please enter the new trump.");
			input = "RED";

			colorUp = input.toUpperCase();

			if (colorUp == "RED") {
				correctInput = true;
			} else if (colorUp == "BLACK") {
				correctInput = true;
			} else if (colorUp == "YELLOW") {
				correctInput = true;
			} else if (colorUp == "GREEN") {
				correctInput = true;
			}

		} while (!correctInput);

		// Call Enum method to return the Enum cast of the input
		trump = CardColor.returnColor(colorUp);
	}
	
	public void playRound(Player curPlayer)
	{
		currentPlayer=curPlayer;
		centerDeck.clear();
		trickColor=null;
		for(int j=players.indexOf(currentPlayer),i=0;i<4;i++)
		{
			System.out.println("Player "+j+", Choose a card to play:");
			currentPlayer.printHand();
			Card temp = findValidCard(currentPlayer);// currentPlayer.chooseCard();
			if(i==0)
			{
				trickColor=temp.getColor();
				centerDeck.add(temp);
				currentPlayer.getPlayerHand().remove(currentPlayer.getPlayerHand().indexOf(temp));
				j=(j+1)%4;
				currentPlayer=players.get(j);
			}
			else if(temp.getColor()!=trickColor && temp.getColor()!=trump && temp.getColor()!=CardColor.white)
			{
				boolean hasGoodColor = false;
				for(Card card : currentPlayer.getPlayerHand())
				{
					if(card.getColor()==trickColor)
					{
						hasGoodColor=true;
					}
				}
				if(hasGoodColor)
				{
					System.out.println("Card invalid. Please choose another");
					i--;
				}
				else
				{
					centerDeck.add(temp);
					currentPlayer.getPlayerHand().remove(currentPlayer.getPlayerHand().indexOf(temp));
					j=(j+1)%4;
					currentPlayer=players.get(j);
				}
				
			}
			else
			{
				centerDeck.add(temp);
				currentPlayer.getPlayerHand().remove(currentPlayer.getPlayerHand().indexOf(temp));
				j=(j+1)%4;
				currentPlayer=players.get(j);
			}
			
		}
	}
	
	public String toJSON(){
		
		try{
			// Create a JaxBContext
			JAXBContext jc = JAXBContext.newInstance(Game.class);
	
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
	
	public Card findValidCard(Player curPlayer)
	{
		for(Card temp: curPlayer.getPlayerHand())
		{
			if(temp.getColor()==trickColor || temp.getColor()==trump || temp.getColor()==CardColor.white)
			{
				return temp;
			}
		}
		return curPlayer.getPlayerHand().get(0);
	}
	
	public void scoreGame()
	{
		team1Score=players.get(0).getCardsWon().getScore()+players.get(2).getCardsWon().getScore();
		team2Score=players.get(1).getCardsWon().getScore()+players.get(3).getCardsWon().getScore();
	}

	public int getTeam1Score() {
		return team1Score;
	}

	public void setTeam1Score(int team1Score) {
		this.team1Score = team1Score;
	}

	public int getTeam2Score() {
		return team2Score;
	}

	public void setTeam2Score(int team2Score) {
		this.team2Score = team2Score;
	}
	
}
