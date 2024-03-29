package com.beergode.decisionmaker.adapters.survey.rest;

import com.beergode.decisionmaker.adapters.survey.rest.dto.SurveyCreateRequest;
import com.beergode.decisionmaker.adapters.survey.rest.dto.SurveyResponse;
import com.beergode.decisionmaker.adapters.survey.rest.dto.VoteCountUpdateRequest;
import com.beergode.decisionmaker.common.model.Page;
import com.beergode.decisionmaker.common.rest.BaseController;
import com.beergode.decisionmaker.common.rest.DataResponse;
import com.beergode.decisionmaker.common.rest.Response;
import com.beergode.decisionmaker.common.usecase.UseCaseHandler;
import com.beergode.decisionmaker.common.usecase.VoidUseCaseHandler;
import com.beergode.decisionmaker.survey.model.Survey;
import com.beergode.decisionmaker.survey.usecase.FinalizeSurvey;
import com.beergode.decisionmaker.survey.usecase.create.SurveyCreate;
import com.beergode.decisionmaker.survey.usecase.SurveyGet;
import com.beergode.decisionmaker.survey.usecase.SurveyPaginate;
import com.beergode.decisionmaker.survey.usecase.VoteCountUpdate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static com.beergode.decisionmaker.survey.usecase.FinalizeSurvey.end;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/decision-maker/surveys")
@CrossOrigin
public class SurveyController extends BaseController {

    private final UseCaseHandler<Survey, SurveyGet> surveyGetUseCaseHandler;
    private final UseCaseHandler<Survey, SurveyCreate> surveyCreateUseCaseHandler;
    private final UseCaseHandler<Page<Survey>, SurveyPaginate> surveyPaginateUseCaseHandler;
    private final VoidUseCaseHandler<VoteCountUpdate> voteCountUpdateUseCaseHandler;
    private final UseCaseHandler<Survey, FinalizeSurvey> surveyFinalizeSurveyUseCaseHandler;

    @GetMapping("/{id}")
    public Response<SurveyResponse> retrieve(
            @PathVariable("id")
            String id) {
        var survey = surveyGetUseCaseHandler.handle(SurveyGet.from(id));
        return respond(SurveyResponse.from(survey));
    }

    @GetMapping
    public Response<DataResponse<SurveyResponse>> paginate(Pageable pageable) {
        var surveyRequest = SurveyPaginate.builder()
                .page(Page.of(pageable.getPageNumber(), pageable.getPageSize()))
                .build();
        var surveyPage = surveyPaginateUseCaseHandler.handle(surveyRequest);
        return respond(toResponse(surveyPage.getItems()), surveyPage.getPageNumber(),
                surveyPage.getSize(), surveyPage.getTotalSize());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Response<SurveyResponse> createSurvey(
            @RequestBody
            SurveyCreateRequest surveyCreateRequest) {
        var survey = surveyCreateUseCaseHandler.handle(surveyCreateRequest.toUseCase());
        return respond(SurveyResponse.from(survey));
    }

    @PutMapping("/{id}")
    public Response<Void> voteCountUpdate(
            @PathVariable("id")
            String id,
            @RequestBody
            VoteCountUpdateRequest voteCountUpdateRequest) {
        var voteCountUpdate = voteCountUpdateRequest.toUseCase(id);
        voteCountUpdateUseCaseHandler.handle(voteCountUpdate);
        return null;
    }

    @PostMapping("/{id}/finalize")
    @ResponseStatus(HttpStatus.CREATED)
    public Response<SurveyResponse> finalize(@PathVariable("id") String id) {
        FinalizeSurvey finalizeSurvey = end().surveyId(id).build();
        var survey = surveyFinalizeSurveyUseCaseHandler.handle(finalizeSurvey);
        return respond(SurveyResponse.from(survey));
    }

    private List<SurveyResponse> toResponse(List<Survey> surveys) {
        return surveys.stream().map(SurveyResponse::from).toList();
    }

}
