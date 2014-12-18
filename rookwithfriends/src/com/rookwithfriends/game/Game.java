package com.rookwithfriends.game;
import java.io.Serializable;
import java.io.StringWriter;
import java.util.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.eclipse.persistence.jaxb.MarshallerProperties;

public class Game extends GameBase{
	private static final long serialVersionUID = 6986630091662956160L;

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
		stage = GameStage.bidding;
		roundNumber = 0;
	}

	public void startGame() {
		createDeck(); 
		shuffleAllDeck();
		dealHands();
	}

	/**
	 * Step 4 -- Round of bidding
	 * @param curPlayer
	 * @param playerBet
	 */
	public void setBid(Player curPlayer, int playerBet) {
		//assume current player has not passed
		if (gameBid >= 200 || playerBet >= 200) {
			bidWinnerId = curPlayer.getPlayerID();
			gameBid = playerBet;
			finnishBetting();
			return;
		//player has passed
		}else if(playerBet == 0){
			numPasses++;
			curPlayer.setHasPassed(true);
		}else if(playerBet > gameBid){
			bidWinnerId = curPlayer.getPlayerID();
			gameBid = playerBet;
			curPlayer.setBid(playerBet);//i don't thing this is being used
		}
		
		//if only one player is left
		if(numPasses == 3){
			finnishBetting();
			return;
		}
		
		endTurn();
	}
	
	private void finnishBetting() {
		this.stage = GameStage.mainGame;
		currentPlayerId = bidWinnerId;
	}
	
	public Boolean isBettingDone() {
		return this.stage != GameStage.bidding;
	}
	
	public void endTurn(){
		switch(stage){
		case mainGame:
			currentPlayerId = (currentPlayerId + 1) % 4;
			break;
		case bidding:
		default:
			int nextPlayerId = currentPlayerId;
			
			for(int i = 0 ; i < players.size() ; i++){
				nextPlayerId = (nextPlayerId + 1) % 4;
				
				if(!players.get(nextPlayerId).getHasPassed()){
					currentPlayerId = nextPlayerId;
					break;
				}
			}
		}
	}
	
	public void playRound(Player curPlayer, Card card)
	{
		Player currentPlayer = getCurrentPlayer();
		
		//First player
		if(centerDeck.size() == 0) {
			trick = card.getColor();
		}
		
		
		centerDeck.add(card);
		currentPlayer.getPlayerHand().remove(card);
		
		endTurn();
	}
	
	public void endTrick(){
		if(centerDeck.size() == 4){
			Card winningCard = centerDeck.findWinningCard(trump);
			players.get(winningCard.getId()).getCardsWon().addAll(centerDeck);
			currentPlayerId = winningCard.getId();
			centerDeck.clear();
			
			trick = null;
			
			if(roundNumber < 10){
				roundNumber += 1;
			}
			else{
				roundNumber = 0;
				stage = stage.bidding;
				bidWinnerId = 0;
			}
			
			if(roundNumber >= 10){
				roundNumber = 0;
				stage = stage.bidding;
				bidWinnerId = 0;
				
				//Give the winner of the last trick the kitty so that it can be added to teams score
				players.get(currentPlayerId).getCardsWon().addAll(kitty);
			}
		}
	}
	
	public Card findValidCard(Player curPlayer){
		for(Card temp: curPlayer.getPlayerHand())
		{
			if(temp.getColor()==trick || temp.getColor()==trump || temp.getColor()==CardColor.white)
			{
				return temp;
			}
		}
		return curPlayer.getPlayerHand().get(0);
	}
	
	/**
	 * calculates the score for team 0 or 1
	 * @param team 0 or 1
	 * @return team score
	 */
	public int getScoreByTeam(int team){
		if(team == 0 || team == 1){
			int player1Score = players.get(team).getCardsWon().getScore(),
				player2Score = players.get(team + 2).getCardsWon().getScore();
			
			//Update the team score
			teamScores[team] += player1Score + player2Score;
			
			return teamScores[team];
		}else{
			throw new IllegalArgumentException("team must be 0 or 1");
		}
	}
}
