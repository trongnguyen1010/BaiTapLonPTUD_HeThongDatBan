package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import Entity.MonAn;
import connectDB.ConnectDB;

public class MonAn_DAO {
   public MonAn_DAO() {
	   try {
           ConnectDB.getInstance().connect();
       } catch (SQLException e) {
           e.printStackTrace();
       }
   }
   public MonAn getMonAnId(String maMonAn ) {
       ConnectDB.getInstance();
       Connection con = ConnectDB.getConnection();
       PreparedStatement stmt = null;
       ResultSet rs = null;
       MonAn ma = null;

       try {
           String sql = "SELECT MaMonAn, TenMonAn, DonGia, SoLuong,duongDanAnh FROM MonAn WHERE MaMonAn = ?";
           stmt = con.prepareStatement(sql);
           stmt.setString(1, maMonAn);
           rs = stmt.executeQuery();

           if (rs.next()) {
               String maMA = rs.getString("maMonAn");
               String tenMA = rs.getString("tenMonAn");
               double dongia = rs.getDouble("donGia");
               int  soluong = rs.getInt("soLuong");
          
              
             
               ma = new MonAn(maMA, tenMA, dongia, soluong);
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
       return ma;
   }
   public boolean insert(MonAn ma) {
       ConnectDB.getInstance();
       Connection con = ConnectDB.getConnection();
       PreparedStatement stmt = null;
       int n = 0;
       try {
           String sql = "INSERT INTO MonAn (MaMonAn, TenMonAn, DonGia, SoLuong) VALUES (?, ?, ?,?)";
           stmt = con.prepareStatement(sql);
           stmt.setString(1, ma.getMaMonAn());
           stmt.setString(2, ma.getTenMonAn());
           stmt.setDouble(3, ma.getDonGia());
           stmt.setInt(4, ma.getSoLuong());
         
           
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
   public boolean update(MonAn ma) {
	    ConnectDB.getInstance();
	    Connection con = ConnectDB.getConnection();
	    PreparedStatement stmt = null;
	    int n = 0;
	    try {
	        String sql = "UPDATE MonAn SET TenMonAn = ?, DonGia = ?, SoLuong = ?  WHERE maMonAn = ?";
	        stmt = con.prepareStatement(sql);
	        stmt.setString(1, ma.getTenMonAn());
	        stmt.setDouble(2, ma.getDonGia());
	        stmt.setInt(3, ma.getSoLuong());
	        stmt.setString(4, ma.getMaMonAn());
	      

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
   public boolean delete(String maMonAn) {
	    ConnectDB.getInstance();
	    Connection con = ConnectDB.getConnection();
	    PreparedStatement stmt = null;
	    int n = 0;
	    try {
	        String sql = "DELETE FROM MonAn WHERE maMonAn = ?";
	        stmt = con.prepareStatement(sql);
	        stmt.setString(1, maMonAn);
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
   public static List<MonAn> getAllMonAn() {
       ConnectDB.getInstance();
       Connection con = ConnectDB.getConnection();
       PreparedStatement stmt = null;
       ResultSet rs = null;
       List<MonAn> listMonAn = new ArrayList<>();

       try {
           String sql = "SELECT MaMonAn, TenMonAn, DonGia,SoLuong  FROM MonAn";
           stmt = con.prepareStatement(sql);
           rs = stmt.executeQuery();

           while (rs.next()) {
        	   String maMA = rs.getString("maMonAn");
               String tenMA = rs.getString("tenMonAn");
               double dongia = rs.getDouble("donGia");
               int  soluong = rs.getInt("soLuong"); 
            

               // Tạo đối tượng MonAn và thêm vào danh sách
               MonAn monAn = new MonAn(maMA, tenMA, dongia,soluong);
               listMonAn.add(monAn);
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
       return listMonAn;
   }


}
