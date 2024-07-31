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
package com.beergode.decisionmaker.survey;

import com.beergode.decisionmaker.survey.adapters.SurveyFakeAdapter;
import com.beergode.decisionmaker.survey.usecase.create.SurveyCreate;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SurveyCreateUseCaseHandlerTest {

    @Test
    void should_create_survey() {
        // given
        var surveyPort = new SurveyFakeAdapter();
        var underTest = new SurveyCreateUseCaseHandler(surveyPort);
        var useCase = Instancio.create(SurveyCreate.class);

        // when
        var result = underTest.handle(useCase);

        // then
        assertThat(result).isNotNull();
        assertThat(useCase.getId()).isEqualTo(result.getId());
        assertThat(useCase.getHandlingKey()).isEqualTo(result.getHandlingKey());
        assertThat(useCase.getContent()).isEqualTo(result.getContent());
    }
}