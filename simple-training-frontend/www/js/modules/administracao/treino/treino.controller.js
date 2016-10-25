angular.module('administracao-treino-module', [])
.controller('AdministracaoTreinoController', ['$scope', '$stateParams', 'TreinoService', 'UsuarioService', 'ExercicioService', 'dialog', '$mdDialog', 'toast', '$q',
                        function ($scope, $stateParams, TreinoService, UsuarioService, ExercicioService, dialog, $mdDialog, toast, $q) {

    $scope.treino = {usuario:{}};
    $scope.alteracao = false;
    $scope.exercicio = {};

    TreinoService.getCategorias().then(function(response){
        if (response.status == 200 && response.data) {
            angular.forEach(response.data, function (value, key) {
                $scope.categorias = response.data;
            });
        }
    });

    $scope.querySearch = function(query) {
        if (query && query.length > 2) {
            var deferred = $q.defer();
            UsuarioService.findUsuario(query).then(function (response) {
                if (response.status == 200 && response.data) {
                    deferred.resolve(response.data);
                } else {
                    deferred.reject(response.data);
                }
            });
            return deferred.promise;
        }
        return new Array();
    }

    $scope.selectedItemChange = function(item) {
        if (item) {
            $scope.treino.usuario = item;
        } else {
            $scope.treino.usuario = {};
        }
    }

    $scope.querySearchExercicio = function(query) {
        if (query && query.length > 2) {
            var deferred = $q.defer();
            ExercicioService.findExercicio(query).then(function (response) {
                if (response.status == 200 && response.data) {
                    deferred.resolve(response.data);
                } else {
                    deferred.reject(response.data);
                }
            });
            return deferred.promise;
        }
        return new Array();
    }

    $scope.selectedItemChangeExercicio = function(item) {
        if (item) {
            $scope.exercicio = item;
        } else {
            $scope.exercicio = {};
        }
    }

    $scope.salvar = function(treino) {
        if($scope.alteracao) {
            TreinoService.alterar(treino).then(function(response){
                if(response.status == 200){
                    $scope.treino = {};
                    toast.alert({message:'administracao.treino.sucesso'});
                } else {
                    toast.alert({message:'administracao.treino.erro'});
                }
            });
        } else {
            ExercicioService.inserir(treino).then(function(response){
                if(response.status == 200){
                    $scope.treino = {};
                    toast.alert({message:'administracao.treino.sucesso'});
                } else {
                    toast.alert({message:'administracao.treino.erro'});
                }
            });
        }
    }

}]);
