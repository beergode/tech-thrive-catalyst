package com.beergode.decisionmaker.adapters.survey.rest.dto;

import com.beergode.decisionmaker.survey.model.Question;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionResponse {
    private UUID id;
    private String text;
    private LocalDateTime createdAt;
    private List<AnswerResponse> answers;

    public static QuestionResponse from(Question question) {
        return QuestionResponse.builder()
                .id(question.getId())
                .text(question.getText())
                .createdAt(question.getCreatedAt())
                .answers(question.getAnswers()
                        .stream()
                        .map(AnswerResponse::from)
                        .toList())
                .build();
    }
}
