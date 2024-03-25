package com.beergode.decisionmaker.survey.rest.dto;

import com.beergode.decisionmaker.survey.usecase.SurveyCreate;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SurveyCreateRequest {

    private Long accountId;

    private BigDecimal price;

    private String referenceCode;

    public SurveyCreate toModel() {
        return SurveyCreate.builder()
                .price(price)
                .accountId(accountId)
                .referenceCode(referenceCode)
                .build();
    }
}