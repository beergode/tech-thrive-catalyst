package com.beergode.decisionmaker.survey;

import com.beergode.decisionmaker.adapters.survey.mongo.SurveyDataAdapter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/decision-maker/surveys")
@CrossOrigin
@RequiredArgsConstructor
public class SurveyControllerOld {

  private final SurveyDataAdapter surveyDataAdapter;

  private static final Map<String, Survey> SURVEY_IN_DB = new HashMap<>();

  @GetMapping("{id}")
  public com.beergode.decisionmaker.survey.model.Survey getSurvey(
      @PathVariable("id")
      String id) {
    return surveyDataAdapter.retrieve(id);
  }

  @GetMapping
  public List<Survey> listSurveys() {
    return List.copyOf(SURVEY_IN_DB.values());
  }

  private static class Survey {
    public String id;
    public String content;
  }
}
