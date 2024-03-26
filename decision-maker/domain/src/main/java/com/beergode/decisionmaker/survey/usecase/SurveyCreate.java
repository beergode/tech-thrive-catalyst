package com.beergode.decisionmaker.survey.usecase;

import com.beergode.decisionmaker.common.model.Status;
import com.beergode.decisionmaker.common.model.UseCase;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SurveyCreate implements UseCase {

    private String id;
    private String content;

}