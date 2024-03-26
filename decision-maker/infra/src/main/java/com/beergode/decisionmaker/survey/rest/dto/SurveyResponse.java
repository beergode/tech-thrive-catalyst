package com.beergode.decisionmaker.survey.rest.dto;

import com.beergode.decisionmaker.common.model.Status;
import com.beergode.decisionmaker.survey.model.Survey;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SurveyResponse {

  private String id;
  private LocalDateTime createdAt;
  private String content;
  private Status status;

  public static SurveyResponse fromModel(Survey survey) {
    return SurveyResponse.builder()
        .id(survey.getId())
        .createdAt(survey.getCreatedAt())
        .content(survey.getContent())
        .status(survey.getStatus())
        .build();
  }
}