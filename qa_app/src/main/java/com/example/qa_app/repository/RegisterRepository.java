// package com.example.qa_app.repository;

// import org.springframework.stereotype.Repository;
// import org.apache.catalina.User;
// import org.springframework.dao.DataAccessException;
// import org.springframework.data.repository.query.Param;
// import org.springframework.jdbc.core.JdbcTemplate;
// import org.springframework.jdbc.core.simple.JdbcClient;

// @Repository
// public class RegisterRepository {
//     private final JdbcTemplate jdbcTemplate;

//     public RegisterRepository(JdbcTemplate jdbcTemplate) {
//         this.jdbcTemplate = jdbcTemplate;
//     }
    
//     public void save(User user) {
//         String sql = ("INSERT INTO users(email, password) VALUES (?, ?)");
//         return jdbcTemplate.update(sql, user.getEmail(), user.getPassword());
//     }
// }