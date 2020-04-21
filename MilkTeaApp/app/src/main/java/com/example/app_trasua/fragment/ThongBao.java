package com.example.app_trasua.fragment;

public class ThongBao {
    private String moTa;
    private String Ngay;
    private int hinhAnh;
    public ThongBao(String moTa,String Ngay,int hinhAnh) {
        this.moTa = moTa;
        this.Ngay=Ngay;
        this.hinhAnh = hinhAnh;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getNgay() {
        return Ngay;
    }

    public void setNgay(String ngay) {
        Ngay = ngay;
    }

    public int getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(int hinhAnh) {
        this.hinhAnh = hinhAnh;
    }
}
