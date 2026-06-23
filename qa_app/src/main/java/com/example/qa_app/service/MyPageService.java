package com.example.qa_app.service;

import com.example.qa_app.repository.HomeRepository;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.qa_app.repository.MyPageRepository;
import com.example.qa_app.model.DTO.Monthly;
import com.example.qa_app.model.Entity.CategoryEditForm;
import com.example.qa_app.model.Entity.MonthlyEditForm;

@Service
public class MyPageService extends BaseService {
    //フィールド
    private final MyPageRepository myPageRepository;
    private final HomeRepository homeRepository;

    //コンストラクタ
    public MyPageService(MyPageRepository myPageRepository, HomeRepository homeRepository) {
        super(homeRepository);
        this.myPageRepository = myPageRepository;
        this.homeRepository = homeRepository;
    }

    //収入の表示、今月使用可能金額の予算・固定費合計の計算はBaseServiceを継承

    //固定費の項目名、金額を表示する
    public List<Monthly> showFixedCosts(long user_id) {
        return myPageRepository.findFixedCosts(user_id);
    }

    //収入を変更する
    public void changeIncome(long user_id, int income) {
        myPageRepository.changeIncome(user_id, income);
    }

    //カテゴリーを表示する
    public List<CategoryEditForm> showCategoriesMypage(long user_id){
        return myPageRepository.findCategories(user_id);
    }
    
    //カテゴリを変更する
    public void changeCategories(long user_id, List<CategoryEditForm> list) {
        for (CategoryEditForm c : list) {
            myPageRepository.changeCategories(user_id, c);
        }
    }

    //固定費の項目を削除する
    public void deleteCost(long user_id, Long id, Monthly monthly) {
        if (monthly.isFinCategory() == false) {
            myPageRepository.deleteCost(user_id, id);
        }
    }

    //固定費の項目を追加する
    public void addCost(long user_id, MonthlyEditForm form) {
        if (form.getFixedName() != null) {
            myPageRepository.addCost(user_id, form);
        }
    }
}
