package Entity;

public class TaiKhoan {
	private String maTaiKhoan;
	private NhanVien nhanVien;
	private String tenDangNhap;
	private String matKhau;

	// Constructors
	public TaiKhoan() {
	}

	public TaiKhoan(String maTaiKhoan) {
		this.maTaiKhoan = maTaiKhoan;
	}

	public TaiKhoan(String maTaiKhoan, NhanVien nhanVien, String tenDangNhap, String matKhau) {
		this.maTaiKhoan = maTaiKhoan;
		this.nhanVien = nhanVien;
		this.tenDangNhap = tenDangNhap;
		this.matKhau = matKhau;
	}

	// Getters and Setters
	public String getMaTaiKhoan() {
		return maTaiKhoan;
	}

	public void setMaTaiKhoan(String maTaiKhoan) {
		this.maTaiKhoan = maTaiKhoan;
	}

	public NhanVien getNhanVien() {
		return nhanVien;
	}

	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}

	public String getTenDangNhap() {
		return tenDangNhap;
	}

	public void setTenDangNhap(String tenDangNhap) {
		this.tenDangNhap = tenDangNhap;
	}

	public String getMatKhau() {
		return matKhau;
	}

	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}
}
