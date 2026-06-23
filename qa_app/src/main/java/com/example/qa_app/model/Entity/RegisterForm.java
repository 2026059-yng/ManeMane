package com.example.qa_app.model.Entity;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterForm {
    // 新規登録時に使用
    private String email;
    private String password;
    // 初期パラメータ設定に使用
    // ユーザーが入力した収入、固定費、カテゴリ、金額を取得
    private Integer incomeAmount;
    private List<String> categories;
    private List<String> fixedName;
    private List<Integer> fixedAmount;

}
