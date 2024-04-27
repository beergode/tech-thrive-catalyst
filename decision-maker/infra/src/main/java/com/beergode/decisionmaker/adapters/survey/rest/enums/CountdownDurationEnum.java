package com.beergode.decisionmaker.adapters.survey.rest.enums;

public enum CountdownDurationEnum {
    FIFTEEN_SECONDS(15),
    THIRTY_SECONDS(30),
    SIXTY_SECONDS(60),
    ONE_HUNDRED_TWENTY_SECONDS(120),
    INFINITE(null);

    private final Integer seconds;

    CountdownDurationEnum(Integer seconds) {
        this.seconds = seconds;
    }

    public Integer getSeconds() {
        return seconds;
    }
}
