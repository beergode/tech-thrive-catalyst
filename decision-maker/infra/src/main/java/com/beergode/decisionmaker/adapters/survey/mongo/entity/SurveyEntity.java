package com.beergode.decisionmaker.adapters.survey.mongo.entity;

import com.beergode.decisionmaker.common.entity.AbstractEntity;
import com.beergode.decisionmaker.survey.model.Survey;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "survey")
public class SurveyEntity extends AbstractEntity {

    @NonNull
    private String content;
    @NonNull
    private QuestionEntity question;

    public static SurveyEntity of(String content, QuestionEntity question) {
        return new SurveyEntity(content, question);
    }

    public Survey toModel() {
        return Survey.builder()
                .id(super.getId())
                .content(content)
                //.question(question.toModel())
                .build();
    }

    private SurveyEntity() {
    }

    private SurveyEntity(@NonNull String content, QuestionEntity question) {
        this.content = content;
        this.question = question;
    }
}
