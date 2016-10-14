angular.module('session-service-module', [])
  .service('SessionService', SessionService);


function SessionService() {
  var getToken = function () {
    return window.localStorage.getItem('token');
  };

  var setToken = function (token) {
    if (token) {
      return window.localStorage.setItem('token', token);
    }
  };

  var clear = function () {
    window.localStorage.removeItem('token');
    window.localStorage.removeItem('user');
  };

  var isAuthenticated = function () {
    return !!window.localStorage.getItem('token');
  };

  var getUser = function () {
    try{
      return JSON.parse(window.localStorage.getItem('user'));
    }catch(error){
      return undefined;
    }
  };

  var setUser = function (user) {
    if (user) {
      return window.localStorage.setItem('user', JSON.stringify(user));
    }
  };

  return {
    getToken: getToken,
    setToken: setToken,
    clear: clear,
    isAuthenticated: isAuthenticated,
    getUser: getUser,
    setUser: setUser
  }

};