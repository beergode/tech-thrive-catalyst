package com.beergode.decisionmaker.survey.usecase;

import com.beergode.decisionmaker.common.model.UseCase;
import java.math.BigDecimal;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SurveyCreate implements UseCase {

    private Long accountId;
    private BigDecimal price;
    private String referenceCode;
}