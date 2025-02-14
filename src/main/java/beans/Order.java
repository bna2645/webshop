package beans;

import java.io.Serializable;
import java.util.Date;

public class Order implements Serializable {
    private int id;
    private int idKhachHang;
    private Date ngayDat;
    private double tongTien;

    public Order() {
    }

    public Order(int id, int idKhachHang, Date ngayDat, double tongTien) {
        this.id = id;
        this.idKhachHang = idKhachHang;
        this.ngayDat = ngayDat;
        this.tongTien = tongTien;
    }

    // Getters & Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdKhachHang() {
        return idKhachHang;
    }

    public void setIdKhachHang(int idKhachHang) {
        this.idKhachHang = idKhachHang;
    }

    public Date getNgayDat() {
        return ngayDat;
    }

    public void setNgayDat(Date ngayDat) {
        this.ngayDat = ngayDat;
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }
}
