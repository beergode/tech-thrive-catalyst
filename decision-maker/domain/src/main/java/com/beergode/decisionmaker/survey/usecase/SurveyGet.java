package com.beergode.decisionmaker.survey.usecase;

import com.beergode.decisionmaker.common.model.UseCase;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SurveyGet  implements UseCase {

    private String id;

    public static SurveyGet from(String id) {
        return SurveyGet.builder()
                .id(id)
                .build();
    }
}
