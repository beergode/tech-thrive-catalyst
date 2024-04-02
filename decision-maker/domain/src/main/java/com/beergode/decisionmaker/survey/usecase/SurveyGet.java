package com.beergode.decisionmaker.survey.usecase;

import com.beergode.decisionmaker.common.model.UseCase;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SurveyGet  implements UseCase {

    private String handlingKey;

    public static SurveyGet from(String handlingKey) {
        return SurveyGet.builder()
                .handlingKey(handlingKey)
                .build();
    }

}
