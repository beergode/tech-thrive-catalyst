package com.beergode.decisionmaker.adapters.survey.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnswerResponse {

    private String answerText;
    private Long voteCount;
}
