package com.beergode.decisionmaker.adapters.survey.rest.dto;


import com.beergode.decisionmaker.survey.usecase.answer.AddAnswer;
import com.beergode.decisionmaker.survey.usecase.answer.AddAnswers;
import com.beergode.decisionmaker.survey.usecase.vote.SurveyVotes;
import java.util.List;
import lombok.Data;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

@Data
public class AnswersRequest {

    private List<AnswerRequest> answerRequests;

    public AddAnswers getAddAnswers(String surveyId) {
        return answerRequests.stream()
                .filter(answerRequest -> answerRequest.getAnswerId() == null)
                .map(answerRequest -> answerRequest.toAddAnswerUseCase(surveyId))
                .collect(collectingAndThen(toList(), surveyAnswers -> new AddAnswers(surveyId, surveyAnswers)
                ));
    }

    public SurveyVotes getSurveyVotes(String surveyId) {
        return answerRequests.stream()
                .filter(answerRequest -> answerRequest.getAnswerId() != null)
                .map(AnswerRequest::toSurveyVoteUseCase)
                .collect(collectingAndThen(toList(), surveyVotes -> new SurveyVotes(surveyId, surveyVotes)
                ));
    }
}
