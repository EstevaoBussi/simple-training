(function() {
angular
.module('profile-component',[])
.directive('profile', profileDirective);

    function profileDirective() {
        return {
            restrict: 'E',
            replace: true,
            templateUrl: '/views/template/profile.tmpl.html',
            scope: {
                options: '='
            },
            link: function (scope, element, attrs) {
                scope.name = function() {
                    return !scope.options || !scope.options.user ? '' : (scope.options.user.name && scope.options.user.name.length > 20 ? scope.options.user.name.substring(0, 20) : scope.options.user.name);
                }

            }
        };
   }
})();