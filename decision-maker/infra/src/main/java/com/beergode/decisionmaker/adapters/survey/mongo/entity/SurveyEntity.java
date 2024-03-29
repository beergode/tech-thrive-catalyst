package com.beergode.decisionmaker.adapters.survey.mongo.entity;

import com.beergode.decisionmaker.common.entity.AbstractEntity;
import com.beergode.decisionmaker.survey.model.Survey;
import java.time.OffsetDateTime;
import java.util.UUID;
import lombok.Getter;
import lombok.NonNull;
import org.springframework.data.mongodb.core.mapping.Document;

import static com.beergode.decisionmaker.survey.model.Survey.survey;

@Getter
@Document(collection = "survey")
public class SurveyEntity extends AbstractEntity {

    @NonNull
    private String content;
    @NonNull
    private QuestionEntity question;
    private OffsetDateTime closedAt;

    public static SurveyEntity of(String  id, String content, QuestionEntity question) {
        return new SurveyEntity(id, content, question);
    }

    public Survey toModel() {
        return survey()
                .id(UUID.fromString(super.getId()))
                .content(content)
                .question(question.toModel(isClosed()))
                .closedAt(closedAt)
                .build();
    }

    private SurveyEntity() {}

    private SurveyEntity(String id, @NonNull String content, QuestionEntity question) {
        this.id = id;
        this.content = content;
        this.question = question;
    }

    private boolean isClosed() {
        return closedAt != null;
    }
}
