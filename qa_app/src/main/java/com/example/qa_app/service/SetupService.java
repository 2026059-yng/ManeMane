package com.example.qa_app.service;

import org.springframework.stereotype.Service;
import com.example.qa_app.model.Entity.RegisterFrom;
import com.example.qa_app.repository.SetupRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SetupService {

    private final SetupRepository setupRepository;

    public void register(int userId, RegisterFrom registerFrom) {
        // 収入
        setupRepository.incomeInsert(
                userId,
                true,
                registerFrom.getIncomeAmount());
        // 固定費
        if (registerFrom.getFixedNames() != null) {
            for (int i = 0; i < registerFrom.getFixedNames().size(); i++) {
                setupRepository.fixedInsert(
                        userId,
                        false,
                        registerFrom.getFixedNames().get(i),
                        registerFrom.getFixedAmounts().get(i));
            }
        }
        // カテゴリ
        for (String category : registerFrom.getCategories()) {
            setupRepository.categoriesInsert(userId, category);
        }
    }

}
