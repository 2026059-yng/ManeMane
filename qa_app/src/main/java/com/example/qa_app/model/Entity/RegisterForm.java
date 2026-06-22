package com.example.qa_app.model.Entity;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class RegisterForm {
    private String email;
    private String password;

    //入力された収入、固定費、カテゴリ、金額取得
    private Integer incomeAmount;
    private List<String> categories;
    private List<String> fixedNames;
    private List<Integer> fixedAmounts;
}
