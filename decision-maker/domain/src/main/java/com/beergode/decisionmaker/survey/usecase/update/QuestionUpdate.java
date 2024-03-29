package com.beergode.decisionmaker.survey.usecase.update;

import com.beergode.decisionmaker.common.model.UseCase;
import java.util.List;
import java.util.UUID;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
@Builder(builderMethodName = "questionUpdate", builderClassName = "Builder")
public class QuestionUpdate implements UseCase {
    private UUID id;
    private String text;
    private List<AnswerUpdate> answers;

    private QuestionUpdate(Builder builder) {
        this.id = builder.id;
        this.text = builder.text;
        this.answers = builder.answers;
    }

    public static Builder questionUpdate() {
        return new Builder();
    }

    public static final class Builder {

        private Builder() {
        }

        public QuestionUpdate build() {
            return new QuestionUpdate(this);
        }
    }

    public String getStringId() {
        return id.toString();
    }
}
