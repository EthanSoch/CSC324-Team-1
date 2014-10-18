<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style>
	#chatWindow{
		display: inline-block;
		border:solid 1px black;
	}
	
	#messages{
		height:500px;
		width:400px;
	}
	
	#buttonBar{
		border-top:solid 1px black;
	}
	
	#messageInput{
		width:300px;
	}
</style>
</head>
<body>
	<div id="chatWindow">
		<div id="messages"></div>
		<div id="buttonBar">
			<input type="text" id="messageInput">
			<input type="button" id="sendButton" value="send" onclick="sendMessage()">
			<input type="button" id="connectButton" value="connect" onclick="connect()">
		</div>
	</div>
	
	<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
	<script type="text/javascript" src="/_ah/channel/jsapi"></script>
	<script>
	function sendMessage(){
		var msg = {"message": $("#messageInput").val()};
		
		$.ajax({
			  type: "POST",
			  url: "chat/message",
			  data: msg,
			  dataType: "text/plain"
		});
	}
	
	function printToConsole(message){
		var node = document.createElement("p");
		node.innerHTML = message
		$("#messages")[0].appendChild(node);
	}
	
	function onOpened() {
	    printToConsole("Channel opened!");
	}

	function onMessage(msg) {
		printToConsole(msg.data);
	}

	function onError(err) {
	    alert(err);
	}

	function onClose() {
	    alert("Channel closed!");
	}
	
	function connect(){
		var token;
		
		$.ajax({
			  type: "GET",
			  url: "chat/message",
			  async: false
		}).done(function( msg ) {
			token = msg;
	  	});
		
		
		channel = new goog.appengine.Channel(token);
	    socket = channel.open();
	    socket.onopen = onOpened;
	    socket.onmessage = onMessage;
	    socket.onerror = onError;
	    socket.onclose = onClose;
	}
	</script>
</body>
</html>