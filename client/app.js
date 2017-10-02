(function () {
    'use strict';

    angular.module('employeeApp',['ngRoute'])
        .config(moduleConfig);

    moduleConfig.$inject=['$routeProvider'];
    function moduleConfig($routeProvider) {

        $routeProvider
            .when('/employees',{
                templateUrl:"templates/viewall.html"
            })
            .when('/addemployee', {
                templateUrl: "templates/addemployee.html"
            })
            .when('/viewemployee',{
                templateUrl:"templates/viewemployee.html"
            })
            .when('/editemployee',{
                templateUrl:"templates/editemployee.html"
            })
            .otherwise({redirectTo: '/employees'});
        }


})();