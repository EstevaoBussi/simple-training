angular.module('treino-module', [])
.controller('TreinoController', ['$scope', '$stateParams', 'TreinoService', 'TreinoRealizadoService', 'dialog', '$mdDialog',
                        function ($scope, $stateParams, TreinoService, TreinoRealizadoService, dialog, $mdDialog) {
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
                TreinoRealizadoService.getTreinos(dataInicial, dataFinal, $scope.tipo).then(function (response) {
                    if (response.status == 200 && response.data && response.data.length > 0) {
                        $scope.treinosRealizados = response.data;
                    } else {
                        delete $scope.treinosRealizados;
                    }
                });
            }
        }
    });

    $scope.finalizarTreino = function(treino) {
        var template = '/views/treino/pronpt-date.tmpl.html';
        var actions = {
            confirm: function(){
                var treinoRealizado = {treino:treino,data:moment()};
                TreinoRealizadoService.inserir(treinoRealizado).then(function(response){
                    alert('a');
                },function(response){
                    alert('b');
                });
            },
            cancel: function(){
                $mdDialog.hide();
            }
        }
        dialog.pronpt(template, actions);
    }
}]);
