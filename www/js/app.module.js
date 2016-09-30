(function() {
  'use strict';
  angular.module('simple-training-app', [
      'ngAnimate',
      'ui.router',
      'ngMaterial',
      'ngAria',
      'ngMdIcons',
      'ngSanitize',
      'pascalprecht.translate',
      'template-module',
      'sidemenu-component',
      'profile-component',
      'treino-module',
      'treino-module-service',
      'config-app',
      'ngMessages'
    ]).config(function($translateProvider, configProvider) {
      configProvider.config.setFormLocale('br');

      $translateProvider.useStaticFilesLoader({
        prefix: 'i18n/locale-',
        suffix: '.json'
      });
      $translateProvider.preferredLanguage('br');
      $translateProvider.useSanitizeValueStrategy();
    }).config(['$stateProvider', '$urlRouterProvider', '$logProvider',
      function($stateProvider, $urlRouterProvider) {

        $urlRouterProvider.otherwise("/");

        $stateProvider
          .state('home', { // Header e sidebar
            url: '/',
            views: {
              '@': {
                templateUrl: 'views/template/template.tmpl.html',
                controller: 'TemplateController'
              }
            }
          }).state('home.content', {
            url: 'content',
            abstract: true
          })
          .state('home.content.treino', {
              url: '/treino/:tipo',
              views: {
                  'content@home': {
                      templateUrl: 'views/treino/lista.view.html',
                      controller: 'TreinoController'
                  }
              }
          })
      }
    ]);
})();
