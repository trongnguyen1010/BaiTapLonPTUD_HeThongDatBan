package DAO;

import java.sql.Timestamp;
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

	public Ban_DAO() {
		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

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

			String sql = "SELECT COUNT(*) FROM PhieuDatBan " + "WHERE MaBan = ? "
					+ "  AND (ThoiGianDat < ? AND ThoiGianKetThuc > ?)";
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
			try {
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (ps != null)
					ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return booked;
	}

	public boolean datBan(String maBan, Date ngayDat, String startTime, String endTime, String sdt) {
		boolean success = false;
		Connection con = ConnectDB.getConnection();
		PreparedStatement ps = null;
		try {
			String maPhieu = "PD" + System.currentTimeMillis();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String dateStr = sdf.format(ngayDat);
			Timestamp bookingStart = Timestamp.valueOf(dateStr + " " + startTime + ":00");
			Timestamp bookingEnd = Timestamp.valueOf(dateStr + " " + endTime + ":00");

			String sql = "INSERT INTO PhieuDatBan (MaPhieu, ThoiGianDat, ThoiGianKetThuc, MaBan, MaKhachHang) "
					+ "VALUES (?, ?, ?, ?, ?)";
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
			try {
				if (ps != null)
					ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return success;
	}

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
				ban.setSoChoNgoi(soChoNgoi);
				banList.add(ban);
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return banList;
	}

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
				ban.setSoChoNgoi(soChoNgoi);
				banList.add(ban);
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return banList;
	}

	public List<Ban> getAllBanByDate(Date selectedDate) {
		List<Ban> banList = new ArrayList<>();
		String sql = "SELECT b.MaBan, " + "       CASE WHEN EXISTS ( " + "           SELECT 1 FROM PhieuDatBan p "
				+ "           WHERE p.MaBan = b.MaBan "
				+ "             AND CAST(? AS DATE) BETWEEN CAST(p.ThoiGianDat AS DATE) AND CAST(p.ThoiGianKetThuc AS DATE) "
				+ "       ) THEN N'Đã đặt' ELSE b.TrangThai END AS TrangThai, " + "       b.MaKhuVuc, b.SoChoNgoi "
				+ "FROM Ban b";
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
				ban.setSoChoNgoi(soChoNgoi);
				banList.add(ban);
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return banList;
	}

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

	public void updateBan(Ban ban) {
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
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

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
