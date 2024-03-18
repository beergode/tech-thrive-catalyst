package com.beergode.decisionmaker.common.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DecisionMakerBusinessException extends RuntimeException {

    private final String key;
    private final String[] args;

    public DecisionMakerBusinessException(String key) {
        super(key);
        this.key = key;
        args = new String[0];
    }

    public DecisionMakerBusinessException(String key, String... args) {
        super(key);
        this.key = key;
        this.args = args;
    }
}