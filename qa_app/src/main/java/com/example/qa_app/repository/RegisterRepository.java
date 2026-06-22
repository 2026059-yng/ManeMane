package com.example.qa_app.repository;

import org.springframework.stereotype.Repository;

import com.example.qa_app.model.DTO.Monthly;
import com.example.qa_app.model.DTO.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.jdbc.core.simple.JdbcClient;

@Repository
public class RegisterRepository {

    @Autowired
    private JdbcClient jdbcClient;

    public RegisterRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public boolean existsByEmail(String email) { // メールアドレスが既にあるか確認
        String sql = "SELECT COUNT(*) FROM users WHERE email = :email";
        Integer count = jdbcClient.sql(sql)
                .param("email", email)
                .query(Integer.class)
                .single();
        return count != null && count > 0;
    }

    public void save(User user) {
        String sql = "INSERT INTO users(email, password) VALUES (:email, :password)";
        jdbcClient.sql(sql)
                .param("email", user.getEmail())
                .param("password", user.getPassword())
                .update();
    }

    public void registerSaveIncome(long userId, boolean isFinCategory, String fixedName, int monthlyAmount) { // 収入の初期設定
        String sql = "INSERT INTO monthly (user_id, financial_category, fixed_name, monthly_amount) VALUES (:userId, :isFinCategory, :fixedName, :monthlyAmount)";
        jdbcClient.sql(sql)
                .param("userId", userId)
                .param("isFinCategory", isFinCategory)
                .param("fixedName", fixedName)
                .param("monthlyAmount", monthlyAmount)
                .update();
    }

    public void registerSaveCategory(long userId, String categoryName) { // カテゴリの初期値を設定
        String sql = "INSERT INTO categories (user_id, category_name) VALUES (:userId, :categoryName)";
        jdbcClient.sql(sql)
                .param("userId", userId)
                .param("categoryName", categoryName)
                .update();
    }

    public long findUserEmail(String email) { // userid取得処理
        return jdbcClient.sql("SELECT id FROM users WHERE email = :email")
                .param("email", email)
                .query(Integer.class)
                .single();

    }
}