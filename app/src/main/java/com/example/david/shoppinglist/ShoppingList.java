package com.example.david.shoppinglist;

import java.io.Serializable;

public class ShoppingList implements Serializable {
private  int count;
private String item;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public ShoppingList(String name, int count){
        this.count = count;
        this.item = name;
    }
}
