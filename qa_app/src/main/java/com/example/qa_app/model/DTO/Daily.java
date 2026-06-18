package com.example.qa_app.model.DTO;

import java.sql.Date;

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

    public int getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public boolean isInOut() {
        return inOut;
    }

    public int getDailyAmount() {
        return dailyAmount;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

}