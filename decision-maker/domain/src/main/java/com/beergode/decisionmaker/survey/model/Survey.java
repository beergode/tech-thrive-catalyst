package com.beergode.decisionmaker.survey.model;

import com.beergode.decisionmaker.survey.usecase.SurveyCreate;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class Survey {

    private String id;
    private String content;
    private LocalDateTime createdAt;

    private Question question;
}
