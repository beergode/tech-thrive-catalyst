package com.beergode.decisionmaker.adapters.survey.rest.dto;

import com.beergode.decisionmaker.survey.usecase.VoteCountUpdate;
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

  public VoteCountUpdate toUseCase() {
    return VoteCountUpdate.builder().questionId(questionId).answerId(answerId).build();
  }
}
