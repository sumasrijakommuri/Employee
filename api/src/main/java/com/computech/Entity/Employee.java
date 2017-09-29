package com.computech.Entity;

import javax.persistence.*;

@Entity
@NamedQueries(
        @NamedQuery(name="Employee.getAll",query= "select employee FROM Employee employee")
)
public class Employee {

    @Id
    private String email;

    private String firstName;
    private String lastName;


    @OneToOne
    private Address address;

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
