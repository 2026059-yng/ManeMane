package com.example.qa_app.model.Entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MonthlyEditForm {
    private String fixedName;
    private Integer monthlyAmount;
}
