package com.rookwithfriends.web;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.rookwithfriends.game.Game;
import com.rookwithfriends.game.GameBase;
import com.rookwithfriends.game.Player;
import com.rookwithfriends.util.CacheUtility;
import com.rookwithfriends.util.JSONUtility;

/**
 * basic functionality class
 */
public abstract class GameSessionBase implements Serializable {
	private static final long serialVersionUID = -5982761068682587934L;
	protected Game game;
	protected UUID gameId;
	protected List<UserSession> players;
	protected int currentBidder;

	public static GameSession getGameSession(UUID gameId){
		CacheUtility util = new CacheUtility();
		GameSession session = (GameSession) util.get(gameId);
		
		return session;
	}
	
	/**
	 * Save this game instance
	 */
	public void saveToCache(){
		CacheUtility util = new CacheUtility();
		util.put(getGameId(), this);
	}
	
	public Game getGame(){
		return game;
	}
	
	/*
	 * Creates player and returns the channel id
	 */
	protected UserSession addPlayer(){
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
	
	protected void sendToAll(Map<String,Object> data){
		sendToAll(JSONUtility.convertToJson(data));
	}
	
	protected void sendToAll(String message){
		for(UserSession player : players){
			player.sendMessage(message);
		}
	}
	
	protected void updateAllPlayersCards(){
		for(UserSession player : players){
			Player gamePlayer = game.getPlayerById(player.getPlayerGameID());
			String jsonString = gamePlayer.toJSON();
			player.sendMessage(jsonString);
		}
	}
	
	protected void updateGameBoard(){
		String jsonString = ((GameBase)game).toJSON();
		for(UserSession player : players){
			player.sendMessage(jsonString);
		}
	}
	
	protected UserSession getUserSession(UUID id){
		for(UserSession player : players){
			if(player.getChannelKey().equals(id))
				return player;
		}
		
		return null;
	}
	
	protected UserSession getUserSession(int id){
		for(UserSession player : players){
			if(player.getPlayerGameID() == id)
				return player;
		}
		
		return null;
	}
	
	protected UserSession getCurentUserSession(){
		return getUserSession(game.getCurrentPlayer());
	}
	
	protected UserSession getUserSession(Player player){
		return getUserSession(player.getPlayerID());
	}
	
	protected UUID getGameId() {
		return gameId;
	}

	protected void setGameId(UUID gameId) {
		this.gameId = gameId;
	}
	
	protected List<UserSession> getPlayers() {
		return players;
	}

	protected void setPlayers(List<UserSession> players) {
		this.players = players;
	}
}
