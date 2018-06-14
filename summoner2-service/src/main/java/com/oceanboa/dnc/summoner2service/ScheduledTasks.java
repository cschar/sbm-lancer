package com.oceanboa.dnc.summoner2service;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.oceanboa.dnc.summoner2service.model.SumBlip;
import com.oceanboa.dnc.summoner2service.repo.SumBlipRepository;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY:MM:DD:HH:mm:ss");

    @Autowired
    SumBlipRepository sumBlipRepo;

//    @Scheduled(fixedRate = 60 * 1000)
    @Scheduled(cron="0 0 * * * *" ) // every hour
    public void renderOutLeaderBoard() {

        Iterator<SumBlip> b = sumBlipRepo.findAll().iterator();

        String namePoints = StreamSupport
                .stream(sumBlipRepo.findAll().spliterator(), false)
                .map(sb -> {  return sb.getName().split(" ")[0] + " " + sb.getLeaguePoints();
                })
                .collect( Collectors.joining( " " ) );

        Date d = new Date();

        String cmd = MessageFormat.format("blender scene.blend --background --python blender_script.py -- {0} {0}",
                DateTime.now().getHourOfDay()); //hour of day is frame

        OriTest.runCommand(cmd+namePoints);

        log.info("Rendered out frame at time {}", dateFormat.format(new Date()));
    }
}
