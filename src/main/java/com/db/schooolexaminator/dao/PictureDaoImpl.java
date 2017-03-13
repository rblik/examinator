package com.db.schooolexaminator.dao;

import com.db.schooolexaminator.model.Picture;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Blik on 03/13/2017.
 */
@Repository
public class PictureDaoImpl implements PictureDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Picture get(Integer confId) {
        List<Picture> list = entityManager.createQuery("SELECT p FROM Picture p WHERE p.configuration.configurationId=:confId", Picture.class)
                .setParameter("confId", confId).getResultList();
        return list.isEmpty()? null: list.get(0);
    }

    @Override
    @Transactional
    public void save(Picture picture) {
        entityManager.persist(picture);
    }
}
