package com.beergode.decisionmaker.survey.port;

import com.beergode.decisionmaker.common.model.Page;
import com.beergode.decisionmaker.survey.model.Survey;
import com.beergode.decisionmaker.survey.usecase.create.SurveyCreate;
import com.beergode.decisionmaker.survey.usecase.SurveyPaginate;
import com.beergode.decisionmaker.survey.usecase.update.SurveyUpdate;

public interface SurveyPort {
    Survey create(SurveyCreate surveyCreate);

    Survey update(SurveyUpdate surveyUpdate);

    Survey retrieve(String id);

    Page<Survey> paginate(SurveyPaginate surveyPaginate);

    Survey retrieveByHandlingKey(String handlingKey);
}
