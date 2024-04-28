package com.beergode.decisionmaker.common.config;

import java.util.UUID;

public class SurveyFilter {

    private String ownerId;
    private String surveyId;
    private String userRemoteAddr;
    private boolean isVoted;
    private boolean isOwner;

    public String getSurveyId() {
        return surveyId;
    }

    public boolean isOwner() {
        return isOwner;
    }

    private SurveyFilter(String ownerId, String surveyId, String userRemoteAddr, boolean isVoted, boolean isOwner) {
        this.ownerId = ownerId;
        this.surveyId = surveyId;
        this.userRemoteAddr = userRemoteAddr;
        this.isVoted = isVoted;
        this.isOwner = isOwner;
    }

    public static SurveyFilter newSurveyFilter(String surveyId, String userRemoteAddr) {
        return new SurveyFilter(UUID.randomUUID().toString(), surveyId, userRemoteAddr, false, true);
    }

    public static SurveyFilter newDummySurveyFilter(String surveyId) {
        return new SurveyFilter(null, surveyId, null, false, false);
    }

    public boolean canVote() {
        return !isVoted;
    }

    public void setVoted(boolean isVoted) {
        this.isVoted = isVoted;
    }

    public boolean isVoted() {
        return isVoted;
    }
}
