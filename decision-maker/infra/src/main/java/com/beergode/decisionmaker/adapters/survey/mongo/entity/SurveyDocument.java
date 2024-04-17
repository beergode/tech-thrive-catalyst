package com.beergode.decisionmaker.adapters.survey.mongo.entity;

import com.beergode.decisionmaker.common.entity.AbstractType;
import com.beergode.decisionmaker.survey.model.Survey;
import java.time.LocalDate;
import java.util.UUID;
import lombok.Getter;
import lombok.NonNull;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import static com.beergode.decisionmaker.survey.model.Survey.survey;

@Getter
@Document(collection = "survey")
public class SurveyDocument extends AbstractType {

  private String handlingKey;
  @NonNull
  private String content;
  private String note;
  @NonNull
  @Field("question")
  private QuestionField question;
  private LocalDate closedAt;
  private SurveySettingEntity setting;
  private Integer participantCount;

  public static SurveyDocument of(String id, String content, String note,
      QuestionField question, SurveySettingEntity setting, String handlingKey) {
    return of(id, content, note, question, null, setting, null, handlingKey);
  }

  public static SurveyDocument of(String id, String content,
      String note, QuestionField question,
      LocalDate closedAt, SurveySettingEntity setting, Integer participantCount,
      String handlingKey) {
    return new SurveyDocument(id, content, note, question, closedAt, setting, participantCount,
        handlingKey);
  }

  public Survey toModel() {
    Survey.Builder builder = survey().id(UUID.fromString(super.getId()))
        .handlingKey(handlingKey)
        .content(content)
        .note(note)
        .question(question.toModel())
        .closedAt(closedAt)
        .participantCount(participantCount);
    if (setting != null) {
      builder.setting(setting.toModel());
    }

    return builder.build();
  }

  private SurveyDocument() {}

  private SurveyDocument(String id, @NonNull String content, String note,
      QuestionField question,
      LocalDate closedAt, SurveySettingEntity setting, Integer participantCount,
      String handlingKey) {
    this.id = id;
    this.handlingKey = handlingKey;
    this.content = content;
    this.note = note;
    this.question = question;
    this.closedAt = closedAt;
    this.setting = setting;
    this.participantCount = participantCount;
  }

}
