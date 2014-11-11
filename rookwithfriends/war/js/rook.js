var myModule = angular.module('rook', ['ngRoute']);

myModule.controller('myController', function($scope, $location) {
  $scope.players = [false,false,false,false];
  $scope.numplayers = 0;
  $scope.inviteUrl = "";
  $scope.playersConnected = true;
  $scope.middleHand = [];
  $scope.playerHand = [];
  
  $scope.middleHand[0] = {num:1,color:"red"};
  $scope.middleHand[1] = {num:1,color:"green"};
  $scope.middleHand[2] = {num:1,color:"black"};
  $scope.middleHand[3] = {num:1,color:"yellow"};
  $scope.middleHand[4] = {num:10.5,color:"white"};
  
  $scope.playerHand[0] = {num:1,color:"red"};
  $scope.playerHand[1] = {num:1,color:"green"};
  $scope.playerHand[2] = {num:1,color:"black"};
  $scope.playerHand[3] = {num:1,color:"yellow"};
  $scope.playerHand[4] = {num:1,color:"green"};
  $scope.playerHand[5] = {num:14,color:"red"};
  $scope.playerHand[6] = {num:1,color:"green"};
  $scope.playerHand[7] = {num:1,color:"black"};
  $scope.playerHand[8] = {num:1,color:"yellow"};
  $scope.playerHand[9] = {num:1,color:"green"};
  
  $scope.change = function() {
    $scope.numplayers = 0;
    $scope.players.forEach( function(player){
      if(player)
        $scope.numplayers++;
      if($scope.numplayers == 4){
	        //$scope.playersConnected = true; -- Used to add find when all players have connected if needed
	        $location.path('/RookBoard'); // Fix this
	      }
    });
  };

  //Players
  $scope.player1 = "player 1";
  $scope.player2 = "player 2";
  $scope.player3 = "player 3";
  $scope.player4 = "player 4";

  $scope.onMessage = function(msg){
    var data = JSON.parse(msg.data);
    $scope.players[data.playerConnected] = true;
    
    $scope.change();
    $scope.$apply();
  }
  
  $scope.onOpened = function() {
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

myModule.config(function($routeProvider, $locationProvider) {
    $routeProvider

        // route for the home page
        .when('/', {
            templateUrl : 'RookGame/lobby.html',
            controller  : 'myController'
        })

        // route for the game page
        .when('/RookBoard', {
            templateUrl : 'RookGame/RookBoard.jsp',
            controller  : 'myController'
        });
	 // use the HTML5 History API
		$locationProvider.html5Mode(true);
});


function getQueryStringValue (key) {  
    return unescape(window.location.search.replace(new RegExp("^(?:.*[&\\?]" + escape(key).replace(/[\.\+\*]/g, "\\$&") + "(?:\\=([^&]*))?)?.*$", "i"), "$1"));  
}  
