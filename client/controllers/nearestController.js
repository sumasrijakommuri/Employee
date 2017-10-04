(function () {
    'use strict';

    var app = angular.module('employeeApp');
    app.controller('NearestController', NearestController);

    NearestController.$inject =['EmployeeService', '$scope'];

    function NearestController(EmployeeService,$scope) {

        var NearestVm = this;
        NearestVm.nearestEmployees = null;

        $scope.getNearest = function () {
            var zipCode = document.getElementById("zipCode").value;
            EmployeeService.getLatLong(zipCode)
                .then(function (response) {
                    console.log(response.data.results[0].geometry.location);
                    NearestVm.latlng = response.data.results[0].geometry.location;
                    EmployeeService.getNearestEmployees(NearestVm.latlng.lat,NearestVm.latlng.lng)
                        .then(function (result) {
                            console.log("Success!");
                            NearestVm.nearestEmployees = result.data;
                            console.log(result.data);
                        },function (error) {
                            console.log("Error!");
                        })

                },function (error) {
                    console.log("Error!");
                })


        }
    }

})();