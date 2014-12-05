package com.rookwithfriends.web;

import java.io.Serializable;
import java.util.Map;
import java.util.UUID;

import com.google.appengine.api.channel.ChannelMessage;
import com.google.appengine.api.channel.ChannelService;
import com.google.appengine.api.channel.ChannelServiceFactory;
import com.rookwithfriends.game.CardSet;
import com.rookwithfriends.util.JSONUtility;

public class UserSession implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6053302007709278003L;
	private int gamePlayerID;
	private UUID channelKey;
	
	public UserSession(int gamePlayerID,UUID channelKey){
		this.gamePlayerID = gamePlayerID;
		this.channelKey = channelKey;
	}
	
	public void sendMessage(CardSet cards){
		
	}
	
	public void sendMessage(Map response){
		String responseJSON = JSONUtility.convertToJson(response);
		sendMessage(responseJSON);
	}
	
	public void sendMessage(String message){
		ChannelService channelService = ChannelServiceFactory.getChannelService();
		channelService.sendMessage(new ChannelMessage(getChannelKey().toString(), message));
	}
	
	public int getPlayerGameID() {
		return gamePlayerID;
	}
	public void setPlayerGameID(int gamePlayerID) {
		this.gamePlayerID = gamePlayerID;
	}
	public UUID getChannelKey() {
		return channelKey;
	}
	public void setChannelKey(UUID channelKey) {
		this.channelKey = channelKey;
	}
}
