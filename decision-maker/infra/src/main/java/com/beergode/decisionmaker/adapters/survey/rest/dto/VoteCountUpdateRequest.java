package com.beergode.decisionmaker.adapters.survey.rest.dto;

import com.beergode.decisionmaker.survey.usecase.SurveyVote;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VoteCountUpdateRequest {

  private String questionId;
  private String answerId;

  public SurveyVote toUseCase(String surveyId) {
    return SurveyVote.builder()
            .surveyId(surveyId)
            .questionId(questionId)
            .answerId(answerId)
            .build();
  }
}
