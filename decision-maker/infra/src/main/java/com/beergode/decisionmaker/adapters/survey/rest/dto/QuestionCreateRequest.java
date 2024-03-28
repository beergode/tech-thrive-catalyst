package com.beergode.decisionmaker.adapters.survey.rest.dto;

import com.beergode.decisionmaker.survey.usecase.QuestionCreate;
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

    private String text;
    private List<AnswerCreateRequest> answers;

    public QuestionCreate toUseCase() {
        return QuestionCreate.builder()
                .text(text)
                .answers(answers.stream()
                        .map(AnswerCreateRequest::toUseCase)
                        .collect(Collectors.toList()))
                .build();
    }
}
