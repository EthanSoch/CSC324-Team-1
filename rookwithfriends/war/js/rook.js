var rookGame = (function($){
	return{
		scope : {},
		gameId: "",
		playerId: "",
		send : function(operation,data){
			data = data == undefined ? {} : data;
			data.op = operation;
			data.gameId = rookGame.gameId;
			data.playerId = rookGame.playerId;
			$.post("rook/messages",data)
		},
		refresh : function(){
			scope.$apply();
		},
		getQueryStringValue : function(key){
			return unescape(window.location.search.replace(new RegExp("^(?:.*[&\\?]" + escape(key).replace(/[\.\+\*]/g, "\\$&") + "(?:\\=([^&]*))?)?.*$", "i"), "$1"));
		},
		selectCards:function(numberOfCards){
			$(".card").click(function(elm){
				var card = $(elm.currentTarget);
				if(card.attr("data-selected") == "true"){
					card.css("bottom","");
					card.attr("data-selected","false");
				}
				else if(rookGame.getSelectedCards().length < numberOfCards){
					card.css("bottom","45px");
					card.attr("data-selected","true");
				}
			});
		},
		getSelectedCards:function(){
			var cardData = [];
			 $(".card[data-selected='true']").each(function(i,card){
				 var dataObj = JSON.parse($(card).attr("data-card"));
				 cardData.push(dataObj)
			 });
			 
			return cardData;
		},
		deselectAllCards : function(){
			$(".card").attr("data-selected","false").css("bottom","").unbind("click");
		}
	}
})(jQuery);

rookGame.gameController = function($scope, $location){
	$scope.numplayers = 0;
	$scope.inviteUrl = "";
	$scope.middleHand = [];
	$scope.playerHand = [];
	$scope.opponentNames = ["Player 1","Player 2","Player 3","Player 4"];
	rookGame.scope = $scope;

	onMessage = function(msg) {
		var data = JSON.parse(msg.data);

		if (data.centerDeck != undefined) {
			$scope.middleHand = data.centerDeck;
		}

		if (data.hand != undefined) {
			$scope.playerHand = data.hand;
		}

		if (data.playersConnected != undefined) {
			$scope.numplayers = parseInt(data.playersConnected);
			if ($scope.numplayers >= 4)
				$location.path('/RookBoard');
		}

		$scope.$apply();
  	}

		  
	onOpened = function() {
		if ($scope.numplayers >= 4) {
			rookGame.send("start");
			$location.path('/RookBoard');
		}

		$scope.$apply();
	}

	onError = function(err) {
		console.log(err);
	}

	onClose = function() {
		console.log("Channel closed!");
	}

	$scope.connect = function() {
		var gameId = rookGame.getQueryStringValue("gameId");
		var data = {
			"isNewGame" : true
		};
		// if url contains a game id Create new game
		if (gameId != "") {
			data.gameId = gameId;
			data.isNewGame = false;
		}

		jQuery.ajax({
			data : data,
			type : "GET",
			url : "rook/messages",
			async : true
		}).done(
				function(msg) {
					var serverMessage = JSON.parse(msg);

					console.log(serverMessage.gameId);
					rookGame.gameId = serverMessage.gameId;
					rookGame.playerId = serverMessage.playerId;

					$scope.numplayers = serverMessage.connectedPlayers;

					channel = new goog.appengine.Channel(serverMessage.token);
					socket = channel.open();
					socket.onopen = onOpened;
					socket.onmessage = onMessage;
					socket.onerror = onError;
					socket.onclose = onClose;

					$scope.inviteUrl = window.location.protocol + "//"
							+ window.location.host + window.location.pathname
							+ "?gameId=" + serverMessage.gameId;
					jQuery("#searchbox").val($scope.inviteUrl);
				});
	}
};

rookGame.routeProvider = function($routeProvider, $locationProvider) {
    $routeProvider
    // route for the home page
    .when('/', {
        templateUrl : 'RookGame/lobby.html'
    })

    // route for the game page
    .when('/RookBoard', {
        templateUrl : 'RookGame/RookBoard.jsp'
    });
 // use the HTML5 History API
	$locationProvider.html5Mode(true);
};

var myModule = angular.module('rook', ['ngRoute']);

myModule.controller('myController', rookGame.gameController);

myModule.config(rookGame.routeProvider);
