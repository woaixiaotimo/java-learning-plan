package com.mvc.po;

import java.util.List;

public class ItemsQueryVo {


    private Items items;

    private UserCustom userCustom;

    private ItemsCustom itemsCustom;


    private List<ItemsCustom> itemsCustomList;


    public Items getItems() {
        return items;
    }

    public void setItems(Items items) {
        this.items = items;
    }

    public ItemsCustom getItemsCustom() {
        return itemsCustom;
    }

    public void setItemsCustom(ItemsCustom itemsCustom) {
        this.itemsCustom = itemsCustom;
    }

    public UserCustom getUserCustom() {
        return userCustom;
    }

    public void setUserCustom(UserCustom userCustom) {
        this.userCustom = userCustom;
    }

    public List<ItemsCustom> getItemsCustomList() {
        return itemsCustomList;
    }

    public void setItemsCustomList(List<ItemsCustom> itemsCustomList) {
        this.itemsCustomList = itemsCustomList;
    }
}
