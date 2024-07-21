package com.beergode.decisionmaker.adapters.survey.rest.dto;


import com.beergode.decisionmaker.survey.usecase.answer.AddAnswer;
import com.beergode.decisionmaker.survey.usecase.vote.SurveyVote;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnswerRequest {

    private String questionId;
    private String answerId;
    private String text;

    public AddAnswer toAddAnswerUseCase(String surveyId) {
        return new AddAnswer(surveyId, questionId, text);
    }

    public SurveyVote toSurveyVoteUseCase() {
        return new SurveyVote(questionId, answerId);
    }
}
