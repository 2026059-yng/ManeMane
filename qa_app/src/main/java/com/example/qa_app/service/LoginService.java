package com.example.qa_app.service;

import java.util.Optional;
import org.springframework.stereotype.Service;
import com.example.qa_app.repository.LoginRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class LoginService {
    private final LoginRepository loginRepository;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public LoginService(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    public boolean authenticate(String email, String password) {
        if (email == null || password == null || email.isBlank() || password.isBlank()) {
            return false;
        }
        Optional<String> hash = loginRepository.findpasswordHash(email);
        if (hash.isEmpty()) {
            return false;
        }
        return passwordEncoder.matches(password, hash.get());
    }

    public Long saveUserId(String email) { //userid
        return loginRepository.findUserId(email);
    }

}
