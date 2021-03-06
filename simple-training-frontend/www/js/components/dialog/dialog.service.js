(function () {
    angular.module('dialog-service', [])
        .service('dialog', function ($mdDialog, $filter) {
            var service = {};

            service.confirm = function (options) {
                var parentEl = angular.element(document.body);
                var confirm = $mdDialog.confirm();

                if (options.title) {
                    confirm.title($filter('translate')(options.title))
                }

                confirm.textContent($filter('translate')(options.message))
                    .ariaLabel($filter('translate')(options.message))
                    .parent(parentEl)
                    .clickOutsideToClose(false)
                confirm.ok($filter('translate')(options.actions.confirm.label))
                    .cancel($filter('translate')(options.actions.cancel.label));

                $mdDialog.show(confirm)
                    .then(function () {
                        if (options.actions.confirm) {
                            return options.actions.confirm.callback();
                        }
                    }, function () {
                        if (options.actions.cancel) {
                            return options.actions.cancel.callback();
                        }
                    });
            };

            function DialogController($scope, $mdDialog, callbacks, model) {
                $scope.callbacks = callbacks;
                $scope.model = model;
            }

            service.pronpt = function(template, callbacks, model, element) {
                var parentEl;
                if (!element) {
                    parentEl = element;
                } else {
                    parentEl = angular.element(document.body);
                }

                $mdDialog.show({
                    parent: parentEl,
                    templateUrl: template,
                    controller: DialogController,
                    locals: {callbacks:callbacks,model:model}
                });
            }

            return service;
        });
})();
