(function () {
    'use strict';

    var app = angular.module('employeeApp');

    app.service('EmployeeService',EmployeeService);

    EmployeeService.$inject=['$q','$http']
    function EmployeeService($q,$http) {
        var self=this;

        self.current = null;
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

        self.getWeather = function (city,state) {
            var config =  {
                method: "GET",
                url:'https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20weather.forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo.places(1)%20where%20text%3D%22'+city+'%2C'+state+'%22)&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys',
                headers: {
                    'Content-Type': 'application/json; charset=utf-8'
                }
            };
            return $http(config);
        }
        
        
        self.share = function (employee) {
            self.current = employee;
        }

        self.getShared = function () {
            return self.current;
        }

        self.update = function (data) {
            var config =  {
                method: "PUT",
                url:'http://localhost:8080/api/employees',
                data: data,
                headers: {
                    'Content-Type': 'application/json; charset=utf-8'
                }
            };
            return $http(config);
        }


        self.getLatLong = function(address){
            var config =  {
                method: "GET",
                url:'http://maps.google.com/maps/api/geocode/json?address='+address+'&sensor=false',
                headers: {
                    'Content-Type': 'application/json; charset=utf-8'
                }
            };
            return $http(config);
        }


        self.getNearestEmployees = function (ziplat, ziplng) {
            var config =  {
                method: "GET",
                url:'http://localhost:8080/api/employees/getnearest/'+ziplat+"/"+ziplng,
                headers: {
                    'Content-Type': 'application/json; charset=utf-8'
                }
            };
            return $http(config);
        }


    }
})();