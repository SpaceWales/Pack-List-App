package com.example.relationaldataaccess.dao;

import com.example.relationaldataaccess.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserJDBC implements UserDAO{

    private JdbcTemplate template;

    public UserJDBC(JdbcTemplate jdbcTemplate) {this.template = jdbcTemplate;}


    @Override
    public List<User> returnAllUsers() {
        String sql = "select * from users";
        SqlRowSet results = template.queryForRowSet(sql);
        List<User> userList = new ArrayList<>();
        while(results.next()){
            User user = new User();
            user.setUser_id(results.getInt("user_id"));
            user.setUsername(results.getString("username"));
            userList.add(user);
        }
        return userList;
    }

    @Override
    public User registerNewUser(User user) {
        //need a sql for inserting and another to get user id out
        String sqlInsert = "insert into users (username) values (?)";
        String sqlRetrieve = "select * from users where username = ?";

        template.update(sqlInsert,user.getUsername());
        SqlRowSet returnedUser = template.queryForRowSet(sqlRetrieve,user.getUsername());
        if(returnedUser.next()){
            user.setUser_id(returnedUser.getInt("user_id"));
            user.setUsername(returnedUser.getString("username"));
        }else{
            //maybe throw a user exception here?
        }
        return user;
    }
}
