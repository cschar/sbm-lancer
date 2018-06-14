package com.oceanboa.dnc.summoner2service;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.oceanboa.dnc.summoner2service.model.SumBlip;
import com.oceanboa.dnc.summoner2service.model.SumLog;
import com.oceanboa.dnc.summoner2service.repo.SumBlipRepository;
import com.oceanboa.dnc.summoner2service.repo.SumLogRepository;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

    private static Integer counter = 0;

    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY:MM:DD:HH:mm");

    @Autowired
    SumBlipRepository sumBlipRepo;

    @Autowired
    SumLogRepository sumLogRepo;

    @Autowired
    private Environment env;


    @Scheduled(cron="0 0 * * * *" ) // every hour
    public void crawl() {
        OriTest.crawlOP();
        log.info("Crawled out op at time {}", dateFormat.format(new Date()));
    }

//    @Scheduled(cron="0 * * * * *" )
    @Scheduled(fixedRate = 5 * 1000)
    public void heartbeat() {
        log.info("heartbeat at time {}", dateFormat.format(new Date()));

        String allowedCors = env.getProperty("allowed_cors");
        log.info(allowedCors);
    }

    @Scheduled(fixedRate = 5 * 60 * 1000) // every 5 minute
//    @Scheduled(cron="0 0 * * * *" ) // every hour
    public void render(){

        ScheduledTasks.counter +=1 ;

//        String cmd = MessageFormat.format("blender scene.blend --background --python blender_script.py -- {0} {0}",
////                DateTime.now().getHourOfDay()); //hour of day is frame
//                ScheduledTasks.counter);

        String filepath = String.format("renders/scene/image-%s.jpg", dateFormat.format(new Date()));
        String cmd = String.format("blender scene.blend --background --python blender_script_no_output.py -- 1 %s", filepath);

        String name = OriTest.runCommand(cmd);

        sumLogRepo.save(new SumLog(String.format("sumlog %d - %s", ScheduledTasks.counter, dateFormat.format(new Date())), filepath));

        log.info("Rendered out frame at time {}", dateFormat.format(new Date()));
    }
}
