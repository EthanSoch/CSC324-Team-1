package com.rookwithfriends.web;

import java.util.*;
import java.io.*;

import com.google.gson.Gson;
import com.rookwithfriends.game.*;
import com.rookwithfriends.util.*;

public class GameSession implements Serializable{
	private static final long serialVersionUID = -443526662938702934L;
	private Game game;
	private UUID gameId;
	private List<UserSession> players;
	private int currentBidder;
	
	public static GameSession getGameSession(UUID gameId){
		CacheUtility util = new CacheUtility();
		GameSession session = (GameSession) util.get(gameId);
		
		return session;
	}
	
	public GameSession(){
		players = new ArrayList<UserSession>();
		
		gameId = UUID.randomUUID();
	}
	
	public void gameInstruction(Map<String,String[]> input){
		//get sending players UserSession and playerid 
		//(these are the 0-3 ids) 
		//the cliends have the channel key which is a UUID that is sent up with every request
		UserSession currentPlayerSession = getUserSessionByChannelKey(UUID.fromString(input.get("playerId")[0]));
		int playerID = currentPlayerSession.getPlayerGameID();
		Player currentPlayer = game.getPlayerById(playerID);
		
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
			/*int bid = Integer.parseInt(input.get("bid")[0]);
			game.setBid(currentPlayer, bid);
			 
			if(!game.getBettingIsDone()){
				//send message to next player asking for their bid
				Player nextPlayer = game.getCurrentPlayer();
				UserSession nextPlayerSession = getUserSessionByPlayerId(nextPlayer.getPlayerID());
				
				Map<String,Object> response = new HashMap<String, Object>();
				response.put("op", "bid");
				
				nextPlayerSession.sendMessage(response);
			}
			else{
				//start the main game
				Map<String,Object> startGameMessage = new HashMap<String, Object>();
				startGameMessage.put("op", "startgame");
				
				Player nextPlayer = game.getCurrentPlayer();
				
				for(UserSession player: players){
					if(nextPlayer.getPlayerID() != player.getPlayerGameID())
						//tell players that the main game has started
						player.sendMessage(startGameMessage);
					else{
						//tell first player to make a move and send it up
						Map<String,Object> startTurnResponse = new HashMap<String, Object>();
						startTurnResponse.put("op", "youTurn");
						player.sendMessage(startTurnResponse);
					}
				}
			}*/
			
			String thePlayerID = input.get("playerId")[0];
			UUID gameID = UUID.fromString(input.get("gameId")[0]);
			
			GameSession session = GameSession.getGameSession(gameID);
			
			Game currentGame = session.getGame();
			
			int whichPlayer = 0;
			
			for(UserSession player : session.getPlayers()){
				
				
				//Player theCurrentPlayer = currentGame.getPlayerById(player.getGameID());
				Player theCurrentPlayer = currentGame.getPlayerById(player.getPlayerGameID());
				int currentID = theCurrentPlayer.getPlayerID();
				
				/*System.out.println("Current:"+currentID);
				System.out.println(player.getChannelKey());
				System.out.println(input.get("playerId")[0]);*/
				
				UUID playerUUID = player.getChannelKey();
				String playerChannelKey = playerUUID.toString();
				if(playerChannelKey.equals(input.get("playerId")[0])){
					whichPlayer = currentID + 1;
					game.setBid(theCurrentPlayer,Integer.parseInt(input.get("playerBet")[0]));
					//System.out.println("THE: " +whichPlayer);
				}				
			}
			
			
				//System.out.println("CURRENT BIDDER"+ whichPlayer);
				
				//Next player to bid
			if(!game.getBettingIsDone()){
				whichPlayer = whichPlayer % 4;
				UserSession player = players.get(whichPlayer);
				startBidding(player,Integer.parseInt(input.get("playerBet")[0]));
			}

				break;
			case "playCard":
				String[] cardJSON = input.get("card");
				
				//i dont understant why this method only takes one argument
				game.playRound(currentPlayer);
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
	
	public void updateAllPlayersCards(){
		for(UserSession player : players){
			Player gamePlayer = game.getPlayerById(player.getPlayerGameID());
			String jsonString = gamePlayer.toJSON();
			player.sendMessage(jsonString);
		}
	}
	
	public void updateGameBoard(){
		for(UserSession player : players){
			String jsonString = game.toJSON();
			player.sendMessage(jsonString);
		}
	}
	
	public void startBidding(UserSession currentPlayer, int theBid){
		
		Map<String,Object> response = new HashMap<String, Object>();
		response.put("startBidding", "true");
		response.put("theBid", theBid);
    	
		String responseJSON = JSONUtility.convertToJson(response);
		
		currentPlayer.sendMessage(responseJSON);
	}
	
	/*
	 * Save this game instance
	 */
	public void saveToCache(){
		CacheUtility util = new CacheUtility();
		util.put(getGameId(), this);
	}
	
	/*
	 * Creates player and returns the channel id
	 */
	public UserSession addPlayer(){
		//Add player to the list
		UUID newChannelKey = UUID.randomUUID();
		UserSession newPlayer = new UserSession(players.size(), newChannelKey);
		players.add(newPlayer);
		
		//send player Connected messages to all other players
		Map<String,Object> response = new HashMap<String, Object>();
		String numOfPlayers = String.valueOf(players.size());
		response.put("playersConnected", numOfPlayers);
    	
		String responseJSON = JSONUtility.convertToJson(response);
		
		for(UserSession player : players){
			if(!player.getChannelKey().equals(newPlayer.getChannelKey()))
				player.sendMessage(responseJSON);
		}
		
		return newPlayer;
	}
	
	private UserSession getUserSessionByChannelKey(UUID id){
		for(UserSession player : players){
			if(player.getChannelKey().equals(id))
				return player;
		}
		
		return null;
	}
	
	private UserSession getUserSessionByPlayerId(int id){
		for(UserSession player : players){
			if(player.getPlayerGameID() == id)
				return player;
		}
		
		return null;
	}
	
	public void sendToAll(Map<String,Object> data){
		sendToAll(JSONUtility.convertToJson(data));
	}
	
	public Game getGame(){
		return game;
	}
	
	public void sendToAll(String message){
		for(UserSession player : players){
			player.sendMessage(message);
		}
	}
	
	public UUID getGameId() {
		return gameId;
	}

	public void setGameId(UUID gameId) {
		this.gameId = gameId;
	}
	
	public List<UserSession> getPlayers() {
		return players;
	}

	public void setPlayers(List<UserSession> players) {
		this.players = players;
	}
}
