package com.beergode.decisionmaker.adapters.survey.rest;

import com.beergode.decisionmaker.adapters.survey.rest.dto.SurveyCreateRequest;
import com.beergode.decisionmaker.adapters.survey.rest.dto.SurveyResponse;
import com.beergode.decisionmaker.common.model.Page;
import com.beergode.decisionmaker.common.rest.BaseController;
import com.beergode.decisionmaker.common.rest.DataResponse;
import com.beergode.decisionmaker.common.rest.Response;
import com.beergode.decisionmaker.common.usecase.UseCaseHandler;
import com.beergode.decisionmaker.survey.model.Survey;
import com.beergode.decisionmaker.survey.usecase.SurveyCreate;
import com.beergode.decisionmaker.survey.usecase.SurveyGet;
import com.beergode.decisionmaker.survey.usecase.SurveyPaginate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/decision-maker/surveys")
@CrossOrigin
public class SurveyController extends BaseController {

    private final UseCaseHandler<Survey, SurveyGet> surveyGetUseCaseHandler;
    private final UseCaseHandler<Survey, SurveyCreate> surveyCreateUseCaseHandler;
    private final UseCaseHandler<Page<Survey>, SurveyPaginate> surveyPaginateUseCaseHandler;

    @GetMapping("/{id}")
    public Response<SurveyResponse> retrieve(@PathVariable("id") String id) {
        var survey = surveyGetUseCaseHandler.handle(SurveyGet.from(id));
        return respond(SurveyResponse.from(survey));
    }

    @GetMapping
    public Response<DataResponse<SurveyResponse>> paginate(Pageable pageable) {
        SurveyPaginate build = SurveyPaginate.builder()
                .page(Page.of(pageable.getPageNumber(), pageable.getPageSize()))
                .build();
        var surveyPage = surveyPaginateUseCaseHandler.handle(build);
        return respond(toResponse(surveyPage.getItems()),
                surveyPage.getPageNumber(),
                surveyPage.getSize(),
                surveyPage.getTotalSize());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Response<SurveyResponse> createSurvey(@RequestBody SurveyCreateRequest surveyCreateRequest) {
        var survey = surveyCreateUseCaseHandler.handle(surveyCreateRequest.toUseCase());
        return respond(SurveyResponse.from(survey));
    }

    private List<SurveyResponse> toResponse(List<Survey> surveys) {
        return surveys.stream()
                .map(SurveyResponse::from)
                .toList();
    }
}
