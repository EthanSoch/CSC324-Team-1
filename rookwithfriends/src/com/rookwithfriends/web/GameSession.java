package com.rookwithfriends.web;

import java.util.*;

import com.google.appengine.api.channel.ChannelMessage;
import com.google.appengine.api.channel.ChannelService;
import com.google.appengine.api.channel.ChannelServiceFactory;

import com.rookwithfriends.game.*;

public class GameSession {
	private Game game;
	private UUID gameId;
	private List<UserSession> players;
	
	public GameSession(){
		//game = new RookGame();
		players = new ArrayList<UserSession>();
		
		gameId = UUID.randomUUID();
	}
	
	public void sendMessage(String message, UserSession player){
		ChannelService channelService = ChannelServiceFactory.getChannelService();
		channelService.sendMessage(new ChannelMessage(player.getChannelKey().toString(), message));
	}
	
	/*
	 * Creates player and returns the channel id
	 */
	public UUID addPlayer(){
		int gamePlayerID = game.addPlayer();
		UUID channelKey = UUID.randomUUID();
		players.add(new UserSession(gamePlayerID, channelKey));
		return channelKey;
	}
	
	public Game getGame(){
		return game;
	}
	
	public UUID getGameId() {
		return gameId;
	}

	public void setGameId(UUID gameId) {
		this.gameId = gameId;
	}
}
