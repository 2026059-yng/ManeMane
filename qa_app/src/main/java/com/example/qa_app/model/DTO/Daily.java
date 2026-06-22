package com.example.qa_app.model.DTO;

import lombok.Getter;
import lombok.Setter;
import java.sql.Date;

@Getter
@Setter
public class Daily {
    private int id;
    private Date date;
    private String categoryName;
    private boolean inOut;
    private int dailyAmount;

    public Daily(int id, Date date, String categoryName, boolean inOut, int dailyAmount) {
        this.id = id;
        this.date = date;
        this.categoryName = categoryName;
        this.inOut = inOut;
        this.dailyAmount = dailyAmount;
    }
}
