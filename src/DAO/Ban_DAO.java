package DAO;

import connectDB.ConnectDB;
import Entity.Ban;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Ban_DAO {

    // Constructor: đảm bảo kết nối cơ sở dữ liệu
    public Ban_DAO() {
        try {
            ConnectDB.getInstance().connect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // Lấy tất cả các bàn từ bảng Ban (không xét ngày)
    public List<Ban> getAllBan() {
        List<Ban> banList = new ArrayList<>();
        String sql = "SELECT MaBan, TrangThai, MaKhuVuc FROM Ban";
        
        try {
            Connection con = ConnectDB.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String maBan = rs.getString("MaBan");
                String trangThai = rs.getString("TrangThai");
                String maKhuVuc = rs.getString("MaKhuVuc");
                Ban ban = new Ban(maBan, trangThai, maKhuVuc);
                banList.add(ban);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return banList;
    }
    
    // Lấy danh sách bàn theo ngày được chọn.
    // Nếu có phiếu đặt cho bàn đó (trong bảng PhieuDatBan) và ngày được chọn nằm trong khoảng [ThoiGianDat, ThoiGianKetThuc],
    // thì trạng thái sẽ được hiển thị là "Đã đặt", ngược lại hiển thị giá trị hiện có của cột TrangThai.
    public List<Ban> getAllBanByDate(Date selectedDate) {
        List<Ban> banList = new ArrayList<>();
        String sql = "SELECT b.MaBan, " +
                     "       CASE WHEN EXISTS ( " +
                     "           SELECT 1 FROM PhieuDatBan p " +
                     "           WHERE p.MaBan = b.MaBan " +
                     "             AND CAST(? AS DATE) BETWEEN CAST(p.ThoiGianDat AS DATE) AND CAST(p.ThoiGianKetThuc AS DATE) " +
                     "       ) THEN N'Đã đặt' ELSE b.TrangThai END AS TrangThai, " +
                     "       b.MaKhuVuc " +
                     "FROM Ban b";
        try {
            Connection con = ConnectDB.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            // Chuyển selectedDate thành java.sql.Date để so sánh theo ngày
            java.sql.Date sqlDate = new java.sql.Date(selectedDate.getTime());
            ps.setDate(1, sqlDate);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String maBan = rs.getString("MaBan");
                String trangThai = rs.getString("TrangThai");
                String maKhuVuc = rs.getString("MaKhuVuc");
                Ban ban = new Ban(maBan, trangThai, maKhuVuc);
                banList.add(ban);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return banList;
    }
    
    // Thêm một bàn mới vào cơ sở dữ liệu
    public void addBan(Ban ban) {
        String sql = "INSERT INTO Ban (MaBan, TrangThai, MaKhuVuc) VALUES (?, ?, ?)";
        
        try {
            Connection con = ConnectDB.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, ban.getMaBan());
            ps.setString(2, ban.getTrangThai());
            ps.setString(3, ban.getMaKhuVuc());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // Cập nhật thông tin bàn đã tồn tại
    public void updateBan(Ban ban) {
        String sql = "UPDATE Ban SET TrangThai = ?, MaKhuVuc = ? WHERE MaBan = ?";
        
        try {
            Connection con = ConnectDB.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, ban.getTrangThai());
            ps.setString(2, ban.getMaKhuVuc());
            ps.setString(3, ban.getMaBan());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // Xóa bàn theo mã bàn
    public void deleteBan(String maBan) {
        String sql = "DELETE FROM Ban WHERE MaBan = ?";
        
        try {
            Connection con = ConnectDB.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, maBan);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
