package com.example.introduce.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller()
public class StudyController {

    @GetMapping("/study/home")
    public String studyHome(){
        return "/study/study-home";
    }
}
