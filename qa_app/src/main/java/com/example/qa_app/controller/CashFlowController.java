package com.example.qa_app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.qa_app.service.CashFlowService;

import jakarta.servlet.http.HttpSession;

@Controller
public class CashFlowController {
  private final CashFlowService cashFlowService;

  public CashFlowController(CashFlowService cashFlowService) {
    this.cashFlowService = cashFlowService;
  }

  @GetMapping("/cashflow")
  public String cashflow(Model model, HttpSession session) {
    // 検証用
    session.setAttribute("user_id", 1);
    // user_idをセッションから取得
    int user_id = (int) session.getAttribute("user_id");
    model.addAttribute("totalExpensesThisMonth", cashFlowService.calcExpensesThisMonth(user_id));
    model.addAttribute("allThisMonth", cashFlowService.showAllThisMonth(user_id));
    model.addAttribute("totalExpensesLastMonth", cashFlowService.calcExpensesLastMonth(user_id));
    model.addAttribute("allLastMonth", cashFlowService.showAllLastMonth(user_id));
    return "cashflow";
  }

  @PostMapping("/cashflow/{id}/delete")
  public String delete(@PathVariable Long id, Model model) {
    cashFlowService.deleteCashFlowItem(id);
    return "redirect:/cashflow";
  }

}