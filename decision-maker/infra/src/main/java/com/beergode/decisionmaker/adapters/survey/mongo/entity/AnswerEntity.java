package com.beergode.decisionmaker.adapters.survey.mongo.entity;

import com.beergode.decisionmaker.common.entity.AbstractEntity;
import com.beergode.decisionmaker.survey.model.Answer;
import com.beergode.decisionmaker.survey.model.Question;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "answer")
public class AnswerEntity extends AbstractEntity {

    private String text;
    private Long voteCount;

    public Answer toModel() {
        return Answer.builder()
                .id(super.getId())
                .text(text)
                .voteCount(voteCount)
                .build();
    }

}
