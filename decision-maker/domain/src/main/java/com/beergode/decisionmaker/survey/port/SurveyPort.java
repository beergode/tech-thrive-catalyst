package com.beergode.decisionmaker.survey.port;

import com.beergode.decisionmaker.survey.model.Survey;
import com.beergode.decisionmaker.survey.usecase.SurveyCreate;

public interface SurveyPort {

    Survey create(SurveyCreate surveyCreate);

    Survey retrieve(Long accountId);

}
