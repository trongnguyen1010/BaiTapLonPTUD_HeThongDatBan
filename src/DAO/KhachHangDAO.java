package DAO;

import connectDB.ConnectDB;
import Entity.KhachHang;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class KhachHangDAO {

    public KhachHangDAO() {
        try {
            ConnectDB.getInstance().connect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Tìm khách hàng theo số điện thoại.
     * Giả sử MaKhachHang được dùng là sdt.
     */
    public static KhachHang getKhachHangByPhone(String sdt) {
        KhachHang kh = null;
        String sql = "SELECT MaKhachHang, TenKhachHang, Email, GioiTinh, SDT FROM KhachHang WHERE SDT = ?";
        try {
            Connection con = ConnectDB.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, sdt);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String maKH = rs.getString("MaKhachHang");
                String tenKH = rs.getString("TenKhachHang");
                String email = rs.getString("Email");
                int gioiTinhBit = rs.getInt("GioiTinh");
                String gioiTinh = (gioiTinhBit == 1) ? "Nam" : "Nữ";
                String soDienThoai = rs.getString("SDT");
                kh = new KhachHang(maKH, tenKH, email, gioiTinh, soDienThoai);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return kh;
    }

    public static KhachHang getKhachHangByName(String ten) {
        KhachHang kh = null;
        String sql = "SELECT MaKhachHang, TenKhachHang, Email, GioiTinh, SDT FROM KhachHang WHERE TenKhachHang = ?";
        try {
            Connection con = ConnectDB.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, ten);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String maKH = rs.getString("MaKhachHang");
                String tenKH = rs.getString("TenKhachHang");
                String email = rs.getString("Email");
                int gioiTinhBit = rs.getInt("GioiTinh");
                String gioiTinh = (gioiTinhBit == 1) ? "Nam" : "Nữ";
                String soDienThoai = rs.getString("SDT");
                kh = new KhachHang(maKH, tenKH, email, gioiTinh, soDienThoai);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return kh;
    }

    public static KhachHang getKhachHangById(String maKhachHang) {
        Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        KhachHang kh = null;
        try {
            String sql = "SELECT MaKhachHang, TenKhachHang, Email, GioiTinh, SDT FROM KhachHang WHERE MaKhachHang = ?";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, maKhachHang);
            rs = stmt.executeQuery();
            if (rs.next()) {
                String maKH = rs.getString("MaKhachHang");
                String tenKH = rs.getString("TenKhachHang");
                String email = rs.getString("Email");
                int gioiTinhBit = rs.getInt("GioiTinh"); // Lấy kiểu bit
                String gioiTinh = (gioiTinhBit == 1) ? "Nam" : "Nữ"; // Chuyển đổi sang chuỗi
                String sdt = rs.getString("SDT");
                kh = new KhachHang(maKH, tenKH, email, gioiTinh, sdt);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if (stmt != null) stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
        return kh;
    }

    /**
     * Chèn khách hàng mới.
     * Ở đây, nếu khách hàng mới, ta sử dụng sdt làm MaKhachHang.
     * Các trường Email có thể để rỗng nếu không cung cấp.
     */
    public static boolean insert(KhachHang kh) {
        Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;
        int n = 0;
        try {
            String sql = "INSERT INTO KhachHang (MaKhachHang, TenKhachHang, Email, GioiTinh, SDT) VALUES (?, ?, ?, ?, ?)";
            stmt = con.prepareStatement(sql);
            // Nếu MaKhachHang chưa có, sử dụng sdt làm mã khách hàng
            if (kh.getMaKhachHang() == null || kh.getMaKhachHang().isEmpty()) {
                stmt.setString(1, kh.getSdt());
            } else {
                stmt.setString(1, kh.getMaKhachHang());
            }
            stmt.setString(2, kh.getTenKhachHang());
            // Email là tùy chọn. Nếu rỗng, đặt chuỗi rỗng.
            if (kh.getEmail() == null || kh.getEmail().isEmpty()) {
                stmt.setString(3, "unknown@example.com"); // hoặc giá trị mặc định nào đó không trùng
            } else {
                stmt.setString(3, kh.getEmail());
            }

            int gioiTinhBit = kh.getGioiTinh().equalsIgnoreCase("Nam") ? 1 : 0;
            stmt.setInt(4, gioiTinhBit);
            stmt.setString(5, kh.getSdt());
            n = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { if (stmt != null) stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
        return n > 0;
    }

    public static boolean update(KhachHang kh) {
        Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;
        int n = 0;
        try {
            String sql = "UPDATE KhachHang SET TenKhachHang=?, Email=?, GioiTinh=?, SDT=? WHERE MaKhachHang=?";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, kh.getTenKhachHang());
            stmt.setString(2, kh.getEmail());
            int gioiTinhBit = kh.getGioiTinh().equalsIgnoreCase("Nam") ? 1 : 0;
            stmt.setInt(3, gioiTinhBit);
            stmt.setString(4, kh.getSdt());
            stmt.setString(5, kh.getMaKhachHang());
            n = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { if (stmt != null) stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
        return n > 0;
    }

    public boolean delete(String maKhachHang) {
        Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;
        int n = 0;
        try {
            String sql = "DELETE FROM KhachHang WHERE MaKhachHang = ?";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, maKhachHang);
            n = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { if (stmt != null) stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
        return n > 0;
    }

    public static List<KhachHang> getAllKhachHang() {
        Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<KhachHang> listKhachHang = new ArrayList<>();
        try {
            String sql = "SELECT MaKhachHang, TenKhachHang, Email, GioiTinh, SDT FROM KhachHang";
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                String maKH = rs.getString("MaKhachHang");
                String tenKH = rs.getString("TenKhachHang");
                String email = rs.getString("Email");
                int gioiTinhBit = rs.getInt("GioiTinh");
                String gioiTinh = (gioiTinhBit == 1) ? "Nam" : "Nữ";
                String sdt = rs.getString("SDT");
                KhachHang kh = new KhachHang(maKH, tenKH, email, gioiTinh, sdt);
                listKhachHang.add(kh);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if (stmt != null) stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
        return listKhachHang;
    }

    public List<KhachHang> searchKhachHang(String searchType, String searchText) {
        Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<KhachHang> listKhachHang = new ArrayList<>();
        try {
            String sql = "SELECT MaKhachHang, TenKhachHang, Email, GioiTinh, SDT FROM KhachHang WHERE ";
            switch (searchType) {
                case "Mã khách hàng":
                    sql += "MaKhachHang LIKE ?";
                    break;
                case "Họ tên":
                    sql += "TenKhachHang LIKE ?";
                    break;
                case "Số điện thoại":
                    sql += "SDT LIKE ?";
                    break;
            }
            stmt = con.prepareStatement(sql);
            stmt.setString(1, "%" + searchText + "%");
            rs = stmt.executeQuery();
            while (rs.next()) {
                String maKH = rs.getString("MaKhachHang");
                String tenKH = rs.getString("TenKhachHang");
                String email = rs.getString("Email");
                int gioiTinhBit = rs.getInt("GioiTinh");
                String gioiTinh = (gioiTinhBit == 1) ? "Nam" : "Nữ";
                String sdt = rs.getString("SDT");
                KhachHang kh = new KhachHang(maKH, tenKH, email, gioiTinh, sdt);
                listKhachHang.add(kh);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if (stmt != null) stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
        return listKhachHang;
    }

    public List<KhachHang> timKiemKhachHang(String hoTen, String sdt, String email, String gioiTinh) {
        List<KhachHang> danhSachKhachHang = new ArrayList<>();
        Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            StringBuilder query = new StringBuilder("SELECT * FROM KhachHang WHERE 1=1");
            if (hoTen != null && !hoTen.isEmpty()) {
                query.append(" AND TenKhachHang LIKE ?");
            }
            if (sdt != null && !sdt.isEmpty()) {
                query.append(" AND SDT LIKE ?");
            }
            if (email != null && !email.isEmpty()) {
                query.append(" AND Email LIKE ?");
            }
            if (gioiTinh != null && !gioiTinh.isEmpty()) {
                query.append(" AND GioiTinh = ?");
            }
            stmt = con.prepareStatement(query.toString());
            int index = 1;
            if (hoTen != null && !hoTen.isEmpty()) {
                stmt.setString(index++, "%" + hoTen + "%");
            }
            if (sdt != null && !sdt.isEmpty()) {
                stmt.setString(index++, "%" + sdt + "%");
            }
            if (email != null && !email.isEmpty()) {
                stmt.setString(index++, "%" + email + "%");
            }
            if (gioiTinh != null && !gioiTinh.isEmpty()) {
                int gioiTinhBit = gioiTinh.equalsIgnoreCase("Nam") ? 1 : 0;
                stmt.setInt(index++, gioiTinhBit);
            }
            rs = stmt.executeQuery();
            while (rs.next()) {
                String maKH = rs.getString("MaKhachHang");
                String tenKH = rs.getString("TenKhachHang");
                String emailKH = rs.getString("Email");
                int gioiTinhBit = rs.getInt("GioiTinh");
                String gioiTinhKH = (gioiTinhBit == 1) ? "Nam" : "Nữ";
                String sdtKH = rs.getString("SDT");
                KhachHang kh = new KhachHang(maKH, tenKH, emailKH, gioiTinhKH, sdtKH);
                danhSachKhachHang.add(kh);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if (stmt != null) stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
        return danhSachKhachHang;
    }
    public static boolean existsByMaKhachHang(String maKH) {
        Connection con = ConnectDB.getConnection();
        String sql = "SELECT COUNT(*) FROM KhachHang WHERE MaKhachHang = ?";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, maKH);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


	


}
