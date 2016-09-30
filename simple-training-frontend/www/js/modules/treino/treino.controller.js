angular.module('treino-module', [])
.controller('TreinoController', ['$scope', '$stateParams', 'TreinoService',
                        function ($scope, $stateParams, TreinoService) {
    $scope.tipo = $stateParams.tipo;
    $scope.filter = false;
    $scope.filterShow = function(){
        $scope.filter = !$scope.filter;
    };
    $scope.dataTreino = new Date();

    $scope.treinos = TreinoService.getTreinos($scope.dataTreino, $scope.tipo);

    $scope.$watch('dataTreino', function(newValue, oldValue) {
        $scope.treinos = TreinoService.getTreinos($scope.dataTreino, $scope.tipo);
    });

}]);