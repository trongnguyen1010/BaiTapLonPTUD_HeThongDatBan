package DAO;

import java.sql.*;
import Entity.HoaDon;
import Entity.KhachHang;
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
    public boolean insert(HoaDon hoaDon) {
        Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;
        int n = 0;
        try {
            String sql = "INSERT INTO HoaDon (MaHoaDon, NgayLap, PhuongThuc, MaKhachHang, MaNhanVien) VALUES (?, ?, ?, ?, ?)";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, hoaDon.getMaHoaDon());
            stmt.setDate(2, Date.valueOf(hoaDon.getNgayLap())); // Chuyển đổi từ LocalDate
            stmt.setString(3, hoaDon.getPhuongThuc());
            stmt.setString(4, hoaDon.getMaKhachHang().getMaKhachHang());
            stmt.setString(5, hoaDon.getMaNhanVien().getMaNhanVien());

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

    // Cập nhật thông tin hóa đơn
    public boolean update(HoaDon hoaDon) {
        Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;
        int n = 0;
        try {
            String sql = "UPDATE HoaDon SET NgayLap = ?, PhuongThuc = ?, MaKhachHang = ?, MaNhanVien = ? WHERE MaHoaDon = ?";
            stmt = con.prepareStatement(sql);
            stmt.setDate(1, Date.valueOf(hoaDon.getNgayLap())); // Chuyển đổi từ LocalDate
            stmt.setString(2, hoaDon.getPhuongThuc());
            stmt.setString(3, hoaDon.getMaKhachHang().getMaKhachHang());
            stmt.setString(4, hoaDon.getMaNhanVien().getMaNhanVien());
            stmt.setString(5, hoaDon.getMaHoaDon());

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

                KhachHang kh = new KhachHang(maKH); // hoặc truyền thêm thông tin nếu cần
            	NhanVien nv = new NhanVien(maNV);   // tương tự

            	hoaDon = new HoaDon(maHD, ngayLap.toLocalDate(), phuongThuc, kh, nv);
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
}
