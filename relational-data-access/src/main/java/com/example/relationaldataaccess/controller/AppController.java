package com.example.relationaldataaccess.controller;

import com.example.relationaldataaccess.dao.ItemDAO;
import com.example.relationaldataaccess.dao.PackDAO;
import com.example.relationaldataaccess.dao.TripDAO;
import com.example.relationaldataaccess.dao.UserDAO;
import com.example.relationaldataaccess.model.Pack;
import com.example.relationaldataaccess.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AppController {

    @Autowired
    ItemDAO itemDAO;
    @Autowired
    PackDAO packDAO;
    @Autowired
    TripDAO tripDAO;
    @Autowired
    UserDAO userDAO;

    @RequestMapping(path="/createpack",method = RequestMethod.POST)
    public Pack createPackMapping(@RequestBody Pack pack){
        return packDAO.createPack(pack);
    }

    @RequestMapping(path="/getAllUsers",method = RequestMethod.GET)
    public List<User> getAllUser(){
        return userDAO.returnAllUsers();
    }

    @RequestMapping(path="/createUser",method = RequestMethod.POST)
    public User registerUser(@RequestBody User user ){
        return userDAO.registerNewUser(user);
    }




}
