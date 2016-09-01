package com.db.schooolexaminator.server;

import com.db.schooolexaminator.dao.ConfigurationDAO;
import com.db.schooolexaminator.model.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Created by JavaSchoolStudent on 01.09.2016.
 */
@Component
public class ExaminatorManagerImpl implements ExaminatorManager {


    @Autowired
    ConfigurationDAO configurationDAO;

    @Autowired
    private Function<Configuration, Examinator> supplier;

    Map<Integer, Examinator> examinators;


    @PostConstruct
    public void init() {
        examinators = new HashMap<Integer, Examinator>();
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
        Examinator examinator = supplier.apply(c);
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
