package com.db.schooolexaminator.telegramserver;

import com.db.schooolexaminator.model.Configuration;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by JavaSchoolStudent on 02.09.2016.
 */

@AllArgsConstructor
@Data
public class ExaminatorConfiguration {
    private Configuration configuration;
    private int pupilId;
}
