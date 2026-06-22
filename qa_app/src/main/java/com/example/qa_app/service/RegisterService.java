package com.example.qa_app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.qa_app.model.DTO.Monthly;
import com.example.qa_app.model.DTO.User;
import com.example.qa_app.model.Entity.RegisterForm;
import com.example.qa_app.repository.RegisterRepository;

@Service
public class RegisterService {

    @Autowired
    private RegisterRepository registerRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public RegisterService(RegisterRepository registerRepository) {
        this.registerRepository = registerRepository;
    }

    public int registerUser(RegisterForm form) { // ユーザ新規登録
        if (registerRepository.existsByEmail(form.getEmail())) { // 例外処理
            throw new IllegalArgumentException("メールアドレスは既に使用されています");
        }
        User user = new User();
        user.setEmail(form.getEmail());
        String hashedPassword = passwordEncoder.encode(form.getPassword());
        user.setPassword(hashedPassword);
        registerRepository.save(user);
        // registerRepository.setupsave(
        //     user_id,
        //     true,
        //     200000;
        // )
        // for (int i = 0; i < 0; i++) {

        // }
        return registerRepository.findUserEmail(user.getEmail()); 
    }

}
