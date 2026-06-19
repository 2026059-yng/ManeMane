package com.example.qa_app.repository;

import java.util.List;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import com.example.qa_app.model.DTO.User;
import com.example.qa_app.model.Entity.EntryForm;
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
        .sql("SELECT monthly_amount FROM monthly WHERE user_id = :user_id AND financial_category = true")
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
      User user = jdbcClient.sql("SELECT * FROM users WHERE id = :user_id")
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
      User user = jdbcClient.sql("SELECT * FROM users WHERE id = :user_id")
          .param("user_id", user_id)
          .query(User.class)
          .single();

      category.setUser(user);
    }

    return categories;
  }

  //臨時収入合計を取得する(dailyテーブル)
  public int findExtraIncThisMonth(int user_id){
    int extraIncome = 0;
    extraIncome = jdbcClient.sql("select SUM(daily_amount) from daily where user_id = :user_id and in_out = false group by user_id")
        .param("user_id", user_id)
        .query(Integer.class)
        .single();

    return extraIncome;
  }

  //支出合計を取得する(dailyテーブル)
  public int findExpensesThisMonth(int user_id){
    int expenses = 0;
    expenses = jdbcClient.sql("select SUM(daily_amount) from daily where user_id = :user_id and in_out = true group by user_id")
        .param("user_id", user_id)
        .query(Integer.class)
        .single();

    return expenses;
  }


  //収支データをdailyテーブルに追加（INSERT）
  public void saveTransaction(EntryForm form, int user_id){
    boolean in_out = Boolean.valueOf(form.getIn_out());

    jdbcClient.sql("INSERT INTO daily(user_id, date, category_name, in_out, daily_amount) VALUES (:user_id, :date, :category_name, :in_out, :daily_amount)")
        .param("user_id", user_id)
        .param("date", form.getDate())
        .param("category_name", form.getCategory_name())
        .param("in_out", in_out)
        .param("daily_amount", form.getDaily_amount())
        .update();
  }

}
