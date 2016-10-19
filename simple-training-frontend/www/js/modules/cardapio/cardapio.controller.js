angular.module('cardapio-module', [])
.controller('CardapioController', ['$scope', '$stateParams', 'CardapioService', 'dialog', '$mdDialog', 'toast',
                        function ($scope, $stateParams, CardapioService, dialog, $mdDialog, toast) {
    $scope.filter = false;
    $scope.filterShow = function(){
        $scope.filter = !$scope.filter;
    };

    $scope.filtro = {};
    $scope.filtro.data = new Date();

    $scope.$watch('filtro.data', function(newValue, oldValue) {
        if($scope.filtro.data) {
            CardapioService.getCardapios($scope.filtro.data).then(function (response) {
                if (response.status == 200) {
                    if (response.data && response.data.length > 0) {
                        $scope.cardapios = response.data;
                    } else {
                        toast.alert({message:'cardapio.emptyList'});
                    }
                } else {
                    delete scope.treinos;
                    toast.alert({message:'cardapio.buscaError'});
                }
            });
        }
    });

}]);
