package com.example.qa_app.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class RegisterRepository {

    private final JdbcTemplate jdbc;

    public void incomeInsert(int userId,boolean financialCategory,Integer monthlyAmount) {
        String sql = "INSERT INTO daily(user_id, financial_category, monthly_amount)VALUES (?, ?, ?, ?)";
        jdbc.update(sql,userId,financialCategory,monthlyAmount);
    }

    public void fixedInsert(int userId,boolean financialCategory,String fixedName,Integer monthlyAmount) {
        String sql = "INSERT INTO daily(user_id, financial_category, fixed_name, monthly_amount)VALUES (?, ?, ?, ?)";
        jdbc.update(sql,userId,financialCategory,fixedName,monthlyAmount);
    }

    public void categoriesInsert(int userId,String categoryName) {
      String sql = "INSERT INTO categories(user_id,category_name)VALUES (?, ?)";
      jdbc.update(sql,userId,categoryName);
    }

}
