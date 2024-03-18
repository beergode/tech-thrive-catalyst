package com.beergode.decisionmaker.common.usecase;

import com.beergode.decisionmaker.common.model.UseCase;

public interface VoidUseCaseHandler<T extends UseCase> {

    void handle(T useCase);
}
