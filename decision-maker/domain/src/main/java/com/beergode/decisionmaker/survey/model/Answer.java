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

    private Answer(Builder builder, boolean isSurveyClosed) {
        this.id = builder.id;
        this.text = builder.text;
        this.voteCount = checkSurveyClosed(builder.voteCount, isSurveyClosed);
    }

    public static Builder answer() {
        return new Builder();
    }

    public static final class Builder {

        private Builder() {
        }

        public Answer build(boolean isSurveyClosed) {
            return new Answer(this, isSurveyClosed);
        }
    }

    public void incrementVoteCount() {
        this.voteCount++;
    }

    public AnswerCreate toUseCase() {
        return answerCreate()
                .text(text)
                .build();
    }

    public AnswerUpdate toUpdate() {
        return answerUpdate()
                .id(this.id)
                .text(this.text)
                .voteCount(this.voteCount)
                .build();
    }

    private Long checkSurveyClosed(Long voteCount, boolean isSurveyClosed) {
        return isSurveyClosed ? voteCount : 0L;
    }
}
