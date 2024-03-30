package com.beergode.decisionmaker.adapters;

import com.beergode.decisionmaker.common.model.Page;
import com.beergode.decisionmaker.survey.model.Survey;
import com.beergode.decisionmaker.survey.port.SurveyPort;
import com.beergode.decisionmaker.survey.usecase.SurveyPaginate;
import com.beergode.decisionmaker.survey.usecase.create.SurveyCreate;
import com.beergode.decisionmaker.survey.usecase.update.SurveyUpdate;

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
}
