package com.example.relationaldataaccess.dao;

import com.example.relationaldataaccess.model.Pack;
import com.example.relationaldataaccess.model.Packlist;
import com.example.relationaldataaccess.model.User;
import org.springframework.stereotype.Component;

public interface PackDAO {


    public Packlist createPack(Packlist packlist);
}
