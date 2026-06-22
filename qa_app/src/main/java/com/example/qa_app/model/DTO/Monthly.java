package com.example.qa_app.model.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Monthly {
    private int id;
    private User user;
    private boolean isFinCategory;
    private int monthlyAmount;

}
