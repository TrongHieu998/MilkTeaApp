package com.example.app_trasua.Model;

public class CartDetail {
    public String itemTen,itemGia,itemSoluong,itemId;

    public CartDetail() {
    }

    public CartDetail(String itemTen, String itemGia, String itemSoluong, String itemId) {
        this.itemTen = itemTen;
        this.itemGia = itemGia;
        this.itemSoluong = itemSoluong;
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

    public String getItemSoluong() {
        return itemSoluong;
    }

    public void setItemSoluong(String itemSoluong) {
        this.itemSoluong = itemSoluong;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }
}
