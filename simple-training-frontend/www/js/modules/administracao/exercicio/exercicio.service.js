angular.module('exercicio-module-service', [])
.service('ExercicioService', [ 'transactionExecutor', '$http', 'constant', 'SessionService',
    function (transactionExecutor, $http, constant, SessionService) {
        var module = {};

        module.findExercicio = function (nome) {
            return $http.get(constant.apiBaseURL.concat('exercicios/byName?nome=').concat(nome), constant.header(SessionService.getToken()));
        };

        module.inserir = function(exercicio) {
            return transactionExecutor('Inserindo Exercício', function () {
                return $http.post(constant.apiBaseURL.concat('exercicios'), exercicio, constant.header(SessionService.getToken()));
            });
        };

        module.alterar = function(exercicio) {
            return transactionExecutor('Alterando Exercício', function () {
                return $http.put(constant.apiBaseURL.concat('exercicios'), exercicio, constant.header(SessionService.getToken()));
            });
        };

        return module;
    }]);