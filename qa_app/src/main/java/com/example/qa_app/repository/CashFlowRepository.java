package com.example.qa_app.repository;

import java.util.List;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import com.example.qa_app.model.DTO.Daily;

@Repository
public class CashFlowRepository {
    private final JdbcClient jdbcClient;

    public CashFlowRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    // 今月分の支出のみのデータを取得
    public List<Daily> findExpensesThisMonth(long user_id) {
        return jdbcClient.sql(
                "SELECT date, category_name, in_out, daily_amount FROM daily WHERE user_id = :user_id AND in_out = TRUE AND date BETWEEN DATE_FORMAT(NOW(), '%Y-%m-01') AND CURRENT_DATE ORDER BY date DESC, daily_amount DESC")
                .param("user_id", user_id)
                .query(Daily.class)
                .list();
    }

    // 今月分の収支データを取得
    public List<Daily> findAllThisMonth(long user_id) {
        return jdbcClient.sql(
                "SELECT date, category_name, in_out, daily_amount FROM daily WHERE user_id = :user_id AND date BETWEEN DATE_FORMAT(NOW(), '%Y-%m-01') AND CURRENT_DATE ORDER BY date DESC, daily_amount DESC")
                .param("user_id", user_id)
                .query(Daily.class)
                .list();
    }

    // dailyテーブルから特定のレコードを削除
    public void deleteCashFlowItem(long id) {
        jdbcClient.sql("DELETE FROM daily WHERE id = :id")
                .param("id", id)
                .update();
    }

    // 先月の支出のみのデータを取得
    public List<Daily> findExpensesLastMonth(long user_id) {
        return jdbcClient.sql(
                "SELECT date, category_name, in_out, daily_amount FROM daily WHERE user_id = :user_id AND in_out = TRUE AND date BETWEEN DATE_FORMAT(NOW()-INTERVAL 1 MONTH, '%Y-%m-01') AND LAST_DAY(NOW() - INTERVAL 1 MONTH) ORDER BY date DESC, daily_amount DESC")
                .param("user_id", user_id)
                .query(Daily.class)
                .list();
    }

    // 先月の収支データを取得
    public List<Daily> findAllLastMonth(long user_id) {
        return jdbcClient.sql(
                "SELECT date, category_name, in_out, daily_amount FROM daily WHERE user_id = :user_id AND date BETWEEN DATE_FORMAT(NOW()-INTERVAL 1 MONTH, '%Y-%m-01') AND LAST_DAY(NOW() - INTERVAL 1 MONTH) ORDER BY date DESC, daily_amount DESC")
                .param("user_id", user_id)
                .query(Daily.class)
                .list();
    }
}
