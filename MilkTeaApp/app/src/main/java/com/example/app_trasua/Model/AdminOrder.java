package com.example.app_trasua.Model;

public class AdminOrder {
    private String itemHoTen,itemSDT,itemDiachi,itemEmail,itemTinhtrang,itemTongtien,itemId;

    public AdminOrder() {
    }

    public AdminOrder(String itemHoTen, String itemSDT, String itemDiachi, String itemEmail, String itemTinhtrang, String itemTongtien) {
        this.itemHoTen = itemHoTen;
        this.itemSDT = itemSDT;
        this.itemDiachi = itemDiachi;
        this.itemEmail = itemEmail;
        this.itemTinhtrang = itemTinhtrang;
        this.itemTongtien = itemTongtien;
    }

    public AdminOrder(String itemHoTen, String itemSDT, String itemDiachi, String itemEmail, String itemTinhtrang, String itemTongtien, String itemId) {
        this.itemHoTen = itemHoTen;
        this.itemSDT = itemSDT;
        this.itemDiachi = itemDiachi;
        this.itemEmail = itemEmail;
        this.itemTinhtrang = itemTinhtrang;
        this.itemTongtien = itemTongtien;
        this.itemId = itemId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemHoTen() {
        return itemHoTen;
    }
    public void setItemHoTen(String itemHoTen) {
        this.itemHoTen = itemHoTen;
    }

    public String getItemSDT() {
        return itemSDT;
    }

    public void setItemSDT(String itemSDT) {
        this.itemSDT = itemSDT;
    }

    public String getItemDiachi() {
        return itemDiachi;
    }

    public void setItemDiachi(String itemDiachi) {
        this.itemDiachi = itemDiachi;
    }

    public String getItemEmail() {
        return itemEmail;
    }

    public void setItemEmail(String itemEmail) {
        this.itemEmail = itemEmail;
    }

    public String getItemTinhtrang() {
        return itemTinhtrang;
    }

    public void setItemTinhtrang(String itemTinhtrang) {
        this.itemTinhtrang = itemTinhtrang;
    }

    public String getItemTongtien() {
        return itemTongtien;
    }

    public void setItemTongtien(String itemTongtien) {
        this.itemTongtien = itemTongtien;
    }
}
