package com.shen.reservation.dto;

public class UserRegistrationDto {
    private String username;
    private String password;
    private String email;


    public UserRegistrationDto(){}


    public UserRegistrationDto(String username, String password, String email) {
        super();
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword(){
        return password;
    }
    public String getEmail() {
        return email;
    }

}
