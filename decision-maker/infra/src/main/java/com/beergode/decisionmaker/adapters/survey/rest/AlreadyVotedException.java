package com.beergode.decisionmaker.adapters.survey.rest;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlreadyVotedException extends RuntimeException {

    private final String key;
    private final String[] args;

    public AlreadyVotedException(String key) {
        super(key);
        this.key = key;
        args = new String[0];
    }

    public AlreadyVotedException(String key, String... args) {
        super(key);
        this.key = key;
        this.args = args;
    }
}
