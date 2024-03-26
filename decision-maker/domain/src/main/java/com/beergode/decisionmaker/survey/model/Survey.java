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

    private Long id;
    private LocalDateTime createdAt;
    private Long accountId;
    private BigDecimal price;
    private String referenceCode;
    private Status status;
}