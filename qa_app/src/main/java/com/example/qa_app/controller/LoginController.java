package com.example.qa_app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.example.qa_app.model.Entity.LoginForm;
import com.example.qa_app.service.LoginService;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute LoginForm form, HttpSession session, Model model) {
        if (loginService.authenticate(form.getEmail(), form.getPassword())) {
            session.setAttribute("user_id", loginService.saveUserId(form.getEmail()));
            session.setAttribute("email", form.getEmail());
           
            return "redirect:/home";
        }
        model.addAttribute("error", "メールアドレス または パスワードが間違っています");
        return "login";
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
