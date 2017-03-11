package com.db.schooolexaminator.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

/**
 * Created by JavaSchoolStudent on 31.08.2016.
 */
@Entity
@lombok.Getter
@Data
@NoArgsConstructor
public class Configuration {

    @Getter
    @Setter
    boolean hasImage;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int configurationId;

    private String title;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Fetch(FetchMode.SUBSELECT)
    private List<Email> emails;

    @Getter
    @JsonUnwrapped
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<OperationConstraint> operationConstraints;

    @Getter
    private int frameCols;
    @Getter
    private int frameRows;

    @JsonIgnore
    public String getPicName() {
        return (!this.isHasImage()) ? "picture" :
                this.getTitle() + "_" + this.getEmails().get(0).getAddress();
    }

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


    public List<String> getListEmailsString() {
        return emails.stream().map(Email::getAddress).collect(toList());
    }
}
