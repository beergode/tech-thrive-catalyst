package com.beergode.decisionmaker.survey;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    public Survey createSurvey(@RequestBody Survey createSurveyPayload) {
        SURVEY_IN_DB.put(createSurveyPayload.id, createSurveyPayload);
        return createSurveyPayload;
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
