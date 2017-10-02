(function () {
    'use strict';

    var app= angular.module('employeeApp');
    app.controller('EmployeeController',EmployeeController);

    EmployeeController.$inject = ['EmployeeService','$scope'];
    function EmployeeController(EmployeeService,$scope) {
        var EmployeeVm = this;

        //Get Employees
        EmployeeService.getEmployees()
            .then(function (response) {
                EmployeeVm.employees = response.data;
                console.log(response.data);
            },function (error) {
                console.log(error);
            });

        
        //Ng-click add 
        $scope.add = function () {
            console.log("Testing add!");
            var data={
                "email":document.getElementsByName("email")[0].value,
                "firstName":document.getElementsByName("firstName")[0].value,
                "lastName":document.getElementsByName("lastName")[0].value,
                "address":{
                    "streetAddress1":document.getElementsByName("address1")[0].value,
                    "streetAddress2":document.getElementsByName("address2")[0].value,
                    "city":document.getElementsByName("city")[0].value,
                    "state":document.getElementsByName("state")[0].value,
                    "country":document.getElementsByName("country")[0].value,
                    "zipCode":document.getElementsByName("zipCode")[0].value
                }
            };

            EmployeeService.createEmployee(data)
                .then(function (response) {
                    console.log("Success!");
                    window.location.href="#!/employees"
                },function (error) {
                    console.log("Error!");
                });
        };
        
        
        //ng-click edit
        $scope.editemp = function (employee) {
            $scope.firstName = employee.firstName;
            console.log(employee.firstName);
            EmployeeVm.firstName="Srija";
            window.location.href = "#!/editemployee/";
        };
        
        //ng-click delete
        $scope.delete = function (employee) {
            if(confirm("Do you want to delete "+ employee.firstName+" "+ employee.lastName)== true)
            {
                console.log(employee.empId);
                EmployeeService.deleteEmployee(employee.empId);
                location.reload();
            }
            else
            {
                console.log("Cancelled");
            };
        }
    }
})();