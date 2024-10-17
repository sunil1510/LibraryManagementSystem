package com.library.system.entity;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String id;
    private String name;
    private String userName;
    private String password;
    private List<Book> rentedBooks;

    public User() {
        this.rentedBooks=new ArrayList<>();
    }

    public User(String id, String name, String userName, String password, List<Book> rentedBooks) {
        this.id = id;
        this.name = name;
        this.userName = userName;
        this.password = password;
        this.rentedBooks = rentedBooks;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Book> getRentedBooks() {
        return rentedBooks;
    }

    public void setRentedBooks(List<Book> rentedBooks) {
        this.rentedBooks = rentedBooks;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", rentedBooks=" + rentedBooks +
                '}';
    }

}
