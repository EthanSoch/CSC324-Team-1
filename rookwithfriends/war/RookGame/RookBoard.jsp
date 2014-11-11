
<!-- Page Content -->
<div>
	<!--User Avatars and Names-->
	<div class="avatarContainer">	
		<a class="circle" id="player2"> 
			<img height="83" width="83" src="avatarImg.png">
			<h5 class="playerName">{{player2}}</h5>
		</a> 
	</div>
	<div>
		<div class="centerStrip avatarContainer">
			<a class="circle" id="player3"> 
				<img height="83" width="83" src="avatarImg.png">
				<h5 class="playerName">{{player3}}</h5>
			</a> 
		</div>
		<div id="table1" class="centerStrip">
			<div id="table2">
				<div id="middleHand">
				<% for( int i = 0 ; i < 5 ; i ++){ %>
					<div class="card three midCards" ng-class="middleHand[<%= i %>].color">
						<div class="cardTop">
							<div class="number">{{middleHand[<%= i %>].num}}</div>
							<div class="color">{{middleHand[<%= i %>].color}}</div>
						</div>
						<div class="cardBottom">
							<div class="number">{{middleHand[<%= i %>].num}}</div>
							<div class="color">{{middleHand[<%= i %>].color}}</div>
						</div>
					</div>
				<% } %>
				</div>
				<div id="playerHand">
				<% for( int i = 0 ; i < 10 ; i ++){ %>
					<div class="card pCard" ng-class="playerHand[<%= i %>].color">
						<div class="cardTop">
							<div class="number">{{playerHand[<%= i %>].num}}</div>
							<div class="color">{{playerHand[<%= i %>].color}}</div>
						</div>
						<div class="cardBottom">
							<div class="number">{{playerHand[<%= i %>].num}}</div>
							<div class="color">{{playerHand[<%= i %>].color}}</div>
						</div>
					</div>
				<% } %>
				</div>
			</div>
		</div>
		<div class="centerStrip avatarContainer">
			<a class="circle" id="player4"> 
				<img height="83" width="83" src="avatarImg.png">
				<h5 class="playerName">{{player4}}</h5>
			</a>
		</div>
	</div>
	<div class="avatarContainer">
		<a class="circle" id="player1">
			<img height="83" width="83" src="avatarImg.png">
			<h5 class="playerName">{{player1}}</h5>
		</a> 
	</div>
</div>
<!--End Player Hand-->
