package DAO;

import connectDB.ConnectDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * DAO xử lý các thao tác gọi món: tạo phiếu gọi và thêm chi tiết gọi món.
 */
public class GoiMonDAO {

    /**
     * Tạo một phiếu gọi món mới cho bàn.
     * @param maBan Mã bàn được gọi
     * @return maPhieu vừa tạo (GM + timestamp), hoặc null nếu thất bại
     */
    public String createOrder(String maBan) {
        String maPhieu = "GM" + System.currentTimeMillis();
        String sql = "INSERT INTO PhieuGoiMon (MaPhieu, MaBan, NgayGoi) VALUES (?, ?, GETDATE())";

        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = ConnectDB.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, maPhieu);
            ps.setString(2, maBan);

            int affected = ps.executeUpdate();
            if (affected == 1) {
                return maPhieu;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            try { if (ps  != null) ps.close();  } catch (SQLException ignored) {}
            // Không đóng connection ở đây nếu ConnectDB quản lý pooling
        }
    }

    /**
     * Thêm một dòng chi tiết gọi món vào phiếu.
     * @param maPhieu  Mã phiếu gọi món (từ createOrder)
     * @param maMon    Mã món ăn
     * @param qty      Số lượng
     * @return true nếu thành công, false nếu lỗi
     */
    public boolean addDetail(String maPhieu, String maMon, int qty) {
        String sql = "INSERT INTO ChiTietGoiMon (MaPhieu, MaMonAn, SoLuong) VALUES (?, ?, ?)";

        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = ConnectDB.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, maPhieu);
            ps.setString(2, maMon);
            ps.setInt   (3, qty);

            int affected = ps.executeUpdate();
            return affected == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try { if (ps  != null) ps.close();  } catch (SQLException ignored) {}
        }
    }
}
