package com.placetopay.dnetix.redirection.Entities;

import com.placetopay.dnetix.redirection.Contracts.Mappable;

import java.util.HashMap;
import java.util.Map;

public class Address implements Mappable {

    private String street;
    private String city;
    private String state;
    private String postalCode;
    private String country;
    private String phone;

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getCountry() {
        return country;
    }

    public String getPhone() {
        return phone;
    }

    public Address setStreet(String street) {
        this.street = street;
        return this;
    }

    public Address setCity(String city) {
        this.city = city;
        return this;
    }

    public Address setState(String state) {
        this.state = state;
        return this;
    }

    public Address setPostalCode(String postalCode) {
        this.postalCode = postalCode;
        return this;
    }

    public Address setCountry(String country) {
        this.country = country;
        return this;
    }

    public Address setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    @Override
    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("street", getStreet());
        map.put("city", getCity());
        map.put("state", getState());
        map.put("postalCode", getPostalCode());
        map.put("country", getCountry());
        map.put("phone", getPhone());
        return map;
    }
}
