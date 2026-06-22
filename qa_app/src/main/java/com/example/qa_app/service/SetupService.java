package com.example.qa_app.service;

import org.springframework.stereotype.Service;
import com.example.qa_app.model.Entity.RegisterForm;
import com.example.qa_app.repository.SetupRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SetupService {

    private final SetupRepository setupRepository;

    public void setup(long userId, RegisterForm registerForm) {
        // 収入
        setupRepository.incomeInsert(
                userId,
                true,
                registerForm.getIncomeAmount());
        // 固定費
        if (registerForm.getFixedNames() != null) {
            for (int i = 0; i < registerForm.getFixedNames().size(); i++) {
                setupRepository.fixedInsert(
                        userId,
                        false,
                        registerForm.getFixedNames().get(i),
                        registerForm.getFixedAmounts().get(i));
            }
        }
        // カテゴリ
        for (String category : registerForm.getCategories()) {
            setupRepository.categoriesInsert(userId, category);
        }
    }

}
