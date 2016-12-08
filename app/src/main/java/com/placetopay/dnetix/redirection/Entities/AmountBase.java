package com.placetopay.dnetix.redirection.Entities;

import com.placetopay.dnetix.redirection.Contracts.Mappable;

import java.util.HashMap;
import java.util.Map;

public class AmountBase implements Mappable {

    private String currency = "COP";
    private Double total;

    public String getCurrency() {
        return currency;
    }

    public Double getTotal() {
        return total;
    }

    public AmountBase setCurrency(String currency) {
        this.currency = currency;
        return this;
    }

    public AmountBase setTotal(Double total) {
        this.total = total;
        return this;
    }

    @Override
    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("currency", getCurrency());
        map.put("total", getTotal());
        return map;
    }
}
