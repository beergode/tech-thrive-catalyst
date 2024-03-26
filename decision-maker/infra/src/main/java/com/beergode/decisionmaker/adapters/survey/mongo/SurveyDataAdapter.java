package com.beergode.decisionmaker.adapters.survey.mongo;


import com.beergode.decisionmaker.adapters.survey.mongo.entity.SurveyEntity;
import com.beergode.decisionmaker.common.model.Status;
import com.beergode.decisionmaker.survey.model.Survey;
import com.beergode.decisionmaker.survey.port.SurveyPort;
import com.beergode.decisionmaker.survey.usecase.SurveyCreate;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SurveyDataAdapter implements SurveyPort {

  // TODO: MongoDB repo to be implemented

  private static final Map<String, SurveyEntity> SURVEY_IN_DB = new HashMap<>();

  @Override
  public Survey create(SurveyCreate surveyCreate) {
    var surveyEntity = new SurveyEntity();
    surveyEntity.setId(surveyCreate.getId());
    surveyEntity.setContent(surveyCreate.getContent());
    surveyEntity.setStatus(Status.ACTIVE);

    SURVEY_IN_DB.put(surveyCreate.getId(), surveyEntity);

    return retrieve(surveyEntity.getId());
  }

  @Override
  public Survey retrieve(String id) {
    return toModel(SURVEY_IN_DB.get(id));
  }


  private Survey toModel(SurveyEntity surveyEntity) {
    return Survey.builder()
        .id(surveyEntity.getId())
        .content(surveyEntity.getContent())
        .build();
  }


}