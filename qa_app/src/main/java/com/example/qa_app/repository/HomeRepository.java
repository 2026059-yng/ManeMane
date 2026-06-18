package com.example.qa_app.repository;

import java.util.List;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import com.example.qa_app.model.DTO.User;
import com.example.qa_app.model.DTO.Monthly;
import com.example.qa_app.model.DTO.Category;

@Repository
public class HomeRepository {
  private JdbcClient jdbcClient;

  HomeRepository(JdbcClient jdbcClient) {
    this.jdbcClient = jdbcClient;
  }

  // 収入を取得する
  public int findIncome(int user_id) {
    Integer income = jdbcClient
        .sql("SELECT monthly_amount FROM monthly WHERE user_id = :user_id AND financial_category = TRUE")
        .param("user_id", user_id)
        .query(Integer.class)
        .single();

    return income;
  }

  // 固定費を取得する
  public List<Monthly> findFixedCosts(int user_id) {
    List<Monthly> costs = jdbcClient
        .sql("SELECT * FROM monthly WHERE user_id = :user_id AND financial_category = FALSE")
        .param("user_id", user_id)
        .query(Monthly.class)
        .list();

    for (Monthly monthly : costs) {
      User user = jdbcClient.sql("SELECT * FROM users WHERE user_id = :user_id")
          .param("user_id", user_id)
          .query(User.class)
          .single();

      monthly.setUser(user);
    }

    return costs;
  }

  // カテゴリー(3件)を取得する
  public List<Category> findCategories(int user_id) {
    List<Category> categories = jdbcClient.sql("SELECT id, category_name FROM categories WHERE user_id = :user_id")
        .param("user_id", user_id)
        .query(Category.class)
        .list();

    for (Category category : categories) {
      User user = jdbcClient.sql("SELECT * FROM users WHERE user_id = :user_id")
          .param("user_id", user_id)
          .query(User.class)
          .single();

      category.setUser(user);
    }

    return categories;
  }

}
