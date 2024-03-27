package com.beergode.decisionmaker.survey.usecase;

import com.beergode.decisionmaker.common.model.Page;
import com.beergode.decisionmaker.common.model.UseCase;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SurveyPaginate implements UseCase {

    private Page page;
}
