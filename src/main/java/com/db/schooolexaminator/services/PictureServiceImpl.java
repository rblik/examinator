package com.db.schooolexaminator.services;

import com.db.schooolexaminator.dao.PictureDao;
import com.db.schooolexaminator.model.Picture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.db.schooolexaminator.services.PictureService.resizeImage;

/**
 * Created by Blik on 03/13/2017.
 */
@Service
public class PictureServiceImpl implements PictureService {
    @Autowired
    private PictureDao pictureDao;

    @Override
    public Picture getByConf(Integer confId) {
        return pictureDao.get(confId);
    }

    @Override
    public void save(Picture picture) {
        pictureDao.save(resizeImage(picture));
    }
}
