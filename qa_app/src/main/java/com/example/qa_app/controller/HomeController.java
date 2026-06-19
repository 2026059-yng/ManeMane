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
import org.springframework.web.bind.annotation.RequestBody;


@Controller
public class HomeController{
  private HomeService homeService;
  private HomeRepository homeRepository;

  HomeController(HomeService homeService, BaseService baseService, HomeRepository homeRepository) {
    this.homeService = homeService;
    this.homeRepository = homeRepository;
    
  }

    @GetMapping("/home")
    public String getHome(HttpSession session, Model model) {

        session.setAttribute("user_id", 1);
        Integer user_id = (Integer)session.getAttribute("user_id");
        List<Integer> amounts = homeService.returnAmounts(user_id);

        List<Category> categories = homeService.showCategories(user_id);
        List<String> category_names = new ArrayList<>();

        for (Category category : categories) {
          category_names.add(category.getCategory_name());
        }        

        model.addAttribute("monthlyBudget", amounts.get(0));
        model.addAttribute("income", amounts.get(1));
        model.addAttribute("fixedCosts", amounts.get(2));
        model.addAttribute("actualExpenses", amounts.get(3));

        model.addAttribute("category_name1", category_names.get(0));
        model.addAttribute("category_name2", category_names.get(1));
        model.addAttribute("category_name3", category_names.get(2));

        return "home";
    }

    @PostMapping("/home")
    public String EntryTransaction(@ModelAttribute EntryForm form, HttpSession session) {
        session.setAttribute("user_id", 1);
        
        System.out.println(form.getIn_out());
        System.out.println(form.getCategory_name());
        System.out.println(form.getDate());

        Integer user_id = (Integer)session.getAttribute("user_id");

        homeService.saveTransaction(form, user_id);
      
        return "redirect:/home";
    }
    
    
}
