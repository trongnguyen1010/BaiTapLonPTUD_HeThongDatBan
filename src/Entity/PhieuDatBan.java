package Entity;

import java.util.Date;

public class PhieuDatBan {
    private String maPhieu;
    private Date thoiGianDat;
    private Date thoiGianKetThuc;
    private String maBan;
    private String maKhachHang;

    // Constructor mặc định
    public PhieuDatBan() {
    }

    // Constructor đầy đủ
    public PhieuDatBan(String maPhieu, Date thoiGianDat, Date thoiGianKetThuc, String maBan, String maKhachHang) {
        this.maPhieu = maPhieu;
        this.thoiGianDat = thoiGianDat;
        this.thoiGianKetThuc = thoiGianKetThuc;
        this.maBan = maBan;
        this.maKhachHang = maKhachHang;
    }

    // Thêm constructor nhận ba tham số (maPhieu, thoiGianDat, thoiGianKetThuc)
    public PhieuDatBan(String maPhieu, java.sql.Timestamp thoiGianDat, java.sql.Timestamp thoiGianKetThuc) {
        this.maPhieu = maPhieu;
        // Chuyển Timestamp thành Date nếu cần:
        this.thoiGianDat = new Date(thoiGianDat.getTime());
        this.thoiGianKetThuc = new Date(thoiGianKetThuc.getTime());
        this.maBan = null;
        this.maKhachHang = null;
    }
    
    // Getters và Setters
    public String getMaPhieu() {
        return maPhieu;
    }

    public void setMaPhieu(String maPhieu) {
        this.maPhieu = maPhieu;
    }

    public Date getThoiGianDat() {
        return thoiGianDat;
    }

    public void setThoiGianDat(Date thoiGianDat) {
        this.thoiGianDat = thoiGianDat;
    }

    public Date getThoiGianKetThuc() {
        return thoiGianKetThuc;
    }

    public void setThoiGianKetThuc(Date thoiGianKetThuc) {
        this.thoiGianKetThuc = thoiGianKetThuc;
    }

    public String getMaBan() {
        return maBan;
    }

    public void setMaBan(String maBan) {
        this.maBan = maBan;
    }

    public String getMaKhachHang() {
        return maKhachHang;
    }

    public void setMaKhachHang(String maKhachHang) {
        this.maKhachHang = maKhachHang;
    }

    @Override
    public String toString() {
        return "PhieuDatBan{" +
                "maPhieu='" + maPhieu + '\'' +
                ", thoiGianDat=" + thoiGianDat +
                ", thoiGianKetThuc=" + thoiGianKetThuc +
                ", maBan='" + maBan + '\'' +
                ", maKhachHang='" + maKhachHang + '\'' +
                '}';
    }
}
