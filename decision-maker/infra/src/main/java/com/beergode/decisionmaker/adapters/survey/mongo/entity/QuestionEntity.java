package com.beergode.decisionmaker.adapters.survey.mongo.entity;

import com.beergode.decisionmaker.common.entity.AbstractEntity;
import com.beergode.decisionmaker.survey.model.Question;
import java.util.List;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "question")
public class QuestionEntity extends AbstractEntity {
    @NonNull
    private String text;
    private List<AnswerEntity> answers;

    public static QuestionEntity of(String text, List<AnswerEntity> answers) {
        return new QuestionEntity(text, answers);
    }

    public Question toModel() {
        return Question.builder()
                .id(super.getId())
                .text(text)
                .answerList(answers.stream()
                        .map(AnswerEntity::toModel)
                        .toList())
                .build();
    }
    private QuestionEntity() { /*Hide No Args Constructor*/}
    private QuestionEntity(String text, List<AnswerEntity> answers) {
        this.text = text;
        this.answers = answers;
    }
}
