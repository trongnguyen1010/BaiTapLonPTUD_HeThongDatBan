package Entity;

public class TaiKhoan {
    private String tenDangNhap;
    private String matKhau;
    private String vaiTro;
    private String maNhanVien;

    public TaiKhoan(String tenDangNhap, String matKhau, String vaiTro, String maNhanVien) {
        this.tenDangNhap = tenDangNhap;
        this.matKhau = matKhau;
        this.vaiTro = vaiTro;
        this.maNhanVien = maNhanVien;
    }

    public String getTenDangNhap() { return tenDangNhap; }
    public String getMatKhau() { return matKhau; }
    public String getVaiTro() { return vaiTro; }
    public String getMaNhanVien() { return maNhanVien; }
}
