package com.beergode.decisionmaker.survey.adapters;

import com.beergode.decisionmaker.common.model.Page;
import com.beergode.decisionmaker.survey.model.Survey;
import com.beergode.decisionmaker.survey.port.SurveyPort;
import com.beergode.decisionmaker.survey.usecase.SurveyPaginate;
import com.beergode.decisionmaker.survey.usecase.create.SurveyCreate;
import com.beergode.decisionmaker.survey.usecase.update.SurveyUpdate;
import org.apache.commons.lang3.NotImplementedException;

public class SurveyFakeAdapter implements SurveyPort {

    private final Survey survey;

    public SurveyFakeAdapter(Survey survey) {
        this.survey = survey;
    }

    @Override
    public Survey create(SurveyCreate surveyCreate) {
        return survey;
    }

    @Override
    public Survey update(SurveyUpdate surveyUpdate) {
        return null;
    }

    @Override
    public Survey retrieve(String id) {
        return null;
    }

    @Override
    public Page<Survey> paginate(SurveyPaginate surveyPaginate) {
        return null;
    }

    @Override
    public Survey retrieveByHandlingKey(String handlingKey) {
        throw new NotImplementedException();
    }

    @Override
    public void delete(String handlingKey) {
        throw new NotImplementedException();
    }

}
