package com.beergode.decisionmaker.adapters.survey.rest.dto;

import com.beergode.decisionmaker.survey.usecase.SurveyGet;
import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SurveyGetRequest {

    @NotNull
    private String handlingKey;

    public SurveyGet toUseCase() {
        return SurveyGet.builder()
                .handlingKey(handlingKey)
                .build();
    }
}
