package com.example.qa_app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.qa_app.service.CashFlowService;

@Controller
public class CashFlowController {
    private final CashFlowService cashFlowService;

    public CashFlowController(CashFlowService cashFlowService) {
        this.cashFlowService = cashFlowService;
  }

  @GetMapping("/cashflow/{user_id}")
  public String cashflow(@PathVariable int user_id, Model model) {
    model.addAttribute("cashflow", cashFlowService.calcExpensesThisMonth(user_id));
    model.addAttribute("cashflow", cashFlowService.showAllThisMonth(user_id) );
    model.addAttribute("cashflow", cashFlowService.calcExpensesLastMonth(user_id)); 
    model.addAttribute("cashflow", cashFlowService.showAllLastMonth(user_id));
    return "cashflow";
  }

  @PostMapping("/cashflow/{id}/delete")
    public String delete(@PathVariable Long id) {
    cashFlowService.deleteCashFlowItem(id);
    return "redirect:/cashflow";
  }

}