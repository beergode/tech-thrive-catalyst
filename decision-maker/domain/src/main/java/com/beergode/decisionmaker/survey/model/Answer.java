package com.beergode.decisionmaker.survey.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class Answer {
    private String id;
    private String text;

    private Long voteCount;

    private LocalDateTime createdAt;
}
