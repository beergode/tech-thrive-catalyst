package com.beergode.decisionmaker.adapters.survey.rest.dto;

import com.beergode.decisionmaker.survey.usecase.update.QuestionUpdate;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionUpdateRequest {

    private UUID id;
    private String text;
    private List<AnswerUpdateRequest> answers;


    public QuestionUpdate toUseCase() {
        return QuestionUpdate.questionUpdate()
                .id(id)
                .text(text)
                .answers(answers.stream()
                        .map(AnswerUpdateRequest::toUseCase)
                        .toList())
                .build();
    }
}
