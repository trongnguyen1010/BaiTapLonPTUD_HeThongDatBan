package dao;

import ConnectDB.ConnectDB;
import entity.TaiKhoan;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TaiKhoan_Dao {
   ArrayList<TaiKhoan> list = new ArrayList();

   public TaiKhoan_Dao() {
   }

   public ArrayList<TaiKhoan> getDSTaiKhoan() {
      try {
         ConnectDB.getInstance();
         Connection con = ConnectDB.getConnection();
         String sql = "SELECT * FROM TaiKhoan";
         CallableStatement myCall = con.prepareCall(sql);
         ResultSet rs = myCall.executeQuery();

         while(rs.next()) {
            String tenDN = rs.getString("TenDangNhap");
            String mk = rs.getString("MatKhau");
            String vaiTro = rs.getString("VaiTro");
            String maNhanVien = rs.getString("MaNhanVien");

            TaiKhoan tk = new TaiKhoan(tenDN, mk, vaiTro, maNhanVien);
            this.list.add(tk);
         }
      } catch (SQLException var8) {
         var8.printStackTrace();
      }

      return this.list;
   }

   public TaiKhoan getTaiKhoanTheoTen(String ten) {
      TaiKhoan tk = null;

      try {
         ConnectDB.getInstance();
         Connection con = ConnectDB.getConnection();
         String sql = "SELECT * FROM TaiKhoan WHERE TenDangNhap = ?";
         CallableStatement myCall = con.prepareCall(sql);
         myCall.setString(1, ten);

         String tenDN;
         String mk;
         String vaiTro;
         String maNhanVien;
         for(ResultSet rs = myCall.executeQuery(); rs.next(); tk = new TaiKhoan(tenDN, mk, vaiTro, maNhanVien)) {
            tenDN = rs.getString("TenDangNhap");
            mk = rs.getString("MatKhau");
            vaiTro = rs.getString("VaiTro");
            maNhanVien = rs.getString("MaNhanVien");
         }
      } catch (SQLException var9) {
         var9.printStackTrace();
      }

      return tk;
   }

   public void addTaiKhoan(TaiKhoan tk) throws Exception {
      Connection con = ConnectDB.getConnection();
      String sql = "INSERT INTO TaiKhoan (TenDangNhap, MatKhau, VaiTro, MaNhanVien) VALUES (?, ?, ?, ?)";
      PreparedStatement statement = con.prepareStatement(sql);
      statement.setString(1, tk.getTenDangNhap());
      statement.setString(2, tk.getMatKhau());
      statement.setString(3, tk.getVaiTro());
      statement.setString(4, tk.getMaNhanVien());
      statement.executeUpdate();
   }

   public boolean updateTaiKhoan(TaiKhoan tk) {
      ConnectDB.getInstance();
      Connection con = ConnectDB.getConnection();
      PreparedStatement stmt = null;
      int n = 0;

      try {
         stmt = con.prepareStatement("UPDATE TaiKhoan SET MatKhau = ?, VaiTro = ?, MaNhanVien = ? WHERE TenDangNhap = ?");
         stmt.setString(1, tk.getMatKhau());
         stmt.setString(2, tk.getVaiTro());
         stmt.setString(3, tk.getMaNhanVien());
         stmt.setString(4, tk.getTenDangNhap());
         n = stmt.executeUpdate();
      } catch (SQLException var14) {
         var14.printStackTrace();
      } finally {
         try {
            if (stmt != null) {
               stmt.close();
            }
         } catch (SQLException var13) {
            var13.printStackTrace();
         }
      }

      return n > 0;
   }
}
