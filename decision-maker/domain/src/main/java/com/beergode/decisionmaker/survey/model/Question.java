package com.beergode.decisionmaker.survey.model;

import com.beergode.decisionmaker.survey.usecase.QuestionCreate;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class Question {

    private String id;
    private String text;
    private LocalDateTime createdAt;

    private List<Answer> answers;

    public QuestionCreate toUseCase() {
        return QuestionCreate.builder()
            .text(text)
            .answers(answers.stream()
                .map(Answer::toUseCase)
                .collect(Collectors.toList()))
            .build();
    }
}
