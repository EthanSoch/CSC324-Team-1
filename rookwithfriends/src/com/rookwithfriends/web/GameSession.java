package com.rookwithfriends.web;

import java.util.*;

import com.rookwithfriends.game.*;
import com.rookwithfriends.util.*;

public class GameSession extends GameSessionBase {
	private static final long serialVersionUID = -443526662938702934L;
	
	public GameSession(){
		players = new ArrayList<UserSession>();
		
		gameId = UUID.randomUUID();
	}
	
	public void gameInstruction(Map<String,String[]> input){
		switch(input.get("op")[0]){
		case "msg":
			UserSession currentPlayerSession = getUserSession(UUID.fromString(input.get("playerId")[0]));
			
			Map<String,Object> msg = new HashMap<String,Object>();
			msg.put("op","msg");
			msg.put("msg","Player " + currentPlayerSession.getPlayerGameID() + " : " + input.get("msg")[0]);
			
			sendToAll(msg);
			break;
		case "start":
			startGame();
			break;
		case "bid":
			setAndRequestBid(input);
			break;
		case "trump":
			setTrump(input);
			break;
		case "playCard":
			playCard(input);
			break;
		case "discardFive":
			discardFive(input);
			break;
		}
	}
	
	public void startGame(){
		game = new Game();
		currentBidder = 0;

		//set user ids
		for(int i = 0 ; i < players.size() ; i++){
			players.get(i).setPlayerGameID(i);
		}
		
		game.startGame();
		updateGameBoard();
		updateAllPlayersCards();
		
		UserSession player = players.get(currentBidder);
		startBidding(player,0);
	}
	
	private void setAndRequestBid(Map<String, String[]> input) {
		UserSession currentPlayerSession = getUserSession(UUID.fromString(input.get("playerId")[0]));
		int playerID = currentPlayerSession.getPlayerGameID();
		Player currentPlayer = game.getPlayerById(playerID);
		
		game.setBid(currentPlayer,Integer.parseInt(input.get("playerBet")[0]));

		//Next player to bid
		if(!game.isBettingDone()){
			Player nextPlayer = game.getCurrentPlayer();
			UserSession nextPlayerSession = getUserSession(nextPlayer);
			
			startBidding(nextPlayerSession,Integer.parseInt(input.get("playerBet")[0]));
			
			//Send current players bid to everyone
			Map<String,Object> response = new HashMap<String, Object>();
			response.put("newPlayerBid", currentPlayerSession.getPlayerGameID()+"-"+input.get("playerBet")[0]);		
			sendToAll(response);
		}
		else{
			requestTrumpAndDiscards();
		}
	}
		
	public void startBidding(UserSession currentPlayer, int theBid){
		Map<String,Object> response = new HashMap<String, Object>();
		response.put("startBidding", "true");
		response.put("theBid", theBid);

		currentPlayer.sendMessage(response);
	}
	
	public void requestTrumpAndDiscards(){
		Player winningPlayer = game.getBidWinner();
		UserSession winningPlayerSession = getUserSession(winningPlayer);
		
		Map<String,Object> response = new HashMap<String, Object>();
		response.put("chooseTrump", "true");
		
		winningPlayerSession.sendMessage(response);
		winningPlayerSession.sendMessage(winningPlayer.toJSON());
	}
	
	public void setTrump(Map<String, String[]> input){
		String tempTrump = input.get("theTrump")[0].toUpperCase();
		CardColor trump = CardColor.returnColor(tempTrump);
		
		//Set the trump
		game.setTrump(trump);
		Player bidWinner = game.getBidWinner();
		
		bidWinner.getPlayerHand().addAll(game.getKitty());
		bidWinner.getPlayerHand().Sort();
	
		String jsonString = bidWinner.toJSON();
		int winnerID = bidWinner.getPlayerID();
		
		UserSession winnerSession = players.get(winnerID);
		winnerSession.sendMessage(jsonString);
		
		//Have winner discard the five cards
		Map<String,Object> response = new HashMap<String, Object>();
		response.put("discardFive", "true");	    	
		String responseJSON = JSONUtility.convertToJson(response);
		winnerSession.sendMessage(responseJSON);
	}
	
	private void discardFive(Map<String, String[]> input) {
		CardSet discardCards = new CardSet();
		
		for(int i = 0 ; i < 5 ; i++){
			 String color = input.get("cards[" + i +"][color]")[0];
			 String rank = input.get("cards[" + i +"][rank]")[0];
			 String id =input.get("cards[" + i +"][id]")[0];
			 Card card = new Card(color,rank,id);
			 
			 discardCards.add(card);
		}
		
		Player bidWinner = game.getBidWinner();
		bidWinner.getPlayerHand().removeAll(discardCards);
		
		UserSession bidWinnerSession = getUserSession(bidWinner);
		bidWinnerSession.sendMessage(bidWinner.toJSON());
		
		requestNextPlayerCard();
	}
	
	private void requestNextPlayerCard(){
		Player currentPlayer = game.getCurrentPlayer();
		UserSession currentPlayerSession = getUserSession(currentPlayer);
		
		Map<String,Object> response = new HashMap<String, Object>();
		response.put("playCard", "true");
		currentPlayerSession.sendMessage(response);
	}
	
	private void playCard(Map<String, String[]> input) {
		UserSession currentPlayerSession = getUserSession(UUID.fromString(input.get("playerId")[0]));
		int playerID = currentPlayerSession.getPlayerGameID();
		Player currentPlayer = game.getPlayerById(playerID);
		
		String color = input.get("cards[0][color]")[0];
		String rank = input.get("cards[0][rank]")[0];
		String id =input.get("cards[0][id]")[0];
		Card card = new Card(color,rank,id);
		
		game.playRound(currentPlayer, card);
		
		updateGameBoard();
		
		if(game.getCenterDeck().size() == 4){
			game.endRound();
			
			if(game.getStage().equals(GameStage.bidding)){
				updateScores();
				
				//startBidding();
			}
		}
		
		currentPlayerSession.sendMessage(currentPlayer.toJSON());
		requestNextPlayerCard();
	}
}
