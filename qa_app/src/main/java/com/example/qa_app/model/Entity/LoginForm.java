package com.example.qa_app.model.Entity;

import lombok.Getter;
import lombok.Setter;

//Entity
@Getter
@Setter
public class LoginForm {

    private String email;
    private String password;

    public LoginForm(String email, String password) {
        this.email = email;
        this.password = password;
    }

}