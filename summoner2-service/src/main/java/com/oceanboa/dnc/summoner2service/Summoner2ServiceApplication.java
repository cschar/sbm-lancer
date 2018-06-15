package com.oceanboa.dnc.summoner2service;

import com.oceanboa.dnc.summoner2service.OriTest;
import com.oceanboa.dnc.summoner2service.model.SumBlip;
import com.oceanboa.dnc.summoner2service.model.SumLog;
import com.oceanboa.dnc.summoner2service.repo.SumBlipRepository;
import com.oceanboa.dnc.summoner2service.repo.SumLogRepository;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;



@SpringBootApplication
@EnableScheduling
public class Summoner2ServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(Summoner2ServiceApplication.class, args);
	}

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY:MM:DD:HH:mm:ss");

    @Autowired
    private Environment env;


    @Value("${ALLOWED_CORS}")
    public static String allowedCORS;


    @Bean
	public CommandLineRunner demo(SumLogRepository repository, SumBlipRepository repoBlip) {
		return (args) -> {

            OriTest.crawlOP();

			repoBlip.save(new SumBlip("zee", 199));
//			repository.save(new SumLog(dateFormat.format(new Date()), "renders/scene/0001.jpg"));

		};
	}

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {

                String allowedCors = env.getProperty("allowed_cors");

                registry.addMapping("/**")
                        .allowedOrigins(allowedCors);
            }

            @Override
            public void addResourceHandlers(ResourceHandlerRegistry registry) {
                registry
                        .addResourceHandler("/files/**")
                        .addResourceLocations("file:./renders/");
//                .addResourceLocations("file:./renders/");
            }
        };
    }
}
