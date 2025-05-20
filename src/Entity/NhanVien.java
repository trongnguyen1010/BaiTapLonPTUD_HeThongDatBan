package Entity;

public class NhanVien {
	private String maNhanVien;
	private String ten;
	private String email;
	private String chucVu;
	private String gioiTinh;
	private String sdt;

	// Constructors
	public NhanVien() {
	}

	public NhanVien(String maNhanVien) {
		this.maNhanVien = maNhanVien;
	}

	public NhanVien(String maNhanVien, String ten, String email, String chucVu, String gioiTinh, String sdt) {
		this.maNhanVien = maNhanVien;
		this.ten = ten;
		this.email = email;
		this.chucVu = chucVu;
		this.gioiTinh = gioiTinh;
		this.sdt = sdt;
	}

	// Getters and Setters
	public String getMaNhanVien() {
		return maNhanVien;
	}

	public void setMaNhanVien(String maNhanVien) {
		this.maNhanVien = maNhanVien;
	}

	public String getTen() {
		return ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getChucVu() {
		return chucVu;
	}

	public void setChucVu(String chucVu) {
		this.chucVu = chucVu;
	}

	public String getGioiTinh() {
		return gioiTinh;
	}

	public void setGioiTinh(String gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	public String getSdt() {
		return sdt;
	}

	public void setSdt(String sdt) {
		this.sdt = sdt;
	}
}
