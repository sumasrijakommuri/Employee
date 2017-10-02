package com.computech.Entity;

import javax.persistence.*;
import java.util.UUID;

@Entity
@NamedQueries({
        @NamedQuery(name="Employee.getAll",query= "select employee FROM Employee employee"),
        @NamedQuery(name = "Employee.findByEmail", query = "select employee from Employee employee where employee.email=:paramEmail")}
)
public class Employee {

    @Id
    private String empId;

    @Column(unique = true, nullable = false)
    private String email;

    private String firstName;
    private String lastName;


    @OneToOne
    private Address address;

    public Employee(){
        empId = UUID.randomUUID().toString();
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

}
