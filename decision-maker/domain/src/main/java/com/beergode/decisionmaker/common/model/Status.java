package com.beergode.decisionmaker.common.model;

public enum Status {

    ACTIVE("ACTIVE"),
    PASSIVE("PASSIVE"),
    DELETED("DELETED");

    private final String value;

    Status(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}

