package com.beergode.decisionmaker.survey.model;

import com.beergode.decisionmaker.survey.usecase.AnswerCreate;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class Answer {
  private String id;
  private String text;
  private Long voteCount = 0L;

  private LocalDateTime createdAt;

  public void incrementVoteCount() {
    this.voteCount++;
  }

  public AnswerCreate toUseCase() {
    return AnswerCreate.builder()
        .text(text)
        .build();
  }
}
