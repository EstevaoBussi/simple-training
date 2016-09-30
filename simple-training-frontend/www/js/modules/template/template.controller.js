angular.module('template-module', [])
.controller('TemplateController', ['$scope', '$mdSidenav',
                          function ($scope, $mdSidenav) {

    $scope.sidemenu = {};
    $scope.header = {};
    $scope.items2 = [];

    $scope.sidemenu.items = $scope.items2;
    $scope.sidemenu.options = {id:'sidemenu',logo:{src: 'bower_components/mtr-material/dist/imgs/mtr-logo.png',href:'#/'}};
    $scope.header.profile = {user:{name:'Estevão Henrique Bussi',details:[{label:'Data Criação',value:'01/01/2016'},{label:'profile.email',value:'estevo@gmail.com'},{label:'profile.telefone',value:'(11)99999-9999'}]},logout:function(){alert('deslogado');},edit:{href:'http://www.google.com.br'}};
    $scope.header.options = {logo:{src: 'bower_components/mtr-material/dist/imgs/mtr-logo.png',href:'#/'},system:{name:'Showcase',environment:'HOMOLOGAÇÃO'}};

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