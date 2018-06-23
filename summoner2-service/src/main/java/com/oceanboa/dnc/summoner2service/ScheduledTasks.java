package com.oceanboa.dnc.summoner2service;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.oceanboa.dnc.summoner2service.model.RenderLog;
import com.oceanboa.dnc.summoner2service.repo.SumBlipRepository;
import com.oceanboa.dnc.summoner2service.repo.RenderLogRepository;
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
    RenderLogRepository renderLogRepo;

    @Autowired
    private Environment env;


   @Scheduled(cron="0 0 * * * *" ) // every hour
    // @Scheduled(cron="0 0 11 * * ?" ) // at 11 everyday
    public void crawl() {
        OriTest.crawlOP();
        log.info("Crawled out op at time {}", dateFormat.format(new Date()));
    }

//    @Scheduled(cron="0 * * * * *" )
    @Scheduled(fixedRate = 5 * 60 * 1000)
    public void heartbeat() {
        log.info("heartbeat at time {}", dateFormat.format(new Date()));

        String allowedCors = env.getProperty("allowed_cors");
        log.info(allowedCors);
    }

//    @Scheduled(fixedRate = 5 * 60 * 60 * 1000) // every 5 hours
//   @Scheduled(cron="0 0 * * * *" ) // every hour
    // @Scheduled(cron="0 0 12 * * ?" ) // at Noon everyday
    public void render(){

        ScheduledTasks.counter +=1 ;

//        String filepath = String.format("renders/scene/image-%s.jpg", dateFormat.format(new Date()));
//        String cmd = String.format("blender blend/scene.blend --background --python blend/blender_script_still.py -- 1 %s", filepath);

        String filepath = String.format("renders/scene/image-%s.jpg", dateFormat.format(new Date()));
        String cmd = String.format("blender blend/scene3beziertext.blend --background --python blend/blender_script_still_2.py -- 1 %s", filepath);

//        String filepath = String.format("renders/scene3/%s/", dateFormat.format(new Date()));
//        String cmd = String.format("blender blend/scene3beziertext.blend --background --python blend/blender_script_anim.py -- 1 30 %s", filepath);

        String name = OriTest.runCommand(cmd);
        renderLogRepo.save(new RenderLog(String.format("renderlog %d - %s", ScheduledTasks.counter, dateFormat.format(new Date())), filepath));

        log.info("Rendered out frame at time {}", dateFormat.format(new Date()));
    }
}
