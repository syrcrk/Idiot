package com.example.beginer.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("main")
public class MainController {
    @RequestMapping("crk")
    public String defaultRestult(){
        return "123";
    }
}
