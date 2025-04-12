package DAO;

import java.sql.*;
import Entity.ChiTietHoaDon;
import Entity.HoaDon;
import Entity.MonAn;
import connectDB.ConnectDB;

public class ChiTietHoaDon_DAO {

    // Thêm món ăn vào chi tiết hóa đơn
    public boolean insert(ChiTietHoaDon chiTiet) {
        Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;
        int n = 0;
        try {
            String sql = "INSERT INTO ChiTietHoaDon (MaHoaDon, MaMonAn, SoLuong, GhiChu) VALUES (?, ?, ?, ?)";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, chiTiet.getMaHoaDon().getMaHoaDon());
            stmt.setString(2, chiTiet.getMaMonAn().getMaMonAn());
            stmt.setInt(3, chiTiet.getSoLuong());
            stmt.setString(4, chiTiet.getGhiChu());

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

    // Xóa món ăn khỏi chi tiết hóa đơn
    public boolean delete(String maHoaDon, String maMonAn) {
        Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;
        int n = 0;
        try {
            String sql = "DELETE FROM ChiTietHoaDon WHERE MaHoaDon = ? AND MaMonAn = ?";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, maHoaDon);
            stmt.setString(2, maMonAn);
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

    // Lấy chi tiết hóa đơn theo mã hóa đơn
    public ResultSet getChiTietHoaDon(String maHoaDon) {
        Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT MaMonAn, SoLuong, GhiChu FROM ChiTietHoaDon WHERE MaHoaDon = ?";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, maHoaDon);
            rs = stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }
}
