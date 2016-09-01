package com.db.schooolexaminator.model;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * Created by JavaSchoolStudent on 31.08.2016.
 */
@Entity
@lombok.Getter
@Setter
@NoArgsConstructor
public class Configuration {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int configurationId;

    private String title;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Email> emails;

    @OneToMany(cascade = CascadeType.ALL)
    @JsonUnwrapped
    private List<Constraint> constraints;

    private int frameCols;
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
