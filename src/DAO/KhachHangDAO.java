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

    public KhachHang getKhachHangById(String maKhachHang) {
        ConnectDB.getInstance();
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
                int gioiTinhBit = rs.getInt("GioiTinh");  // Lấy kiểu bit
                String gioiTinh = (gioiTinhBit == 1) ? "Nam" : "Nữ";  // Chuyển đổi sang chuỗi
                String sdt = rs.getString("SDT");

                kh = new KhachHang(maKH, tenKH, email, gioiTinh, sdt);
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
        return kh;
    }


    public boolean insert(KhachHang kh) {
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;
        int n = 0;
        try {
            String sql = "INSERT INTO KhachHang (MaKhachHang, TenKhachHang, Email, GioiTinh, SDT) VALUES (?, ?, ?, ?, ?)";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, kh.getMaKhachHang());
            stmt.setString(2, kh.getTenKhachHang());
            stmt.setString(3, kh.getEmail());
            int gioiTinhBit = kh.getGioiTinh().equals("Nam") ? 1 : 0;  // Chuyển đổi về bit
            stmt.setInt(4, gioiTinhBit);
            stmt.setString(5, kh.getSdt());
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


    public boolean update(KhachHang kh) {
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;
        int n = 0;
        try {
            String sql = "UPDATE KhachHang SET TenKhachHang=?, Email=?, GioiTinh=?, SDT=? WHERE MaKhachHang=?";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, kh.getTenKhachHang());
            stmt.setString(2, kh.getEmail());
            int gioiTinhBit = kh.getGioiTinh().equals("Nam") ? 1 : 0;  // Chuyển đổi về bit
            stmt.setInt(3, gioiTinhBit);
            stmt.setString(4, kh.getSdt());
            stmt.setString(5, kh.getMaKhachHang());
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


    // Xóa khách hàng
    public boolean delete(String maKhachHang) {
        ConnectDB.getInstance();
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
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return n > 0;
    }

    public List<KhachHang> getAllKhachHang() {
        ConnectDB.getInstance();
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
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return listKhachHang;
    }

    public List<KhachHang> searchKhachHang(String searchType, String searchText) {
        ConnectDB.getInstance();
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
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return listKhachHang;
    }

    public List<KhachHang> timKiemKhachHang(String hoTen, String sdt, String email, String gioiTinh) {
        List<KhachHang> danhSachKhachHang = new ArrayList<>();
        ConnectDB.getInstance();
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
                // Chuyển đổi "Nam" thành 1 và "Nữ" thành 0
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
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return danhSachKhachHang;
    }

}