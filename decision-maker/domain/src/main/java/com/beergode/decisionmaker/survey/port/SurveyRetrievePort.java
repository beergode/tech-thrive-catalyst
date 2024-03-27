package com.beergode.decisionmaker.survey.port;

import com.beergode.decisionmaker.common.model.Page;
import com.beergode.decisionmaker.survey.model.Survey;
import com.beergode.decisionmaker.survey.usecase.SurveyPaginate;

public interface SurveyRetrievePort {

    Survey retrieve(String id);

    Page<Survey> paginate(SurveyPaginate surveyPaginate);
}
