package com.beergode.decisionmaker.adapters.survey.rest.dto;

import com.beergode.decisionmaker.survey.usecase.QuestionCreate;
import com.beergode.decisionmaker.survey.usecase.SurveyCreate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionCreateRequest {

    private String questionText;
    private List<AnswerCreateRequest> answerList;

    public QuestionCreate toUseCase() {
        return QuestionCreate.builder()
                .text(questionText)
                .answerList(answerList.stream()
                        .map(AnswerCreateRequest::toUseCase)
                        .collect(Collectors.toList()))
                .build();
    }
}
