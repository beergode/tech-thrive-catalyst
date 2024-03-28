package com.beergode.decisionmaker.adapters.survey.rest.dto;

import com.beergode.decisionmaker.survey.usecase.AnswerCreate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnswerCreateRequest {

    private String text;

    public AnswerCreate toUseCase() {
        return AnswerCreate.builder()
                .text(text)
                .build();
    }
}
