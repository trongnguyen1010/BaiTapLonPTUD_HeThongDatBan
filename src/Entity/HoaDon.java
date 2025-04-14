package Entity;

import java.time.LocalDate;

public class HoaDon {
	private String maHoaDon;
	private LocalDate ngayLap;
	private String phuongThuc;
	private KhachHang maKhachHang;
	private NhanVien maNhanVien;

	// Constructor, getters, setters
	public HoaDon(String maHoaDon, LocalDate ngayLap, String phuongThuc, KhachHang maKhachHang, NhanVien maNhanVien) {
		this.maHoaDon = maHoaDon;
		this.ngayLap = ngayLap;
		this.phuongThuc = phuongThuc;
		this.maKhachHang = maKhachHang;
		this.maNhanVien = maNhanVien;
	}

	public String getMaHoaDon() {
		return maHoaDon;
	}

	public void setMaHoaDon(String maHoaDon) {
		this.maHoaDon = maHoaDon;
	}

	public LocalDate getNgayLap() {
		return ngayLap;
	}

	public void setNgayLap(LocalDate ngayLap) {
		this.ngayLap = ngayLap;
	}

	public String getPhuongThuc() {
		return phuongThuc;
	}

	public void setPhuongThuc(String phuongThuc) {
		this.phuongThuc = phuongThuc;
	}

	public KhachHang getMaKhachHang() {
		return maKhachHang;
	}

	public void setMaKhachHang(KhachHang maKhachHang) {
		this.maKhachHang = maKhachHang;
	}

	public NhanVien getMaNhanVien() {
		return maNhanVien;
	}

	public void setMaNhanVien(NhanVien maNhanVien) {
		this.maNhanVien = maNhanVien;
	}
}
