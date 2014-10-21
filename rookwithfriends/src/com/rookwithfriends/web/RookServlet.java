package com.rookwithfriends.web;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

import javax.jws.WebParam;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.channel.ChannelMessage;
import com.google.appengine.api.channel.ChannelService;
import com.google.appengine.api.channel.ChannelServiceFactory;
import com.google.gwt.dev.util.collect.HashMap;
import com.rookwithfriends.util.CacheUtility;
import com.rookwithfriends.util.JSONUtility;;

@SuppressWarnings("serial")
public class RookServlet extends HttpServlet {
	@Override
	protected void doGet( HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//connecting or creating a new game?
		//TODO paramaters to connect to existing game or host new game
		boolean createNewGame = true;
		String channelKey;
		
		Map<String, String> returnObject = new HashMap<String, String>();
		
		if(createNewGame){
			//adds gameId to returnObject
			channelKey = createNewGame(returnObject);
		} else{
			//get session
			channelKey = addToExistingGame(request.getParameter("gameID"));
		}
		
		ChannelService channelService = ChannelServiceFactory.getChannelService();
		String token = channelService.createChannel(channelKey);
		returnObject.put("token", token);
		
		String returnJsonObject = JSONUtility.convertToJson(returnObject);

		response.setContentType("text/plain");
		response.getWriter().write(returnJsonObject);
	}
	
	@Override
	protected void doPost( HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		
		String message = request.getParameter("message");
		
		ChannelService channelService = ChannelServiceFactory.getChannelService();
		String channelKey = "xyz";
		channelService.sendMessage(new ChannelMessage(channelKey, message));
	}
	
	private String addToExistingGame(String gameIDString) {
		CacheUtility util = new CacheUtility();
		
		//TODO get channel key from client on player join
		UUID gameID = UUID.fromString(gameIDString);
		GameSession session = (GameSession) util.get(gameID);
		
		String channelKey = session.addPlayer().toString();
		
		return channelKey;
	}

	private String createNewGame(Map<String, String> returnObject) {
		GameSession newSession = new GameSession();
		String channelKey = newSession.addPlayer().toString();
		
		CacheUtility util = new CacheUtility();
		util.put(newSession.getGameId(), newSession);

		returnObject.put("gameId", newSession.getGameId().toString());
		return channelKey;
	}
}
