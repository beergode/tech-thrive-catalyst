package com.beergode.decisionmaker.adapters.survey.rest.dto;

import com.beergode.decisionmaker.survey.usecase.update.AnswerUpdate;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnswerUpdateRequest {

    private UUID id;
    private String text;
    private Long voteCount;
    private boolean isCustom;

    public AnswerUpdate toUseCase() {
        return AnswerUpdate.answerUpdate()
                .id(id)
                .text(text)
                .voteCount(voteCount)
                .isCustom(isCustom)
                .build();
    }
}
