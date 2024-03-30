package com.beergode.decisionmaker.adapters.survey.mongo.entity;

import com.beergode.decisionmaker.common.entity.AbstractEntity;
import com.beergode.decisionmaker.survey.model.Question;
import java.util.List;
import java.util.UUID;
import lombok.Getter;
import lombok.NonNull;
import org.springframework.data.mongodb.core.mapping.Document;

import static com.beergode.decisionmaker.survey.model.Question.question;

@Getter
@Document(collection = "question")
public class QuestionEntity extends AbstractEntity {
    @NonNull
    private String text;
    private List<AnswerEntity> answers;

    public static QuestionEntity of(String id, String text, List<AnswerEntity> answers) {
        return new QuestionEntity(id, text, answers);
    }

    public Question toModel() {
        return question()
                .id(UUID.fromString(super.getId()))
                .text(text)
                .answers(answers.stream()
                        .map(AnswerEntity::toModel)
                        .toList())
                .build();
    }
    private QuestionEntity() { /*Hide No Args Constructor*/}
    private QuestionEntity(String id, String text, List<AnswerEntity> answers) {
        this.id = id;
        this.text = text;
        this.answers = answers;
    }
}
