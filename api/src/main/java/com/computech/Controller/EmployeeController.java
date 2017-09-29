package com.computech.Controller;

import com.computech.Entity.Employee;
import com.computech.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@RestController
@CrossOrigin
public class EmployeeController {

    @Autowired
    EmployeeService service;

    @RequestMapping(method= RequestMethod.GET,path = "/employees", produces=APPLICATION_JSON_UTF8_VALUE)
    public List<Employee> getAll(){
        return service.getAll();
    }

    @RequestMapping(method = RequestMethod.GET,path="/employees/{id}",produces = APPLICATION_JSON_UTF8_VALUE)
    public Employee getOne(@PathVariable("id") String email){
        return service.getOne(email);
    }

    @RequestMapping(method = RequestMethod.POST, path="/employees", consumes = APPLICATION_JSON_UTF8_VALUE, produces = APPLICATION_JSON_UTF8_VALUE)
    public Employee create(@RequestBody Employee employee)
    {
        return service.create(employee);
    }

    @RequestMapping(method = RequestMethod.PUT, path="/employees", consumes = APPLICATION_JSON_UTF8_VALUE, produces = APPLICATION_JSON_UTF8_VALUE)
    public Employee update(@RequestBody Employee employee)
    {
        return service.update(employee);
    }

    @RequestMapping(method = RequestMethod.DELETE, path="/employees/{id}")
    public void delete(@PathVariable("id") String email)
    {
        service.delete(email);
    }
}
