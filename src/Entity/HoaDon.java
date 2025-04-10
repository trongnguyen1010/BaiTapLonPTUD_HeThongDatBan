package Entity;



import java.sql.Date;

public class HoaDon {
    private String maHoaDon;
    private Date ngayLap;
    private String phuongThuc;
   // private NhanVien nhanVien;
    private KhachHang khachHang;
    private double tongTien;

    public HoaDon() {
    }

    public HoaDon(String maHoaDon, Date ngayLap, String phuongThuc /*,NhanVien nhanVien*/, KhachHang khachHang, double tongTien) {
        this.maHoaDon = maHoaDon;
        this.ngayLap = ngayLap;
        this.phuongThuc = phuongThuc;
        //this.nhanVien = nhanVien;
        this.khachHang = khachHang;
        this.tongTien = tongTien;
    }

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public Date getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(Date ngayLap) {
        this.ngayLap = ngayLap;
    }//

    public String getPhuongThuc() {
        return phuongThuc;
    }

    public void setPhuongThuc(String phuongThuc) {
        this.phuongThuc = phuongThuc;
    }

//    public NhanVien getNhanVien() {
//        return nhanVien;
//    }
//
//    public void setNhanVien(NhanVien nhanVien) {
//        this.nhanVien = nhanVien;
//    }

    public KhachHang getKhachHang() {
        return khachHang;
    }

    public void setKhachHang(KhachHang khachHang) {
        this.khachHang = khachHang;
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }

 
}
