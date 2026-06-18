package com.example.qa_app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import jakarta.servlet.http.HttpSession;

import com.example.qa_app.service.CashFlowService;



@Controller
public class CashFlowController {
    private final CashFlowService cashFlowService;

    public CashFlowController(CashFlowService cashFlowService) {
        this.cashFlowService = cashFlowService;
  }
  int user_id ;
  @GetMapping("/cashflow")
  public String cashflow(@PathVariable int user_id, Model model, HttpSession session) {
    user_id = (int)session.getAttribute("user_id");
    model.addAttribute("totalExpensesThisMonth", cashFlowService.calcExpensesThisMonth(session.getAttribute(user_id)));
    model.addAttribute("allThisMonth", cashFlowService.showAllThisMonth(session.getAttribute("user_id")));
    model.addAttribute("totalExpensesLastMonth", cashFlowService.calcExpensesLastMonth(session.getAttribute("user_id"))); 
    model.addAttribute("allLastMonth", cashFlowService.showAllLastMonth(session.getAttribute("user_id")));
    return "cashflow";
  }

  @PostMapping("/cashflow/{id}/delete")
    public String delete(@PathVariable Long id) {
    cashFlowService.deleteCashFlowItem(id);
    return "redirect:/cashflow";
  }

}