/*
 * Copyright 2022-2024 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.beergode.decisionmaker.survey.adapters;

import com.beergode.decisionmaker.common.model.Page;
import com.beergode.decisionmaker.survey.model.Survey;
import com.beergode.decisionmaker.survey.port.SurveyPort;
import com.beergode.decisionmaker.survey.usecase.SurveyPaginate;
import com.beergode.decisionmaker.survey.usecase.create.SurveyCreate;
import com.beergode.decisionmaker.survey.usecase.update.SurveyUpdate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.apache.commons.lang3.NotImplementedException;
import org.instancio.junit.InstancioExtension;
import org.junit.jupiter.api.extension.ExtendWith;


@ExtendWith(InstancioExtension.class)
public class SurveyFakeAdapter implements SurveyPort {
    private final Map<UUID, Survey> surveyStorage = new HashMap<>();

    @Override
    public Survey create(SurveyCreate surveyCreate) {
        var survey = surveyCreate.toSurvey();
        surveyStorage.put(survey.getId(), survey);
        return surveyStorage.get(survey.getId());
    }

    @Override
    public Survey update(SurveyUpdate surveyUpdate) {
        throw new NotImplementedException();
    }

    @Override
    public Survey retrieve(String id) {
        return surveyStorage.get(UUID.fromString(id));
    }

    @Override
    public Page<Survey> paginate(SurveyPaginate surveyPaginate) {
        var pageNumber = surveyPaginate.getPage().getPageNumber();
        var pageSize = surveyPaginate.getPage().getSize();
        var totalSize = surveyPaginate.getPage().getTotalSize();
        List<Survey> surveys = surveyStorage.values().stream()
                .skip(pageNumber)
                .limit(pageSize)
                .toList();
        return Page.of(surveys, pageNumber, pageSize, totalSize);
    }

    @Override
    public Survey retrieveByHandlingKey(String handlingKey) {
        return surveyStorage.values().stream()
                .filter(survey -> survey.getHandlingKey().equals(handlingKey))
                .findFirst().orElseThrow(null);
    }

    @Override
    public void delete(String handlingKey) {
        var existingSurvey = surveyStorage.values().stream()
                        .filter(survey -> survey.getHandlingKey().equals(handlingKey))
                        .findFirst().orElseThrow(null);
        surveyStorage.remove(existingSurvey.getId());
    }
}
