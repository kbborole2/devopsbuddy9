package com.devopsbuddy9.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by kb on 7/15/2017.
 */
@Controller
public class CopyController {

    @RequestMapping("/about")
    public String about(){
      return "copy/about";
    }
}
