package com.example.qa_app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.qa_app.model.DTO.Daily;
import com.example.qa_app.repository.CashFlowRepository;

@Service
public class CashFlowService {
    private final CashFlowRepository cashFlowRepository;

    public CashFlowService(CashFlowRepository cashFlowRepository) {
        this.cashFlowRepository = cashFlowRepository;
    }

    // 今月分の支出の合計金額を計算
    public int calcExpensesThisMonth(Long user_id) {
        List<Daily> expenses = cashFlowRepository.findExpensesThisMonth(user_id);
        int sum = 0;
        for (int i = 0; i < expenses.size(); i++) {
            sum += expenses.get(i).getDailyAmount();
        }
        return sum;
    };


    // 今月分の収支のデータを表示
    public List<Daily> showAllThisMonth(Long user_id) {
        return cashFlowRepository.findAllThisMonth(user_id);
    }

    // 今月分の収支のデータを削除
    public void deleteCashFlowItem(Long id) {
        cashFlowRepository.deleteCashFlowItem(id);
    }

    // 先月の支出の合計金額を計算
    public int calcExpensesLastMonth(Long user_id) {
        List<Daily> expenses = cashFlowRepository.findExpensesLastMonth(user_id);
        int sum = 0;
        for (int i = 0; i < expenses.size(); i++) {
            sum += expenses.get(i).getDailyAmount();
        }
        return sum;
    }

    // 先月の収支のデータを表示
    public List<Daily> showAllLastMonth(Long user_id) {
        return cashFlowRepository.findAllLastMonth(user_id);
    }
}
