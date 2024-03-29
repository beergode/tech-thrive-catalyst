package com.beergode.decisionmaker.adapters.survey.rest.dto;

import com.beergode.decisionmaker.survey.model.Question;
import com.beergode.decisionmaker.survey.model.Survey;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SurveyResponse {
    private String id;
    private String content;

    private LocalDateTime createdAt;

    private Question question;

    public static SurveyResponse from(Survey survey) {
        return SurveyResponse.builder()
                .id(survey.getId())
                .content(survey.getContent())
                .createdAt(survey.getCreatedAt())
                .question(survey.getQuestion())
                .build();
    }
}
