package com.beergode.decisionmaker.adapters.survey.mongo.entity;


import com.beergode.decisionmaker.common.entity.AbstractEntity;
import com.beergode.decisionmaker.survey.model.Survey;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@Document(collection = "survey")
public class SurveyEntity extends AbstractEntity {

  @Field("content")
  private String content;


  public Survey toModel() {
    return Survey.builder()
        .id(super.getId())
        .content(content)
        .status(super.getStatus())
        .build();
  }
}
