package com.db.schooolexaminator.dto;

import lombok.Data;

/**
 * Created by Blik on 03/13/2017.
 */
@Data
public class ConfDto {
    private int configurationId;
    private String title;

    public ConfDto(Object[] entity) {
        this.configurationId = (Integer) entity[0];
        this.title = String.valueOf(entity[1]);
    }
}
