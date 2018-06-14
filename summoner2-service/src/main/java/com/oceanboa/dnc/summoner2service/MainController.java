package com.oceanboa.dnc.summoner2service;

import com.oceanboa.dnc.summoner2service.model.SumBlip;
import com.oceanboa.dnc.summoner2service.repo.SumBlipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Controller
@CrossOrigin(origins = "http://localhost:8080")
public class MainController {

    @Autowired
    SumBlipRepository sumBlipRepo;

    @GetMapping("/hello")
    public String index(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {

        OriTest ot = new OriTest();
        name = ot.getTopSums().stream().reduce((x,y) -> {

            return (x + y);
        }).get();

        model.addAttribute("name", name);
        return "run";
    }



    @GetMapping("/run")
    public String run(@RequestParam(name="name", required=false, defaultValue="World") String name,
                      @RequestParam(name="frame", required=false, defaultValue="1") String frame, Model model) {


        String namePoints = StreamSupport
                .stream(sumBlipRepo.findAll().spliterator(), false)
                .map(sb -> {
                   return sb.getName().split(" ")[0] + " " + sb.getLeaguePoints();
                })
                .collect( Collectors.joining( " " ) );



        name = OriTest.runCommand("blender scene.blend --background --python blender_script.py -- 4 4 "+namePoints);
//        name = MainController.runCommand("which blender");

        model.addAttribute("name", name);

        return "run";
    }

    @GetMapping("/run2")
    public String run2(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {


        name = OriTest.runCommand("uname -a");

        model.addAttribute("name", name);

        return "run";
    }


}

