package com.rookwithfriends.game;

import java.io.Serializable;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.eclipse.persistence.jaxb.MarshallerProperties;

@XmlRootElement
public abstract class GameBase implements Serializable{
	private static final long serialVersionUID = -3186136871484467548L;
	// Declare Class Members//
	protected CardColor trick;
	protected CardColor trump;
	protected int gameBid=100,numPasses;
	protected CardSet centerDeck;
	@XmlTransient 
	protected CardSet allDeck,kitty;
	@XmlTransient 
	protected List<Player> players;
	@XmlTransient 
	protected int bidWinnerId, currentPlayerId;
	@XmlTransient 
	GameStage stage;
	
	public GameStage getStage() {
		return stage;
	}

	public void setStage(GameStage stage) {
		this.stage = stage;
	}

	public int getRoundNumber() {
		return roundNumber;
	}

	public void setRoundNumber(int roundNumber) {
		this.roundNumber = roundNumber;
	}

	@XmlTransient 
	protected int roundNumber;
	
	//CRUD
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
	
	// Methods for Game
	public void shuffleAllDeck(){
		allDeck.Shuffle();
	}
	
	public int getGameBid() {
		return gameBid;
	}

	@XmlTransient 
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

	public CardColor getTrick() {
		return trick;
	}

	public void setTrick(CardColor trick) {
		this.trick = trick;
	}

	@XmlTransient 
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
	
	public void setTrump(CardColor inTrump) {
		trump = inTrump;
	}
	
	public CardColor getTrump() {
		return trump;
	}

	public Player getBidWinner(){
		return players.get(bidWinnerId);
	}

	public Player getCurrentPlayer(){
		return players.get(currentPlayerId);
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
	
	public String toJSON(){
		
		try{
			// Create a JaxBContext
			JAXBContext jc = JAXBContext.newInstance(GameBase.class);
	
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
