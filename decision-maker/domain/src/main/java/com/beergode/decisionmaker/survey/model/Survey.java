package com.beergode.decisionmaker.survey.model;

import com.beergode.decisionmaker.survey.usecase.update.SurveyUpdate;
import com.beergode.decisionmaker.survey.usecase.vote.SurveyVote;
import com.beergode.decisionmaker.survey.usecase.vote.SurveyVotes;
import java.util.Timer;
import java.util.TimerTask;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Slf4j
@Getter
@EqualsAndHashCode
@Builder(builderMethodName = "survey",
        builderClassName = "Builder")
public class Survey {
    private UUID id;
    private String handlingKey;
    private String content;
    private String note;
    private Integer countdownDurationSeconds;
    private Instant createdAt;
    private LocalDate closedAt;
    private List<Question> questions;
    private SurveySetting setting;
    private Integer participantCount;

    private Survey(Builder builder) {
        this.id = builder.id;
        this.handlingKey = builder.handlingKey;
        this.content = builder.content;
        this.note = builder.note;
        this.countdownDurationSeconds = builder.countdownDurationSeconds;
        this.questions = builder.questions;
        this.closedAt = builder.closedAt;
        this.setting = builder.setting;
        this.participantCount = builder.participantCount;
    }

    public static Builder survey() {
        return new Builder();
    }

    public static final class Builder {

        private Builder() {
        }

        public Survey build() {
            return new Survey(this);
        }
    }

    public void hideVoteCounts() {
        this.getAllAnswers()
                .forEach(Answer::hideVoteCounts);
    }

    public List<Answer> getAllAnswers() {
        if (questions.isEmpty()) {
            return List.of();
        }
        return questions.stream()
                .map(Question::getAnswers)
                .flatMap(List::stream)
                .toList();
    }

    public void addAnswer(Answer answer, String questionId) {
        this.questions.stream()
                .filter(question -> questionId.equals(question.getId().toString()))
                .findFirst()
                .ifPresent(question -> question.addAnswer(answer));
    }

    public SurveyUpdate toUpdate() {
        return SurveyUpdate.surveyUpdate()
                .id(this.id)
                .handlingKey(this.handlingKey)
                .content(this.content)
                .note(this.note)
                .questions(this.questions.stream()
                        .map(Question::toUpdate)
                        .toList())
                .closedAt(this.closedAt)
                .setting(this.setting == null ? null : this.setting.toUpdate())
                .participantCount(this.participantCount)
                .build();
    }

    public Long getCreatedLong() {
        return createdAt == null ? null : createdAt.toEpochMilli();
    }

    public void close() {
        this.closedAt = LocalDate.now();
    }

    public boolean isClosed() {
        return this.closedAt != null;
    }

    public Survey checkCountDown(Runnable runnable) {
        if (countdownDurationSeconds != null && countdownDurationSeconds == 0) {
            scheduleClose(runnable);
        }
        return this;
    }

    public void incrementVoteCount(SurveyVotes useCase) {
        this.incrementParticipantCount();
        this.getAllAnswers()
                .stream()
                .filter(answer -> useCase.surveyVotes()
                        .stream()
                        .map(SurveyVote::answerId)
                        .anyMatch(answer.getStringId()::equals))
                .forEach(Answer::incrementVoteCount);

        if (this.isReachedToParticipantLimit()) {
            log.info("Reached to maximum participant count for id {}. Survey is closed",
                    this.getId());
            this.close();
        }
    }

    private void incrementParticipantCount() {
        if (this.participantCount != null) {
            this.participantCount++;
        } else {
            this.participantCount = 1;
        }
    }

    private boolean isReachedToParticipantLimit() {
        if (this.participantCount != null && this.setting != null
                && this.setting.getParticipantLimit() != null) {
            return this.participantCount >= this.setting.getParticipantLimit();
        } else {
            return false;
        }
    }

    private void scheduleClose(Runnable action) {
        new Timer()
                .schedule(new TimerTask() {
                    @Override
                    public void run() {
                        close();
                        action.run();
                    }
                }, getCountdownDurationSeconds() * 1000L);
    }

}
