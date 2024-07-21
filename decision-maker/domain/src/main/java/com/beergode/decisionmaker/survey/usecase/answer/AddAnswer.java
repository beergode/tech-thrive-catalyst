package com.beergode.decisionmaker.survey.usecase.answer;

import com.beergode.decisionmaker.common.model.UseCase;

public record AddAnswer(String surveyId, String questionId, String text) implements UseCase {
}
