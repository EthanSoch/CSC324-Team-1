var myModule = angular.module('rook', ['ngRoute']);

myModule.controller('myController', function($scope, $location) {
  $scope.players = [false,false,false,false];
  $scope.numplayers = 0;
  $scope.inviteUrl = "";
  $scope.playersConnected = true;
  
  
  
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

  //Middle Hand Numbers
  $scope.MTN1 = 1;
  $scope.MBN1 = 1;

  $scope.MTN2 = 4;
  $scope.MBN2 = 4;

  $scope.MTN3 = 6;
  $scope.MBN3 = 6;

  $scope.MTN4 = 1;
  $scope.MBN4 = 1;

  $scope.MTN5 = 2;
  $scope.MBN5 = 2;

  //Middle Hand Card Colors
  $scope.MTC1 = "Red";
  $scope.MBC1 = "Red";

  $scope.MTC2 = "Green";
  $scope.MBC2 = "Green";

  $scope.MTC3 = "Black";
  $scope.MBC3 = "Black";

  $scope.MTC4 = "Yellow";
  $scope.MBC4 = "Yellow";

  $scope.MTC5 = "Green";
  $scope.MBC5 = "Green";

  //Player Hand Numbers
  $scope.PTN1 = 1;
  $scope.PBN1 = 1;

  $scope.PTN2 = 4;
  $scope.PBN2 = 4;

  $scope.PTN3 = 6;
  $scope.PBN3 = 6;

  $scope.PTN4 = 1;
  $scope.PBN4 = 1;

  $scope.PTN5 = 2;
  $scope.PBN5 = 2;

  $scope.PTN6 = 1;
  $scope.PBN6 = 1;

  $scope.PTN7 = 4;
  $scope.PBN7 = 4;

  $scope.PTN8 = 6;
  $scope.PBN8 = 6;

  $scope.PTN9 = 1;
  $scope.PBN9 = 1;

  $scope.PTN10 = 2;
  $scope.PBN10 = 2;

  //Player Hand Card Colors
  $scope.PTC1 = "Red";
  $scope.PBC1 = "Red";

  $scope.PTC2 = "Green";
  $scope.PBC2 = "Green";

  $scope.PTC3 = "Black";
  $scope.PBC3 = "Black";

  $scope.PTC4 = "Yellow";
  $scope.PBC4 = "Yellow";

  $scope.PTC5 = "Green";
  $scope.PBC5 = "Green";

  $scope.PTC6 = "Red";
  $scope.PBC6 = "Red";

  $scope.PTC7 = "Green";
  $scope.PBC7 = "Green";

  $scope.PTC8 = "Black";
  $scope.PBC8 = "Black";

  $scope.PTC9 = "Yellow";
  $scope.PBC9 = "Yellow";

  $scope.PTC10 = "Green";
  $scope.PBC10 = "Green";


  //Setting Middle Hand Card Classes
  $scope.MC1 = function() {
        var className = '';

        if ($scope.MTC1 == "Red")
            className += 'red';

        else if ($scope.MTC1 == "Black")
            className += 'black';

        else if ($scope.MTC1 == "Yellow")
            className += 'yellow';          

        else if ($scope.MTC1 == "Green")
            className += 'green';

        return className;
    };

   $scope.MC2 = function() {
        var className = '';

        if ($scope.MTC2 == "Red")
            className += 'red';

        else if ($scope.MTC2 == "Black")
            className += 'black';

        else if ($scope.MTC2 == "Yellow")
            className += 'yellow';          

        else if ($scope.MTC2 == "Green")
            className += 'green';

        return className;
    };
    $scope.MC3 = function() {
        var className = '';

        if ($scope.MTC3 == "Red")
            className += 'red';

        else if ($scope.MTC3 == "Black")
            className += 'black';

        else if ($scope.MTC3 == "Yellow")
            className += 'yellow';          

        else if ($scope.MTC3 == "Green")
            className += 'green';

        return className;
    };
    $scope.MC4 = function() {
        var className = '';

        if ($scope.MTC4 == "Red")
            className += 'red';

        else if ($scope.MTC4 == "Black")
            className += 'black';

        else if ($scope.MTC4 == "Yellow")
            className += 'yellow';          

        else if ($scope.MTC4 == "Green")
            className += 'green';

        return className;
    };
    $scope.MC5 = function() {
        var className = '';

        if ($scope.MTC5 == "Red")
            className += 'red';

        else if ($scope.MTC5 == "Black")
            className += 'black';

        else if ($scope.MTC5 == "Yellow")
            className += 'yellow';          

        else if ($scope.MTC5 == "Green")
            className += 'green';

        return className;
    };

  //Setting Players Hand Classes
    $scope.PC1 = function() {
        var className = '';

        if ($scope.PTC1 == "Red")
            className += 'red';

        else if ($scope.PTC1 == "Black")
            className += 'black';

        else if ($scope.PTC1 == "Yellow")
            className += 'yellow';          

        else if ($scope.PTC1 == "Green")
            className += 'green';

        return className;
    };
    $scope.PC2 = function() {
        var className = '';

        if ($scope.PTC2 == "Red")
            className += 'red';

        else if ($scope.PTC2 == "Black")
            className += 'black';

        else if ($scope.PTC2 == "Yellow")
            className += 'yellow';          

        else if ($scope.PTC2 == "Green")
            className += 'green';

        return className;
    };
    $scope.PC3 = function() {
        var className = '';

        if ($scope.PTC3== "Red")
            className += 'red';

        else if ($scope.PTC3 == "Black")
            className += 'black';

        else if ($scope.PTC3 == "Yellow")
            className += 'yellow';          

        else if ($scope.PTC3 == "Green")
            className += 'green';

        return className;
    };
    $scope.PC4 = function() {
        var className = '';

        if ($scope.PTC4 == "Red")
            className += 'red';

        else if ($scope.PTC4 == "Black")
            className += 'black';

        else if ($scope.PTC4 == "Yellow")
            className += 'yellow';          

        else if ($scope.PTC4 == "Green")
            className += 'green';

        return className;
    };
    $scope.PC5 = function() {
        var className = '';

        if ($scope.PTC5 == "Red")
            className += 'red';

        else if ($scope.PTC5 == "Black")
            className += 'black';

        else if ($scope.PTC5 == "Yellow")
            className += 'yellow';          

        else if ($scope.PTC5 == "Green")
            className += 'green';

        return className;
    };
    $scope.PC6 = function() {
        var className = '';

        if ($scope.PTC6 == "Red")
            className += 'red';

        else if ($scope.PTC6 == "Black")
            className += 'black';

        else if ($scope.PTC6 == "Yellow")
            className += 'yellow';          

        else if ($scope.PTC6 == "Green")
            className += 'green';

        return className;
    };
    $scope.PC7 = function() {
        var className = '';

        if ($scope.PTC7 == "Red")
            className += 'red';

        else if ($scope.PTC7 == "Black")
            className += 'black';

        else if ($scope.PTC7 == "Yellow")
            className += 'yellow';          

        else if ($scope.PTC7 == "Green")
            className += 'green';

        return className;
    };
    $scope.PC8 = function() {
        var className = '';

        if ($scope.PTC8 == "Red")
            className += 'red';

        else if ($scope.PTC8 == "Black")
            className += 'black';

        else if ($scope.PTC8 == "Yellow")
            className += 'yellow';          

        else if ($scope.PTC8 == "Green")
            className += 'green';

        return className;
    };
    $scope.PC9 = function() {
        var className = '';

        if ($scope.PTC9 == "Red")
            className += 'red';

        else if ($scope.PTC9 == "Black")
            className += 'black';

        else if ($scope.PTC9 == "Yellow")
            className += 'yellow';          

        else if ($scope.PTC9 == "Green")
            className += 'green';

        return className;
    };
    $scope.PC10 = function() {
        var className = '';

        if ($scope.PTC10 == "Red")
            className += 'red';

        else if ($scope.PTC10 == "Black")
            className += 'black';

        else if ($scope.PTC10 == "Yellow")
            className += 'yellow';          

        else if ($scope.PTC10 == "Green")
            className += 'green';

        return className;
    };

 /* $scope.checkConnected = function(){

  }*/
 


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
            templateUrl : 'lobby.html',
            controller  : 'myController'
        })

        // route for the game page
        .when('/RookBoard', {
            templateUrl : 'RookBoard.html',
            controller  : 'myController'
        });
	 // use the HTML5 History API
		$locationProvider.html5Mode(true);
});


function getQueryStringValue (key) {  
    return unescape(window.location.search.replace(new RegExp("^(?:.*[&\\?]" + escape(key).replace(/[\.\+\*]/g, "\\$&") + "(?:\\=([^&]*))?)?.*$", "i"), "$1"));  
}  