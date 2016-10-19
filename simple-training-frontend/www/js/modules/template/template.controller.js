angular.module('template-module', [])
.controller('TemplateController', ['$scope', '$mdSidenav', 'SessionService', '$state', 'TreinoService',
                          function ($scope, $mdSidenav, SessionService, $state, TreinoService) {

    $scope.sidemenu = {};
    $scope.header = {};
    $scope.items2 = [];
    $scope.user = SessionService.getUser();

    $scope.sidemenu.items = $scope.items2;
    $scope.sidemenu.options = {id:'sidemenu'};

    $scope.logout = function () {
        SessionService.logout();
        $state.go('home', {});
    }

    $scope.header.profile = {
        user:{
            name:$scope.user.nome,
            details:[{
                    label:'profile.dataNascimento',
                    value:moment($scope.user.dataNascimento).format('DD/MM/YY')
                },{
                    label:'profile.email',
                    value:$scope.user.email
                }]
        },
        logout:$scope.logout,
        edit:{
            href:'http://www.google.com.br'
        }
    };

    $scope.toggleOpen = function() {
        $mdSidenav('left').toggle();
    }

    $scope.items2.push({
        name: 'menu.treinos',
        type: 'toggle',
        pages: []
    });

    TreinoService.getCategorias().then(function(response){
        if (response.status == 200 && response.data) {
            angular.forEach(response.data, function (value, key) {
                var page = {
                    name: 'menu.treino.'+value,
                    type: 'link',
                    state: 'home.content.treino({tipo:value})'
                }
                $scope.items2[0].pages.push(page);
            });
        }
    });

}]);