package com.beergode.decisionmaker.adapters.survey.rest.dto;

import com.beergode.decisionmaker.survey.usecase.answer.AddAnswer;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddAnswerRequest {

    private String questionId;
    @NotEmpty(message = "answer text is required")
    private String text;

    public AddAnswer toUseCase(String surveyId) {
        return new AddAnswer(surveyId, questionId, text);
    }
}
