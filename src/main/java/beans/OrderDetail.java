package beans;

import java.io.Serializable;

public class OrderDetail implements Serializable {
    private int id;
    private int idDonHang;
    private int idSanPham;
    private int soLuong;
    private double donGia;

    public OrderDetail() {
    }

    public OrderDetail(int id, int idDonHang, int idSanPham, int soLuong, double donGia) {
        this.id = id;
        this.idDonHang = idDonHang;
        this.idSanPham = idSanPham;
        this.soLuong = soLuong;
        this.donGia = donGia;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getIdDonHang() {
        return idDonHang;
    }
    public void setIdDonHang(int idDonHang) {
        this.idDonHang = idDonHang;
    }

    public int getIdSanPham() {
        return idSanPham;
    }
    public void setIdSanPham(int idSanPham) {
        this.idSanPham = idSanPham;
    }

    public int getSoLuong() {
        return soLuong;
    }
    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getDonGia() {
        return donGia;
    }
    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }
}
