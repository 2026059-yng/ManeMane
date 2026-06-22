package com.example.qa_app.repository;

import java.util.List;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import com.example.qa_app.model.DTO.User;
import com.example.qa_app.model.Entity.CategoryEditForm;
import com.example.qa_app.model.Entity.MonthlyEditForm;
import com.example.qa_app.model.DTO.Monthly;

@Repository
public class MyPageRepository {
    //フィールド
    private final JdbcClient jdbcClient;

    //コンストラクタ
    public MyPageRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

  // すべての固定費を取得する
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
  public List<CategoryEditForm> findCategories(int user_id) {
    List<CategoryEditForm> categories = jdbcClient.sql("SELECT id, category_name FROM categories WHERE user_id = :user_id")
        .param("user_id", user_id)
        .query(CategoryEditForm.class)
        .list();

    return categories;
  }

  //monthlyテーブルの収入を更新する（UPDATE）
  public void changeIncome(int user_id, int income) {
    jdbcClient.sql("UPDATE monthly SET monthly_amount = :income WHERE user_id = :user_id AND financial_category = true")
      .param("user_id", user_id)
      .param("income", income)
      .update();
  }

  //categoryテーブルのカテゴリー名を更新する
  public void changeCategories(int user_id, CategoryEditForm c) {
    jdbcClient.sql("UPDATE categories SET category_name = :category_name WHERE user_id = :user_id and id = :id")
      .param("user_id", user_id)
      .param("category_name", c.getCategory_name())
      .param("id", c.getId())
      .update();
  }

  //monthlyテーブルから特定のidのレコード(固定費)を削除する（DELETE）
  public void deleteCost(int user_id, Long id) {
    jdbcClient.sql("DELETE FROM monthly WHERE user_id = :user_id AND id = :id")
      .param("user_id", user_id)
      .param("id", id)
      .update();
  }

  //monthlyテーブルへの固定費（項目・金額）の追加する（INSERT）
  public void addCost(int user_id, MonthlyEditForm form) {
    jdbcClient.sql("INSERT INTO monthly (user_id, financial_category, fixed_name, monthly_amount) VALUES (:user_id, :financial_category, :fixed_name, :monthly_amount)")
      .param("user_id", user_id)
      .param("financial_category", false)
      .param("fixed_name", form.getFixedName())
      .param("monthly_amount", form.getMonthlyAmount())
      .update();
  }
}

