(function () {
    'use strict';

    var app = angular.module('employeeApp');
    app.controller('EditController', EditController);

    EditController.$inject= ['EmployeeService','$scope'];

    function EditController(EmployeeService,$scope) {
        var EditVm = this;

        EditVm.currentEmployee = EmployeeService.getShared();
        console.log(EditVm.currentEmployee);


        $scope.update = function () {
            var address = document.getElementsByName("address1")[0].value + document.getElementsByName("address2")[0].value +
                document.getElementsByName("city")[0].value
                + document.getElementsByName("state")[0].value ;

            EmployeeService.getLatLong(address)
                .then(function (response) {
                    console.log(response.data.results[0].geometry.location);
                    EditVm.latlong = response.data.results[0].geometry.location;
                },function (error) {
                    console.log("Error!");
                });

            var data = {
                "empId": EditVm.currentEmployee.empId,
                "email": document.getElementsByName("email")[0].value,
                "firstName": document.getElementsByName("firstName")[0].value,
                "lastName": document.getElementsByName("lastName")[0].value,
                "address": {
                    "addressId": EditVm.currentEmployee.address.addressId,
                    "streetAddress1": document.getElementsByName("address1")[0].value,
                    "streetAddress2": document.getElementsByName("address2")[0].value,
                    "city": document.getElementsByName("city")[0].value,
                    "state": document.getElementsByName("state")[0].value,
                    "country": document.getElementsByName("country")[0].value,
                    "zipCode": document.getElementsByName("zipCode")[0].value,
                    "latitude": EditVm.latlong.lat.toString(),
                    "longitude":EditVm.latlong.lng
                }
            };

            EmployeeService.update(data)
                .then(function (response) {
                    console.log("Success!");
                    window.location.href = "#!/employees";
                }, function (error) {
                    console.log(error.toString());
                    console.log("Error!");
                })


        };

    }
})();