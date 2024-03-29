package com.beergode.decisionmaker.adapters.survey.mongo.entity;

import com.beergode.decisionmaker.common.entity.AbstractEntity;
import com.beergode.decisionmaker.survey.model.Answer;
import java.util.UUID;
import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;

import static com.beergode.decisionmaker.survey.model.Answer.answer;

@Getter
@Document(collection = "answer")
public class AnswerEntity extends AbstractEntity {

    private String text;
    private Long voteCount;

    public static AnswerEntity of(String id, String text) {
        return new AnswerEntity(id, text, null);
    }

    public Answer toModel(boolean isSurveyClosed) {
        return answer()
                .id(UUID.fromString(super.getId()))
                .text(text)
                .voteCount(voteCount)
                .build(isSurveyClosed);
    }
    private AnswerEntity() { /*Hide No Args Constructor*/}
    private AnswerEntity(String id, String text, Long voteCount) {
        this.id = id;
        this.text = text;
        this.voteCount = voteCount;
    }
}
