package com.beergode.decisionmaker.survey.model;

import com.beergode.decisionmaker.survey.usecase.create.AnswerCreate;
import com.beergode.decisionmaker.survey.usecase.update.AnswerUpdate;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import static com.beergode.decisionmaker.survey.usecase.create.AnswerCreate.answerCreate;
import static com.beergode.decisionmaker.survey.usecase.update.AnswerUpdate.answerUpdate;

@Getter
@EqualsAndHashCode
@Builder(builderMethodName = "answer", builderClassName = "Builder")
public class Answer {
    private UUID id;
    private String text;
    private Long voteCount;

    private LocalDateTime createdAt;

    private Answer(Builder builder) {
        this.id = builder.id;
        this.text = builder.text;
        this.voteCount = builder.voteCount;
    }

    public static Builder answer() {
        return new Builder();
    }

    public static final class Builder {

        private Builder() {
        }

        public Answer build() {
            return new Answer(this);
        }
    }

    public void hideVoteCounts() {
        this.voteCount = 0L;
    }

    public void incrementVoteCount() {
        if (this.voteCount != null) {
            this.voteCount++;
        } else {
            this.voteCount = 1L;
        }
    }

    public AnswerCreate toUseCase() {
        return answerCreate()
                .text(text)
                .build();
    }

    public AnswerUpdate toUpdate() {
        return answerUpdate()
                .id(this.id != null ? this.id : UUID.randomUUID())
                .text(this.text)
                .voteCount(this.voteCount)
                .build();
    }

    public String getStringId() {
        return this.id.toString();
    }

    public boolean isCustom() {
        return this.voteCount == null;
    }
}
