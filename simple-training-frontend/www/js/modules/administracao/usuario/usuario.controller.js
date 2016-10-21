angular.module('administracao-usuario-module', [])
.controller('AdministracaoUsuarioController', ['$scope', '$stateParams', 'UsuarioService', 'dialog', '$mdDialog', 'toast', '$q',
                        function ($scope, $stateParams, UsuarioService, dialog, $mdDialog, toast, $q) {

    $scope.usuario = {};
    $scope.alteracao = false;

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
            $scope.usuario = item;
        } else {
            $scope.usuario = {};
        }
    }

    $scope.salvar = function(usuario) {
        if($scope.alteracao) {
            UsuarioService.alterar(usuario).then(function(response){
                if(response.status == 200){
                    $scope.usuario = {};
                    toast.alert({message:'administracao.usuario.sucesso'});
                } else {
                    toast.alert({message:'administracao.usuario.erro'});
                }
            });
        } else {
            UsuarioService.inserir(usuario).then(function(response){
                if(response.status == 200){
                    $scope.usuario = {};
                    toast.alert({message:'administracao.usuario.sucesso'});
                } else {
                    toast.alert({message:'administracao.usuario.erro'});
                }
            });
        }
    }

}]);
