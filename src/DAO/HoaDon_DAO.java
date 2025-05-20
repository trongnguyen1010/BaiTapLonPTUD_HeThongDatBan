
package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Entity.HoaDon;

import Entity.KhachHang;
import Entity.MonAn;
import Entity.NhanVien;
import connectDB.ConnectDB;

public class HoaDon_DAO {

	public HoaDon_DAO() {
		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}



	// Thêm hóa đơn mới
	public static boolean insert(HoaDon hoaDon, List<MonAn> dsMonAnTrongHoaDon) {
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			String sql = "INSERT INTO HoaDon (MaHoaDon, NgayLap, PhuongThuc, MaKhachHang, MaNhanVien,ThanhTien) VALUES (?, ?, ?, ?, ?,?)";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, hoaDon.getMaHoaDon());
			stmt.setDate(2, Date.valueOf(hoaDon.getNgayLap())); // Chuyển đổi từ LocalDate
			stmt.setString(3, hoaDon.getPhuongThuc());
			stmt.setString(4, hoaDon.getMaKhachHang().getMaKhachHang());
			stmt.setString(5, hoaDon.getMaNhanVien().getMaNhanVien());
	        stmt.setDouble(6, hoaDon.getThanhTien());

			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return n > 0;
	}

	// Cập nhật thông tin hóa đơn
	public boolean update(HoaDon hoaDon) {
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			String sql = "UPDATE HoaDon SET NgayLap = ?, PhuongThuc = ?, MaKhachHang = ?, MaNhanVien = ? ,ThanhTien = ? WHERE MaHoaDon = ?";
			stmt = con.prepareStatement(sql);
			stmt.setDate(1, Date.valueOf(hoaDon.getNgayLap())); // Chuyển đổi từ LocalDate
			stmt.setString(2, hoaDon.getPhuongThuc());
			stmt.setString(3, hoaDon.getMaKhachHang().getMaKhachHang());
			stmt.setString(4, hoaDon.getMaNhanVien().getMaNhanVien());
			stmt.setDouble(5, hoaDon.getThanhTien());
			stmt.setString(6, hoaDon.getMaHoaDon());

			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return n > 0;
	}

	// Lấy thông tin hóa đơn
	public HoaDon getHoaDon(String maHoaDon) {
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		HoaDon hoaDon = null;
		try {
			String sql = "SELECT MaHoaDon, NgayLap, PhuongThuc, MaKhachHang, MaNhanVien FROM HoaDon WHERE MaHoaDon = ?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, maHoaDon);
			rs = stmt.executeQuery();

			if (rs.next()) {
				String maHD = rs.getString("MaHoaDon");
				Date ngayLap = rs.getDate("NgayLap");
				String phuongThuc = rs.getString("PhuongThuc");
				String maKH = rs.getString("MaKhachHang");
				String maNV = rs.getString("MaNhanVien");
				double thanhTien = rs.getDouble("ThanhTien");

				KhachHang kh = new KhachHang(maKH); // hoặc truyền thêm thông tin nếu cần
				NhanVien nv = new NhanVien(maNV); // tương tự

				hoaDon = new HoaDon(maHD, ngayLap.toLocalDate(), phuongThuc, kh, nv,thanhTien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return hoaDon;
	}

	// Xóa hóa đơn
	public boolean delete(String maHoaDon) {
        Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;
        int n = 0;
        try {
            String sql = "DELETE FROM HoaDon WHERE MaHoaDon = ?";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, maHoaDon);
            n = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return n > 0;
    }
	//
	public List<HoaDon> getAllHoaDon() {
	    Connection con = ConnectDB.getConnection();
	    PreparedStatement stmt = null;
	    ResultSet rs = null;
	    List<HoaDon> danhSachHoaDon = new ArrayList<>();

	    try {
	    	String sql = "SELECT hd.MaHoaDon, hd.NgayLap, hd.PhuongThuc, hd.ThanhTien, " +
	                "kh.MaKhachHang, kh.TenKhachHang, " +
	                "nv.MaNhanVien, nv.TenNhanVien " +
	                "FROM HoaDon hd " +
	                "JOIN KhachHang kh ON hd.MaKhachHang = kh.MaKhachHang " +
	                "JOIN NhanVien nv ON hd.MaNhanVien = nv.MaNhanVien";


	        stmt = con.prepareStatement(sql);
	        rs = stmt.executeQuery();

	        while (rs.next()) {
	            String maHD = rs.getString("MaHoaDon");
	            Date ngayLap = rs.getDate("NgayLap");
	            String phuongThuc = rs.getString("PhuongThuc");
	            double thanhTien = rs.getDouble("ThanhTien");
	            String maKH = rs.getString("MaKhachHang");
	            String tenKH = rs.getString("TenKhachHang");

	            String maNV = rs.getString("MaNhanVien");
	            String tenNV = rs.getString("TenNhanVien");

	            // Tạo đối tượng KhachHang đầy đủ thông tin
	            KhachHang kh = new KhachHang();
	            kh.setMaKhachHang(maKH);
	            kh.setTenKhachHang(tenKH);

	            // Tạo đối tượng NhanVien đầy đủ thông tin
	            NhanVien nv = new NhanVien();
	            nv.setMaNhanVien(maNV);
	            nv.setTen(tenNV);

	            // Tạo đối tượng HoaDon
	            HoaDon hoaDon = new HoaDon(maHD, ngayLap.toLocalDate(), phuongThuc, kh, nv,thanhTien);
	            danhSachHoaDon.add(hoaDon);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (stmt != null) stmt.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    return danhSachHoaDon;
	}
	

}
