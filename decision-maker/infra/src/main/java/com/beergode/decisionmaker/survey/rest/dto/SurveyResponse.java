package com.beergode.decisionmaker.survey.rest.dto;

import com.beergode.decisionmaker.survey.model.Survey;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SurveyResponse {

    private Long id;
    private Long accountId;
    private BigDecimal price;
    private String referenceCode;

    public static SurveyResponse fromModel(Survey survey) {
        return SurveyResponse.builder()
                .id(survey.getId())
                .price(survey.getPrice())
                .accountId(survey.getAccountId())
                .referenceCode(survey.getReferenceCode())
                .build();
    }
}