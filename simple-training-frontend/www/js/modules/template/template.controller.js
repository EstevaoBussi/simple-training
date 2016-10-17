angular.module('template-module', [])
.controller('TemplateController', ['$scope', '$mdSidenav', 'SessionService', '$state',
                          function ($scope, $mdSidenav, SessionService, $state) {

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
        pages: [{
            name: 'menu.academia',
            type: 'link',
            state: 'home.content.treino({tipo:\'ACADEMIA\'})'
        },
        {
            name: 'menu.casa',
            type: 'link',
            state: 'home.content.treino({tipo:\'CASA\'})'
        }]
    });

}]);