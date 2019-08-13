package com.test.tmhkc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("navigation")
public class NavigationController {

    @RequestMapping("index")
    public String index(){
        return "index";
    }
}
