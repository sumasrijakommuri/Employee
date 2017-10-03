(function () {
    'use strict';

    var app = angular.module('employeeApp');
    app.controller('EmployeeController', EmployeeController);

    EmployeeController.$inject = ['EmployeeService', '$scope', '$routeParams'];

    function EmployeeController(EmployeeService, $scope, $routeParams) {
        var EmployeeVm = this;

        //Get Employees
        EmployeeService.getEmployees()
            .then(function (response) {
                EmployeeVm.employees = response.data;
                console.log(response.data);
            }, function (error) {
                console.log(error);
            });


        //Ng-click add 
        $scope.add = function () {
            console.log("Testing add!");
            var data = {
                "email": document.getElementsByName("email")[0].value,
                "firstName": document.getElementsByName("firstName")[0].value,
                "lastName": document.getElementsByName("lastName")[0].value,
                "address": {
                    "streetAddress1": document.getElementsByName("address1")[0].value,
                    "streetAddress2": document.getElementsByName("address2")[0].value,
                    "city": document.getElementsByName("city")[0].value,
                    "state": document.getElementsByName("state")[0].value,
                    "country": document.getElementsByName("country")[0].value,
                    "zipCode": document.getElementsByName("zipCode")[0].value
                }
            };

            EmployeeService.createEmployee(data)
                .then(function (response) {
                    console.log("Success!");
                    window.location.href = "#!/employees";
                }, function (error) {
                    alert("Invalid input!");
                    document.getElementById("addform").reset();
                    console.log(error);
                });
        };


        //ng-click edit
        $scope.editemp = function (empId) {
            var currentid = $routeParams.id;

           console.log(empId);
            EmployeeService.getEmployee(empId)
                .then(function (response) {
                    console.log("Success!");
                    EmployeeVm.currentEmployee= response.data;
                    console.log(EmployeeVm.currentEmployee);
                    window.location.href = "#!/editemployee";
                    console.log("After relocation")
                    console.log(EmployeeVm.currentEmployee);
                }, function (error) {
                    console.log("Error!");
                })
        };

        //ng-click delete
        $scope.delete = function (employee) {
            if (confirm("Do you want to delete " + employee.firstName + " " + employee.lastName) == true) {
                console.log(employee.empId);
                EmployeeService.deleteEmployee(employee.empId);
                location.reload();
            }
            else {
                console.log("Cancelled");
            }
            ;
        }
    }
})();