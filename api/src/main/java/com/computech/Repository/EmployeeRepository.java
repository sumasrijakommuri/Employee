package com.computech.Repository;

import com.computech.Entity.Employee;

import java.util.List;

public interface EmployeeRepository {
    List<Employee> getAll();
    Employee getOne(String email);
    Employee create(Employee employee);
    Employee update(Employee employee);
    void delete(Employee employee);

}
