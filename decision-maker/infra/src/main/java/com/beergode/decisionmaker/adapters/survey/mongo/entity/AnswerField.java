package com.beergode.decisionmaker.adapters.survey.mongo.entity;

import com.beergode.decisionmaker.common.entity.AbstractType;
import com.beergode.decisionmaker.survey.model.Answer;
import java.util.UUID;
import lombok.Getter;

import static com.beergode.decisionmaker.survey.model.Answer.answer;

@Getter
public class AnswerField extends AbstractType {
    private String text;
    private Long voteCount;
    private boolean isCustom;

    public static AnswerField of(String id, String text) {
        return of(id, text, null, false);
    }

    public static AnswerField of(String id, String text, Long voteCount, boolean isCustom) {
        return new AnswerField(id, text, voteCount, isCustom);
    }

    public Answer toModel() {
        return answer()
                .id(UUID.fromString(super.getId()))
                .text(text)
                .voteCount(voteCount)
                .isCustom((isCustom))
                .build();
    }

    private AnswerField() { /*Hide No Args Constructor*/}

    private AnswerField(String id, String text, Long voteCount, boolean isCustom) {
        this.id = id;
        this.text = text;
        this.voteCount = voteCount;
        this.isCustom = isCustom;
    }
}
