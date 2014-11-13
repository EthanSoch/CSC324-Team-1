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
	//private Player currentBidder = 0;
	
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
		
		switch(input.get("op")[0]){
		case "msg":
			Map<String,Object> msg = new HashMap<String,Object>();
			msg.put("op","msg");
			msg.put("msg", input.get("msg")[0]);
			sendToAll(msg);
			break;
		case "bid":
			//Need to pull out bid and playerID
			//input.get("msg")[0]);
			//Get player ID
			/*Game.setBid(playerID);
			if(!Game.getBettingIsDone()) {
				Player theBidder = game.getPlayerById(players.get(currentBidder).getGameID());
				startBidding(theBidder);
			}*/
			break;
			
		}
	}

	public void startGame(){
		game = new Game();
		
		//set user ids
		for(int i = 0 ; i < players.size() ; i++){
			players.get(i).setGameID(i);
		}
		
		game.startGame();
		updateGameBoard();
		updateAllPlayersCards();
	}
	
	public void updateAllPlayersCards(){
		for(UserSession player : players){
			Player gamePlayer = game.getPlayerById(player.getGameID());
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
	
	/*public void startBidding(Player currentPlayer){
		//currentPlayer.sendMessage(startBid); //Not sure what we specifically want to send
		currentBidder++;
		
		if(currentBidder == 4){ //Make sure it loops around
			currentBidder = 0;
		}
	}*/
	
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
		String playerIdString = String.valueOf(newPlayer.getGameID());
		response.put("playerConnected", playerIdString);
    	
		String responseJSON = JSONUtility.convertToJson(response);
		
		for(UserSession player : players){
			if(!player.getChannelKey().equals(newPlayer.getChannelKey()))
				player.sendMessage(responseJSON);
		}

		if(players.size() == 4){
			startGame();
		}
		
		return newPlayer;
	}
	
	public void sendToAll(Map<String,Object> data){
		sendToAll(JSONUtility.convertToJson(data));
	}
	
	public void sendToAll(String message){
		for(UserSession player : players){
			player.sendMessage(message);
		}
	}
	
//	public Game getGame(){
//		return game;
//	}
	
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
