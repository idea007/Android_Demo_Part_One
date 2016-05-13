package com.example.idea.androiddemopartone.model;

/**
 * Created by idea on 16/5/12.
 */
public class ItemInfo {

    private String name;
    private int avatar;

    public ItemInfo(){}

    public ItemInfo(String name, int avatar) {
        this.name = name;
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }

}
