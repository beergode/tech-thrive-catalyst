package com.beergode.decisionmaker.survey.usecase.update;

import java.util.UUID;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@Builder(builderMethodName = "answerUpdate", builderClassName = "Builder")
public class AnswerUpdate {

    private UUID id;
    private String text;
    private Long voteCount;
    private boolean isCustom;

    private AnswerUpdate(Builder builder) {
        this.id = builder.id;
        this.text = builder.text;
        this.voteCount = builder.voteCount;
        this.isCustom = builder.isCustom;
    }

    public static Builder answerUpdate() {
        return new Builder();
    }

    public static final class Builder {

        private Builder() {
        }

        public AnswerUpdate build() {
            return new AnswerUpdate(this);
        }
    }

    public String getStringId() {
        if (this.id != null) {
            return this.id.toString();
        } else {
            UUID randomUUID = UUID.randomUUID();
            return randomUUID.toString();
        }
    }

}
