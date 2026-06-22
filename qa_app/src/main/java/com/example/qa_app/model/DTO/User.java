package com.example.qa_app.model.DTO;

import lombok.Getter;
import lombok.Setter;

//DTO
@Getter
@Setter
public class User {
    private long id;
    private String email;
    private String password;
}
