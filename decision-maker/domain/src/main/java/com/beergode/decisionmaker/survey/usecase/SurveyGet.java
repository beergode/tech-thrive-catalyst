package com.beergode.decisionmaker.survey.usecase;

import com.beergode.decisionmaker.common.model.UseCase;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SurveyGet  implements UseCase {

    private String id;
    private String handlingId;

    public static SurveyGet from(String id) {
        return SurveyGet.builder()
                .id(id)
                .build();
    }

    public static SurveyGet fromHandlingId(String handlingId) {
        return SurveyGet.builder()
                .handlingId(handlingId)
                .build();
    }
}
