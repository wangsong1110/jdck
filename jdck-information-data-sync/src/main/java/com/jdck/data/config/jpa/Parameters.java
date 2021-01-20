package com.jdck.data.config.jpa;

import lombok.Data;

@Data
public class Parameters<T> {
    private operator operator;
    private String attributeName;
    private T value;

    public Parameters(operator operator, String attributeName, T value) {
        this.operator = operator;
        this.attributeName = attributeName;
        this.value = value;
    }
    public Parameters(){}
}
