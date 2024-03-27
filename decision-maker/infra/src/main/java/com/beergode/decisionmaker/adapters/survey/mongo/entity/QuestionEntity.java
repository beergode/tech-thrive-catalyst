package com.beergode.decisionmaker.adapters.survey.mongo.entity;

import com.beergode.decisionmaker.common.entity.AbstractEntity;
import com.beergode.decisionmaker.survey.model.Question;
import com.beergode.decisionmaker.survey.model.Survey;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Document(collection = "question")
public class QuestionEntity extends AbstractEntity {
    @NonNull
    private String text;
    private List<AnswerEntity> answers;

    public Question toModel() {
        return Question.builder()
                .id(super.getId())
                .text(text)
                .answerList(answers.stream()
                        .map(AnswerEntity::toModel)
                        .toList())
                .build();
    }
}
