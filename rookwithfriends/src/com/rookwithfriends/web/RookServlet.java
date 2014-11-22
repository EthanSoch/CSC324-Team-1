package com.rookwithfriends.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.channel.ChannelService;
import com.google.appengine.api.channel.ChannelServiceFactory;
import com.rookwithfriends.util.JSONUtility;

@SuppressWarnings("serial")
public class RookServlet extends HttpServlet {
	@Override
	protected void doGet( HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//If new game bool
		boolean createNewGame = request.getParameter("isNewGame").equals("true") ? true : false ;
		UUID gameID;
		
		//If new game create game and save to cache else get game id from request parameter
		if(createNewGame){
			gameID = createNewGame();
		} else{
			String gameIDString = request.getParameter("gameId");
			gameID = UUID.fromString(gameIDString);
		}
		
		//get session and add player
		GameSession session = GameSession.getGameSession(gameID);
		UserSession newPlayer = session.addPlayer();
		session.saveToCache();
		
		//Create channelService for player using channelKey as the key
		ChannelService channelService = ChannelServiceFactory.getChannelService();
		String channelKey = newPlayer.getChannelKey().toString();
		String token = channelService.createChannel(channelKey);
		
		//Generate Client return object
		Map<String,Object> returnObject = new HashMap<String, Object>();
		returnObject.put("token", token);
		returnObject.put("connectedPlayers", session.getPlayers().size());
		returnObject.put("gameId", session.getGameId().toString());
		returnObject.put("playerId", newPlayer.getChannelKey().toString());
		
		response.setContentType("text/plain");
		response.getWriter().write(JSONUtility.convertToJson(returnObject));
	}
	
	@Override
	protected void doPost( HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		
		//String message = request.getParameter("message");
		UUID gameID = UUID.fromString(request.getParameter("gameId"));
		
		Map<String,String[]> paramaters = request.getParameterMap();
		
		//get Session
		GameSession session = GameSession.getGameSession(gameID);
		session.gameInstruction(paramaters);
		
		
		//for(UserSession player : session.getPlayers()){
		//	player.sendMessage(message);
		//}
	}

	private UUID createNewGame() {
		GameSession newSession = new GameSession();
		newSession.saveToCache();

		return newSession.getGameId();
	}
}
