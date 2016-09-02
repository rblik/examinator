package com.db.schooolexaminator.model;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.*;

import javax.persistence.*;
import java.util.List;

/**
 * Created by JavaSchoolStudent on 31.08.2016.
 */
@Entity
@lombok.Getter
@Data
@NoArgsConstructor
public class Configuration {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int configurationId;

    private String title;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Email> emails;

    @Getter
    @JsonUnwrapped
    @OneToMany(cascade = CascadeType.ALL)
    private List<OperationConstraint> operationConstraints;

    @Getter
    private int frameCols;
    @Getter
    private int frameRows;

    public Configuration(String title, List<Email> emails, List<OperationConstraint> operationConstraints, int frameCols, int frameRows) {
        this.title = title;
        this.emails = emails;
        this.operationConstraints = operationConstraints;
        this.frameCols = frameCols;
        this.frameRows = frameRows;
    }

    @Override
    public String toString() {
        return "Configuration{" +
                "configurationId=" + configurationId +
                ", title='" + title + '\'' +
                ", emails=" + emails +
                ", operationConstraints=" + operationConstraints +
                ", frameCols=" + frameCols +
                ", frameRows=" + frameRows +
                '}';
    }
}
