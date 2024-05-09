package com.beergode.decisionmaker.adapters.survey.rest;

import com.beergode.decisionmaker.adapters.survey.rest.dto.AnswerRequest;
import com.beergode.decisionmaker.adapters.survey.rest.dto.SurveyCreateRequest;
import com.beergode.decisionmaker.adapters.survey.rest.dto.SurveyResponse;
import com.beergode.decisionmaker.adapters.survey.rest.dto.VoteCountUpdateRequest;
import com.beergode.decisionmaker.common.filter.IPFilter;
import com.beergode.decisionmaker.common.model.Page;
import com.beergode.decisionmaker.common.rest.BaseController;
import com.beergode.decisionmaker.common.rest.DataResponse;
import com.beergode.decisionmaker.common.rest.Response;
import com.beergode.decisionmaker.survey.model.Survey;
import com.beergode.decisionmaker.survey.usecase.SurveyFinalize;
import com.beergode.decisionmaker.survey.usecase.SurveyGet;
import com.beergode.decisionmaker.survey.usecase.SurveyPaginate;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
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

import static com.beergode.decisionmaker.adapters.survey.rest.dto.SurveyResponse.from;
import static com.beergode.decisionmaker.survey.usecase.SurveyFinalize.end;

@RestController
@RequestMapping("/api/decision-maker/surveys")
@CrossOrigin
@Slf4j
public class SurveyController extends BaseController {
    private final IPFilter ipFilter;

    public SurveyController(IPFilter ipFilter) {
        this.ipFilter = ipFilter;
    }

    @GetMapping("/{handlingKey}")
    public Response<SurveyResponse> retrieve(HttpServletRequest request,
            @PathVariable("handlingKey") String handlingKey) {
        var survey = publish(Survey.class, SurveyGet.from(handlingKey));
        log.debug("Survey is retrieved for handlingKey {}", handlingKey);
        var surveyFilter = ipFilter.getSurveyFilter(request, survey.getId().toString());
        return respond(from(survey, surveyFilter.isOwner(), surveyFilter.canVote()));
    }

    @GetMapping
    @SuppressWarnings("unchecked")
    public Response<DataResponse<SurveyResponse>> paginate(Pageable pageable) {
        var surveyRequest = SurveyPaginate.builder().page(Page.of(pageable.getPageNumber(), pageable.getPageSize()))
                .build();
        var surveyPage = publish(Page.class, surveyRequest);
        var surveyResponses = toResponse(surveyPage.getItems());

        return respond(surveyResponses, surveyPage.getPageNumber(), surveyPage.getSize(), surveyPage.getTotalSize());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Response<SurveyResponse> createSurvey(HttpServletRequest request,
            @RequestBody SurveyCreateRequest surveyCreateRequest) {
        var survey = publish(Survey.class, surveyCreateRequest.toUseCase());
        ipFilter.createItem(request, survey.getId().toString());
        return respond(from(survey));
    }

//    @PutMapping("/{id}")
//    public Response<Void> voteCountUpdate(HttpServletRequest request, @PathVariable("id") String id,
//            @RequestBody VoteCountUpdateRequest voteCountUpdateRequest) {
//
//        boolean isAlreadyVoted = ipFilter.updateVote(request);
//        if (isAlreadyVoted) {
//            throw new AlreadyVotedException("409", "You have already voted!");
//        }
//        publish(voteCountUpdateRequest.toUseCase(id));
//
//        return null;
//    }

    @PutMapping("/{id}")
    public Response<Void> answer(HttpServletRequest request, @PathVariable("id") String id,
            @RequestBody AnswerRequest answerRequest) {

        boolean isAlreadyVoted = ipFilter.updateVote(request);
        if (isAlreadyVoted) {
            throw new AlreadyVotedException("409", "You have already voted!");
        }
        if (answerRequest.getAnswerId() != null) {
            publish(answerRequest.toSurveyVoteUseCase(id));
        } else {
            publish(answerRequest.toAddAnswerUseCase(id));
        }
        return null;
    }

    //    @PutMapping("/{id}/answers")
    //    public Response<SurveyResponse> addAnswer(HttpServletRequest request, @PathVariable("id") String id,
    //            @RequestBody AddAnswerRequest addAnswerRequest) {
    //        var survey = publish(Survey.class, addAnswerRequest.toUseCase(id));
    //        return respond(from(survey));
    //    }

    @PostMapping("/{id}/finalize")
    @ResponseStatus(HttpStatus.CREATED)
    public Response<SurveyResponse> finalize(@PathVariable("id") String id) {
        SurveyFinalize surveyFinalize = end().surveyId(id).build();
        var survey = publish(Survey.class, surveyFinalize);
        return respond(from(survey));
    }

    @GetMapping("/test")
    public String test() {
        return "Test Successful!";
    }

    private List<SurveyResponse> toResponse(List<Survey> surveys) {
        return surveys.stream().map(SurveyResponse::from).toList();
    }

}
