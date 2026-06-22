package com.example.qa_app.model.DTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Monthly {
    private Long id;
    private User user;
    private boolean financial_category;
    private String fixed_name;
    private int monthly_amount;
}
