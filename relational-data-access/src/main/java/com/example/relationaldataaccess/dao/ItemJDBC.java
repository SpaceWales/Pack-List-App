package com.example.relationaldataaccess.dao;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class ItemJDBC implements ItemDAO{

    JdbcTemplate jdbcTemplate;

    public ItemJDBC(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void testMethod(){
        String sql = "select * from item";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql);
        while(result.next()){
            String name = result.getString("item_name");
            System.out.println(name);
        }
    }

}
