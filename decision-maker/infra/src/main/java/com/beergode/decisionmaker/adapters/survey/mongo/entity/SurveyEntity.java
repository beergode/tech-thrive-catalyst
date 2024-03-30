package com.beergode.decisionmaker.adapters.survey.mongo.entity;

import com.beergode.decisionmaker.common.entity.AbstractEntity;
import com.beergode.decisionmaker.survey.model.Survey;
import lombok.Getter;
import lombok.NonNull;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.UUID;

import static com.beergode.decisionmaker.survey.model.Survey.survey;

@Getter
@Document(collection = "survey")
public class SurveyEntity extends AbstractEntity {

    @NonNull
    private String content;
    @NonNull
    private QuestionEntity question;
    private LocalDate closedAt;

    public static SurveyEntity of(String  id, String content, QuestionEntity question) {
        return of(id, content, question, null);
    }

    public static SurveyEntity of(String  id, String content, QuestionEntity question, LocalDate closedAt) {
        return new SurveyEntity(id, content, question, closedAt);
    }

    public Survey toModel() {
        return survey()
                .id(UUID.fromString(super.getId()))
                .content(content)
                .question(question.toModel())
                .closedAt(closedAt)
                .build();
    }

    private SurveyEntity() {}

    private SurveyEntity(String id, @NonNull String content, QuestionEntity question, LocalDate closedAt) {
        this.id = id;
        this.content = content;
        this.question = question;
        this.closedAt = closedAt;
    }

}
