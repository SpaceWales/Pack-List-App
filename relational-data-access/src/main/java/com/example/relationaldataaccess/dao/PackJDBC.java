package com.example.relationaldataaccess.dao;

import com.example.relationaldataaccess.model.Item;
import com.example.relationaldataaccess.model.Pack;
import com.example.relationaldataaccess.model.Packlist;
import com.example.relationaldataaccess.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PackJDBC implements PackDAO{

    private JdbcTemplate template;

    public PackJDBC(JdbcTemplate jdbcTemplate) {this.template = jdbcTemplate;}

    @Override
    public Packlist createPack(Packlist packlist) {
        //packlist comes in with
        //Pack-> List of items
        //User-> username and id

        //TODO figure out pack_id

        //need to sql insert packlist with with and user_id

        String createPacklistDB = "insert into packlist (user_id) values (?)";
        template.update(createPacklistDB,packlist.getUser().getUser_id());

        //this is potentially disastrous but.. idk a better way right now
        int pack_id = 0;
        String getPackId = "select * from packlist where user_id = ? order by pack_id desc limit 1";
        SqlRowSet getterPack = template.queryForRowSet(getPackId,packlist.getUser().getUser_id());
        if(getterPack.next()){
            pack_id = getterPack.getInt("pack_id");
        }

        //push items into database with associated pack_id
        List<Item> itemList = packlist.getPack().getItemList();
        for(Item item : itemList){
            String sql = "insert into item (pack_id,item_name,item_weight,weight_unit)" +
                    "values(?,?,?,?)";

            template.update(sql,pack_id,item.getItem_name(),
                    item.getItem_weight(),item.getWeight_unit());
        }
        return packlist;
    }

}
