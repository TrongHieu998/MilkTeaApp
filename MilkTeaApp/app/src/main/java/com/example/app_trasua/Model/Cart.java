package com.example.app_trasua.Model;

public class Cart {
    private String itemId,itemTen,itemGia,itemSoluong, itemTongtien;

    public Cart() {
    }

    public Cart(String itemId, String itemTen, String itemGia, String itemSoluong) {
        this.itemId = itemId;
        this.itemTen = itemTen;
        this.itemGia = itemGia;
        this.itemSoluong = itemSoluong;
    }

    public String getItemTongtien() {
        return itemTongtien;
    }

    public void setItemTongtien(String itemTongtien) {
        this.itemTongtien = itemTongtien;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
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
}
