package com.example.qa_app.service;

import com.example.qa_app.QaAppApplication;
import com.example.qa_app.model.Entity.EntryForm;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.qa_app.repository.HomeRepository;

@Service
public class HomeService extends BaseService {

  private HomeRepository homeRepository;

  public HomeService(HomeRepository homeRepository) {
    super(homeRepository);
    this.homeRepository = homeRepository;
  }

  // 実支出額を計算
  public int calcActualExpenses(long user_id) {
    return homeRepository.findExpensesThisMonth(user_id)
        - homeRepository.findExtraIncThisMonth(user_id);
  }

  // 現在の使用可能金額の計算
  public Integer calcAvailableBalance(long user_id) {
    return super.calcMonthlyBudget(user_id) - calcActualExpenses(user_id);
  }

  // 現在の使用可能金額・収入・固定費合計・実支出をListに格納し、返す
  public List<Integer> returnAmounts(long user_id) {
    List<Integer> amounts = Arrays.asList(
        calcAvailableBalance(user_id),
        super.showIncome(user_id),
        super.calcSumFixedCosts(user_id),
        calcActualExpenses(user_id));

    return amounts;
  }

  // データベースに収支を書き込む
  public void saveTransaction(EntryForm form, long user_id) {
    homeRepository.saveTransaction(form, user_id);
  }

}
