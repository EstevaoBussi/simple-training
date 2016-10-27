angular.module('treino-module', [])
.controller('TreinoController', ['$scope', '$stateParams', 'TreinoService', 'TreinoRealizadoService', 'dialog', '$mdDialog', 'toast',
function ($scope, $stateParams, TreinoService, TreinoRealizadoService, dialog, $mdDialog, toast) {
    $scope.tipo = $stateParams.tipo;
    $scope.filter = false;
    $scope.filterShow = function(){
        $scope.filter = !$scope.filter;
    };
    $scope.filtro = {};
    $scope.filtro.dataTreino = new Date();

    $scope.$watch('filtro.realizado', function(newValue, oldValue) {
        if($scope.filtro.dataTreino) {
            if (!$scope.filtro.realizado) {
                treinosDisponiveis($scope.dateTreino, $scope.tipo, $scope, TreinoService, toast);
            } else {
                treinosExecutados($scope.dataTreino, $scope.tipo, $scope, TreinoRealizadoService, toast);
            }
        }
    });

    $scope.$watch('filtro.dataTreino', function(newValue, oldValue) {
        if($scope.filtro.dataTreino) {
            if (!$scope.filtro.realizado) {
                treinosDisponiveis($scope.dateTreino, $scope.tipo, $scope, TreinoService, toast);
            } else {
                treinosExecutados($scope.dataTreino, $scope.tipo, $scope, TreinoRealizadoService, toast);
            }
        }
    });

    $scope.finalizarTreino = function(treino) {
        var template = '/views/treino/pronpt-date.tmpl.html';
        var model = new Date();
        var actions = {
            confirm: function(data){
                var treinoRealizado = {treino:treino,data:moment(data).format('YYYY-MM-DD')};
                TreinoRealizadoService.inserir(treinoRealizado).then(function(response){
                    if (response.data) {
                        treinosExecutados($scope.dataTreino, $scope.tipo, $scope, TreinoRealizadoService);
                        $scope.filtro.realizado = true;
                    }
                },function(response){
                    toast.alert({message:'treino.buscaError'});
                });
            },
            cancel: function(){
                $mdDialog.hide();
            }
        }
        dialog.pronpt(template, actions, model);
    }
}]);

function treinosDisponiveis(data, tipo, scope, service, toast){
    service.getTreinos(data, tipo).then(function (response) {
        if (response.status == 200) {
            if (response.data && response.data.length > 0) {
                scope.treinos = response.data;
            } else {
                toast.alert({message:'treino.emptyList'});
            }
        } else {
            delete scope.treinos;
            toast.alert({message:'treino.buscaError'});
        }
    });
}

function treinosExecutados(data, tipo, scope, service, toast){
    var dataInicial = moment(data).date(1);
    var dataFinal = moment().set('month', dataInicial.get('month') + 1).subtract(1);
    service.getTreinos(dataInicial, dataFinal, tipo).then(function (response) {
        if (response.status == 200) {
            if (response.data && response.data.length > 0) {
                scope.treinosRealizados = response.data;
            } else {
                toast.alert({message:'treino.emptyList'});
            }
        } else {
            delete scope.treinosRealizados;
            toast.alert({message:'treino.buscaError'});
        }
    });
}
