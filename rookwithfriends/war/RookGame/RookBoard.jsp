
<!-- Page Content -->
<div style="margin: 0 auto 0 auto;width:1250px;">
	<!--User Avatars and Names-->
	<div class="avatarContainer">	
		<a class="circle" id="player2"> 
			<img height="83" width="83" src="avatarImg.png">
			<h5 class="playerName">{{opponentNames[1]}}</h5>
		</a> 
	</div>
	<div>
		<div class="centerStrip avatarContainer">
			<a class="circle" id="player3"> 
				<img height="83" width="83" src="avatarImg.png">
				<h5 class="playerName">{{opponentNames[2]}}</h5>
			</a> 
		</div>
		<div id="table1" class="centerStrip">
			<div id="table2">
				<div id="middleHand" >
					<div class="card three midCards" ng-class="card.color" ng-repeat="card in middleHand" data-card="{{card}}">
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
					<div class="card pCard" ng-class="card.color" ng-repeat="card in playerHand" data-card="{{card}}">
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
		<div class="centerStrip avatarContainer">
			<a class="circle" id="player4"> 
				<img height="83" width="83" src="avatarImg.png">
				<h5 class="playerName">{{opponentNames[3]}}</h5>
			</a>
		</div>
	</div>
	<div class="avatarContainer">
		<a class="circle" id="player1">
			<img height="83" width="83" src="avatarImg.png">
			<h5 class="playerName">{{opponentNames[0]}}</h5>
		</a> 
	</div>
</div>
<!--End Player Hand-->
