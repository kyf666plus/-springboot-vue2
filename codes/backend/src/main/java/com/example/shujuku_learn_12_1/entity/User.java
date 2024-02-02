package com.example.shujuku_learn_12_1.entity;

public class User  {

    private int id;
    private String username;
    private String password;
    private String idCard; // 与数据库表字段名一致
    private Boolean isAdmin;

    public User() {

    }

    public User(int id, String username, String password, String idCard) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.idCard = idCard;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", idCard='" + idCard + '\'' +
                ", isAdmin=" + isAdmin +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public Boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }
}
