package com.db.schooolexaminator.telegramserver.picture;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by JavaSchoolStudent on 01.09.2016.
 */

@org.springframework.stereotype.Component
@PropertySource("classpath:pictures.properties")
public class PictureManager {

    @Value("${picturesFolder}")
    private String pathToDir;

    @Value("defaultPicture")
    private String defaultFileName;

    String getPathToDir() {
        return "C:\\Users\\JavaSchoolStudent\\Desktop\\SchoolExaminator\\src\\main\\resources\\pictures";
    }


    public String defaultPictureFileName() {
        return "picture.jpg";
    }

}
