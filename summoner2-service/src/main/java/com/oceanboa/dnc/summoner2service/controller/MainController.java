package com.oceanboa.dnc.summoner2service.controller;

import com.oceanboa.dnc.summoner2service.OriTest;
import com.oceanboa.dnc.summoner2service.model.SumBlip;
import com.oceanboa.dnc.summoner2service.model.SumLog;
import com.oceanboa.dnc.summoner2service.repo.SumBlipRepository;
import com.oceanboa.dnc.summoner2service.repo.SumLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Controller
//@CrossOrigin(origins = "http://localhost:8080")
public class MainController {

    @Autowired
    SumBlipRepository sumBlipRepo;

    @Autowired
    SumLogRepository sumLogRepo;



    @GetMapping("/log")
    public String sumlogs(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {


        List<SumLog> sumlogs = StreamSupport
                .stream(sumLogRepo.findAll().spliterator(), false)
                .limit(10)
                .collect(Collectors.toList());

        model.addAttribute("sumlogs", sumlogs);
        return "log";
    }



//    @GetMapping("/run")
    public String run(@RequestParam(name="name", required=false, defaultValue="World") String name,
                      @RequestParam(name="frame", required=false, defaultValue="1") String frame, Model model) {

        model.addAttribute("name", name);

        return "run";
    }


}

