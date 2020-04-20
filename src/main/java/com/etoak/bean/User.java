package com.etoak.bean;
import lombok.Data;

@Data
public class User {
    private int id;
    private  String name;
    private String password;
    private String gender;
    private Integer age;
    private String birthday;
}
