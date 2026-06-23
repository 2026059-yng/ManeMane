package com.example.qa_app.controller;

import com.example.qa_app.model.DTO.Monthly;
import com.example.qa_app.model.Entity.CategoryEditForm;
import com.example.qa_app.model.Entity.CategoryListForm;
import com.example.qa_app.model.Entity.MonthlyEditForm;
import com.example.qa_app.service.MyPageService;

import jakarta.servlet.http.HttpSession;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class MyPageController {
    private final MyPageService myPageService;

    MyPageController(MyPageService myPageService) {
        this.myPageService = myPageService;
    }

    @GetMapping("/mypage")
    public String getData(HttpSession session, Model model) {
        Long user_id = (Long)session.getAttribute("user_id");
        String email = (String)session.getAttribute("email");

        //今月の使用可能金額表示
        model.addAttribute("monthlyBudget", myPageService.calcMonthlyBudget(user_id));

        //収入表示
        model.addAttribute("income", myPageService.showIncome(user_id));

        //カテゴリ名表示
        List<CategoryEditForm> list = myPageService.showCategoriesMypage(user_id);
        CategoryListForm categories = new CategoryListForm();
        categories.setCategories(list);
        model.addAttribute("categories", categories); 

        //固定費の合計金額・各名称・各金額の表示
        model.addAttribute("fixedCosts", myPageService.calcSumFixedCosts(user_id));
        List<Monthly> costs = myPageService.showFixedCosts(user_id);
        model.addAttribute("costs", costs);

        //登録メールアドレスの表示
        model.addAttribute("email", email);

        return "mypage";
    }

    // 収入変更
    @PostMapping("/income")
    public String postIncome(HttpSession session, @RequestParam int income) {
        Integer user_id = (Integer)session.getAttribute("user_id");

        myPageService.changeIncome(user_id, income); 
        return "redirect:/mypage";
    }

    // カテゴリーの変更
    @PostMapping("/category")
    public String postCategory(HttpSession session, @ModelAttribute CategoryListForm form) {
        Integer user_id = (Integer)session.getAttribute("user_id");

        List<CategoryEditForm> list = form.getCategories();
        myPageService.changeCategories(user_id, list);

        return "redirect:/mypage";
    }

    // 固定費の削除
    @PostMapping("/deleteCost/{id}")
    public String deleteCost(HttpSession session, @PathVariable Long id, Monthly monthly) {
        Integer user_id = (Integer)session.getAttribute("user_id");

        myPageService.deleteCost(user_id, id, monthly);
        return "redirect:/mypage";
    }

    // 固定費の追加
    @PostMapping("/cost")
    public String postCost(HttpSession session,@ModelAttribute MonthlyEditForm form) {
        Integer user_id = (Integer)session.getAttribute("user_id");

        myPageService.addCost(user_id, form);
        return "redirect:/mypage";
    }

    //ログアウト
    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
