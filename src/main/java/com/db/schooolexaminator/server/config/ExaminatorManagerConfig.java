package com.db.schooolexaminator.server.config;

import com.db.schooolexaminator.model.Configuration;
import com.db.schooolexaminator.server.Examinator2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import java.util.function.Function;



@org.springframework.context.annotation.Configuration
public class ExaminatorManagerConfig {


    @Bean
    public Function<Configuration, Examinator2> examinatorSupplier(){
        return c -> {
            Examinator2 e = examinator();
            e.setConfiguration(c);
            return e;
        };
    }


/*

    @Bean
    @Scope("prototype")
    public Configuration configuration() {
        return new Configuration();
    }
*/

    @Bean
    @Scope("prototype")
    public Examinator2 examinator() {
        return new Examinator2();
    }



}