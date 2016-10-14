(function () {
    angular.module('transaction-service', ['ngMaterial'])
        .
    service('transactionExecutor', function ($q, $mdDialog, $timeout) {
        return function (title, transaction) {
            var defer = $q.defer();
            $mdDialog.show({
                templateUrl: '/views/template/transaction.tmpl.html',
                parent: angular.element(document.body),
                controller: function ($scope, $mdDialog) {
                    $scope.title = title;
                    $timeout(function () {
                        transaction()
                            .then(function (response) {
                                $mdDialog.hide();
                                defer.resolve(response);
                            }, function (err) {
                                $mdDialog.hide();
                                defer.reject(err);
                            })
                    }, 500);
                },
                clickOutsideToClose: false,
            });
            return defer.promise;
        }
    });
})();
