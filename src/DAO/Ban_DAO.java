package DAO;

import connectDB.ConnectDB;
import Entity.Ban;
import Entity.PhieuDatBan;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Ban_DAO {

    public Ban_DAO() {
        try {
            ConnectDB.getInstance().connect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Kiểm tra xem bàn có bị đặt trong khoảng thời gian không
    public boolean isTableBooked(String maBan, Date ngayDat, String startTime, String endTime) {
        boolean booked = false;
        Connection con = ConnectDB.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String dateStr = sdf.format(ngayDat);
            Timestamp bookingStart = Timestamp.valueOf(dateStr + " " + startTime + ":00");
            Timestamp bookingEnd = Timestamp.valueOf(dateStr + " " + endTime + ":00");

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
                booked = count > 0;
            }
        } catch (SQLException | IllegalArgumentException e) {
            e.printStackTrace();
        } finally {
            try { if (rs != null) rs.close(); } catch (SQLException ex) { ex.printStackTrace(); }
            try { if (ps != null) ps.close(); } catch (SQLException ex) { ex.printStackTrace(); }
        }
        return booked;
    }

 // Đặt bàn, trả về mã phiếu nếu thành công, ngược lại trả về null
    public String datBan(String maBan, Date ngayDat, String startTime, String endTime, String maKhachHang) {
        // Tạo mã phiếu duy nhất
        String maPhieu = "PD" + System.currentTimeMillis();
        // Chuẩn bị ngày-thời gian
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = sdf.format(ngayDat);
        Timestamp bookingStart = Timestamp.valueOf(dateStr + " " + startTime + ":00");
        Timestamp bookingEnd   = Timestamp.valueOf(dateStr + " " + endTime   + ":00");

        String sql = "INSERT INTO PhieuDatBan (MaPhieu, ThoiGianDat, ThoiGianKetThuc, MaBan, MaKhachHang) "
                   + "VALUES (?, ?, ?, ?, ?)";

        try (Connection con = ConnectDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, maPhieu);
            ps.setTimestamp(2, bookingStart);
            ps.setTimestamp(3, bookingEnd);
            ps.setString(4, maBan);
            ps.setString(5, maKhachHang);

            int rows = ps.executeUpdate();
            if (rows > 0) {
                return maPhieu;   // chèn thành công, trả về mã phiếu
            } else {
                return null;      // chèn không thành công
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    
    // Lấy danh sách bàn theo tiêu chí: khu vực, số chỗ và bàn không bị đặt trong ngày (theo ngày)
    public List<Ban> getBanTheoTieuChi(String khuVuc, int soCho, Date ngayDat) {
        List<Ban> banList = new ArrayList<>();
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT b.MaBan, b.TrangThai, b.SoChoNgoi, b.MaKhuVuc ");
        sql.append("FROM Ban b ");
        sql.append("WHERE b.SoChoNgoi >= ? ");
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
            ps.setInt(1, soCho);
            java.sql.Date sqlDate = new java.sql.Date(ngayDat.getTime());
            ps.setDate(2, sqlDate);
            ps.setDate(3, sqlDate);
            if (!"All".equalsIgnoreCase(khuVuc)) {
                ps.setString(4, khuVuc);
            }

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String maBan = rs.getString("MaBan");
                String trangThai = rs.getString("TrangThai");
                int soChoNgoi = rs.getInt("SoChoNgoi");
                String maKhuVuc = rs.getString("MaKhuVuc");
                Ban ban = new Ban(maBan, trangThai, maKhuVuc, soChoNgoi);
                banList.add(ban);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return banList;
    }

    // Lấy tất cả các bàn
    public List<Ban> getAllBan() {
        List<Ban> banList = new ArrayList<>();
        String sql = "SELECT MaBan, TrangThai, MaKhuVuc, SoChoNgoi FROM Ban";
        try {
            Connection con = ConnectDB.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String maBan = rs.getString("MaBan");
                String trangThai = rs.getString("TrangThai");
                String maKhuVuc = rs.getString("MaKhuVuc");
                int soChoNgoi = rs.getInt("SoChoNgoi");
                Ban ban = new Ban(maBan, trangThai, maKhuVuc, soChoNgoi);
                banList.add(ban);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return banList;
    }

    // Lấy danh sách bàn theo ngày hiển thị (đã cập nhật trạng thái bàn theo lịch đặt)
    public List<Ban> getAllBanByDate(Date selectedDate) {
        List<Ban> banList = new ArrayList<>();
        String sql = "SELECT b.MaBan, " +
                     "       CASE WHEN EXISTS ( " +
                     "           SELECT 1 FROM PhieuDatBan p " +
                     "           WHERE p.MaBan = b.MaBan " +
                     "             AND CAST(? AS DATE) BETWEEN CAST(p.ThoiGianDat AS DATE) AND CAST(p.ThoiGianKetThuc AS DATE) " +
                     "       ) THEN N'Đã đặt' ELSE b.TrangThai END AS TrangThai, " +
                     "       b.MaKhuVuc, b.SoChoNgoi " +
                     "FROM Ban b";
        try {
            Connection con = ConnectDB.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            java.sql.Date sqlDate = new java.sql.Date(selectedDate.getTime());
            ps.setDate(1, sqlDate);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String maBan = rs.getString("MaBan");
                String trangThai = rs.getString("TrangThai");
                String maKhuVuc = rs.getString("MaKhuVuc");
                int soChoNgoi = rs.getInt("SoChoNgoi");
                Ban ban = new Ban(maBan, trangThai, maKhuVuc, soChoNgoi);
                banList.add(ban);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return banList;
    }
    
 // Trong Ban_DAO:
    public List<Ban> getAllBanByDateTime(Date selectedDate, Date now) {
        List<Ban> list = new ArrayList<>();
        String sql =
          "SELECT b.MaBan,\n" +
          "  CASE\n" +
          "    WHEN EXISTS (\n" +
          "      SELECT 1 FROM PhieuDatBan p\n" +
          "      WHERE p.MaBan = b.MaBan\n" +
          "        AND CAST(p.ThoiGianDat AS DATE) = CAST(? AS DATE)\n" +      // lọc đúng ngày selectedDate
          "        AND ? BETWEEN p.ThoiGianDat AND p.ThoiGianKetThuc\n" +      // đang phục vụ
          "    ) THEN N'Đang phục vụ'\n" +
          "    WHEN EXISTS (\n" +
          "      SELECT 1 FROM PhieuDatBan p\n" +
          "      WHERE p.MaBan = b.MaBan\n" +
          "        AND CAST(p.ThoiGianDat AS DATE) = CAST(? AS DATE)\n" +      // cùng ngày
          "        AND p.ThoiGianDat > ?\n" +                                  // giờ đặt còn sau now => sắp (Đã đặt)
          "    ) THEN N'Đã đặt'\n" +
          "    ELSE N'Trống'\n" +
          "  END AS TrangThai,\n" +
          "  b.MaKhuVuc, b.SoChoNgoi\n" +
          "FROM Ban b";
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
          Timestamp nowTs  = new Timestamp(now.getTime());
          java.sql.Date selDate = new java.sql.Date(selectedDate.getTime());
          // Tham số thứ tự: ?, ?, ?, ?
          ps.setDate(1, selDate);
          ps.setTimestamp(2, nowTs);
          ps.setDate(3, selDate);
          ps.setTimestamp(4, nowTs);
          try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
              list.add(new Ban(
                rs.getString("MaBan"),
                rs.getString("TrangThai"),
                rs.getString("MaKhuVuc"),
                rs.getInt("SoChoNgoi")
              ));
            }
          }
        } catch (SQLException ex) {
          ex.printStackTrace();
        }
        return list;
    }

//    
//    public List<Ban> getAllBanByDateTime(Date selectedDate) {
//        List<Ban> list = new ArrayList<>();
//        String sql =
//          "SELECT b.MaBan,\n" +
//          "  CASE\n" +
//          "    -- 1) Nếu ngày chọn < ngày hôm nay thì luôn trống\n" +
//          "    WHEN CAST(? AS DATE) < CAST(GETDATE() AS DATE) THEN N'Trống'\n" +
//          "    -- 2) Nếu ngày chọn > ngày hôm nay thì nếu có booking nào trong ngày đó => đã đặt, ngược lại trống\n" +
//          "    WHEN CAST(? AS DATE) > CAST(GETDATE() AS DATE)\n" +
//          "      AND EXISTS(\n" +
//          "        SELECT 1 FROM PhieuDatBan p\n" +
//          "        WHERE p.MaBan=b.MaBan\n" +
//          "          AND CAST(p.ThoiGianDat AS DATE)=CAST(? AS DATE)\n" +
//          "      ) THEN N'Đã đặt'\n" +
//          "    WHEN CAST(? AS DATE) > CAST(GETDATE() AS DATE) THEN N'Trống'\n" +
//          "    -- 3) Nếu ngày chọn = hôm nay: kiểm xem có booking đang diễn ra không => Đang phục vụ\n" +
//          "    WHEN CAST(? AS DATE)=CAST(GETDATE() AS DATE)\n" +
//          "      AND EXISTS(\n" +
//          "        SELECT 1 FROM PhieuDatBan p\n" +
//          "        WHERE p.MaBan=b.MaBan\n" +
//          "          AND ? BETWEEN p.ThoiGianDat AND p.ThoiGianKetThuc\n" +
//          "      ) THEN N'Đang phục vụ'\n" +
//          "    -- 4) Nếu ngày chọn = hôm nay và còn booking sắp tới trong ngày => Đã đặt\n" +
//          "    WHEN CAST(? AS DATE)=CAST(GETDATE() AS DATE)\n" +
//          "      AND EXISTS(\n" +
//          "        SELECT 1 FROM PhieuDatBan p\n" +
//          "        WHERE p.MaBan=b.MaBan\n" +
//          "          AND p.ThoiGianDat > ?\n" +
//          "      ) THEN N'Đã đặt'\n" +
//          "    ELSE N'Trống'\n" +
//          "  END AS TrangThai,\n" +
//          "  b.MaKhuVuc, b.SoChoNgoi\n" +
//          "FROM Ban b";
//        try (Connection con = ConnectDB.getConnection();
//             PreparedStatement ps = con.prepareStatement(sql)) {
//
//            Timestamp now = new Timestamp(System.currentTimeMillis());
//            java.sql.Date sqlDate = new java.sql.Date(selectedDate.getTime());
//
//            // 1 & 2 & 3 & 4 lần lượt dùng selectedDate & now
//            ps.setDate(1, sqlDate);
//            ps.setDate(2, sqlDate);
//            ps.setDate(3, sqlDate);
//            ps.setDate(4, sqlDate);
//            ps.setDate(5, sqlDate);
//            ps.setTimestamp(6, now);
//            ps.setDate(7, sqlDate);
//            ps.setTimestamp(8, now);
//
//            try (ResultSet rs = ps.executeQuery()) {
//                while (rs.next()) {
//                    list.add(new Ban(
//                      rs.getString("MaBan"),
//                      rs.getString("TrangThai"),
//                      rs.getString("MaKhuVuc"),
//                      rs.getInt("SoChoNgoi")
//                    ));
//                }
//            }
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//        return list;
//    }



//     Thêm bàn mới
    public void addBan(Ban ban) {
        String sql = "INSERT INTO Ban (MaBan, TrangThai, MaKhuVuc, SoChoNgoi) VALUES (?, ?, ?, ?)";
        try {
            Connection con = ConnectDB.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, ban.getMaBan());
            ps.setString(2, ban.getTrangThai());
            ps.setString(3, ban.getMaKhuVuc());
            ps.setInt(4, ban.getSoChoNgoi());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    
    
    // Cập nhật thông tin bàn, nhưng chỉ cho phép cập nhật nếu bàn không có lịch đặt trong tương lai
    public boolean updateBan(Ban ban) {
        if (hasFutureBooking(ban.getMaBan())) {
            // Nếu bàn có lịch đặt trong tương lai, không cho phép cập nhật.
            return false;
        }
        String sql = "UPDATE Ban SET TrangThai = ?, MaKhuVuc = ?, SoChoNgoi = ? WHERE MaBan = ?";
        try {
            Connection con = ConnectDB.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, ban.getTrangThai());
            ps.setString(2, ban.getMaKhuVuc());
            ps.setInt(3, ban.getSoChoNgoi());
            ps.setString(4, ban.getMaBan());
            ps.executeUpdate();
            ps.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    // Xóa bàn, chỉ cho phép xóa nếu bàn không có lịch đặt trong tương lai
    public boolean deleteBan(String maBan) {
        if (hasFutureBooking(maBan)) {
            // Nếu có lịch đặt trong tương lai, không được phép xóa
            return false;
        }
        String sql = "DELETE FROM Ban WHERE MaBan = ?";
        try {
            Connection con = ConnectDB.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, maBan);
            ps.executeUpdate();
            ps.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    // Lấy thông tin bàn theo mã bàn
    public Ban getBanById(String maBan) {
        Ban ban = null;
        String sql = "SELECT MaBan, TrangThai, MaKhuVuc, SoChoNgoi FROM Ban WHERE MaBan = ?";
        try {
            Connection con = ConnectDB.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, maBan);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String trangThai = rs.getString("TrangThai");
                String maKhuVuc = rs.getString("MaKhuVuc");
                int soChoNgoi = rs.getInt("SoChoNgoi");
                ban = new Ban(maBan, trangThai, maKhuVuc, soChoNgoi);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ban;
    }
    
    // Tìm kiếm bàn theo mã bàn chứa keyword
    public List<Ban> searchBanByMa(String keyword) {
        List<Ban> list = new ArrayList<>();
        String sql = "SELECT MaBan, TrangThai, MaKhuVuc, SoChoNgoi FROM Ban WHERE MaBan LIKE ?";
        try {
            Connection con = ConnectDB.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String maBan = rs.getString("MaBan");
                String trangThai = rs.getString("TrangThai");
                String maKhuVuc = rs.getString("MaKhuVuc");
                int soChoNgoi = rs.getInt("SoChoNgoi");
                Ban ban = new Ban(maBan, trangThai, maKhuVuc, soChoNgoi);
                list.add(ban);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    // Kiểm tra xem bàn có lịch đặt trong tương lai hay không
    public boolean hasFutureBooking(String maBan) {
        boolean hasBooking = false;
        String sql = "SELECT COUNT(*) FROM PhieuDatBan WHERE MaBan = ? AND ThoiGianDat > GETDATE()";
        try {
            Connection con = ConnectDB.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, maBan);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                hasBooking = count > 0;
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hasBooking;
    }
    
    // Hàm lấy lịch đặt của bàn (trong ngày) để hiển thị lịch đặt cho bàn
    public List<PhieuDatBan> getBookingSchedule(String maBan, Date ngayDat) {
        List<PhieuDatBan> schedule = new ArrayList<>();
        String sql = "SELECT MaPhieu, ThoiGianDat, ThoiGianKetThuc FROM PhieuDatBan " +
                     "WHERE MaBan = ? AND CAST(ThoiGianDat AS DATE) = ?";
        try {
            Connection con = ConnectDB.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, maBan);
            ps.setDate(2, new java.sql.Date(ngayDat.getTime()));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                // Sử dụng constructor có nhận 5 tham số nếu bạn muốn cài đặt đầy đủ,
                // hoặc sử dụng constructor 3 tham số để tạo tạm đối tượng hiển thị lịch đặt.
                // Ở đây mình sử dụng constructor 3 tham số và để các trường còn lại là null.
                PhieuDatBan pdb = new PhieuDatBan(
                        rs.getString("MaPhieu"),
                        rs.getTimestamp("ThoiGianDat"),
                        rs.getTimestamp("ThoiGianKetThuc")
                );
                schedule.add(pdb);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return schedule;
    }
}
