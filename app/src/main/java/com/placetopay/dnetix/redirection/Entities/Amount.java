package com.placetopay.dnetix.redirection.Entities;

import com.placetopay.dnetix.redirection.Contracts.Mappable;

import java.util.Map;

public class Amount extends AmountBase implements Mappable {
    @Override
    public Map<String, Object> toMap() {
        Map<String, Object> map = super.toMap();
        return map;
    }
}
