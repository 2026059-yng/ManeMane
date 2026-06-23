package com.example.qa_app.controller;

import com.example.qa_app.model.DTO.Category;
import com.example.qa_app.model.Entity.EntryForm;
import com.example.qa_app.repository.HomeRepository;
import com.example.qa_app.service.BaseService;
import com.example.qa_app.service.HomeService;

import jakarta.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {
  private HomeService homeService;
  private HomeRepository homeRepository;

  HomeController(HomeService homeService, BaseService baseService, HomeRepository homeRepository) {
    this.homeService = homeService;
    this.homeRepository = homeRepository;

  }

  @GetMapping("/home")
  public String getHome(HttpSession session, Model model) {
    // Httpsessionからuser_idを取得
    Long user_id = (Long)session.getAttribute("user_id");

    // 使用可能金額・収入・固定費合計・実支出の金額を格納したリストを受け取る
    List<Integer> amounts = homeService.returnAmounts(user_id);

    // カテゴリー表示のためのリストを取得
    List<Category> categories = homeService.showCategories(user_id);
    // カテゴリーの名前のみを格納するリストを作成（下のループで格納）
    List<String> category_names = new ArrayList<>();

    for (Category category : categories) {
      category_names.add(category.getCategory_name());
    }

    // 4つの金額を格納したリストを、それぞれの変数に割り当て
    model.addAttribute("availableBalance", amounts.get(0));
    model.addAttribute("income", amounts.get(1));
    model.addAttribute("fixedCosts", amounts.get(2));
    model.addAttribute("actualExpenses", amounts.get(3));

    // カテゴリー名を格納したリストを、割り当てる
    model.addAttribute("category_name1", category_names.get(0));
    model.addAttribute("category_name2", category_names.get(1));
    model.addAttribute("category_name3", category_names.get(2));

    return "home";
  }

  @PostMapping("/home")
  public String EntryTransaction(@ModelAttribute EntryForm form, HttpSession session) {
    Long user_id = (Long)session.getAttribute("user_id");

    // 支出・臨時収入に関する入力データをDBに追加するメソッドの呼び出し
    homeService.saveTransaction(form, user_id);

    return "redirect:/home";
  }

}
