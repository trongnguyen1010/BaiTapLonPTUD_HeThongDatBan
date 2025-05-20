package DAO;

import connectDB.ConnectDB;

import Entity.NhanVien;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NhanVienDAO {

	public NhanVienDAO() {
		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public NhanVien getNhanVienById(String maNhanVien) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		NhanVien nv = null;

		try {
			String sql = "SELECT MaNhanVien, TenNhanVien, Email, ChucVu, GioiTinh, SDT FROM NhanVien WHERE MaNhanVien = ?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, maNhanVien);
			rs = stmt.executeQuery();

			if (rs.next()) {
				String maNV = rs.getString("MaNhanVien");
				String ten = rs.getString("TenNhanVien");
				String email = rs.getString("Email");
				String chucVu = rs.getString("ChucVu");
				int gioiTinhBit = rs.getInt("GioiTinh");
				String gioiTinh = (gioiTinhBit == 1) ? "Nam" : "Nữ";
				String sdt = rs.getString("SDT");

				nv = new NhanVien(maNV, ten, email, chucVu, gioiTinh, sdt);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return nv;
	}

	public boolean insert(NhanVien nv) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			String sql = "INSERT INTO NhanVien (MaNhanVien, TenNhanVien, Email, ChucVu, GioiTinh, SDT) VALUES (?, ?, ?, ?, ?, ?)";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, nv.getMaNhanVien());
			stmt.setString(2, nv.getTen());
			stmt.setString(3, nv.getEmail());
			stmt.setString(4, nv.getChucVu());
			int gioiTinhBit = nv.getGioiTinh().equals("Nam") ? 1 : 0;
			stmt.setInt(5, gioiTinhBit);
			stmt.setString(6, nv.getSdt());
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return n > 0;
	}

	public boolean update(NhanVien nv) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			String sql = "UPDATE NhanVien SET TenNhanVien=?, Email=?, ChucVu=?, GioiTinh=?, SDT=? WHERE MaNhanVien=?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, nv.getTen());
			stmt.setString(2, nv.getEmail());
			stmt.setString(3, nv.getChucVu());
			int gioiTinhBit = nv.getGioiTinh().equals("Nam") ? 1 : 0;
			stmt.setInt(4, gioiTinhBit);
			stmt.setString(5, nv.getSdt());
			stmt.setString(6, nv.getMaNhanVien());
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return n > 0;
	}

	public boolean delete(String maNhanVien) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			String sql = "DELETE FROM NhanVien WHERE MaNhanVien = ?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, maNhanVien);
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return n > 0;
	}

	public List<NhanVien> getAllNhanVien() {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<NhanVien> listNhanVien = new ArrayList<>();

		try {
			String sql = "SELECT MaNhanVien, TenNhanVien, Email, ChucVu, GioiTinh, SDT FROM NhanVien";
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();

			while (rs.next()) {
				String maNV = rs.getString("MaNhanVien");
				String ten = rs.getString("TenNhanVien");
				String email = rs.getString("Email");
				String chucVu = rs.getString("ChucVu");
				int gioiTinhBit = rs.getInt("GioiTinh");
				String gioiTinh = (gioiTinhBit == 1) ? "Nam" : "Nữ";
				String sdt = rs.getString("SDT");

				NhanVien nv = new NhanVien(maNV, ten, email, chucVu, gioiTinh, sdt);
				listNhanVien.add(nv);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listNhanVien;
	}

	public List<NhanVien> searchNhanVien(String searchType, String searchText) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<NhanVien> listNhanVien = new ArrayList<>();

		try {
			String sql = "SELECT MaNhanVien, TenNhanVien, Email, ChucVu, GioiTinh, SDT FROM NhanVien WHERE ";
			switch (searchType) {
			case "Mã nhân viên":
				sql += "MaNhanVien LIKE ?";
				break;
			case "Tên":
				sql += "TenNhanVien LIKE ?";
				break;
			case "Số điện thoại":
				sql += "SDT LIKE ?";
				break;
			case "Chức vụ":
				sql += "ChucVu LIKE ?";
				break;
			}

			stmt = con.prepareStatement(sql);
			stmt.setString(1, "%" + searchText + "%");
			rs = stmt.executeQuery();

			while (rs.next()) {
				String maNV = rs.getString("MaNhanVien");
				String ten = rs.getString("TenNhanVien");
				String email = rs.getString("Email");
				String chucVu = rs.getString("ChucVu");
				int gioiTinhBit = rs.getInt("GioiTinh");
				String gioiTinh = (gioiTinhBit == 1) ? "Nam" : "Nữ";
				String sdt = rs.getString("SDT");

				NhanVien nv = new NhanVien(maNV, ten, email, chucVu, gioiTinh, sdt);
				listNhanVien.add(nv);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listNhanVien;
	}

	public List<NhanVien> timKiemNhanVien(String hoTen, String sdt, String email, String gioiTinh, String chucVu) {
        List<NhanVien> danhSachNhanVien = new ArrayList<>();
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            StringBuilder query = new StringBuilder("SELECT * FROM NhanVien WHERE 1=1");

            if (hoTen != null && !hoTen.isEmpty()) {
                query.append(" AND TenNhanVien LIKE ?");
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
            if (chucVu != null && !chucVu.isEmpty()) {
                query.append(" AND ChucVu = ?");
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
            if (chucVu != null && !chucVu.isEmpty()) {
                stmt.setString(index++, chucVu);
            }

            rs = stmt.executeQuery();
            while (rs.next()) {
                String maNV = rs.getString("MaNhanVien");
                String tenNV = rs.getString("TenNhanVien");
                String emailNV = rs.getString("Email");
                String chucVuNV = rs.getString("ChucVu");
                int gioiTinhBit = rs.getInt("GioiTinh");
                String gioiTinhNV = (gioiTinhBit == 1) ? "Nam" : "Nữ";
                String sdtNV = rs.getString("SDT");

                NhanVien nv = new NhanVien(maNV, tenNV, emailNV, chucVuNV, gioiTinhNV, sdtNV);
                danhSachNhanVien.add(nv);
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
        return danhSachNhanVien;
    }

}