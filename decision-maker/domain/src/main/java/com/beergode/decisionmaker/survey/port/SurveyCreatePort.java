package com.beergode.decisionmaker.survey.port;

import com.beergode.decisionmaker.survey.model.Survey;
import com.beergode.decisionmaker.survey.usecase.SurveyCreate;

public interface SurveyCreatePort {

    Survey create(SurveyCreate surveyCreate);
}
