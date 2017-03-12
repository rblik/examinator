package com.db.schooolexaminator.telegramserver.config;

import com.db.schooolexaminator.telegramserver.Examinator;
import com.db.schooolexaminator.telegramserver.ExaminatorConfiguration;
import com.db.schooolexaminator.telegramserver.ExaminatorImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import java.util.function.Function;



@org.springframework.context.annotation.Configuration
public class ExaminatorManagerConfig {
    // TODO: 03/08/2017 MOVE ALL TO XML
    @Bean
    public Function<ExaminatorConfiguration, Examinator> examinatorSupplier(){
        return c -> {
            Examinator e = examinator();
            e.setPupilId(c.getPupilId());
            e.setConfiguration(c.getConfiguration());
            return e;
        };
    }

    @Bean
    @Scope("prototype")
    public Examinator examinator() {
        return new ExaminatorImpl();
    }

}