package com.beergode.decisionmaker.survey.model;

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
}
