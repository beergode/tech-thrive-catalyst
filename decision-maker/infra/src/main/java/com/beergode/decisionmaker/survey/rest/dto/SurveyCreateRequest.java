package com.beergode.decisionmaker.survey.rest.dto;

import com.beergode.decisionmaker.survey.usecase.SurveyCreate;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SurveyCreateRequest {

  private String id;
  private String content;

  public SurveyCreate toModel() {
    return SurveyCreate.builder().id(id).content(content).build();
  }
}