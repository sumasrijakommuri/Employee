package com.computech.Service;

import com.computech.Entity.Employee;
import com.computech.Exceptions.BadRequestException;
import com.computech.Exceptions.ResourceNotFoundException;
import com.computech.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeServiceImplementation implements EmployeeService {

    @Autowired
    EmployeeRepository repository;

    @Transactional(readOnly =true)
    public List<Employee> getAll() {
        return repository.getAll();
    }
    @Transactional(readOnly =true)
    public Employee getOne(String email) {
        Employee exists = repository.getOne(email);
        if(exists==null)
        {
            throw new BadRequestException("Employee with email "+email+ " does not exist.");
        }
        return exists;
    }

    @Transactional
    public Employee create(Employee employee) {
        Employee exists = repository.getOne(employee.getEmail());

        if(exists != null)
        {
            throw new BadRequestException("Employee with email "+employee.getEmail()+" already exists.");
        }
        return repository.create(employee);
    }

    @Transactional
    public Employee update(Employee employee) {
        return repository.update(employee);
    }

    @Transactional
    public void delete(String email) {
        Employee exists = repository.getOne(email);
        if(exists==null)
        {
            throw new ResourceNotFoundException("The employee with email "+email +" does not exist.");
        }
        repository.delete(exists);
    }
}
