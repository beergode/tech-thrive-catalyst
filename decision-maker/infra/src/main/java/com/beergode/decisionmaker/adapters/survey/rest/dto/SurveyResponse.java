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
    private String handlingKey;
    private String content;
    private String note;
    private Integer countdownDurationSeconds;
    private Long createdAt;
    private Question question;
    private LocalDate closedAt;
    private SurveySetting setting;
    private Integer participantCount;
    private boolean isOwner;
    private boolean canVote;

    public static SurveyResponse from(Survey survey) {
        return SurveyResponse.builder()
                .id(survey.getId().toString())
                .handlingKey(survey.getHandlingKey())
                .content(survey.getContent())
                .note(survey.getNote())
                .countdownDurationSeconds(survey.getCountdownDurationSeconds())
                .createdAt(survey.getCreatedLong())
                .question(survey.getQuestion())
                .closedAt(survey.getClosedAt())
                .setting(survey.getSetting())
                .participantCount(survey.getParticipantCount())
                .build();
    }

    public static SurveyResponse from(Survey survey, boolean isOwner, boolean isVoted) {
        return SurveyResponse.builder()
                .id(survey.getId().toString())
                .handlingKey(survey.getHandlingKey())
                .content(survey.getContent())
                .note(survey.getNote())
                .countdownDurationSeconds(survey.getCountdownDurationSeconds())
                .createdAt(survey.getCreatedLong())
                .question(survey.getQuestion())
                .closedAt(survey.getClosedAt())
                .setting(survey.getSetting())
                .participantCount(survey.getParticipantCount())
                .isOwner(isOwner)
                .canVote(isVoted)
                .build();
    }
}
