package com.beergode.decisionmaker.adapters.survey.rest.dto;


import com.beergode.decisionmaker.survey.usecase.AddAnswer;
import com.beergode.decisionmaker.survey.usecase.SurveyVote;
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
        return AddAnswer.builder()
                .surveyId(surveyId)
                .questionId(questionId)
                .text(text)
                .build();
    }

    public SurveyVote toSurveyVoteUseCase(String surveyId) {
        return SurveyVote.builder()
                .surveyId(surveyId)
                .questionId(questionId)
                .answerId(answerId)
                .build();
    }
}
