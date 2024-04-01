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


    private String handlingKey;
    @NonNull
    private String content;
    @NonNull
    private QuestionEntity question;
    private LocalDate closedAt;
    private SurveySettingEntity setting;
    private Integer participantCount;

    public static SurveyEntity of(String  id, String content, QuestionEntity question, SurveySettingEntity setting, String handlingKey) {
        return of(id, content, question, null, setting, null, handlingKey);
    }

    public static SurveyEntity of(String  id, String content, QuestionEntity question, LocalDate closedAt, SurveySettingEntity setting, Integer participantCount, String handlingKey) {
        return new SurveyEntity(id, content, question, closedAt, setting, participantCount, handlingKey);
    }

    public Survey toModel() {
        Survey.Builder builder =  survey()
                .id(UUID.fromString(super.getId()))
                .handlingKey(handlingKey)
                .content(content)
                .question(question.toModel())
                .closedAt(closedAt)
                .participantCount(participantCount);
        if (setting != null) {
            builder.setting(setting.toModel());
        }

        return builder.build();
    }

    private SurveyEntity() {}

    private SurveyEntity(String id, @NonNull String content, QuestionEntity question, LocalDate closedAt, SurveySettingEntity setting, Integer participantCount, String handlingKey) {
        this.id = id;
        this.handlingKey = handlingKey;
        this.content = content;
        this.question = question;
        this.closedAt = closedAt;
        this.setting = setting;
        this.participantCount = participantCount;
    }

}
