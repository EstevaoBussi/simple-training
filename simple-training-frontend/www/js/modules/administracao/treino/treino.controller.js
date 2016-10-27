angular.module('administracao-treino-module', [])
.controller('AdministracaoTreinoController', ['$rootScope', '$scope', '$stateParams', 'TreinoService', 'UsuarioService', 'ExercicioService', 'dialog', '$mdDialog', 'toast', '$q',
    function ($rootScope, $scope, $stateParams, TreinoService, UsuarioService, ExercicioService, dialog, $mdDialog, toast, $q) {

        $rootScope.titlePage = 'administracao.treino.titulo';
        $scope.novoTreino = function() {
            $scope.treino = {usuario:{}, exercicios:new Array()};
        }

        $scope.novoTreino();
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
                $scope.treino.usuario = item;
            } else {
                $scope.treino.usuario = {};
            }
        }

        TreinoService.getCategorias().then(function(response){
            if (response.status == 200 && response.data) {
                angular.forEach(response.data, function (value, key) {
                    $scope.categorias = response.data;
                });
            }
        });

        $scope.adicionarExercicio = function(indice, exercicio) {
            var model = {indice:indice};
            model.exercicio = exercicio ? exercicio : {exercicio:{exercicio:{}}};
            var actions = {
                confirm: function(indice, exercicio){
                    if (indice) {
                        $scope.treino.exercicios[indice] = exercicio;
                    } else {
                        $scope.treino.exercicios.push(exercicio);
                    }
                    $mdDialog.hide();
                },
                cancel: function(){
                    $mdDialog.hide();
                },
                remover: function(indice){
                    $scope.treino.exercicios.splice(indice, 1);
                    $mdDialog.hide();
                },
                querySearchExercicio: function(query) {
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
                },
                selectedItemChangeExercicio: function(exercicio, item) {
                    if (item) {
                        exercicio.exercicio = item;
                    } else {
                        exercicio.exercicio = {};
                    }
                }
            }
            dialog.pronpt('/views/administracao/treino/pronpt-exercicio.tmpl.html', actions, model);
        }

        $rootScope.floatingButton = $scope.adicionarExercicio;

        $scope.salvar = function(treino) {
            if($scope.alteracao) {
                TreinoService.alterar(treino).then(function(response){
                    if(response.status == 200){
                        $scope.novoTreino();
                        toast.alert({message:'administracao.treino.sucesso'});
                    } else {
                        toast.alert({message:'administracao.treino.erro'});
                    }
                });
            } else {
                TreinoService.inserir(treino).then(function(response){
                    if(response.status == 200){
                        $scope.novoTreino();
                        toast.alert({message:'administracao.treino.sucesso'});
                    } else {
                        toast.alert({message:'administracao.treino.erro'});
                    }
                });
            }
        }

    }]);
