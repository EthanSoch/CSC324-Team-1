package com.rookwithfriends.web;

import java.util.*;
import java.io.*;

import com.google.gson.Gson;
import com.rookwithfriends.game.*;
import com.rookwithfriends.util.*;

public class GameSession implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -443526662938702934L;
	//private Game game;//Can't create the game right now cause it needs to be Serializable
	private UUID gameId;
	private List<UserSession> players;
	
	public static GameSession getGameSession(UUID gameId){
		CacheUtility util = new CacheUtility();
		GameSession session = (GameSession) util.get(gameId);
		
		return session;
	}
	
	public GameSession(){
		//game = new RookGame();
		players = new ArrayList<UserSession>();
		
		gameId = UUID.randomUUID();
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
		String playerIdString = String.valueOf(newPlayer.getGameID());
		response.put("playerConnected", playerIdString);
    	
		String responseJSON = JSONUtility.convertToJson(response);
		
		for(UserSession player : players){
			if(!player.getChannelKey().equals(newPlayer.getChannelKey()))
				player.sendMessage(responseJSON);
		}
		
		return newPlayer;
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

	public void startGame(){
		//TODO Needs to be inplemented
	}
}
