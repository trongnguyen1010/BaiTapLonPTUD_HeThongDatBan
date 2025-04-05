package DAO;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import connectDB.ConnectDB;
import Entity.TaiKhoan;

public class TaiKhoan_DAO {
    private Connection conn;

    public TaiKhoan_DAO() {
        conn = ConnectDB.getConnection();
    }

    public TaiKhoan getTaiKhoanTheoTen(String tenDangNhap) {
        TaiKhoan tk = null;
        try {
            String sql = "SELECT * FROM TaiKhoan WHERE TenDangNhap = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, tenDangNhap);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                tk = new TaiKhoan(rs.getString("TenDangNhap"), rs.getString("MatKhau"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tk;
    }
}
