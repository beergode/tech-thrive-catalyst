package com.beergode.decisionmaker.adapters.survey.rest;

import com.beergode.decisionmaker.adapters.survey.rest.dto.SurveyCreateRequest;
import com.beergode.decisionmaker.adapters.survey.rest.dto.SurveyResponse;
import com.beergode.decisionmaker.adapters.survey.rest.dto.VoteCountUpdateRequest;
import com.beergode.decisionmaker.common.model.Page;
import com.beergode.decisionmaker.common.rest.BaseController;
import com.beergode.decisionmaker.common.rest.DataResponse;
import com.beergode.decisionmaker.common.rest.Response;
import com.beergode.decisionmaker.common.usecase.UseCaseHandler;
import com.beergode.decisionmaker.survey.model.Survey;
import com.beergode.decisionmaker.survey.usecase.SurveyFinalize;
import com.beergode.decisionmaker.survey.usecase.SurveyGet;
import com.beergode.decisionmaker.survey.usecase.SurveyPaginate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

import static com.beergode.decisionmaker.survey.usecase.SurveyFinalize.end;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/decision-maker/surveys")
@CrossOrigin
@Slf4j
public class SurveyController extends BaseController {

  @GetMapping("/{handlingKey}")
  public Response<SurveyResponse> retrieve(
      @PathVariable("handlingKey") String handlingKey) {
    var survey = publish(Survey.class, SurveyGet.from(handlingKey));
    log.debug("Survey is retrieved for handlingKey {}", handlingKey);
    return respond(SurveyResponse.from(survey));
  }

  @GetMapping
  @SuppressWarnings("unchecked")
  public Response<DataResponse<SurveyResponse>> paginate(Pageable pageable) {
    var surveyRequest = SurveyPaginate.builder()
            .page(Page.of(pageable.getPageNumber(), pageable.getPageSize()))
            .build();
    var surveyPage = publish(Page.class, surveyRequest);
    var surveyResponses = toResponse(surveyPage.getItems());

    return respond(surveyResponses, surveyPage.getPageNumber(),
            surveyPage.getSize(), surveyPage.getTotalSize());
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Response<SurveyResponse> createSurvey(
      @RequestBody SurveyCreateRequest surveyCreateRequest) {
    var survey = publish(Survey.class, surveyCreateRequest.toUseCase());
    return respond(SurveyResponse.from(survey));
  }

  @PutMapping("/{id}")
  public Response<Void> voteCountUpdate(
      @PathVariable("id") String id,
      @RequestBody VoteCountUpdateRequest voteCountUpdateRequest) {
    publish(voteCountUpdateRequest.toUseCase(id));
    return null;
  }

  @PostMapping("/{id}/finalize")
  @ResponseStatus(HttpStatus.CREATED)
  public Response<SurveyResponse> finalize(@PathVariable("id") String id) {
    SurveyFinalize surveyFinalize = end().surveyId(id).build();
    var survey = publish(Survey.class, surveyFinalize);
    return respond(SurveyResponse.from(survey));
  }

  @GetMapping("/test")
  public String test() {
    return "Test Successful!";
  }

  private List<SurveyResponse> toResponse(List<Survey> surveys) {
    return surveys.stream().map(SurveyResponse::from).toList();
  }

}
