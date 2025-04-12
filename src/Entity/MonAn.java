package Entity;

import java.awt.Color;
import java.util.Objects;

public class MonAn {
  private String maMonAn;
  private String tenMonAn;
  private double donGia;
  private int soLuong;
  private byte[] hinhAnh;
  private int maloai;
 //
  
  public MonAn(String maMonAn, String tenMonAn, double donGia, int soLuong,byte[] hinhAnh ) {
	super();
	this.maMonAn = maMonAn;
	this.tenMonAn = tenMonAn;
	this.donGia = donGia;
	this.soLuong = soLuong;
	 this.hinhAnh = hinhAnh;
	 //
}
  

public MonAn() {
	super();
	// TODO Auto-generated constructor stub
}

public String getMaMonAn() {
	return maMonAn;
}
public void setMaMonAn(String maMonAn) {
	this.maMonAn = maMonAn;
}
public String getTenMonAn() {
	return tenMonAn;
}
public void setTenMonAn(String tenMonAn) {
	this.tenMonAn = tenMonAn;
}
public double getDonGia() {
	return donGia;
}
public void setDonGia(double donGia) {
	this.donGia = donGia;
}
public int getSoLuong() {
	return soLuong;
}
public void setSoLuong(int soLuong) {
	this.soLuong = soLuong;
}
@Override
public int hashCode() {
	return Objects.hash(maMonAn);
}
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	MonAn other = (MonAn) obj;
	return Objects.equals(maMonAn, other.maMonAn);
}

private String duongDanAnh;

public String getDuongDanAnh() {
    return duongDanAnh;
}

public void setDuongDanAnh(String duongDanAnh) {
    this.duongDanAnh = duongDanAnh;
}
public byte[] gethinhAnh() {
	return hinhAnh;
}


public void sethinhAnh(byte[] hinhAnh) {
	this.hinhAnh = hinhAnh;
}


public int getMaloai() {
	return maloai;
}

public void setMaloai(int maloai) {
	this.maloai = maloai;
}




  
  
}
