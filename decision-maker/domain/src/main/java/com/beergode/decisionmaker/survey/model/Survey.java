package com.beergode.decisionmaker.survey.model;

import com.beergode.decisionmaker.common.model.Status;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.With;

@Data
@Builder
@ToString
@With
@EqualsAndHashCode
public class Survey {

    private String id;
    private LocalDateTime createdAt;
    private String content;
    private Status status;
}