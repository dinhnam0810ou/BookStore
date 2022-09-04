package com.example.bookstore.model;

public class GioHang {
    private String tenSach;
    private String giaSach;
    private String hinhSach;
    private int soLuong;

    public GioHang(String tenSach, String giaSach, String hinhSach, int soLuong) {
        this.tenSach = tenSach;
        this.giaSach = giaSach;
        this.hinhSach = hinhSach;
        this.soLuong = soLuong;
    }

    public GioHang() {
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public String getGiaSach() {
        return giaSach;
    }

    public void setGiaSach(String giaSach) {
        this.giaSach = giaSach;
    }

    public String getHinhSach() {
        return hinhSach;
    }

    public void setHinhSach(String hinhSach) {
        this.hinhSach = hinhSach;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
}
