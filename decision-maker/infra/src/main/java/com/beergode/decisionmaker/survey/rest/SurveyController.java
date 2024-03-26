package com.beergode.decisionmaker.survey.rest;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/decision-maker/surveys")
@CrossOrigin
public class SurveyController {

    private static final Map<String, Survey> SURVEY_IN_DB = new HashMap<>();

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createSurveyHexa(Survey createSurveyPayload) {
        SURVEY_IN_DB.put(createSurveyPayload.id, createSurveyPayload);
    }

    private static class Survey {
        public String id;
        public String content;
    }
}
