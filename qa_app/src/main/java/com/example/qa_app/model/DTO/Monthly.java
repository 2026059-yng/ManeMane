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
    private long id;
    private User user;
    private boolean isFinCategory;
    private String fixed_name;
    private int monthlyAmount;
}
