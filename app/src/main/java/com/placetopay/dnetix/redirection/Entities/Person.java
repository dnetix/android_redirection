package com.placetopay.dnetix.redirection.Entities;

import com.placetopay.dnetix.redirection.Contracts.Mappable;

import java.util.HashMap;
import java.util.Map;

public class Person implements Mappable {

    private String document;
    private String documentType;
    private String name;
    private String surname;
    private String company;
    private String email;
    private Address address;
    private String mobile;

    public Person() {
        this.address = new Address();
    }

    public String getDocument() {
        return document;
    }

    public String getDocumentType() {
        return documentType;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getCompany() {
        return company;
    }

    public String getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    public String getMobile() {
        return mobile;
    }

    public Person setDocument(String document) {
        this.document = document;
        return this;
    }

    public Person setDocumentType(String documentType) {
        this.documentType = documentType;
        return this;
    }

    public Person setName(String name) {
        this.name = name;
        return this;
    }

    public Person setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public Person setCompany(String company) {
        this.company = company;
        return this;
    }

    public Person setEmail(String email) {
        this.email = email;
        return this;
    }

    public Person setMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public boolean isEmpty() {
        if (getName() == null)
            return true;
        return false;
    }

    @Override
    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("document", getDocument());
        map.put("documentType", getDocumentType());
        map.put("name", getName());
        map.put("surname", getSurname());
        map.put("company", getCompany());
        map.put("email", getEmail());
        map.put("mobile", getMobile());
        map.put("address", getAddress().toMap());
        return map;
    }
}
