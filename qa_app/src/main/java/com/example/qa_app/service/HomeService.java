package com.example.qa_app.service;

import com.example.qa_app.QaAppApplication;
import com.example.qa_app.model.Entity.EntryForm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
  public int calcActualExpenses(long user_id, int num) {
    Optional<Integer> expensesOpt = homeRepository.findExpensesThisMonth(user_id);
    Optional<Integer> extraIncOpt = homeRepository.findExtraIncThisMonth(user_id);

    int expenses = 0;
    int extraInc = 0;

    if (expensesOpt.isEmpty() && extraIncOpt.isEmpty()) { // 支出、臨時収入のどちらもデータがない場合
      return 0;
    } else if (expensesOpt.isEmpty() &&!(extraIncOpt.isEmpty())) { // 臨時収入のみデータが存在する場合
      extraInc = extraIncOpt.get();
    } else if (!(expensesOpt.isEmpty()) && extraIncOpt.isEmpty()) { // 支出のみデータが存在する場合
      expenses = expensesOpt.get();
    } else { // 支出、臨時収入のどちらもデータが存在する場合
      expenses = expensesOpt.get();
      extraInc = extraIncOpt.get();
    }


    if(num==1){
      // numが1の場合は支出-臨時収入の計算を行う
      //3000-5000=-2000
      return expenses - extraInc;
    }else{
   // numが2の場合は支出-臨時収入の計算を行い、マイナス値になったら0を設定する
   //3000-5000=-2000→0を返す
      if((expenses - extraInc) < 0){
        return 0;
      }else{
        return expenses - extraInc;
      }
    }

  }

  // 現在の使用可能金額の計算
  public Integer calcAvailableBalance(long user_id) {
    int actualExpenses = calcActualExpenses(user_id, 1);

    return super.calcMonthlyBudget(user_id) - actualExpenses;
  }

  // 現在の使用可能金額・収入・固定費合計・実支出をListに格納し、返す
  public List<Integer> returnAmounts(long user_id) {
    int actualExpenses = calcActualExpenses(user_id, 2);

    List<Integer> amounts = Arrays.asList(
        calcAvailableBalance(user_id),
        super.showIncome(user_id),
        super.calcSumFixedCosts(user_id),
        actualExpenses);

    
    return amounts;
  }

  // データベースに収支を書き込む
  public void saveTransaction(EntryForm form, long user_id) {
    homeRepository.saveTransaction(form, user_id);
  }

}
