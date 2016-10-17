angular.module('treino-module', [])
.controller('TreinoController', ['$scope', '$stateParams', 'TreinoService',
                        function ($scope, $stateParams, TreinoService) {
    $scope.tipo = $stateParams.tipo;
    $scope.filter = false;
    $scope.filterShow = function(){
        $scope.filter = !$scope.filter;
    };
    $scope.dataTreino = new Date();

    $scope.$watch('dataTreino', function(newValue, oldValue) {
        TreinoService.getTreinos($scope.dataTreino, $scope.tipo).then(function (response) {
            if (response.status == 200 && response.data && response.data.length > 0) {
                $scope.treinos = response.data;
            } else {
                delete $scope.treinos;
            }
        });
    });

}]);