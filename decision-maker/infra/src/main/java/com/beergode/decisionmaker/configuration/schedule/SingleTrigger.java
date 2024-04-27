package com.beergode.decisionmaker.configuration.schedule;

import java.time.Instant;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;

public class SingleTrigger implements Trigger {

    private final Instant startTime;

    public SingleTrigger(Instant startTime) {
        this.startTime = startTime;
    }

    @Override
    public Instant nextExecution(TriggerContext triggerContext) {
        return triggerContext.lastCompletion() == null ? startTime : null;
    }
}

