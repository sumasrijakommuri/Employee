package com.computech.Repository;

import com.computech.Entity.Address;
import com.computech.Entity.Employee;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class EmployeeRepositoryImplementation implements EmployeeRepository {

    @PersistenceContext
    EntityManager entityManager;

    public List<Employee> getAll() {

        TypedQuery<Employee> query = entityManager.createNamedQuery("Employee.getAll", Employee.class);

        return query.getResultList();
    }

    public Employee getOne(String email) {
        return entityManager.find(Employee.class,email);
    }

    public Employee create(Employee employee) {
        Address address = employee.getAddress();
        entityManager.persist(address);
        employee.setAddress(address);
        entityManager.persist(employee);

        return employee;
    }

    public Employee update(Employee employee) {
        Address address = employee.getAddress();
        entityManager.merge(address);
        employee.setAddress(address);
        entityManager.merge(employee);
        return employee;
    }

    public void delete(Employee employee) {
        Address address = employee.getAddress();
        entityManager.remove(employee);
        entityManager.remove(address);
    }


}
