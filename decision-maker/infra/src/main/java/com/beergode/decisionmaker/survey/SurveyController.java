package com.beergode.decisionmaker.survey;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/decision-maker/surveys")
@CrossOrigin
public class SurveyController {

    private static final Map<String, Survey> SURVEY_IN_DB = new HashMap<>();

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createSurvey(Survey createSurveyPayload) {
        SURVEY_IN_DB.put(createSurveyPayload.id, createSurveyPayload);
    }

    @GetMapping("{id}")
    public Survey createSurvey(@PathVariable String id) {
        return SURVEY_IN_DB.get(id);
    }

    private static class Survey {
        public String id;
        public String content;
    }
}
