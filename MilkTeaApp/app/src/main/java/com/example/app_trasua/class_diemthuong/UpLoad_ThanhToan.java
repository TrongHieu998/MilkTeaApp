package com.example.app_trasua.class_diemthuong;

public class UpLoad_ThanhToan {
    public String itemHoTen;
    public String itemSDT;
    public String itemDiaChi;
    public String itemEmail;
    public String itemTen;
    public String itemGia;
    public String itemSoluong;
    public String itemThanhTien;
    public String itemTinhtrang;

    public UpLoad_ThanhToan() {
    }

    public UpLoad_ThanhToan(String itemHoTen, String itemSDT, String itemDiaChi,
                            String itemEmail, String itemTen, String itemGia,
                            String itemSoluong, String itemThanhTien, String itemTinhtrang) {
        this.itemHoTen = itemHoTen;
        this.itemSDT = itemSDT;
        this.itemDiaChi = itemDiaChi;
        this.itemEmail = itemEmail;
        this.itemTen = itemTen;
        this.itemGia = itemGia;
        this.itemSoluong = itemSoluong;
        this.itemThanhTien = itemThanhTien;
        this.itemTinhtrang = itemTinhtrang;
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

    public String getItemTinhtrang() {
        return itemTinhtrang;
    }

    public void setItemTinhtrang(String itemTinhtrang) {
        this.itemTinhtrang = itemTinhtrang;
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

    public String getItemDiaChi() {
        return itemDiaChi;
    }

    public void setItemDiaChi(String itemDiaChi) {
        this.itemDiaChi = itemDiaChi;
    }

    public String getItemEmail() {
        return itemEmail;
    }

    public void setItemEmail(String itemEmail) {
        this.itemEmail = itemEmail;
    }

    public String getItemThanhTien() {
        return itemThanhTien;
    }

    public void setItemThanhTien(String itemThanhTien) {
        this.itemThanhTien = itemThanhTien;
    }
}
