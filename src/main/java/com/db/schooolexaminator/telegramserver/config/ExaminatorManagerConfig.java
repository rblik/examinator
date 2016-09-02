package com.db.schooolexaminator.telegramserver.config;

import com.db.schooolexaminator.dao.ConfigurationDAO;
import com.db.schooolexaminator.dao.UserDao;
import com.db.schooolexaminator.dao.UserDaoImpl;
import com.db.schooolexaminator.services.TeacherService;
import com.db.schooolexaminator.telegramserver.Examinator;
import com.db.schooolexaminator.telegramserver.ExaminatorConfiguration;
import com.db.schooolexaminator.telegramserver.ExaminatorImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.orm.jpa.EntityManagerFactoryAccessor;
import org.springframework.security.core.userdetails.User;

import java.util.function.Function;



@org.springframework.context.annotation.Configuration
public class ExaminatorManagerConfig {

    @Bean
    public Function<ExaminatorConfiguration, Examinator> examinatorSupplier(){
        return c -> {
            Examinator e = examinator();
            e.setPupilId(c.getPupilId());
            e.setConfiguration(c.getConfiguration());
            return e;
        };
    }
/*    @Bean
    public ConfigurationDAO configurationDAO() {
        return new ConfigurationDAO();
    }*/

    @Bean
    public TeacherService teacherService() {
        return new TeacherService();
    }
    @Bean
    @Scope("prototype")
    public Examinator examinator() {
        return new ExaminatorImpl();
    }

}