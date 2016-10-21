angular.module('usuario-module-service', [])
.service('UsuarioService', [ 'transactionExecutor', '$http', 'constant', 'SessionService',
    function (transactionExecutor, $http, constant, SessionService) {
        var module = {};

        module.findUsuario = function (nome) {
            return $http.get(constant.apiBaseURL.concat('usuarios/byName?nome=').concat(nome), constant.header(SessionService.getToken()));
        };

        module.inserir = function(usuario) {
            return transactionExecutor('Inserindo Usuário', function () {
                return $http.post(constant.apiBaseURL.concat('usuarios'), usuario, constant.header(SessionService.getToken()));
            });
        };

        module.alterar = function(usuario) {
            return transactionExecutor('Alterando Usuário', function () {
                return $http.put(constant.apiBaseURL.concat('usuarios'), usuario, constant.header(SessionService.getToken()));
            });
        };

        return module;
    }]);