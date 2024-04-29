package com.beergode.decisionmaker.adapters.survey.rest.dto;

import com.beergode.decisionmaker.survey.usecase.SurveyVote;
import jakarta.validation.constraints.NotEmpty;
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
  @NotEmpty(message = "answer id is required")
  private String answerId;

  public SurveyVote toUseCase(String surveyId) {
    return SurveyVote.builder()
            .surveyId(surveyId)
            .questionId(questionId)
            .answerId(answerId)
            .build();
  }
}
