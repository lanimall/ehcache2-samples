package com.github.lanimall.samples.ehcache2.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.lanimall.samples.ehcache2.domain.audit.AuditColumns;
import com.github.lanimall.samples.ehcache2.domain.audit.AuditListener;
import com.github.lanimall.samples.ehcache2.domain.audit.AuditableColumns;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by fabien.sanglier on 10/28/16.
 */
@Entity
@EntityListeners(AuditListener.class)
@Table(name = "surveyresponses",
        uniqueConstraints={@UniqueConstraint(columnNames = {"respondent_id" , "survey_id"})})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SurveyResponse implements AuditableColumns, Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column (name="surveyresponse_id")
    private Long id;

    @ManyToOne (optional = false)
    @JoinColumn (name = "survey_id")
    private Survey survey;

    @Column(name = "respondent_id", nullable = false)
    private Long respondentID;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy="surveyResponse")
    private Collection<Answer> answers;

    @Embedded
    @JsonIgnore
    private AuditColumns auditColumns;

    protected SurveyResponse() { //JPA
    }

    public SurveyResponse(Long id) {
        this.id = id;
    }

    public SurveyResponse(Long surveyId, Long respondentID) {
        this.survey = new Survey(surveyId);
        this.respondentID = respondentID;
    }

    public SurveyResponse(Survey survey, Long respondentID){
        this.survey = survey;
        this.respondentID = respondentID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRespondentID() {
        return respondentID;
    }

    public void setRespondentID(Long respondentID) {
        this.respondentID = respondentID;
    }

    public Survey getSurvey() {
        return survey;
    }

    public void setSurvey(Survey survey) {
        this.survey = survey;
    }

    public Collection<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(Collection<Answer> answers) {
        this.answers = answers;
    }

    @Override
    public AuditColumns getAuditColumns() {
        return auditColumns;
    }

    @Override
    public void setAuditColumns(AuditColumns auditColumns) {
        this.auditColumns = auditColumns;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SurveyResponse that = (SurveyResponse) o;

        if (answers != null ? !answers.equals(that.answers) : that.answers != null) return false;
        if (auditColumns != null ? !auditColumns.equals(that.auditColumns) : that.auditColumns != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (respondentID != null ? !respondentID.equals(that.respondentID) : that.respondentID != null) return false;
        if (survey != null ? !survey.equals(that.survey) : that.survey != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (survey != null ? survey.hashCode() : 0);
        result = 31 * result + (respondentID != null ? respondentID.hashCode() : 0);
        result = 31 * result + (answers != null ? answers.hashCode() : 0);
        result = 31 * result + (auditColumns != null ? auditColumns.hashCode() : 0);
        return result;
    }
}
