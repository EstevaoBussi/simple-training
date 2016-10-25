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
      'date-component',
      'administracao-usuario-module',
      'usuario-module-service',
      'administracao-exercicio-module',
      'exercicio-module-service',
      'administracao-treino-module',
      'treino-module',
      'treino-module-service',
      'treino-realizado-module-service',
      'cardapio-module',
      'cardapio-module-service',
      'login-module',
      'session-service-module',
      'dialog-service',
      'transaction-service',
      'toast-service',
      'config-app',
      'naif.base64',
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

        $urlRouterProvider.otherwise("/login");

        $stateProvider
          .state('login', {
              url: '/login',
              views: {
                  '@': {
                      templateUrl: 'views/template/template.tmpl.html',
                      controller: 'LoginController'
                  }
              }
          })
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
          .state('home.content.cardapio', {
              url: '/cardapio',
              views: {
                  'content@home': {
                      templateUrl: 'views/cardapio/lista.view.html',
                      controller: 'CardapioController'
                  }
              }
          })
          .state('home.content.admUsuario', {
              url: '/administracao-usuario',
              views: {
                  'content@home': {
                      templateUrl: 'views/administracao/usuario/form.view.html',
                      controller: 'AdministracaoUsuarioController'
                  }
              }
          })
          .state('home.content.admExercicio', {
              url: '/administracao-exercicio',
              views: {
                  'content@home': {
                      templateUrl: 'views/administracao/exercicio/form.view.html',
                      controller: 'AdministracaoExercicioController'
                  }
              }
          })
          .state('home.content.admTreino', {
              url: '/administracao-treino',
              views: {
                  'content@home': {
                      templateUrl: 'views/administracao/treino/form.view.html',
                      controller: 'AdministracaoTreinoController'
                  }
              }
          })
      }
    ]);
})();
