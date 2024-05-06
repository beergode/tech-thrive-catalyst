package com.beergode.decisionmaker.survey.model;

import com.beergode.decisionmaker.survey.usecase.create.QuestionCreate;
import com.beergode.decisionmaker.survey.usecase.update.QuestionUpdate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import static com.beergode.decisionmaker.survey.usecase.create.QuestionCreate.questionCreate;
import static com.beergode.decisionmaker.survey.usecase.update.QuestionUpdate.questionUpdate;
import static java.util.stream.Collectors.toList;

@Getter
@EqualsAndHashCode
@Builder(builderMethodName = "question", builderClassName = "Builder")
public class Question {

    private UUID id;
    private String text;
    private LocalDateTime createdAt;
    private List<Answer> answers = new ArrayList<>();
    private boolean isMultipleChoice;

    private Question(Builder builder) {
        this.id = builder.id;
        this.text = builder.text;
        this.answers = builder.answers;
        this.isMultipleChoice = builder.isMultipleChoice;
    }

    public static Builder question() {
        return new Builder();
    }

    public static final class Builder {

        private Builder() {
        }

        public Question build() {
            return new Question(this);
        }
    }

    public QuestionCreate toUseCase() {
        return questionCreate()
                .text(text)
                .isMultipleChoice(isMultipleChoice)
                .answers(answers.stream()
                        .map(Answer::toUseCase)
                        .collect(toList()))
                .build();
    }

    public QuestionUpdate toUpdate() {
        return questionUpdate()
                .id(this.id)
                .text(this.text)
                .answers(this.answers.stream()
                        .map(Answer::toUpdate)
                        .toList())
                .build();
    }
}
