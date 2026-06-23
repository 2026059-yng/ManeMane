package com.example.qa_app.service;

import org.springframework.stereotype.Service;
import com.example.qa_app.model.Entity.RegisterForm;
import com.example.qa_app.repository.SetupRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SetupService {

    private final SetupRepository setupRepository;

    public void setup(Long userId, RegisterForm registerForm) {
        // 収入
        setupRepository.incomeInsert(
            registerForm.getIncomeAmount(),
            userId);
        // 固定費
        if (registerForm.getFixedName() != null) {
            for (int i = 0; i < registerForm.getFixedName().size(); i++) {
                setupRepository.fixedInsert(
                        userId,
                        false,
                        registerForm.getFixedName().get(i),
                        registerForm.getFixedAmount().get(i));
            }
        }
        // カテゴリ
        setupRepository.categoriesDelete(userId);
        for (String category : registerForm.getCategories()) {
            setupRepository.categoriesInsert(userId, category);
        }
    }

}
