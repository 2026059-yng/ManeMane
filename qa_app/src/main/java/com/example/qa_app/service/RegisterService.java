package com.example.qa_app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.qa_app.model.DTO.User;
import com.example.qa_app.model.Entity.RegisterFrom;
import com.example.qa_app.repository.RegisterRepository;

import java.util.Arrays;
import java.util.List;

@Service
public class RegisterService {

    @Autowired
    private RegisterRepository registerRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public RegisterService(RegisterRepository registerRepository) {
        this.registerRepository = registerRepository;
    }

    public long registerUser(RegisterFrom form) { // ユーザ新規登録
        if (registerRepository.existsByEmail(form.getEmail())) { // 例外処理
            throw new IllegalArgumentException("メールアドレスは既に使用されています");
        }
        User user = new User();
        user.setEmail(form.getEmail());
        String hashedPassword = passwordEncoder.encode(form.getPassword());
        user.setPassword(hashedPassword);
        registerRepository.save(user);

        long userId = registerRepository.findUserEmail(user.getEmail());
        registerRepository.registerSaveIncome(
            userId,
            true,
            "収入",
            200000
        );
        List<String> defaultCategories = Arrays.asList("食費","趣味・娯楽","その他");
        for (int i = 0; i < defaultCategories.size(); i++) {
            registerRepository.registerSaveCategory(
                userId,
                defaultCategories.get(i)
            );
        }

        return registerRepository.findUserEmail(user.getEmail()); 
    }

}