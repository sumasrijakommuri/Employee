(function () {
    'use strict';

    var app = angular.module('employeeApp');

    app.service('EmployeeService',EmployeeService);

    EmployeeService.$inject=['$q','$http']
    function EmployeeService($q,$http) {
        var self=this;

        self.getEmployees = function () {
            return $http.get("http://localhost:8080/api/employees")
                .then(function (response) {
                    return response;},
                    function (error) {
                    return $q.reject(error);
                });
        };

        self.getEmployee = function (id) {
            return $http.get("http://localhost:8080/api/employees/"+id)
                .then(function (response) {
                    return response;
                },
                    function (error) {
                        return $q.reject(error)
                    });
        };
        
        self.createEmployee = function (data) {
            var config =  {
                method: "POST",
                url:'http://localhost:8080/api/employees',
                data: data,
                headers: {
                    'Content-Type': 'application/json; charset=utf-8'
                }
            };
            return $http(config);
        }

        self.deleteEmployee = function (id) {
            var config =  {
                method: "DELETE",
                url:'http://localhost:8080/api/employees/'+id,
                headers: {
                    'Content-Type': 'application/json; charset=utf-8'
                }
            };
            return $http(config);
        }
    }
})();