angular.module('cardapio-module-service', [])
.service('CardapioService', [ 'transactionExecutor', '$http', 'constant', 'SessionService',
    function (transactionExecutor, $http, constant, SessionService) {
        var module = {};

        module.getCardapios = function (data, tipo) {
            return transactionExecutor('Carregando Cardapios', function () {
                return $http.get(constant.apiBaseURL.concat('cardapios?data=').concat(moment(data).format('YYYY-MM-DD'))
                    .concat('&usuario=').concat(SessionService.getUser().codigo), constant.header(SessionService.getToken()));
            });
        }

        return module;
    }]);