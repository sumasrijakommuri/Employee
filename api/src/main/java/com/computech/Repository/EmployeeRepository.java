package com.computech.Repository;

import com.computech.Coordinates;
import com.computech.Entity.Employee;

import java.util.List;

public interface EmployeeRepository {
    List<Employee> getAll();
    Employee getOne(String empId);
    Employee create(Employee employee);
    Employee update(Employee employee);
    void delete(Employee employee);
    Employee finByEmail(String email);
    List<Employee> getNearest(String lat, String lng);
}
