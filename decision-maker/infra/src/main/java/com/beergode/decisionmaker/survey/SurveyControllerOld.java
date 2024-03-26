package com.beergode.decisionmaker.survey;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/decision-maker/surveys/old")
@CrossOrigin
public class SurveyControllerOld {

    private static final Map<String, Survey> SURVEY_IN_DB = new HashMap<>();

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createSurveyOld(Survey createSurveyPayload) {
        SURVEY_IN_DB.put(createSurveyPayload.id, createSurveyPayload);
    }

    @GetMapping("{id}")
    public Survey getSurvey(@PathVariable("id") String id) {
        return SURVEY_IN_DB.get(id);
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
