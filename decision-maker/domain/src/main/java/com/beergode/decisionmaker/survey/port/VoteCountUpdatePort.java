package com.beergode.decisionmaker.survey.port;

import com.beergode.decisionmaker.survey.usecase.VoteCountUpdate;

public interface VoteCountUpdatePort {

    void voteCountUpdate(VoteCountUpdate voteCountUpdate);
}
