package com.oceanboa.dnc.summoner2service.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;
import org.springframework.stereotype.Component;

@Component
public class SpringDataRestCustomization extends RepositoryRestConfigurerAdapter {

    @Autowired
    private Environment env;

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {

        String allowedCors = env.getProperty("allowed_cors");

        config.getCorsRegistry()
                .addMapping("/**")
//                .addMapping("/person/**")
                .allowedOrigins(allowedCors);


//                .allowedMethods("PUT", "DELETE")
//                .allowedHeaders("header1", "header2", "header3")
//                .exposedHeaders("header1", "header2")
//                .allowCredentials(false).maxAge(3600);
    }
}
