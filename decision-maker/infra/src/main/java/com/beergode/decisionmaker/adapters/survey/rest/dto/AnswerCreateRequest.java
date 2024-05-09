package com.beergode.decisionmaker.adapters.survey.rest.dto;

import com.beergode.decisionmaker.survey.usecase.create.AnswerCreate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.beergode.decisionmaker.survey.usecase.create.AnswerCreate.answerCreate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnswerCreateRequest {

    private String text;
    private boolean isCustom;

    public AnswerCreate toUseCase() {
        return answerCreate()
                .text(text)
                .isCustom(isCustom)
                .build();
    }
}
