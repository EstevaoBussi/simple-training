(function() {angular.module('date-component',[])
    .directive('dateConverter', dateDirective);

    function dateDirective() {
        return {
            restrict: 'A',
            require: '^ngModel',
            link: function (scope, element, attrs, ngModel) {

                ngModel.$formatters.push(formatter);
                ngModel.$parsers.push(parser);

                function parser(value) {
                    var m = moment(value);
                    var valid = m.isValid();
                    ngModel.$setValidity('datetime', valid);
                    if (valid) return m.format('YYYY-MM-DD');
                    else return value;
                }

                function formatter(value) {
                    if (value) {
                        var m = moment(value);
                        if (m.isValid()) {
                            m.add(1, 'days');
                            return new Date(m.format("YYYY-MM-DD"));
                        }
                        else {
                            return undefined;
                        }
                    } else {
                        return undefined;
                    }
                }

            }
        };
   }
})();