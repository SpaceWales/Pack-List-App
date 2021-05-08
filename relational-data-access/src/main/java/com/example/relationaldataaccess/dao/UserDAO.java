package com.example.relationaldataaccess.dao;

import com.example.relationaldataaccess.model.User;

import java.util.List;

public interface UserDAO {

    public List<User> returnAllUsers();
    public User registerNewUser(User user);
}
