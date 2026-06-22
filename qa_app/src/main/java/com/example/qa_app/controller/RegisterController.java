package com.example.qa_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;
import com.example.qa_app.model.Entity.RegisterForm;
import com.example.qa_app.service.RegisterService;
import jakarta.servlet.http.HttpSession;

@Controller
public class RegisterController {
    @Autowired
    private RegisterService registerService;

    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }

    @GetMapping("/register") // 新規登録
    public String showregister(Model model) {
        model.addAttribute("registerForm", new RegisterForm());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute RegisterForm form, Model model, HttpSession session) {
        try {
            long user_id = registerService.registerUser(form);
            session.setAttribute("user_id", user_id); // session保持
            return "redirect:/setup"; // 保持してパラメータ設定
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", e.getMessage()); // error時
            return "register";
        }
    }
}
