package com.db.schooolexaminator.dao;

import com.db.schooolexaminator.model.Picture;

/**
 * Created by Blik on 03/13/2017.
 */
public interface PictureDao {

    Picture get(Integer confName);

    void save(Picture picture);
}
