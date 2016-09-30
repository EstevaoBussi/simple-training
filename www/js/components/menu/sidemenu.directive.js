(function() {
  angular.module('sidemenu-component', [])
      .directive('sidemenu', sidemenuDirective);

  function sidemenuDirective($parse){
    return {
      restrict: 'E',
      replace:true,
      templateUrl: '/views/template/sidemenu.tmpl.html',
      link: function(scope, element, attrs) {
        if (attrs.options) {
          scope.options = $parse(attrs.options)(scope);
        }

        if (attrs.items) {
          scope.items = $parse(attrs.items)(scope);
        }

        scope.selectMenuId = function(parm) {

          scope.menuIdSelectedSub = false;

          if (scope.menuIdSelected !== parm) {
            scope.menuIdSelected = parm;
          } else {
            scope.menuIdSelected = false;
          }
        }

        scope.selectMenuIdSub = function(param) {
          //2° nível selecionado
          if (scope.menuIdSelectedSub !== param) {
            scope.menuIdSelectedSub = param;
          } else {
            scope.menuIdSelectedSub = false;
          }
        }

        scope.selectItem = function(link) {
          scope.selected = angular.isNumber(link) ? scope.items[link] : link;
        }


        scope.showDate = false;

        scope.showLanguage = true;

        scope.abertoFechado = false;

      }
    };
  }
})();