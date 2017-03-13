package com.db.schooolexaminator.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

/**
 * Created by Blik on 03/13/2017.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Access(AccessType.FIELD)
public class Picture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(fetch = LAZY)
    private Configuration configuration;

    @Basic(fetch=LAZY)
    @Lob
//    @Type(type = "text")
    private byte[] content;

    public Picture(Configuration configuration, byte[] content) {
        this.configuration = configuration;
        this.content = content;
    }
}
