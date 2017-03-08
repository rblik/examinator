package com.db.schooolexaminator.telegramserver;

import com.db.schooolexaminator.model.Configuration;
import com.db.schooolexaminator.services.ConfigurationService;
import com.db.schooolexaminator.services.TeacherServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;

/**
 * Created by JavaSchoolStudent on 01.09.2016.
 */
@SuppressWarnings("SpringAutowiredFieldsWarningInspection")
@Component
public class ExaminatorManagerImpl implements ExaminatorManager {


    @Autowired
    private ConfigurationService configurationService;

    @Autowired
    private Function<ExaminatorConfiguration, Examinator> supplier;



    private ConcurrentMap<Integer, Examinator> examinators;


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
        Configuration configuration = configurationService.get(configurationId);
        if (configuration == null) {
            return false;
        }
        Examinator examinator = supplier.apply(new ExaminatorConfiguration(configuration, pupilId));

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
