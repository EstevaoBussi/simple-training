angular.module('treino-module-service', [])
.service('TreinoService', [ 'transactionExecutor', '$http', 'constant', 'SessionService',
    function (transactionExecutor, $http, constant, SessionService) {
        var module = {};

        module.getTreinos = function (data, tipo) {
            return transactionExecutor('Carregando Treinos', function () {
                return $http.get(constant.apiBaseURL.concat('treinos?dataTreino=').concat(moment(data).format('YYYY-MM-DD'))
                    .concat('&categoria=').concat(tipo)
                    .concat('&usuario=').concat(SessionService.getUser().username), constant.header(SessionService.getToken()));
            });
        }

        return module;
    }]);