package com.devopsbuddy9.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by kb on 7/12/2017.
 */
@Controller
public class IndexController {
//Here I am requesting a page called index.html which is the home page
    @RequestMapping("/")
    public String home(){
        return "index";
    }

}
