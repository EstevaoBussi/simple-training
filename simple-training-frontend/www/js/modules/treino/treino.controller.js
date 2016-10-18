angular.module('treino-module', [])
.controller('TreinoController', ['$scope', '$stateParams', 'TreinoService',
                        function ($scope, $stateParams, TreinoService) {
    $scope.tipo = $stateParams.tipo;
    $scope.filter = false;
    $scope.realizado = false;
    $scope.filterShow = function(){
        $scope.filter = !$scope.filter;
    };
    $scope.dataTreino = new Date();

    $scope.$watch('dataTreino', function(newValue, oldValue) {
        if($scope.dataTreino) {
            if (!$scope.realizado) {
                TreinoService.getTreinos($scope.dataTreino, $scope.tipo).then(function (response) {
                    if (response.status == 200 && response.data && response.data.length > 0) {
                        $scope.treinos = response.data;
                    } else {
                        delete $scope.treinos;
                    }
                });
            } else {
                var dataInicial = moment($scope.dataTreino).date(1);
                var dataFinal = moment().set('month', dataInicial.get('month') + 1).subtract(1);
                TreinoService.getTreinos(dataInicial, dataFinal, $scope.tipo).then(function (response) {
                    if (response.status == 200 && response.data && response.data.length > 0) {
                        $scope.treinosRealizados = response.data;
                    } else {
                        delete $scope.treinosRealizados;
                    }
                });
            }
        }
    });

}]);
