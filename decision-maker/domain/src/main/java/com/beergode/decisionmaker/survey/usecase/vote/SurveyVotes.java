package com.beergode.decisionmaker.survey.usecase.vote;

import com.beergode.decisionmaker.common.model.UseCase;
import java.util.List;

public record SurveyVotes(String surveyId, List<SurveyVote> surveyVotes) implements UseCase {
}
