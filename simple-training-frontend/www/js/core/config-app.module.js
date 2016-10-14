(function() {angular.module('config-app', [])
    .constant('constant', Config())
    .provider('config', function() {
        this.config = {};
        this.config.setFormLocale = function(formLocale) {
            this.formLocale = formLocale;
        };

        this.$get = function() {
            return this.config;
        };

    })
    .filter('nospace', function() {
        return function(value) {
            return (!value) ? '' : value.replace(/ /g, '');
        };
    })
    .filter('humanizeDoc', function() {
        return function(doc) {
            if (!doc) return;
            if (doc.type === 'directive') {
                return doc.name.replace(/([A-Z])/g, function($1) {
                    return '-' + $1.toLowerCase();
                });
            }

            return doc.label || doc.name;
        };
    })
    .config(function($mdDateLocaleProvider){
        var locale = window.navigator.userLanguage || window.navigator.language;
        moment.locale(locale);
        $mdDateLocaleProvider.parseDate = function(dateString) {
            var m = moment(dateString, 'L', true);
            return m.isValid() ? m.toDate() : new Date(NaN);
        };
        $mdDateLocaleProvider.formatDate = function(date) {
            return date ? moment(date).format('L') : null;
        };
    })
    .config(function($mdThemingProvider) {
        $mdThemingProvider.definePalette('training', {
            '50': '#CFD8DC',
            '100': '#607D8B',
            '200': '#455A64',
            '300': '#FFFFFF',
            '400': '#9E9E9E',
            '500': '#455A64',
            '600': '#212121',
            '700': '#757575',
            '800': '#BDBDBD',
            '900': '#BDBDBD',
            'A100': '#CFD8DC',
            'A200': '#CFD8DC',
            'A400': '#455A64',
            'A700': '#FFFFFF',
            'contrastDefaultColor': 'light',
            'contrastDarkColors': '50 100 200 300 400 500 A100 A200 A400'
        });

        $mdThemingProvider.definePalette('training-accent', {
            '50': '#e8eaf6',
            '100': '#c5cae9',
            '200': '#9fa8da',
            '300': '#7986cb',
            '400': '#5c6bc0',
            '500': '#3f51b5',
            '600': '#3949ab',
            '700': '#303f9f',
            '800': '#283593',
            '900': '#1a237e',
            'A100': '#8c9eff',
            'A200': '#536dfe',
            'A400': '#3d5afe',
            'A700': '#304ffe',
            'contrastDefaultColor': 'light',
            'contrastDarkColors': '50 100 200 A100'
        });
        $mdThemingProvider.theme('default')
            .primaryPalette('training')
            .accentPalette('training-accent');
    });

    function Config() {

        var config = {};
        config.apiBaseURL = 'http://localhost:8190/api/v1/';
        config.logoutPath = 'http://google.com.br/';

        config.header = function (token){
            return {headers: {"Google-Token": token}};
        }
        return config;
    }
})
();