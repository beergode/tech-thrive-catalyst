package com.beergode.decisionmaker.adapters.survey.mongo.entity;

import com.beergode.decisionmaker.common.entity.AbstractType;
import com.beergode.decisionmaker.survey.model.Question;
import java.util.List;
import java.util.UUID;
import lombok.Getter;
import lombok.NonNull;
import org.springframework.data.mongodb.core.mapping.Field;

import static com.beergode.decisionmaker.survey.model.Question.question;

@Getter
public class QuestionField extends AbstractType {
    @NonNull
    private String text;
    @Field("answers")
    private List<AnswerField> answers;

    public static QuestionField of(String id, String text, List<AnswerField> answers) {
        return new QuestionField(id, text, answers);
    }

    public Question toModel() {
        return question()
                .id(UUID.fromString(super.getId()))
                .text(text)
                .answers(answers.stream()
                        .map(AnswerField::toModel)
                        .toList())
                .build();
    }
    private QuestionField() { /*Hide No Args Constructor*/}
    private QuestionField(String id, String text, List<AnswerField> answers) {
        this.id = id;
        this.text = text;
        this.answers = answers;
    }
}
