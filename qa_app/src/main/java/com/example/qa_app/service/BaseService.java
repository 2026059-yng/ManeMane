// package com.example.qa_app.service;

// import java.util.List;

// import org.springframework.stereotype.Service;

// import com.example.qa_app.model.DTO.Monthly;
// import com.example.qa_app.model.DTO.Category;
// import com.example.qa_app.repository.HomeRepository;

// @Service
// public class BaseService {
//   private HomeRepository homeRepository;

//   //デフォルトコンストラクタ
//   public BaseService(){

//   }

//   //コンストラクタ
//   public BaseService(HomeRepository homeRepository){
//     this.homeRepository = homeRepository;
//   }

//   //収入を表示
//   public int showIncome(int user_id){
//     return homeRepository.findIncome(user_id);
//   }

//   //カテゴリーを表示
//   public List<Category> showCategories(int user_id){
//     return homeRepository.findCategories(user_id);
//   }

//   //使用可能金額の予算を計算（収入 - 固定費合計）
//   public int calcMonthlyBudget(int user_id){
//     return homeRepository.findIncome(user_id)
//              - calcSumFixedCosts(user_id);    
//   }

//   //固定費の合計を計算
//   public int calcSumFixedCosts(int user_id){
//     int fixedCosts = 0;
//     List<Monthly> costs = homeRepository.findFixedCosts(user_id);

//     for (Monthly monthly : costs) {
//       fixedCosts += monthly.getMonthly_amount();
//     }

//     return fixedCosts;
//   }

// }
