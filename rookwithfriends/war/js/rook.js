var myModule = angular.module('rook', []);

myModule.controller('myController', function($scope) {
	$scope.players = [false,false,false,false];
	$scope.numplayers = 0;
	
	$scope.change = function() {
		$scope.numplayers = 0;
		$scope.players.forEach( function(player){
			if(player)
				$scope.numplayers++;
		});
	};
	
	$scope.onMessage = function(msg){
		var data = JSON.parse(msg.data);
		console.log("Message data" + data);
		
		$scope.players[data.playerConnected] = true;
		
	    $scope.change();
	    $scope.$apply();
	}
	
	$scope.onOpened = function() {
	    console.log("connected");
	    
	    /*
	    jQuery($scope.players).each(function(i,player){
	    	if(!player){
	    		$scope.players[i] = true;
	    		return false;
	    	}
	    });
	    */
	    $scope.change();
	    $scope.$apply();
	}

	$scope.onError = function(err) {
	    alert(err);
	}

	$scope.onClose = function() {
	    alert("Channel closed!");
	}
	
	$scope.connect = function(isNewGame){
			var serverMessage;
			var data = {"isNewGame" : isNewGame};
			
			if(!isNewGame)
				data.gameId = jQuery("#gameId").val();
			
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
	}
});