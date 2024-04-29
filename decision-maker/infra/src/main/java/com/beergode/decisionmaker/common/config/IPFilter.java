package com.beergode.decisionmaker.common.config;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.beergode.decisionmaker.common.config.SurveyFilter.newDummySurveyFilter;
import static com.beergode.decisionmaker.common.config.SurveyFilter.newSurveyFilter;
import static com.beergode.decisionmaker.common.config.SurveyFilter.newVotedSurveyFilter;
import static org.springframework.util.CollectionUtils.isEmpty;

@Component
public class IPFilter {

    private static final boolean ALREADY_VOTED = true;
    private final Map<String, List<SurveyFilter>> ipVoteRegistry = new ConcurrentHashMap<>();

    public boolean updateVote(HttpServletRequest httpServletRequest) {
        var path = httpServletRequest.getRequestURI();
        var ip = httpServletRequest.getRemoteAddr();
        var surveyId = path.substring(path.lastIndexOf('/') + 1);

        var surveyFilters = ipVoteRegistry.getOrDefault(ip, new ArrayList<>());

        var currentSurveyFilter = surveyFilters.stream()
                .filter(surveyFilter -> surveyFilter.getSurveyId().equals(surveyId))
                .findFirst();

        if (currentSurveyFilter.isEmpty()) {
            surveyFilters.add(newVotedSurveyFilter(surveyId, ip));
            ipVoteRegistry.put(ip, surveyFilters);
            return !ALREADY_VOTED;
        }

        if (currentSurveyFilter.get().isVoted()) {
            return ALREADY_VOTED;
        }

        currentSurveyFilter.get().setVoted(true);
        return !ALREADY_VOTED;
    }

    public void createItem(HttpServletRequest httpServletRequest, String surveyId) {
        var ip = httpServletRequest.getRemoteAddr();
        var existingSurveyFilters = ipVoteRegistry.get(ip);
        var surveyFilter = newSurveyFilter(surveyId, ip);
        if (isEmpty(existingSurveyFilters)) {
            var surveyFilters = new ArrayList<>(List.of(surveyFilter));
            ipVoteRegistry.put(ip, surveyFilters);
        } else {
            ipVoteRegistry.get(ip).add(surveyFilter);
        }
    }

    public SurveyFilter getSurveyFilter(HttpServletRequest request, String surveyId) {
        String ip = request.getRemoteAddr();
        var surveyFilters = ipVoteRegistry.get(ip);
        return isEmpty(surveyFilters) ? newDummySurveyFilter(surveyId) : surveyFilters
                .stream()
                .filter(surveyFilter -> surveyFilter.getSurveyId()
                        .equals(surveyId))
                .findFirst()
                .orElse(newDummySurveyFilter(surveyId));
    }
}