angular.module('treino-realizado-module-service', [])
.service('TreinoRealizadoService', [ 'transactionExecutor', '$http', 'constant', 'SessionService',
    function (transactionExecutor, $http, constant, SessionService) {
        var module = {};

        module.getTreinos = function (dataInicio, dataFim, tipo) {
            return transactionExecutor('Carregando Treinos', function () {
                return $http.get(constant.apiBaseURL.concat('treinosRealizados?dataInicial=').concat(dataInicio ? moment(dataInicio).format('YYYY-MM-DD') : null)
                    .concat('&dataFinal=').concat(dataFim ? moment(dataFim).format('YYYY-MM-DD') : null)
                    .concat('&categoria=').concat(tipo)
                    .concat('&usuario=').concat(SessionService.getUser().codigo), constant.header(SessionService.getToken()));
            });
        }

        module.inserir = function(treinoRealizado) {
            return transactionExecutor('Executando Treino', function () {
                return $http.post(constant.apiBaseURL.concat('treinosRealizados'), treinoRealizado, constant.header(SessionService.getToken()));
            });
        }

        return module;
    }]);