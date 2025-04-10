//package DAO;
//
//import connectDB.ConnectDB;
//import Entity.HoaDon;
//import Entity.KhachHang;
////import Entity.NhanVien;
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//
//public class HoaDon_DAO {
//
//    public HoaDon_DAO() {
//        try {
//            ConnectDB.getInstance().connect();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public boolean insert(HoaDon hoaDon) {
//        Connection con = ConnectDB.getConnection();
//        PreparedStatement stmt = null;
//        int n = 0;
//        try {
//            String sql = "INSERT INTO HoaDon (MaHoaDon, NgayLap, PhuongThuc, MaNhanVien, MaKhachHang) VALUES (?, ?, ?, ?, ?)";
//            stmt = con.prepareStatement(sql);
//            stmt.setString(1, hoaDon.getMaHoaDon());
//            stmt.setDate(2, new java.sql.Date(hoaDon.getNgayLap().getTime()));
//            stmt.setString(3, hoaDon.getPhuongThuc());
//          //  stmt.setString(4, hoaDon.getNhanVien().getId());
//            stmt.setString(5, hoaDon.getKhachHang().getMaKhachHang());
//            n = stmt.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (stmt != null) stmt.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        return n > 0;
//    }
//
//    public boolean update(HoaDon hoaDon) {
//        Connection con = ConnectDB.getConnection();
//        PreparedStatement stmt = null;
//        int n = 0;
//        try {
//            String sql = "UPDATE HoaDon SET NgayLap = ?, PhuongThuc = ?, MaNhanVien = ?, MaKhachHang = ? WHERE MaHoaDon = ?";
//            stmt = con.prepareStatement(sql);
//            stmt.setDate(1, new java.sql.Date(hoaDon.getNgayLap().getTime()));
//            stmt.setString(2, hoaDon.getPhuongThuc());
//           // stmt.setString(3, hoaDon.getNhanVien().getId());
//            stmt.setString(4, hoaDon.getKhachHang().getMaKhachHang());
//            stmt.setString(5, hoaDon.getMaHoaDon());
//            n = stmt.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (stmt != null) stmt.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        return n > 0;
//    }
//
//    public boolean delete(String maHoaDon) {
//        Connection con = ConnectDB.getConnection();
//        PreparedStatement stmt = null;
//        int n = 0;
//        try {
//            String sql = "DELETE FROM HoaDon WHERE MaHoaDon = ?";
//            stmt = con.prepareStatement(sql);
//            stmt.setString(1, maHoaDon);
//            n = stmt.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (stmt != null) stmt.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        return n > 0;
//    }
//
//    public List<HoaDon> getAllHoaDon() {
//        Connection con = ConnectDB.getConnection();
//        PreparedStatement stmt = null;
//        ResultSet rs = null;
//        List<HoaDon> list = new ArrayList<>();
//
//        try {
//            String sql = "SELECT HD.MaHoaDon, HD.NgayLap, HD.PhuongThuc, HD.MaNhanVien, NV.HoTen AS TenNV, NV.SDT AS SDTNV, " +
//                         "NV.GioiTinh AS GioiTinhNV, NV.NgayVaoLam, KH.MaKhachHang, KH.HoTen AS TenKH, KH.SDT AS SDTKH, KH.GioiTinh AS GioiTinhKH, KH.NgayThamGia " +
//                         "FROM HoaDon HD " +
//                         "LEFT JOIN NhanVien NV ON HD.MaNhanVien = NV.MaNhanVien " +
//                         "LEFT JOIN KhachHang KH ON HD.MaKhachHang = KH.MaKhachHang";
//
//            stmt = con.prepareStatement(sql);
//            rs = stmt.executeQuery();
//
//            while (rs.next()) {
//                HoaDon hd = new HoaDon();
//                hd.setMaHoaDon(sql);
//                hd.setNgayLap(null);
//                hd.setPhuongThuc(rs.getString("PhuongThuc"));
////
////                NhanVien nv = new NhanVien();
////                nv.setId(rs.getString("MaNhanVien"));
////                nv.setHoTen(rs.getString("TenNV"));
////                nv.setSdt(rs.getString("SDTNV"));
////                nv.setGioiTinh(rs.getString("GioiTinhNV"));
////                nv.setNgayVaoLam(rs.getDate("NgayVaoLam"));
////                hd.setNhanVien(nv);
//
//                KhachHang kh = new KhachHang();
//                kh.setMaKhachHang(sql);
//                kh.setTenKhachHang(sql);
//                kh.setSdt(rs.getString("SDTKH"));
//                kh.setGioiTinh(rs.getString("GioiTinhKH"));
//               // kh.setNgayThamGia(rs.getDate("NgayThamGia"));
//                hd.setKhachHang(kh);
//
//                list.add(hd);
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (rs != null) rs.close();
//                if (stmt != null) stmt.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//
//        return list;
//    }
//
//    public HoaDon getHoaDonById(String id) {
//        Connection con = ConnectDB.getConnection();
//        PreparedStatement stmt = null;
//        ResultSet rs = null;
//        HoaDon hd = null;
//
//        try {
//            String sql = "SELECT HD.MaHoaDon, HD.NgayLap, HD.PhuongThuc, HD.MaNhanVien, NV.HoTen AS TenNV, NV.SDT AS SDTNV, " +
//                         "NV.GioiTinh AS GioiTinhNV, NV.NgayVaoLam, KH.MaKhachHang, KH.HoTen AS TenKH, KH.SDT AS SDTKH, KH.GioiTinh AS GioiTinhKH, KH.NgayThamGia " +
//                         "FROM HoaDon HD " +
//                         "LEFT JOIN NhanVien NV ON HD.MaNhanVien = NV.MaNhanVien " +
//                         "LEFT JOIN KhachHang KH ON HD.MaKhachHang = KH.MaKhachHang " +
//                         "WHERE HD.MaHoaDon = ?";
//
//            stmt = con.prepareStatement(sql);
//            stmt.setString(1, id);
//            rs = stmt.executeQuery();
//
//            if (rs.next()) {
//                hd = new HoaDon();
//                hd.set(rs.getString("MaHoaDon"));
//                hd.setMaHoaDon(id);
//                hd.setPhuongThuc(rs.getString("PhuongThuc"));
//
////                NhanVien nv = new NhanVien();
////                nv.setId(rs.getString("MaNhanVien"));
////                nv.setHoTen(rs.getString("TenNV"));
////                nv.setSdt(rs.getString("SDTNV"));
////                nv.setGioiTinh(rs.getString("GioiTinhNV"));
////                nv.setNgayVaoLam(rs.getDate("NgayVaoLam"));
////                hd.setNhanVien(nv);
//
//                KhachHang kh = new KhachHang();
//                kh.setId(rs.getString("MaKhachHang"));
//                kh.setHoTen(rs.getString("TenKH"));
//                kh.setSdt(rs.getString("SDTKH"));
//                kh.setGioiTinh(rs.getString("GioiTinhKH"));
//              //  kh.setNgayThamGia(rs.getDate("NgayThamGia"));
//                hd.setKhachHang(kh);
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (rs != null) rs.close();
//                if (stmt != null) stmt.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//
//        return hd;
//    }
//}
