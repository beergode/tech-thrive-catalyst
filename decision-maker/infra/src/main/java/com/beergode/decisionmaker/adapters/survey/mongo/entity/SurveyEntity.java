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
@Document(collection = "payment")
public class SurveyEntity extends AbstractEntity {

  @Field("account_id")
  private Long accountId;

  @Field("price")
  private BigDecimal price;

  @Field("reference_code")
  private String referenceCode;


  public Survey toModel() {
    return Survey.builder()
        .id(super.getId())
        .accountId(accountId)
        .price(price)
        .referenceCode(referenceCode)
        .status(super.getStatus())
        .build();
  }
}
