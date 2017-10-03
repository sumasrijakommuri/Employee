package com.computech.Service;

import com.computech.EmailValidator;
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

    EmailValidator emailValidator = new EmailValidator();

    @Autowired
    EmployeeRepository repository;

    @Transactional(readOnly =true)
    public List<Employee> getAll() {
        return repository.getAll();
    }
    @Transactional(readOnly =true)
    public Employee getOne(String empId) {
        Employee exists = repository.getOne(empId);
        if(exists==null)
        {
            throw new BadRequestException("Employee does not exist.");
        }
        return exists;
    }

    @Transactional
    public Employee create(Employee employee) {
        Employee exists = repository.finByEmail(employee.getEmail());

        if(exists != null)
        {
            throw new BadRequestException("Employee with email "+employee.getEmail()+" already exists.");
        }
        else{
           boolean valid = emailValidator.validate(employee.getEmail());
           if(!valid)
           {
               throw new BadRequestException("Email address provided is not valid.");
           }
        }
        return repository.create(employee);
    }

    @Transactional
    public Employee update(Employee employee) {

            boolean valid = emailValidator.validate(employee.getEmail());
            if(!valid)
            {
                throw new BadRequestException("Email address provided is not valid.");
            }

        return repository.update(employee);
    }

    @Transactional
    public void delete(String empId) {
        Employee exists = repository.getOne(empId);
        if(exists==null)
        {
            throw new ResourceNotFoundException("The employee with email "+empId +" does not exist.");
        }
        repository.delete(exists);
    }
    @Transactional
    public Employee findByEmail(String email){
        return repository.finByEmail(email);
    }

}
