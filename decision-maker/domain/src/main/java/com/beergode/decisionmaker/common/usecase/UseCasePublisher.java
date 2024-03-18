package com.beergode.decisionmaker.common.usecase;

import com.beergode.decisionmaker.common.model.UseCase;

public interface UseCasePublisher {

    <R, T extends UseCase> R publish(Class<R> returnClass, T useCase);

    <R, T extends UseCase> void publish(T useCase);

    <R> R publish(Class<R> returnClass);
}
