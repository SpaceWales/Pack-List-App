package com.example.relationaldataaccess.model;

public class Item {
    private int item_id;
    private int pack_id;
    private String item_name;
    private double item_weight;
    private String weight_unit;

    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    public int getPack_id() {
        return pack_id;
    }

    public void setPack_id(int pack_id) {
        this.pack_id = pack_id;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public double getItem_weight() {
        return item_weight;
    }

    public void setItem_weight(double item_weight) {
        this.item_weight = item_weight;
    }

    public String getWeight_unit() {
        return weight_unit;
    }

    public void setWeight_unit(String weight_unit) {
        this.weight_unit = weight_unit;
    }
}
