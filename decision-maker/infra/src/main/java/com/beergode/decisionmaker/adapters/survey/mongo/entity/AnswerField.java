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

    public static AnswerField of(String id, String text) {
        return of(id, text, 0L);
    }

    public static AnswerField of(String id, String text, Long voteCount) {
        return new AnswerField(id, text, voteCount);
    }

    public Answer toModel() {
        return answer()
                .id(UUID.fromString(super.getId()))
                .text(text)
                .voteCount(voteCount)
                .build();
    }

    private AnswerField() { /*Hide No Args Constructor*/}

    private AnswerField(String id, String text, Long voteCount) {
        this.id = id;
        this.text = text;
        this.voteCount = voteCount;
    }
}
