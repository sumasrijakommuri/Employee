package com.computech.Service;

import com.computech.Entity.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> getAll();
    Employee getOne(String email);
    Employee create(Employee employee);
    Employee update(Employee employee);
    void delete(String email);
}
