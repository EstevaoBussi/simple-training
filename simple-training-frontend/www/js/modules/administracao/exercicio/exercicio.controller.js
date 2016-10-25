angular.module('administracao-exercicio-module', [])
.controller('AdministracaoExercicioController', ['$scope', '$stateParams', 'ExercicioService', 'dialog', '$mdDialog', 'toast', '$q',
                        function ($scope, $stateParams, ExercicioService, dialog, $mdDialog, toast, $q) {

    $scope.exercicio = {};
    $scope.alteracao = false;

    $scope.querySearch = function(query) {
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

    $scope.$watch('image', function() {
        if ($scope.image) {
            $scope.exercicio.image = {};
            $scope.exercicio.image.type = 'data:' + $scope.image.filetype + ';base64';
            $scope.exercicio.image.src = $scope.image.base64;
        }
    });

    $scope.selectedItemChange = function(item) {
        if (item) {
            $scope.exercicio = item;
        } else {
            $scope.exercicio = {};
        }
    }

    $scope.salvar = function(exercicio) {
        if($scope.alteracao) {
            ExercicioService.alterar(exercicio).then(function(response){
                if(response.status == 200){
                    $scope.exercicio = {};
                    toast.alert({message:'administracao.exercicio.sucesso'});
                } else {
                    toast.alert({message:'administracao.exercicio.erro'});
                }
            });
        } else {
            ExercicioService.inserir(exercicio).then(function(response){
                if(response.status == 200){
                    $scope.exercicio = {};
                    toast.alert({message:'administracao.exercicio.sucesso'});
                } else {
                    toast.alert({message:'administracao.exercicio.erro'});
                }
            });
        }
    }

}]);
