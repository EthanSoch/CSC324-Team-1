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
					card.attr("data-selected","false");
				}
				else if(rookGame.getSelectedCards().length < numberOfCards){
					card.attr("data-selected","true");
				}
			});
			
			rookGame.scope.canSubmitCards = true;
			rookGame.scope.cardsToSubmit = numberOfCards;
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
			rookGame.scope.canSubmitCards = false;
		}
	}
})(jQuery);

rookGame.gameController = function($scope, $modal, $location, $log, $rootScope){
	$scope.numplayers = 0;
	$scope.inviteUrl = "";
	$scope.middleHand = [];
	$scope.playerHand = [];
	$scope.showChat = false;
	$scope.trump = "";
	$scope.trickColor = "";
	$scope.modalID = "myModalContent.html";
	$scope.opponents = [{name:"Player 1",bid:0},{name:"Player 2",bid:0},{name:"Player 3",bid:0},{name:"Player 4",bid:0}];
	rookGame.scope = $scope;
	$scope.selectedIndex = -1; /* Not Selected */
	$scope.canSubmitCards = false;
	$scope.cardsToSubmit = 0;
	$rootScope.winningTeam = "PlaceHolder"
	
	$scope.select= function(i) {
	  $scope.selectedIndex=i;
	};
	//Values for Bids/Scores//
		
		$scope.team2Score = 0;
		$scope.team1Score = 0;
		
		$rootScope.topBid = 100;
		
	
	  $scope.items = ['item1', 'item2', 'item3'];
	  $scope.open = function (page){
		  $rootScope.modalVal = page;
	     var modalInstance = $modal.open({
	      templateUrl: page,
	      controller: 'modalController',
	      resolve: {
	        items: function () {
	          return $scope.items;
	        }
	      }
	    });

	    modalInstance.result.then(function (selectedItem) {
	      $scope.selected = selectedItem;
	    }, function () {
	      $log.info('Modal dismissed at: ' + new Date());
	    });
	  };

	// Please note that $modalInstance represents a modal window (instance) dependency.
	// It is not the same as the $modal service used above.
	$scope.discardTheFive = function(){
		rookGame.send("discardFive", {"theFive" : rookGame.getSelectedCards()});
	}

	onMessage = function(msg) {
		var data = JSON.parse(msg.data);

		if(data.msg != undefined) {
			printToConsole(data.msg);
		}
		
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
		
		if (data.startBidding != undefined) {
			 $scope.open("myModalContent.html");
		}
		
		if (data.chooseTrump != undefined) {
			 $scope.open("trumpContent.html");
		}
		
		if(data.trump != undefined){
			$scope.trump = data.trump;
		}
		
		if(data.newPlayerBid != undefined){
			//#-BID
			console.log(data.newPlayerBid);
			var playerNumber = parseInt(data.newPlayerBid[0]);
			console.log(data.newPlayerBid);
			var playerBidNew = parseInt(data.newPlayerBid.substring(2, data.newPlayerBid.length));
			
			if(playerBidNew == 0){
				$scope.opponents[playerNumber].bid = "pass";
			}else{
				$scope.opponents[playerNumber].bid = playerBidNew;
				$rootScope.topBid = playerBidNew;
			}
			
		}
		
		if(data.discardFive != undefined){
			rookGame.selectCards(5);
		}
		
		if(data.trickColor != undefined){
			$scope.trickColor = data.trickColor;
		}

		if(data.playCard != undefined){
			rookGame.selectCards(1);
		}

		$scope.$apply();
  	}
	
	$scope.submitCards = function(cardsToSubmit){
		var cardIsGood = false;
		var cards = rookGame.getSelectedCards();
			
		if(cards.length == cardsToSubmit){
			var operation = cardsToSubmit == 5 ? "discardFive" : "playCard";
			if(operation=="playCard")
			{
				while(!cardIsGood)
				{
					if(cards[0].color != $scope.trump && cards[0].color != $scope.trickColor && cards[0].color != "white")
					{	//card wasn't valid
						// loop through $scope.playerHand
						
						var hasGoodColor = false;
						//check if they have a valid card
						for(var i = 0; i < $scope.playerHand.length; i++)
						{
							var temp = $scope.playerHand[i];
							if(temp.color == $scope.trickColor)
							{
								hasGoodColor=true;
							}
						}
						
						//they have a valid card
						if(hasGoodColor)
						{
						// they need to choose card again
							cardIsGood = false;
							cards = rookGame.getSelectedCards();
						}
						//they don't have a valid card, let them play it
						else
						{
							cardIsGood = true;
						}
					}
					else
					{
						cardIsGood = true;
					}
				}
			}
			rookGame.send(operation, {"cards":cards});
			rookGame.deselectAllCards();
		}else{
			alert("You must select " + cardsToSubmit +" cards");
		}
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

rookGame.modalController = function($scope, $modalInstance, items, $rootScope){
	$scope.bidWarning = false;
	$scope.colorWarning = false;
	$scope.value = 150;
	$scope.selectedItem = null;
	$scope.items = items;
	$scope.selected = {
			item: $scope.items[0]
	};
	  
    $scope.okBet = function (bet) {
    	if (bet <= $rootScope.topBid && bet != 0) {
			$scope.bidWarning = true;
		} else {
			var data = {
				"playerBet" : bet
			};
			rookGame.send("bid", data);

			$modalInstance.close($scope.selected.item);
		}
	};
	  
    $scope.okColor = function () {
    	if ($scope.selectedItem == null){
    		$scope.colorWarning = true;
    	}
    	else{
			rookGame.send("trump",{"theTrump" : $scope.selectedItem});
    		$modalInstance.close($scope.selected.item);
		}
    };

	$scope.cancel = function () {
	    $modalInstance.dismiss('cancel');
	  };

    $scope.selectItem = function( item ) {
        $scope.selectedItem = item;
    };
	 
}
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

var myModule = angular.module('rook', ['ngRoute','ui.bootstrap']);

myModule.controller('myController', rookGame.gameController);
myModule.controller('modalController', rookGame.modalController);

myModule.config(rookGame.routeProvider);
