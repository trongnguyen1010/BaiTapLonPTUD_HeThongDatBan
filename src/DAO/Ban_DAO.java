package DAO;

//import java.security.Timestamp;
import java.sql.Timestamp; // ✅ DÙNG CÁI NÀY
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Entity.Ban;
import connectDB.ConnectDB;

public class Ban_DAO {

    // Constructor: đảm bảo kết nối cơ sở dữ liệu
    public Ban_DAO() {
        try {
            ConnectDB.getInstance().connect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Kiểm tra xem bàn có bị đặt trong khoảng thời gian mới
     * @param maBan Mã bàn cần kiểm tra
     * @param ngayDat Ngày đặt (không bao gồm giờ)
     * @param startTime Thời gian bắt đầu đặt (ví dụ "10:00")
     * @param endTime Thời gian kết thúc đặt (ví dụ "12:00")
     * @return true nếu bàn đã có phiếu đặt trong khoảng thời gian đó, false nếu không.
     */
    public boolean isTableBooked(String maBan, Date ngayDat, String startTime, String endTime) {
        boolean booked = false;
        Connection con = ConnectDB.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            // Tạo timestamp kết hợp ngày đặt và giờ
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String dateStr = sdf.format(ngayDat);
            // Giả sử startTime và endTime ở định dạng "HH:mm"
            Timestamp bookingStart = Timestamp.valueOf(dateStr + " " + startTime + ":00");
            Timestamp bookingEnd   = Timestamp.valueOf(dateStr + " " + endTime + ":00");
            
            // Kiểm tra xem có phiếu đặt nào giao thời gian không.
            // Điều kiện giao nhau: existing.ThoiGianDat < newBookingEnd AND existing.ThoiGianKetThuc > newBookingStart
            String sql = "SELECT COUNT(*) FROM PhieuDatBan " +
                         "WHERE MaBan = ? " +
                         "  AND (ThoiGianDat < ? AND ThoiGianKetThuc > ?)";
            ps = con.prepareStatement(sql);
            ps.setString(1, maBan);
            ps.setTimestamp(2, bookingEnd);
            ps.setTimestamp(3, bookingStart);
            rs = ps.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                if (count > 0) {
                    booked = true;
                }
            }
        } catch (SQLException | IllegalArgumentException e) {
            e.printStackTrace();
        } finally {
            try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if (ps != null) ps.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
        return booked;
    }
    
    /**
     * Lưu phiếu đặt bàn mới vào CSDL.
     * @param maBan Mã bàn được đặt
     * @param ngayDat Ngày đặt (không bao gồm giờ)
     * @param startTime Thời gian bắt đầu đặt (ví dụ "10:00")
     * @param endTime Thời gian kết thúc đặt (ví dụ "12:00")
     * @param sdt SĐT khách hàng (hoặc mã khách hàng) để liên kết thông tin đặt bàn
     * @return true nếu đặt bàn thành công, false nếu không.
     */
    public boolean datBan(String maBan, Date ngayDat, String startTime, String endTime, String sdt) {
        boolean success = false;
        Connection con = ConnectDB.getConnection();
        PreparedStatement ps = null;
        try {
            // Tạo mã phiếu đặt bàn tự sinh (ví dụ: PD + current time millis)
            String maPhieu = "PD" + System.currentTimeMillis();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String dateStr = sdf.format(ngayDat);
            Timestamp bookingStart = Timestamp.valueOf(dateStr + " " + startTime + ":00");
            Timestamp bookingEnd   = Timestamp.valueOf(dateStr + " " + endTime + ":00");
            
            String sql = "INSERT INTO PhieuDatBan (MaPhieu, ThoiGianDat, ThoiGianKetThuc, MaBan, MaKhachHang) " +
                         "VALUES (?, ?, ?, ?, ?)";
            ps = con.prepareStatement(sql);
            ps.setString(1, maPhieu);
            ps.setTimestamp(2, bookingStart);
            ps.setTimestamp(3, bookingEnd);
            ps.setString(4, maBan);
            ps.setString(5, sdt);
            
            int n = ps.executeUpdate();
            if (n > 0) {
                success = true;
            }
        } catch (SQLException | IllegalArgumentException e) {
            e.printStackTrace();
        } finally {
            try { if (ps != null) ps.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
        return success;
    }
    
    /**
     * Lấy danh sách bàn theo tiêu chí:
     * - Số chỗ tối thiểu (soCho)
     * - Khu vực (khuVuc): nếu bằng "All" thì không lọc theo khu vực
     * - Ngày đặt (ngayDat): Bàn không có phiếu đặt nào trong ngày này.
     */
    public List<Ban> getBanTheoTieuChi(String khuVuc, int soCho, Date ngayDat) {
        List<Ban> banList = new ArrayList<>();
        // Xây dựng câu truy vấn SQL động
        // Lấy thông tin cột: MaBan, TrangThai, SoChoNgoi, MaKhuVuc từ bảng Ban
        // Giả sử: nếu có phiếu đặt nào mà ngày đặt (các trường ThoiGianDat và ThoiGianKetThuc) bao phủ ngày hiện tại,
        // thì bàn đó không khả dụng. (Chúng ta dùng NOT EXISTS để loại trừ.)
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT b.MaBan, b.TrangThai, b.SoChoNgoi, b.MaKhuVuc ");
        sql.append("FROM Ban b ");
        sql.append("WHERE b.SoChoNgoi >= ? ");
        // Loại trừ bàn đã đặt trong ngày (dựa trên bảng PhieuDatBan):
        sql.append("AND NOT EXISTS ( ");
        sql.append("    SELECT 1 FROM PhieuDatBan pdb ");
        sql.append("    WHERE pdb.MaBan = b.MaBan ");
        sql.append("      AND CAST(pdb.ThoiGianDat AS DATE) <= ? ");
        sql.append("      AND CAST(pdb.ThoiGianKetThuc AS DATE) >= ? ");
        sql.append(") ");
        if (!"All".equalsIgnoreCase(khuVuc)) {
            sql.append("AND b.MaKhuVuc = ? ");
        }
        
        try {
            Connection con = ConnectDB.getConnection();
            PreparedStatement ps = con.prepareStatement(sql.toString());
            // Thiết lập tham số
            // 1. Số chỗ tối thiểu
            ps.setInt(1, soCho);
            // 2. Ngày đặt: truyền cùng giá trị cho tham số kiểm tra ngày bắt đầu và kết thúc
            java.sql.Date sqlDate = new java.sql.Date(ngayDat.getTime());
            ps.setDate(2, sqlDate);
            ps.setDate(3, sqlDate);
            // 3. Nếu khuVuc không phải "All", thiết lập tham số khuVuc
            if (!"All".equalsIgnoreCase(khuVuc)) {
                ps.setString(4, khuVuc);
            }
            
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String maBan = rs.getString("MaBan");
                String trangThai = rs.getString("TrangThai");
                // Nếu cần, bạn có thể lấy thêm soChoNgoi và maKhuVuc để dựng đối tượng Ban
                int soChoNgoi = rs.getInt("SoChoNgoi");
                String maKhuVuc = rs.getString("MaKhuVuc");
                Ban ban = new Ban(maBan, trangThai, maKhuVuc);
                // Nếu bạn có setter cho soChoNgoi, bạn có thể set nó ở đây.
                banList.add(ban);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return banList;
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
