package com.test.tmhkc.controller;

import com.test.tmhkc.anno.TestAnno;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RequestMapping("testController")
@RestController
public class TestController {

    @TestAnno
    @RequestMapping("test")
    public String test(@RequestParam Map map){

        log.info("111111111111");

        return "test";
    }


}
