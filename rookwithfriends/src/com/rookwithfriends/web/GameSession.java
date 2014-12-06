package com.rookwithfriends.web;

import java.util.*;
import java.io.*;

import com.google.gson.Gson;
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
			Map<String,Object> msg = new HashMap<String,Object>();
			msg.put("op","msg");
			msg.put("msg", input.get("msg")[0]);
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
			String[] cardJSON = input.get("card");
			
			//i dont understant why this method only takes one argument
			//game.playRound(currentPlayer);
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
			game.endTurn();
			Player nextPlayer = game.getCurrentPlayer();
			UserSession nextPlayerSession = getUserSession(nextPlayer);
			
			startBidding(nextPlayerSession,Integer.parseInt(input.get("playerBet")[0]));
			
			//Send current players bid to everyone
			Map<String,Object> response = new HashMap<String, Object>();
			response.put("newPlayerBid", currentPlayerSession.getPlayerGameID()+"-"+input.get("playerBet")[0]);		
			sendToAll(response);
		}
		else{
			requestTrumpAndDiscards(currentPlayerSession,currentPlayer);
		}
	}
		
	public void startBidding(UserSession currentPlayer, int theBid){
		Map<String,Object> response = new HashMap<String, Object>();
		response.put("startBidding", "true");
		response.put("theBid", theBid);

		currentPlayer.sendMessage(response);
	}
	
	public void requestTrumpAndDiscards(UserSession currentPlayer, Player player){
		Map<String,Object> response = new HashMap<String, Object>();
		response.put("chooseTrump", "true");
		
		currentPlayer.sendMessage(response);
		currentPlayer.sendMessage(player.toJSON());
	}
	
	public void setTrump(Map<String, String[]> input){
		String tempTrump = input.get("theTrump")[0].toUpperCase();
		CardColor trump = CardColor.returnColor(tempTrump);
		
		//Set the trump
		game.setTrump(trump);
		Player bidWinner = game.getBidWinner();
				System.out.println(bidWinner.getPlayerID());
		
		bidWinner.combineHand(game.getKitty());
		bidWinner.getPlayerHand().Sort();
	
		String jsonString = bidWinner.toJSON();
		int winnerID = bidWinner.getPlayerID();
		
		UserSession winnerSession = players.get(winnerID);
		winnerSession.sendMessage(jsonString);
	}
}
