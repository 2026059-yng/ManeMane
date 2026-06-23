package com.example.qa_app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import lombok.RequiredArgsConstructor;
import tools.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpSession;

import com.example.qa_app.model.Entity.RegisterForm;
import com.example.qa_app.service.SetupService;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class SetupController {

    private final SetupService setupService;

    @GetMapping("/setup")
    public String getRegister() {
        return "/setup";
    }
    

    @PostMapping("/setup")
    public String register(
            @RequestParam String allData,HttpSession session) throws Exception {
                ObjectMapper mapper = new ObjectMapper();
                RegisterForm registerForm = mapper.readValue(allData, RegisterForm.class);
                Long user_id = (Long) session.getAttribute("user_id");
                setupService.setup(user_id, registerForm);
                return "redirect:/home";
            }
        }
