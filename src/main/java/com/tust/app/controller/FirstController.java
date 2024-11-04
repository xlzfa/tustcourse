package com.tust.app.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FirstController {

    @RequestMapping("/")
    public String firstPage(){
        return "redirect:/page";
    }


}
