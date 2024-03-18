package com.beergode.decisionmaker.common.usecase;

import com.beergode.decisionmaker.common.model.UseCase;

public interface UseCaseHandler<R, T extends UseCase> {

    R handle(T useCase);
}
