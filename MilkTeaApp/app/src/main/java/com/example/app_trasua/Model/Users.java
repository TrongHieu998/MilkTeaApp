package com.example.app_trasua.Model;

public class Users {
    String Ten,SDT,MatKhau,Img,Diachi,Email;

    public Users() {

    }

    public Users(String ten, String SDT, String matKhau, String img, String diachi, String email) {
        Ten = ten;
        this.SDT = SDT;
        MatKhau = matKhau;
        Img = img;
        Diachi = diachi;
        Email = email;
    }

    public Users(String ten, String SDT, String matKhau) {
        Ten = ten;
        this.SDT = SDT;
        MatKhau = matKhau;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getImg() {
        return Img;
    }

    public void setImg(String img) {
        Img = img;
    }

    public String getDiachi() {
        return Diachi;
    }

    public void setDiachi(String diachi) {
        Diachi = diachi;
    }

    public String getTen() {
        return Ten;
    }

    public void setTen(String ten) {
        Ten = ten;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getMatKhau() {
        return MatKhau;
    }

    public void setMatKhau(String matKhau) {
        MatKhau = matKhau;
    }
}
