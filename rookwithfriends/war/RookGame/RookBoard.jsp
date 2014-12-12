<!-- Page Content -->
<div style="margin: 0 auto 0 auto;width:1300px;">
	<script type="text/ng-template" id="myModalContent.html">
        <div class="modal-header">
            <h3 class="modal-title">Make a Bid!</h3>
        </div>
		<div class="modal-body" id="modalMain">
		<h3>Current Bid is: {{topBid}}</h3> 
		<div id="slider">
			<input step="5" min={{topBid}} max=200 class="bar" type="range" id="rangeinput" ng-model="value" onchange="rangevalue.value=value;"/>
			<output id="rangevalue"><span>50</span></output>
		</div>
		<h3>Your Bid is: {{value}}</h3> 
		</div>
        </div>
		<div class="alert alert-danger" role="alert" ng-show="bidWarning">Bid must be more than current bid, please select again.</div>
        <div class="modal-footer">
            <button class="btn btn-primary" ng-click="okBet(value)">OK</button>
            <button class="btn btn-warning" ng-click="okBet(0)">Pass</button>
        </div>
    </script>
    <script type="text/ng-template" id="gameOver.html">
        <div class="modal-header">
            <h3 class="modal-title">The Game Is Over!</h3>
        </div>
		<div class="modal-body" id="modalMain">
			<h3 id="winningHeader">The Winning Team Is: {{winningTeam}}</h3> 
		<div id ="balloonDiv">
			<div class="balloon" id="balloonLeft"></div>
            <div class="balloon" id="balloonRight"></div>
		</div>
		</div>
        <div class="modal-footer">
            <button class="btn btn-primary" ng-click="okBet(0)">OK</button>
            <button class="btn btn-warning" ng-click="cancel()">Close</button>
        </div>
    </script>
    <script type="text/ng-template" id="trumpContent.html">
        <div class="modal-header">
            <h3 class="modal-title">Select a trump color!</h3>
        </div>
		<div class="modal-body" id="modalMain">
		<div class="color-selection">
  				<ul class="color-select">
  				  <li><a ng-class="{'selected': selectedItem === 'red'}" ng-click="selectItem('red')" style="background: #FF0000;"></a></li>
  				  <li><a ng-class="{'selected': selectedItem === 'black'}" ng-click="selectItem('black')" style="background: #000000;"></a></li>
   				  <li><a ng-class="{'selected': selectedItem === 'green'}" ng-click="selectItem('green')" style="background: #008000;"></a></li>
   				  <li><a ng-class="{'selected': selectedItem === 'yellow'}" ng-click="selectItem('yellow')" style="background: #FFFF00;"></a></li>
  				</ul>
			<span id="colorSelSpan">Please select a color for the trump.</span>
			<div class="alert alert-danger" role="alert" ng-show="colorWarning">Please select a color first.</div>
		</div><!--/ color-selection -->
        </div>
		<div class="alert alert-danger" role="alert" ng-show="bidWarning">Bid must be more than current bid, please select again.</div>
        <div class="modal-footer">
            <button class="btn btn-primary" ng-click="okColor()">OK</button>
        </div>
    </script>
    <!-- Player Stat Boxes -->
	<!--User Avatars and Names-->
	<div class="avatarContainer">
		<div class="StatBox playerMidAlign">
			<div class="box">
				<div class="box__header">
					<h3 class="box__header-title">Team 1</h3>
				</div>
				<div class="box__body">
					<div class="stats stats--main" id="player2Points">Score: {{opponents[(3 + currentPlayer) % 4].score}}</div>
					<div class="stats" id="player2CB">Current Bid: {{opponents[(3 + currentPlayer) % 4].bid}}</div>
				</div>
			</div>
			<a class="circle" id="player2"> <img height="83" width="83"
				src="avatarImg.png">
				<h5 class="playerName">{{opponents[(3 + currentPlayer) % 4].name}}</h5>
			</a>
		</div>
	</div>
	<div>
		<div class="centerStrip avatarContainer" id="player3Container">
			<div class="StatBox">
				<div class="box">
					<div class="box__header">
						<h3 class="box__header-title">Team 2</h3>
					</div>
					<div class="box__body">
						<div class="stats stats--main" id="player2Points">Score: {{opponents[(2 + currentPlayer) % 4].score}}</div>
						<div class="stats" id="player2CB">Current Bid: {{opponents[(2 + currentPlayer) % 4].bid}}</div>
					</div>
				</div>
				<a class="circle" id="player3"> <img height="83" width="83"
					src="avatarImg.png">
					<h5 class="playerName">{{opponents[(2 + currentPlayer) % 4].name}}</h5>
				</a>
			</div>
		</div>
		<div id="table1" class="centerStrip">
			<div id="table2">
				<div id="middleHand">
					<div class="card pCard repeatCards" ng-class="card.color"
						ng-click="select($index)" ng-repeat="card in middleHand"
						data-card="{{card}}">
						<div class="cardTop">
							<div class="number">{{card.rank}}</div>
							<div class="color"></div>
						</div>
						<div class="cardMiddle">{{card.rank}}</div>
						<div class="cardBottom">
							<div class="number">{{card.rank}}</div>
							<div class="color"></div>
						</div>
					</div>
				</div>
				<div id="playerHand">
					<div class="card pCard" ng-class="card.color"
						ng-click="select($index)" ng-repeat="card in playerHand"
						data-card="{{card}}">
						<div class="cardTop">
							<div class="number">{{card.rank}}</div>
							<div class="color"></div>
						</div>
						<div class="cardMiddle">{{card.rank}}</div>
						<div class="cardBottom">
							<div class="number">{{card.rank}}</div>
							<div class="color"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="centerStrip avatarContainer" id="player2Container">
			<div class="StatBox">
				<div class="box">
					<div class="box__header">
						<h3 class="box__header-title">Team 2</h3>
					</div>
					<div class="box__body">
						<div class="stats stats--main" id="player2Points">Score: {{opponents[(1 + currentPlayer) % 4].score}}</div>
						<div class="stats" id="player2CB">Current Bid: {{opponents[(1 + currentPlayer) % 4].bid}}</div>
					</div>
				</div>
				<a class="circle" id="player4"> <img height="83" width="83"
					src="avatarImg.png">
					<h5 class="playerName">{{opponents[(1 + currentPlayer) % 4].name}}</h5>
				</a>
			</div>
		</div>
	</div>
	<div class="avatarContainer" id="player1Container">
		<div class="StatBox playerMidAlign" >
			<div class="box">
				<div class="box__header">
					<h3 class="box__header-title">Team 1</h3>
				</div>
				<div class="box__body">
					<div class="stats stats--main" id="player2Points">Score: {{opponents[currentPlayer].score}}</div>
					<div class="stats" id="player2CB">Current Bid: {{opponents[currentPlayer].bid}}</div>
					<div class="stats" id="trickCol">Trick Color: {{trickColor}}</div>
				</div>
			</div>
			<a class="circle" id="player1"> <img height="83" width="83"
				src="avatarImg.png">
				<h5 class="playerName">{{opponents[currentPlayer].name}}</h5>
			</a>
		</div>
	</div>
	<menu id="chtMenu" class="dropup">
	  <button class="msg glyphicon glyphicon-comment menuBtn" ng-click="showChat = !showChat"></button>
	</menu>
	<div id="chatWindow" ng-show="showChat">
	<div id="messages"></div>
	<div id="buttonBar">
		<div class="input-group" id="chatInput">
	     	<input type="text" id="messageInput" class="form-control">
	       <span class="input-group-btn">
	          <button class="btn btn-default" id="sendButton" value="send" onclick="sendMessage()" type="button">Send!</button>
	       </span>
	       </div><!-- /input-group --> 
		</div>
	</div>
	<a href="#" class='button button3' ng-click="submitCards(cardsToSubmit)" ng-show="canSubmitCards" id="selectCardBtn" >Select Cards! <span id="cardCount"> {{cardsChoosen}} of {{cardsNeeded}} Selected.</span></a>
</div>
	<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
	<script type="text/javascript" src="/_ah/channel/jsapi"></script>
	<script>
	function sendMessage(){
		var msg = {"message": $("#messageInput").val()};
		//this.value='';
		rookGame.send("msg",{msg:$("#messageInput").val()});
	}
	
	function printToConsole(message){
		var node = document.createElement("div");
		node.innerHTML = message
		$("#messages")[0].appendChild(node);
	}
	
	function onMessage(msg) {
		printToConsole(msg.data);
	}
	</script>

<!--End Player Hand-->
