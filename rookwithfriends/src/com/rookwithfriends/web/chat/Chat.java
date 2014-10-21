package com.rookwithfriends.web.chat;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.channel.ChannelMessage;
import com.google.appengine.api.channel.ChannelService;
import com.google.appengine.api.channel.ChannelServiceFactory;

@SuppressWarnings("serial")
public class Chat extends HttpServlet {
	@Override
	protected void doGet( HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		ChannelService channelService = ChannelServiceFactory.getChannelService();
		
		String channelKey = "xyz";
		String token = channelService.createChannel(channelKey);

		response.setContentType("text/plain");
		response.getWriter().write(token);
	}
	
	@Override
	protected void doPost( HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		
		String message = request.getParameter("message");
		
		ChannelService channelService = ChannelServiceFactory.getChannelService();
		String channelKey = "xyz";
		channelService.sendMessage(new ChannelMessage(channelKey, message));
	}
}
