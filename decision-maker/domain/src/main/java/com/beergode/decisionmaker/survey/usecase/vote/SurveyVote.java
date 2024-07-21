package com.beergode.decisionmaker.survey.usecase.vote;

import com.beergode.decisionmaker.common.model.UseCase;
import lombok.Builder;
import lombok.Data;

public record SurveyVote(String questionId, String answerId) implements UseCase {
}
