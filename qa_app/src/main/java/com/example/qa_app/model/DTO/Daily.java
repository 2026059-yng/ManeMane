package com.example.qa_app.model.DTO;

import lombok.Getter;
import lombok.Setter;
import java.sql.Date;

@Getter
@Setter
public class Daily {
    private  Date date;
    private String categoryName;
    private  boolean isInOut;
    private  int dailyAmount;
    public Daily(Date date,String categoryName,boolean isInOut,int dailyAmount){
        this.date=date;
        this.categoryName=categoryName;
        this.isInOut=isInOut;
        this.dailyAmount=dailyAmount;
    }
}
