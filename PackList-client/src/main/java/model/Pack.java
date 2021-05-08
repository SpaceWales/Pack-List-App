package model;

import java.util.ArrayList;
import java.util.List;

public class Pack {

    private List<Item> itemList;

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }

    public void addItem(Item item){
        if(this.itemList == null){
            this.itemList = new ArrayList<>();
        }
        this.itemList.add(item);
    }


}
