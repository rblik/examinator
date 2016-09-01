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

    @OneToMany(cascade = CascadeType.ALL)
    private List<Email> emails;

    @Getter
    @OneToMany(cascade = CascadeType.ALL)
    @JsonUnwrapped
    private List<Constraint> constraints;

    @Getter
    private int frameCols;
    @Getter
    private int frameRows;

    public Configuration(String title, List<Email> emails, List<Constraint> constraints, int frameCols, int frameRows) {
        this.title = title;
        this.emails = emails;
        this.constraints = constraints;
        this.frameCols = frameCols;
        this.frameRows = frameRows;
    }

    @Override
    public String toString() {
        return "Configuration{" +
                "configurationId=" + configurationId +
                ", title='" + title + '\'' +
                ", emails=" + emails +
                ", constraints=" + constraints +
                ", frameCols=" + frameCols +
                ", frameRows=" + frameRows +
                '}';
    }
}
