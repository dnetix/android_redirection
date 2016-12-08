package com.placetopay.dnetix.redirection.Entities;

import com.placetopay.dnetix.redirection.Contracts.Mappable;

import java.util.HashMap;
import java.util.Map;

public class Payment implements Mappable {

    private String reference;
    private String description;
    private Amount amount;
    private Person shipping;

    public Payment() {
        this.amount = new Amount();
        this.shipping = new Person();
    }

    public String getReference() {
        return reference;
    }

    public String getDescription() {
        return description;
    }

    public Amount getAmount() {
        return amount;
    }

    public Person getShipping() {
        return shipping;
    }

    public Payment setReference(String reference) {
        this.reference = reference;
        return this;
    }

    public Payment setDescription(String description) {
        this.description = description;
        return this;
    }

    @Override
    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("reference", getReference());
        map.put("description", getDescription());
        map.put("amount", getAmount().toMap());
        map.put("shipping", getShipping().toMap());
        return map;
    }
}
