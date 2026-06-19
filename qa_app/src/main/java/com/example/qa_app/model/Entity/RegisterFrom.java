package com.example.qa_app.model.Entity;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterFrom {
    private Integer incomeAmount;
    private List<String> categories;
    private List<String> fixedNames;
    private List<Integer> fixedAmounts;

}
