package com.computech.Entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class Address {

    @Id
    private String addressId;

    private String streetAddress1;
    private String streetAddress2;

    private String state;
    private String country;

    private String zipCode;

    public Address(){
        addressId = UUID.randomUUID().toString();
    }

    public String getAddressId() {
        return addressId;
    }


    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getStreetAddress1() {
        return streetAddress1;
    }

    public void setStreetAddress1(String streetAddress1) {
        this.streetAddress1 = streetAddress1;
    }

    public String getStreetAddress2() {
        return streetAddress2;
    }

    public void setStreetAddress2(String streetAddress2) {
        this.streetAddress2 = streetAddress2;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}
