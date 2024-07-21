package com.beergode.decisionmaker.survey.usecase.answer;

import com.beergode.decisionmaker.common.model.UseCase;
import java.util.List;

public record AddAnswers(String surveyId, List<AddAnswer> addAnswers) implements UseCase {
}
