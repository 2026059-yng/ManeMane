package com.example.qa_app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    //urlにアクセスされたとき
    @GetMapping("/home")
    public String getMethodName() {
        return "home";
    }
    
}
