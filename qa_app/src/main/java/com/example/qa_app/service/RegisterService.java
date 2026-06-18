// package com.example.qa_app.service;

// import java.time.LocalDate;
// import java.time.LocalDateTime;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.stereotype.Service;
// import com.example.qa_app.model.DTO.User;
// import com.example.qa_app.model.Entity.RegisterForm;
// import com.example.qa_app.repository.RegisterRepository;

// @Service
// public class RegisterService {
//     private final RegisterRepository registerRepository;
//     // private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

//     public RegisterService(RegisterRepository registerRepository) {
//         this.registerRepository = registerRepository;

//     }

//     public void registerUser(RegisterForm form) { 
        
//         if(form.getEmail() == null || form.getPassword().isEmpty() || form.getEmail() == null || form.getEmail().isBlank()) {
//             User user = new User();
//             user.setEmail(form.getEmail());
//             user.setPassword((form.getPassword()));
//             return registerRepository.save(user) > 0;
            
//     }
// }
// }