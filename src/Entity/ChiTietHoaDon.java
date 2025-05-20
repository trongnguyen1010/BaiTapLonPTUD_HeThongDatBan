package Entity;

public class ChiTietHoaDon {
	private HoaDon maHoaDon;
	private MonAn maMonAn;
	private int soLuong;
	private String ghiChu;

	// Constructor, getters, setters
	public ChiTietHoaDon(HoaDon maHoaDon, MonAn maMonAn, int soLuong, String ghiChu) {
		this.maHoaDon = maHoaDon;
		this.maMonAn = maMonAn;
		this.soLuong = soLuong;
		this.ghiChu = ghiChu;
	}

	public HoaDon getMaHoaDon() {
		return maHoaDon;
	}

	public void setMaHoaDon(HoaDon maHoaDon) {
		this.maHoaDon = maHoaDon;
	}

	public MonAn getMaMonAn() {
		return maMonAn;
	}

	public void setMaMonAn(MonAn maMonAn) {
		this.maMonAn = maMonAn;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	public String getGhiChu() {
		return ghiChu;
	}

	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}
}
