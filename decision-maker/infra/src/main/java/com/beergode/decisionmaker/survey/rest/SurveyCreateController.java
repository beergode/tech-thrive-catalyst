package com.beergode.decisionmaker.survey.rest;

import com.beergode.decisionmaker.common.rest.BaseController;
import com.beergode.decisionmaker.common.rest.Response;
import com.beergode.decisionmaker.survey.model.Survey;
import com.beergode.decisionmaker.survey.rest.dto.SurveyCreateRequest;
import com.beergode.decisionmaker.survey.rest.dto.SurveyResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/decision-maker/surveys")
@CrossOrigin
public class SurveyCreateController extends BaseController {

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Response<SurveyResponse> createSurvey(
      @RequestBody
      SurveyCreateRequest surveyCreateRequest) {
    var survey = publish(Survey.class, surveyCreateRequest.toModel());
    return respond(SurveyResponse.fromModel(survey));
  }

}
