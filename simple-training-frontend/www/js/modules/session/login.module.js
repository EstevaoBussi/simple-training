angular.module('login-module',[])
    .controller('LoginController', ['$scope','$timeout', '$http', 'SessionService', 'constant', '$location', '$state', 'transactionExecutor', 'toast',
        function($scope, $timeout, $http, SessionService, constant, $location,  $state, transactionExecutor, toast) {
            $scope.cred = {};

            var getUsuario = function(token) {
                return transactionExecutor('Autenticando', function () {
                    return $http.get(constant.apiBaseURL.concat('usuarios'), constant.header(token));
                });
            }

            $scope.error = function() {
                delete $scope.user;
                SessionService.logout();
                toast.alert({message:'login.error'});
            }

            $scope.signIn = function (googleUser) {
                $scope.user = true;
                var profile = googleUser.getBasicProfile();
                var auth = googleUser.getAuthResponse();
                var token = auth.id_token;
                try {
                    SessionService.setToken(token);
                    getUsuario(token).then(function (response) {
                        if (response.status == 200 && response.data) {
                            $scope.user = response.data;
                            if (!SessionService.getUser()) {
                                $state.go('home', {});
                            }
                            SessionService.setUser(response.data);
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