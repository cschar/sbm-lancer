package com.oceanboa.dnc.summoner2service;

import com.oceanboa.dnc.summoner2service.model.RenderLog;
import com.oceanboa.dnc.summoner2service.model.SumBlip;
import com.oceanboa.dnc.summoner2service.repo.SumBlipRepository;
import com.oceanboa.dnc.summoner2service.repo.RenderLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;


@SpringBootApplication
@EnableScheduling
public class Summoner2ServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(Summoner2ServiceApplication.class, args);
	}

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY:MM:DD:HH:mm:ss");

    @Autowired
    RenderLogRepository renderLogRepo;

    @Autowired
    private Environment env;


    @Value("${ALLOWED_CORS}")
    public static String allowedCORS;


    @Bean
	public CommandLineRunner demo(RenderLogRepository repository, SumBlipRepository repoBlip) {
		return (args) -> {

            OriTest.crawlOP();
//            String filepath = String.format("renders/scene/image-%s.jpg", dateFormat.format(new Date()));
//            String cmd = String.format("blender blend/scene.blend --background --python blend/blender_script_still.py -- 1 %s", filepath);
//            String filepath = String.format("renders/scene3/image-%s.jpg", dateFormat.format(new Date()));
//            String cmd = String.format("blender blend/scene3beziertext.blend --background --python blend/blender_script_still_2.py -- 1 %s", filepath);
////


//  String cmd = String.format("blender blend/scene3-bezier-text.blend --background --python blend/blender_script_anim.py -- 1 2 %s", filepath);

//            String name = OriTest.runCommand(cmd);
//            renderLogRepo.save(new RenderLog(String.format("renderlog %d - %s", 0, dateFormat.format(new Date())), filepath, "anim"));



			repoBlip.save(new SumBlip("zee", 199));
//			repository.save(new RenderLog(dateFormat.format(new Date()), "renders/scene/0001.jpg"));

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
