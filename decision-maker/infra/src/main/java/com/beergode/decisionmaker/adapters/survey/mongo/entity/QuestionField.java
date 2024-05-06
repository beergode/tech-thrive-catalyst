package com.beergode.decisionmaker.adapters.survey.mongo.entity;

import com.beergode.decisionmaker.common.entity.AbstractType;
import com.beergode.decisionmaker.survey.model.Question;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.NonNull;
import org.springframework.data.mongodb.core.mapping.Field;

import static com.beergode.decisionmaker.survey.model.Question.question;
import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;

@Getter
public class QuestionField extends AbstractType {
    private String text;
    @Field("answers")
    private List<AnswerField> answers;
    private boolean isMultipleChoice;

    public static QuestionField of(String id, String text, List<AnswerField> answers, boolean isMultipleChoice) {
        return new QuestionField(id, text, answers, isMultipleChoice);
    }

    public static QuestionField of(String id, String text, List<AnswerField> answers) {
        return of(id, text, answers, true);
    }

    public Question toModel() {
        return question()
                .id(UUID.fromString(super.getId()))
                .text(text)
                .isMultipleChoice(isMultipleChoice)
                .answers(answers != null
                        ? answers.stream()
                        .map(AnswerField::toModel)
                        .collect(toList())
                        : emptyList())
                .build();
    }

    private QuestionField() { /*Hide No Args Constructor*/}

    private QuestionField(String id, String text, List<AnswerField> answers, boolean isMultipleChoice) {
        this.id = id;
        this.text = text;
        this.answers = answers;
        this.isMultipleChoice = isMultipleChoice;
    }
}
