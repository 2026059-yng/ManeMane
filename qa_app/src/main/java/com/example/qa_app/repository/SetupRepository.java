package com.example.qa_app.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class SetupRepository {
    
    private final JdbcTemplate jdbc;

    public void incomeInsert(int monthlyAmount, long userId) {
        String sql = "UPDATE monthly SET monthly_amount = ? WHERE user_id = ? AND financial_category = true";
        jdbc.update(sql, monthlyAmount, userId);
    }

    public void fixedInsert(Long userId, boolean financialCategory, String fixedName, Integer monthlyAmount) {
        String sql = "INSERT INTO monthly(user_id, financial_category, fixed_name, monthly_amount)VALUES (?, ?, ?, ?)";
        jdbc.update(sql, userId, financialCategory, fixedName, monthlyAmount);
    }

    public void categoriesDelete(Long userId) {
        String sql = "DELETE FROM categories WHERE user_id = ?";
        jdbc.update(sql, userId);
    }

    public void categoriesInsert(Long userId, String categoryName) {
        String sql = "INSERT INTO categories(user_id,category_name)VALUES (?, ?)";
        jdbc.update(sql, userId, categoryName);
    }

}
