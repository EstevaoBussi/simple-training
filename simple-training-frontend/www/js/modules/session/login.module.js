angular.module('login-module',[])
    .controller('LoginController', ['$scope','$timeout', '$http', 'SessionService', 'constant', '$location', '$state', 'transactionExecutor',
        function($scope, $timeout, $http, SessionService, constant, $location,  $state, transactionExecutor) {
            $scope.cred = {};

            var getUsuario = function(token) {
                return transactionExecutor('Autenticando', function () {
                    return $http.get(constant.apiBaseURL.concat('usuarios'), constant.header(token));
                });
            }

            $scope.error = function() {
                delete $scope.user;
                SessionService.removeToken();
                $state.go('login', {});
            }

            $scope.signIn = function (googleUser) {
                var profile = googleUser.getBasicProfile();
                var auth = googleUser.getAuthResponse();
                var token = auth.id_token;
                $scope.user = {};
                try {
                    SessionService.setToken(token);
                    getUsuario(token).then(function (response) {
                        if (response.status == 200) {
                            SessionService.setUser(response.data);
                            $state.go('home', {});
                        } else {
                            $scope.error();
                        }
                    }, function (response) {
                        $scope.error();
                    });
                } catch (error) {
                    $scope.error();
                }

            }

            $scope.signOut = function (gapi) {
                var auth2 = gapi.auth2.getAuthInstance();
                auth2.signOut().then(function () {
                    window.location.replace(constant.logoutPath);
                });
            };

        }]);