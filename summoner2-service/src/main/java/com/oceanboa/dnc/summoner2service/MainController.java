package com.oceanboa.dnc.summoner2service;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    @GetMapping("/hello")
    public String index(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {

        OriTest ot = new OriTest();
        name = ot.getTopSums().stream().reduce((x,y) -> {

            return (x + y);
        }).get();

        model.addAttribute("name", name);
        return "index";
    }

}

