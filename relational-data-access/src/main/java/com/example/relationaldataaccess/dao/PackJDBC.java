package com.example.relationaldataaccess.dao;

import com.example.relationaldataaccess.model.Item;
import com.example.relationaldataaccess.model.Pack;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PackJDBC implements PackDAO{

    private JdbcTemplate template;

    public PackJDBC(JdbcTemplate jdbcTemplate) {this.template = jdbcTemplate;}

    @Override
    public Pack createPack(Pack pack) {
        //pack will come in with a list of items
        //all items will go in pack_id 1 until users get figured out

        //TODO figure out pack_id

        List<Item> itemList = pack.getItemList();
        for(Item item : itemList){
            String sql = "insert into item (pack_id,item_name,item_weight,weight_unit)" +
                    "values(?,?,?,?)";
            //temp value
            int pack_id = 1;
            template.update(sql,pack_id,item.getItem_name(),
                    item.getItem_weight(),item.getWeight_unit());
        }
        return pack;
    }
}
