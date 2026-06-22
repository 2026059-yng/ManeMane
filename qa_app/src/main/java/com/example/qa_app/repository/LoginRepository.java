package com.example.qa_app.repository;

import java.util.Optional;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

@Repository
public class LoginRepository {
    private JdbcClient jdbcClient;

    public LoginRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public Optional<String> findpasswordHash(String email) {
        String sql = ("SELECT password FROM users WHERE email = :email");
        return jdbcClient.sql(sql)
                .param("email", email)
                .query(String.class)
                .optional();
    }

    // ユーザーIDの取得
    public int findUserId(String email){
        return jdbcClient.sql("SELECT id FROM users WHERE email = :email")
                .param("email", email)
                .query(Integer.class)
                .single();
    }
}
