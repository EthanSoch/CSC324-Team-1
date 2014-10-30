var myModule = angular.module('rook', []);

myModule.controller('myController', function($scope) {
	$scope.players = [false,false,false,false];
	$scope.numplayers = 0;
	$scope.inviteUrl = "";
	
	$scope.change = function() {
		$scope.numplayers = 0;
		$scope.players.forEach( function(player){
			if(player)
				$scope.numplayers++;
		});
	};
	
	$scope.onMessage = function(msg){
		var data = JSON.parse(msg.data);

		$scope.players[data.playerConnected] = true;
		
	    $scope.change();
	    $scope.$apply();
	}
	
	$scope.onOpened = function() {
	    console.log("connected");
	    $scope.change();
	    $scope.$apply();
	}

	$scope.onError = function(err) {
	    alert(err);
	}

	$scope.onClose = function() {
	    alert("Channel closed!");
	}
	
	$scope.connect = function(){
			var gameId = getQueryStringValue("gameId");
			var data = {"isNewGame" : true};
			//if url contains a game id Create new game
			if(gameId != ""){
				data.gameId = gameId;
				data.isNewGame = false;
			}
			
			var serverMessage;
			
			jQuery.ajax({
				  data: data,
				  type: "GET",
				  url: "rook/messages",
				  async: false
			}).done(function( msg ) {
				serverMessage = JSON.parse(msg);
		  	});
			
			console.log(serverMessage.gameId);
			
			for(var i = 0; i <  serverMessage.connectedPlayer; i++)
				$scope.players[i] = true;
			
			channel = new goog.appengine.Channel(serverMessage.token);
		    socket = channel.open();
		    socket.onopen = $scope.onOpened;
		    socket.onmessage = $scope.onMessage;
		    socket.onerror = $scope.onError;
		    socket.onclose = $scope.onClose;
		    
		    $scope.inviteUrl = window.location.protocol + "//" + window.location.host + window.location.pathname + "?gameId=" +serverMessage.gameId;
	}
});

function getQueryStringValue (key) {  
	  return unescape(window.location.search.replace(new RegExp("^(?:.*[&\\?]" + escape(key).replace(/[\.\+\*]/g, "\\$&") + "(?:\\=([^&]*))?)?.*$", "i"), "$1"));  
}  