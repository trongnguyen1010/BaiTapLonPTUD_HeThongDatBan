package DAO;

import connectDB.ConnectDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ChiTietPhieuDatBan_DAO {
    public boolean addChiTiet(String maPhieu, String maMonAn, int soLuong) {
        String sql = "INSERT INTO ChiTietPhieuDatBan (MaPhieu, MaMonAn, SoLuong) VALUES (?, ?, ?)";
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, maPhieu);
            ps.setString(2, maMonAn);
            ps.setInt(3, soLuong);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
