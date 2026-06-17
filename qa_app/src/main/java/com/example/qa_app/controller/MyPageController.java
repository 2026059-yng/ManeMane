package com.example.qa_app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyPageController {
    @GetMapping("/mypage")
    public String GetMethodName() {
        return "mypage";
    }
    
}
