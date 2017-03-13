package com.db.schooolexaminator.telegramserver.picture;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by JavaSchoolStudent on 01.09.2016.
 */

@org.springframework.stereotype.Component
@PropertySource("classpath:pictures.properties")
public class PictureManager {

    @Value("${EXAMINATOR_HOMEDIR}")
    @Getter
    @Setter
    private String homeDir;

    protected static final String pic_black = "black";
    protected static final String pic_default = "picture";

    public String defaultPictureFileName(String pic) {
        return this.getHomeDir() + this.getPictureFileName(pic);
    }

    public String getPictureFileName(String configTitle) {
        return "/pictures/" + configTitle + ".jpg";
    }

}
