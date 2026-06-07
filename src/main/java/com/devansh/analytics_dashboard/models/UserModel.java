package com.devansh.analytics_dashboard.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Document(collection = "users")
@Getter
@Setter
public class UserModel {

    @Id
    private String id;
    private String username;
    private String email;
    private String password;
    
}