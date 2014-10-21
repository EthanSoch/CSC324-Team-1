package com.rookwithfriends.web;

import java.util.UUID;

public class UserSession {
	private int gamePlayerID;
	private UUID channelKey;
	
	public UserSession(int gamePlayerID,UUID channelKey){
		this.gamePlayerID = gamePlayerID;
		this.channelKey = channelKey;
	}
	
	public int getGameID() {
		return gamePlayerID;
	}
	public void setGameID(int gamePlayerID) {
		this.gamePlayerID = gamePlayerID;
	}
	public UUID getChannelKey() {
		return channelKey;
	}
	public void setChannelKey(UUID channelKey) {
		this.channelKey = channelKey;
	}
}
