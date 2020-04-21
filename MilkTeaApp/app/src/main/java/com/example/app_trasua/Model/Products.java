package com.example.app_trasua.Model;

public class Products {
    public String itemTen;
    public String itemGia;
    public String itemImg;
    public String itemChitiet;
    private String itemId;

    public Products() {

    }

    public Products(String itemTen, String itemGia, String itemImg, String itemChitiet, String itemId) {
        this.itemTen = itemTen;
        this.itemGia = itemGia;
        this.itemImg = itemImg;
        this.itemChitiet = itemChitiet;
        this.itemId = itemId;
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

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }
}
