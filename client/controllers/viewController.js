(function () {
    'use strict';

    var app = angular.module('employeeApp');
    app.controller('ViewController', ViewController);

    ViewController.$inject= ['EmployeeService','$scope'];

    function ViewController(EmployeeService,$scope){
            var viewVm = this;

            viewVm.currentEmployee = EmployeeService.getShared();


            EmployeeService.getWeather(viewVm.currentEmployee.address.city,viewVm.currentEmployee.address.country)
                .then(function (response) {
                    console.log("Success!");
                    console.log(response.data.query.results.channel.item.forecast);
                    viewVm.weatherdetails = response.data.query.results.channel.item.forecast;
                }, function (error) {
                    console.log("Error!");
                })

    }


})();