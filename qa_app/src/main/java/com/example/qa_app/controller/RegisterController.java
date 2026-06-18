// package com.example.qa_app.controller;

// import org.springframework.stereotype.Controller;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.ModelAttribute;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestParam;
// import com.example.qa_app.model.Entity.RegisterForm;
// import com.example.qa_app.repository.RegisterRepository;
// import com.example.qa_app.service.RegisterService;

// import ch.qos.logback.core.model.Model;

// @Controller
// public class RegisterController {
//     private final RegisterService registerService;

//     public RegisterController(RegisterService registerService) {
//         this.registerService = registerService;
//     }

//     @GetMapping("/register")
//     public String registerForm(Model model) {
//         model.addAttribute("registerForm", new RegisterForm());
//         return "register";
//     }

//     @PostMapping("/register")
//     public String register(@ModelAttribute RegisterForm register, Model model) {
//         if (registerService.register(registerForm())) {
//             return "setup";
//         }
//         return "regisuter";
//     }
// }
