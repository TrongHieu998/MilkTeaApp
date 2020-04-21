package com.example.app_trasua.Model;

import com.example.app_trasua.class_home.FoodData;

import java.util.ArrayList;

public class Confirm {
    public String itemHoTen, itemSDT,itemEmail,itemDiachi,itemTinhtrang,itemTongtien;
    public ArrayList<CartDetail> arrayList;


    public Confirm() {
    }

    public Confirm(String itemHoTen, String itemSDT, String itemEmail, String itemDiachi, String itemTinhtrang, String itemTongtien, ArrayList<CartDetail> arrayList) {
        this.itemHoTen = itemHoTen;
        this.itemSDT = itemSDT;
        this.itemEmail = itemEmail;
        this.itemDiachi = itemDiachi;
        this.itemTinhtrang = itemTinhtrang;
        this.itemTongtien = itemTongtien;
        this.arrayList = arrayList;
    }

    public String getItemTinhtrang() {
        return itemTinhtrang;
    }

    public void setItemTinhtrang(String itemTinhtrang) {
        this.itemTinhtrang = itemTinhtrang;
    }

    public ArrayList<CartDetail> getArrayList() {
        return arrayList;
    }

    public void setArrayList(ArrayList<CartDetail> arrayList) {
        this.arrayList = arrayList;
    }

    public void setItemDiachi(String itemDiachi) {
        this.itemDiachi = itemDiachi;
    }

    public String getItemTongtien() {
        return itemTongtien;
    }

    public void setItemTongtien(String itemTongtien) {
        this.itemTongtien = itemTongtien;
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

    public String getItemEmail() {
        return itemEmail;
    }

    public void setItemEmail(String itemEmail) {
        this.itemEmail = itemEmail;
    }

    public String getItemDiachi() {
        return itemDiachi;
    }
}
