package com.beergode.decisionmaker.survey.usecase;

import com.beergode.decisionmaker.common.model.UseCase;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class QuestionCreate implements UseCase {

    private String text;
    private List<AnswerCreate> answers;

}
