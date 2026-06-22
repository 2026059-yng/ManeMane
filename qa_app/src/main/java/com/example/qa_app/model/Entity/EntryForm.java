package com.example.qa_app.model.Entity;

import java.sql.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EntryForm {
  private Date date;
  private String category_name;
  private String in_out;
  private int daily_amount;
}
