package com.beergode.decisionmaker.adapters.survey.mongo;

import com.beergode.decisionmaker.adapters.survey.mongo.entity.SurveyEntity;
import com.beergode.decisionmaker.adapters.survey.mongo.repository.SurveyMongoRepository;
import com.beergode.decisionmaker.common.model.Page;
import com.beergode.decisionmaker.survey.model.Survey;
import com.beergode.decisionmaker.survey.port.SurveyRetrievePort;
import com.beergode.decisionmaker.survey.usecase.SurveyPaginate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SurveyRetrieveDataAdapter implements SurveyRetrievePort {

    private final SurveyMongoRepository surveyMongoRepository;

    @Override
    public Survey retrieve(String id) {
        return surveyMongoRepository.findById(id).orElseThrow(null).toModel();
    }

    @Override
    public Page<Survey> paginate(SurveyPaginate surveyPaginate) {
        var pageRequest = PageRequest.of(surveyPaginate.getPage().getPageNumber(),surveyPaginate.getPage().getSize());
        var surveyPage = surveyMongoRepository.findAll(pageRequest);
        var surveys = surveyPage.stream().map(SurveyEntity::toModel).toList();
        return Page.of(surveys, surveyPage.getNumber(), surveyPage.getSize(), surveyPage.getTotalElements());
    }
}
