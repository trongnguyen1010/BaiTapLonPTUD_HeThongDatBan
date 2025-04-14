package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import Entity.NhanVien;
import Entity.TaiKhoan;

public class TaiKhoanDAO {

    public TaiKhoanDAO() {
        try {
            ConnectDB.getInstance().connect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public TaiKhoan getTaiKhoanTheoTenDangNhap(String tenDangNhap) {
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        TaiKhoan tk = null;

        try {
            // Join với bảng NhanVien để lấy thông tin nhân viên
            String sql = "SELECT tk.MaTaiKhoan, tk.TenDangNhap, tk.MatKhau, nv.* " +
                         "FROM TaiKhoan tk JOIN NhanVien nv ON tk.MaNhanVien = nv.MaNhanVien " +
                         "WHERE tk.TenDangNhap = ?";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, tenDangNhap);
            rs = stmt.executeQuery();

            if (rs.next()) {
                String maTaiKhoan = rs.getString("MaTaiKhoan");
                String matKhau = rs.getString("MatKhau");
                
                // Lấy thông tin nhân viên từ kết quả join
                NhanVien nv = new NhanVien();
                nv.setMaNhanVien(rs.getString("MaNhanVien"));
                nv.setTen(rs.getString("TenNhanVien"));
                nv.setEmail(rs.getString("Email"));
                nv.setChucVu(rs.getString("ChucVu"));
                int gioiTinhBit = rs.getInt("GioiTinh");
                nv.setGioiTinh((gioiTinhBit == 1) ? "Nam" : "Nữ");
                nv.setSdt(rs.getString("SDT"));

                // Tạo đối tượng TaiKhoan
                tk = new TaiKhoan(maTaiKhoan, nv, tenDangNhap, matKhau);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(rs, stmt);
        }
        return tk;
    }
    
    public TaiKhoan getTaiKhoanById(String maTaiKhoan) {
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        TaiKhoan tk = null;

        try {
            String sql = "SELECT tk.MaTaiKhoan, tk.TenDangNhap, tk.MatKhau, nv.* " +
                         "FROM TaiKhoan tk JOIN NhanVien nv ON tk.MaNhanVien = nv.MaNhanVien " +
                         "WHERE tk.MaTaiKhoan = ?";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, maTaiKhoan);
            rs = stmt.executeQuery();

            if (rs.next()) {
                String tenDangNhap = rs.getString("TenDangNhap");
                String matKhau = rs.getString("MatKhau");
                
                NhanVien nv = new NhanVien();
                nv.setMaNhanVien(rs.getString("MaNhanVien"));
                nv.setTen(rs.getString("TenNhanVien"));
                nv.setEmail(rs.getString("Email"));
                nv.setChucVu(rs.getString("ChucVu"));
                int gioiTinhBit = rs.getInt("GioiTinh");
                nv.setGioiTinh((gioiTinhBit == 1) ? "Nam" : "Nữ");
                nv.setSdt(rs.getString("SDT"));

                tk = new TaiKhoan(maTaiKhoan, nv, tenDangNhap, matKhau);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(rs, stmt);
        }
        return tk;
    }
    
    public List<TaiKhoan> getAllTaiKhoan() {
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<TaiKhoan> listTaiKhoan = new ArrayList<>();

        try {
            String sql = "SELECT tk.MaTaiKhoan, tk.TenDangNhap, tk.MatKhau, nv.* " +
                         "FROM TaiKhoan tk JOIN NhanVien nv ON tk.MaNhanVien = nv.MaNhanVien";
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                String maTaiKhoan = rs.getString("MaTaiKhoan");
                String tenDangNhap = rs.getString("TenDangNhap");
                String matKhau = rs.getString("MatKhau");
                
                NhanVien nv = new NhanVien();
                nv.setMaNhanVien(rs.getString("MaNhanVien"));
                nv.setTen(rs.getString("TenNhanVien"));
                nv.setEmail(rs.getString("Email"));
                nv.setChucVu(rs.getString("ChucVu"));
                int gioiTinhBit = rs.getInt("GioiTinh");
                nv.setGioiTinh((gioiTinhBit == 1) ? "Nam" : "Nữ");
                nv.setSdt(rs.getString("SDT"));

                TaiKhoan tk = new TaiKhoan(maTaiKhoan, nv, tenDangNhap, matKhau);
                listTaiKhoan.add(tk);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(rs, stmt);
        }
        return listTaiKhoan;
    }

    public boolean insert(TaiKhoan tk) {
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;
        int n = 0;
        try {
            String sql = "INSERT INTO TaiKhoan (MaTaiKhoan, MaNhanVien, TenDangNhap, MatKhau) VALUES (?, ?, ?, ?)";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, tk.getMaTaiKhoan());
            stmt.setString(2, tk.getNhanVien().getMaNhanVien());
            stmt.setString(3, tk.getTenDangNhap());
            stmt.setString(4, tk.getMatKhau());
            n = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(null, stmt);
        }
        return n > 0;
    }

    public boolean update(TaiKhoan tk) {
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;
        int n = 0;
        try {
            String sql = "UPDATE TaiKhoan SET MaNhanVien = ?, TenDangNhap = ?, MatKhau = ? WHERE MaTaiKhoan = ?";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, tk.getNhanVien().getMaNhanVien());
            stmt.setString(2, tk.getTenDangNhap());
            stmt.setString(3, tk.getMatKhau());
            stmt.setString(4, tk.getMaTaiKhoan());
            n = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(null, stmt);
        }
        return n > 0;
    }

    public boolean delete(String maTaiKhoan) {
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;
        int n = 0;
        try {
            String sql = "DELETE FROM TaiKhoan WHERE MaTaiKhoan = ?";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, maTaiKhoan);
            n = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(null, stmt);
        }
        return n > 0;
    }
    
    public List<TaiKhoan> searchTaiKhoan(String searchType, String searchText) {
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<TaiKhoan> listTaiKhoan = new ArrayList<>();

        try {
            String sql = "SELECT tk.MaTaiKhoan, tk.TenDangNhap, tk.MatKhau, nv.* " +
                         "FROM TaiKhoan tk JOIN NhanVien nv ON tk.MaNhanVien = nv.MaNhanVien " +
                         "WHERE ";
            
            switch(searchType) {
                case "Mã tài khoản":
                    sql += "tk.MaTaiKhoan LIKE ?";
                    break;
                case "Tên đăng nhập":
                    sql += "tk.TenDangNhap LIKE ?";
                    break;
                case "Chủ sở hữu":
                    sql += "nv.TenNhanVien LIKE ?";
                    break;
            }
            
            stmt = con.prepareStatement(sql);
            stmt.setString(1, "%" + searchText + "%");
            rs = stmt.executeQuery();

            while (rs.next()) {
                String maTaiKhoan = rs.getString("MaTaiKhoan");
                String tenDangNhap = rs.getString("TenDangNhap");
                String matKhau = rs.getString("MatKhau");
                
                NhanVien nv = new NhanVien();
                nv.setMaNhanVien(rs.getString("MaNhanVien"));
                nv.setTen(rs.getString("TenNhanVien"));
                nv.setEmail(rs.getString("Email"));
                nv.setChucVu(rs.getString("ChucVu"));
                int gioiTinhBit = rs.getInt("GioiTinh");
                nv.setGioiTinh((gioiTinhBit == 1) ? "Nam" : "Nữ");
                nv.setSdt(rs.getString("SDT"));

                TaiKhoan tk = new TaiKhoan(maTaiKhoan, nv, tenDangNhap, matKhau);
                listTaiKhoan.add(tk);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(rs, stmt);
        }
        return listTaiKhoan;
    }
    
    public List<TaiKhoan> timKiemTaiKhoan(String tenNhanVien, String tenDangNhap, String chucVu) {
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<TaiKhoan> listTaiKhoan = new ArrayList<>();

        try {
            String sql = "SELECT tk.MaTaiKhoan, tk.TenDangNhap, tk.MatKhau, nv.* " +
                         "FROM TaiKhoan tk JOIN NhanVien nv ON tk.MaNhanVien = nv.MaNhanVien " +
                         "WHERE 1=1";

            if (tenNhanVien != null && !tenNhanVien.trim().isEmpty()) {
                sql += " AND nv.TenNhanVien LIKE ?";
            }
            if (tenDangNhap != null && !tenDangNhap.trim().isEmpty()) {
                sql += " AND tk.TenDangNhap LIKE ?";
            }
            if (chucVu != null && !chucVu.trim().isEmpty()) {
                sql += " AND nv.ChucVu LIKE ?";
            }

            stmt = con.prepareStatement(sql);

            int paramIndex = 1;
            if (tenNhanVien != null && !tenNhanVien.trim().isEmpty()) {
                stmt.setString(paramIndex++, "%" + tenNhanVien + "%");
            }
            if (tenDangNhap != null && !tenDangNhap.trim().isEmpty()) {
                stmt.setString(paramIndex++, "%" + tenDangNhap + "%");
            }
            if (chucVu != null && !chucVu.trim().isEmpty()) {
                stmt.setString(paramIndex++, "%" + chucVu + "%");
            }

            rs = stmt.executeQuery();

            while (rs.next()) {
                String maTaiKhoan = rs.getString("MaTaiKhoan");
                String user = rs.getString("TenDangNhap");
                String pass = rs.getString("MatKhau");

                NhanVien nv = new NhanVien();
                nv.setMaNhanVien(rs.getString("MaNhanVien"));
                nv.setTen(rs.getString("TenNhanVien"));
                nv.setEmail(rs.getString("Email"));
                nv.setChucVu(rs.getString("ChucVu"));
                nv.setGioiTinh(rs.getInt("GioiTinh") == 1 ? "Nam" : "Nữ");
                nv.setSdt(rs.getString("SDT"));

                TaiKhoan tk = new TaiKhoan(maTaiKhoan, nv, user, pass);
                listTaiKhoan.add(tk);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(rs, stmt);
        }

        return listTaiKhoan;
    }

 
    private void close(ResultSet rs, PreparedStatement stmt) {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}