package com.example.qa_app.model.DTO;

public class Daily {
    private  int date;
    private String categoryName;
    private  boolean isInOut;
    private  int dailyAmount;
    public Daily(int date,String categoryName,boolean isInOut,int dailyAmount){
        this.date=date;
        this.categoryName=categoryName;
        this.isInOut=isInOut;
        this.dailyAmount=dailyAmount;
    }
    public int getDate() {
        return date;
    }
    public String getCategoryName() {
        return categoryName;
    }
    public boolean isInOut() {
        return isInOut;
    }
    
    public int getDailyAmount() {
        return dailyAmount;
    }

   public void setCategoryName(String categoryName){
     this.categoryName=categoryName;
   }

    

}
