package com.beergode.decisionmaker.adapters.survey.rest.dto;

import com.beergode.decisionmaker.survey.model.Answer;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnswerResponse {
    private UUID id;
    private String text;
    private Long voteCount;
    private LocalDateTime createdAt;
    private boolean isCustom;

    public static AnswerResponse from(Answer answer) {
        return AnswerResponse.builder()
                .id(answer.getId())
                .text(answer.getText())
                .voteCount(answer.getVoteCount())
                .createdAt(answer.getCreatedAt())
                .isCustom(answer.isCustom())
                .build();
    }
}
