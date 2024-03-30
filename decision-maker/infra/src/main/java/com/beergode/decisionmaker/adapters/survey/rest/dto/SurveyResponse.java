package com.beergode.decisionmaker.adapters.survey.rest.dto;

import com.beergode.decisionmaker.survey.model.Question;
import com.beergode.decisionmaker.survey.model.Survey;
import com.beergode.decisionmaker.survey.model.SurveySetting;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SurveyResponse {
    private String id;
    private String content;

    private Long createdAt;

    private Question question;

    private LocalDate closedAt;
    private SurveySetting setting;
    private Integer participantCount;

    public static SurveyResponse from(Survey survey) {
        return SurveyResponse.builder()
                .id(survey.getId().toString())
                .content(survey.getContent())
                .createdAt(survey.getCreatedLong())
                .question(survey.getQuestion())
                .closedAt(survey.getClosedAt())
                .setting(survey.getSetting())
                .participantCount(survey.getParticipantCount())
                .build();
    }
}
