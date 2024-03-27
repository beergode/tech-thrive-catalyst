package com.beergode.decisionmaker.survey.usecase;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AnswerCreate {

    private String text;
}
