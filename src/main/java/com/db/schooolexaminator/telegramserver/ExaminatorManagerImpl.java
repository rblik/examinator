package com.db.schooolexaminator.telegramserver;

import com.db.schooolexaminator.dao.ConfigurationDAO;
import com.db.schooolexaminator.model.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;

/**
 * Created by JavaSchoolStudent on 01.09.2016.
 */
@Component
public class ExaminatorManagerImpl implements ExaminatorManager {


    @Autowired
    ConfigurationDAO configurationDAO;

    @Autowired
    private Function<ExaminatorConfiguration, Examinator> supplier;

    ConcurrentMap<Integer, Examinator> examinators;


    @PostConstruct
    public void init() {
        examinators = new ConcurrentHashMap<Integer,Examinator>();
    }

    @Override
    public boolean hasExaminator(int pupilId) {
        return examinators.containsKey(pupilId);
    }

    @Override
    public boolean createExaminator(int pupilId, int configurationId) {
        Configuration c = configurationDAO.getConfigurationbyId(configurationId);
        if (c == null) {
            return false;
        }
        Examinator examinator = supplier.apply(new ExaminatorConfiguration(c, pupilId));

        examinators.put(pupilId, examinator);
        return true;
    }

    @Override
    public Examinator getExaminator(int pupilId) {
        return examinators.get(pupilId);
    }

    public void removeExaminator(int pupilId) {
        examinators.remove(pupilId);
    }
}
