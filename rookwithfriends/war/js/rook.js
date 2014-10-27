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
		var data = msg.data;
		
		$scope.players[data.playerConnected] = true;
		
	}
	
	$scope.onOpened = function() {
	    console.log("connected");
	    jQuery($scope.players).each(function(i,player){
	    	if(!player){
	    		$scope.players[i] = true;
	    		return false;
	    	}
	    });
	    
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
			var token;
			var data = {"isNewGame" : true};
			
			jQuery.ajax({
				  data: data,
				  type: "GET",
				  url: "rook/messages",
				  async: false
			}).done(function( msg ) {
				token = msg;
		  	});
			
			
			channel = new goog.appengine.Channel(token);
		    socket = channel.open();
		    socket.onopen = $scope.onOpened;
		    socket.onmessage = $scope.onMessage;
		    socket.onerror = $scope.onError;
		    socket.onclose = $scope.onClose;
	}
});