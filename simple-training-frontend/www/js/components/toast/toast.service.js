(function () {
    angular.module('toast-service', [])
        .service('toast', function ($mdToast, $filter) {
            var service = {};

            service.alert = function (options) {
                $mdToast.show(
                    $mdToast.simple()
                        .textContent($filter('translate')(options.message))
                        .position('bottom right')
                        .hideDelay(5000)
                );
            };

            service.action = function(options) {
                var toast = $mdToast.simple()
                    .textContent($filter('translate')(options.message))
                    .action($filter('translate')(options.action.label))
                    .highlightAction(false)
                    .highlightClass('md-accent')
                    .position('bottom right')
                    .hideDelay(5000);
                $mdToast.show(toast).then(options.action.execute);
            };

            return service;
        });
})();
