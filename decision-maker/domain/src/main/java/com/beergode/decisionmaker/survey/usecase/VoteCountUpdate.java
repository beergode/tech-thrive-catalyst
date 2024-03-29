package com.beergode.decisionmaker.survey.usecase;

import com.beergode.decisionmaker.common.model.UseCase;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VoteCountUpdate implements UseCase {

  private String surveyId;
  private String questionId;
  private String answerId;
}
