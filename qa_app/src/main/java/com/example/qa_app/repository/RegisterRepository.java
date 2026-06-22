package com.example.qa_app.repository;

import org.springframework.stereotype.Repository;

import com.example.qa_app.model.DTO.Monthly;
import com.example.qa_app.model.DTO.User;
import org.springframework.beans.factory.annotation.Autowired;
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

    public void registersave(User user, Monthly monthly) { //カテゴリと金額の取得
        String sql = "INSERT INTO monthly (user_id, financial_category, monthly_amount) VALUES (?, ?, ?)";
        jdbcClient.sql(sql)
                .param("financial_category", monthly.isFinCategory())
                .param("monthly_amount", monthly.getMonthlyAmount())
                .update();
    }

    public int findUserEmail(String email) { // userid取得処理(int)
        return jdbcClient.sql("SELECT id FROM users WHERE email = :email")
                .param("email", email)
                .query(Integer.class)
                .single();
    
    }
}