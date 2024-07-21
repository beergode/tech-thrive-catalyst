package com.beergode.decisionmaker.survey.usecase.create;

import com.beergode.decisionmaker.common.model.UseCase;

import java.security.SecureRandom;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
@Builder(builderMethodName = "surveyCreate", builderClassName = "Builder")
public class SurveyCreate implements UseCase {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-";
    private static final int KEY_LENGTH = 10;
    private static final Random random = new SecureRandom();

    private UUID id;
    private String handlingKey;
    private String content;
    private String note;
    private Integer countdownDurationSeconds;
    private List<QuestionCreate> questions;
    private SurveySettingCreate setting;

    private SurveyCreate(Builder builder) {
        this.id = UUID.randomUUID();
        this.handlingKey = generateKey();
        this.note = builder.note;
        this.countdownDurationSeconds = builder.countdownDurationSeconds;
        this.content = builder.content;
        this.questions = builder.questions;
        this.setting = builder.setting;
    }

    public static Builder surveyCreate() {
        return new Builder();
    }

    public static final class Builder {

        private Builder() {
        }

        public SurveyCreate build() {
            return new SurveyCreate(this);
        }
    }

    public String getStringId() {
        return this.id.toString();
    }

    private static String generateKey() {
        StringBuilder keyBuilder = new StringBuilder(KEY_LENGTH);
        for (int i = 0; i < KEY_LENGTH; i++) {
            keyBuilder.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        return keyBuilder.toString();
    }
}
