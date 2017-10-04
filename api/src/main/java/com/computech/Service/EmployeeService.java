package com.computech.Service;

import com.computech.Coordinates;
import com.computech.Entity.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> getAll();
    Employee getOne(String empId);
    Employee create(Employee employee);
    Employee update(Employee employee);
    void delete(String email);
    Employee findByEmail(String email);
    List<Employee> getNearest(String lat, String lng);
}
