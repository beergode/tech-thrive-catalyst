package com.beergode.decisionmaker.survey.usecase;

import com.beergode.decisionmaker.common.model.UseCase;
import com.beergode.decisionmaker.survey.model.Question;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SurveyCreate implements UseCase {

    private String content;
    private QuestionCreate question;

}
