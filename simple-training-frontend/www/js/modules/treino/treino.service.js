angular.module('treino-module-service', [])
.service('TreinoService', [ 'transactionExecutor', '$http', 'constant', 'SessionService',
    function (transactionExecutor, $http, constant, SessionService) {
        var module = {};

        module.getCategorias = function () {
            return $http.get(constant.apiBaseURL.concat('treinos/categorias'), constant.header(SessionService.getToken()));
        }

        module.getTreinos = function (data, tipo) {
            return transactionExecutor('Carregando Treinos', function () {
                return $http.get(constant.apiBaseURL.concat('treinos?dataTreino=').concat(moment(data).format('YYYY-MM-DD'))
                    .concat('&categoria=').concat(tipo)
                    .concat('&usuario=').concat(SessionService.getUser().codigo), constant.header(SessionService.getToken()));
            });
        }

        module.inserir = function(treino) {
            return transactionExecutor('Inserindo Treino', function () {
                return $http.post(constant.apiBaseURL.concat('treinos'), treino, constant.header(SessionService.getToken()));
            });
        };

        module.alterar = function(usuario) {
            return transactionExecutor('Alterando Treino', function () {
                return $http.put(constant.apiBaseURL.concat('treino'), treino, constant.header(SessionService.getToken()));
            });
        };

        return module;
    }]);