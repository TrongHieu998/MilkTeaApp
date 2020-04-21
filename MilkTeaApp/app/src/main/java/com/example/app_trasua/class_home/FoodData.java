package com.example.app_trasua.class_home;

public class FoodData {
    public String itemTen;
    public String itemGia;
    public String itemImg;
    public String itemChitiet;
    public String itemId;
    private String id;
    private String key;

    public FoodData() {
    }
    public FoodData(String itemTen, String itemGia, String itemImg, String itemChitiet) {
        this.itemTen = itemTen;
        this.itemGia = itemGia;
        this.itemImg = itemImg;
        this.itemChitiet = itemChitiet;
    }

    public FoodData(String itemTen, String itemGia, String itemImg, String itemChitiet, String itemId) {
        this.itemTen = itemTen;
        this.itemGia = itemGia;
        this.itemImg = itemImg;
        this.itemChitiet = itemChitiet;
        this.itemId = itemId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getId() {
        return id;
    }
    public String getItemTen() {
        return itemTen;
    }

    public void setItemTen(String itemTen) {
        this.itemTen = itemTen;
    }

    public String getItemGia() {
        return itemGia;
    }

    public void setItemGia(String itemGia) {
        this.itemGia = itemGia;
    }

    public String getItemImg() {
        return itemImg;
    }

    public void setItemImg(String itemImg) {
        this.itemImg = itemImg;
    }

    public String getItemChitiet() {
        return itemChitiet;
    }

    public void setItemChitiet(String itemChitiet) {
        this.itemChitiet = itemChitiet;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
