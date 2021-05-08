package com.example.relationaldataaccess.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class TripJDBC implements TripDAO{

    private JdbcTemplate template;

    public TripJDBC(JdbcTemplate jdbcTemplate) {this.template = jdbcTemplate;}
}
