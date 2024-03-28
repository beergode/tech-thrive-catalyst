package com.beergode.decisionmaker.adapters.survey.mongo.entity;

import com.beergode.decisionmaker.common.entity.AbstractEntity;
import com.beergode.decisionmaker.survey.model.Answer;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "answer")
public class AnswerEntity extends AbstractEntity {

    private String text;
    private Long voteCount;

    public static AnswerEntity of(String text) {
        return new AnswerEntity(text, null);
    }

    public Answer toModel() {
        return Answer.builder()
                .id(super.getId())
                .text(text)
                .voteCount(voteCount)
                .build();
    }
    private AnswerEntity() { /*Hide No Args Constructor*/}
    private AnswerEntity(String text, Long voteCount) {
        this.text = text;
        this.voteCount = voteCount;
    }
}
