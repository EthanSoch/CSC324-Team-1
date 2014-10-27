var myModule = angular.module('rook', []);

myModule.controller('myController', function ($scope) {

    $scope.player1 = false;
    $scope.player2 = false;
    $scope.player3 = false;
    $scope.player4 = false;
    $scope.numplayers = 0;
    $scope.change = function() {
    	$scope.numplayers = 0;
         if($scope.player1 == true){
          	$scope.numplayers++;
          }
         if($scope.player2 == true){
          	$scope.numplayers++;
          }
         if($scope.player3 == true){
          	$scope.numplayers++;
          }
         if($scope.player4 == true){
          	$scope.numplayers++;
          }
        };

});