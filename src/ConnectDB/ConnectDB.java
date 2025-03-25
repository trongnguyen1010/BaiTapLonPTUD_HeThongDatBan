package ConnectDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
    private static Connection con = null; // Biến kết nối
    private static ConnectDB instance = new ConnectDB(); // Singleton instance

    // Lấy instance của ConnectDB (Singleton Pattern)
    public static ConnectDB getInstance() {
        return instance;
    }

    // Kết nối đến cơ sở dữ liệu
    public static Connection getConnection() {
        if (con == null) { // Nếu chưa kết nối, tiến hành kết nối
            try {
                // Đăng ký driver SQL Server
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

                // Kết nối đến cơ sở dữ liệu
                con = DriverManager.getConnection(
                        "jdbc:sqlserver://localhost:1433;databaseName=QuanLyNhaHang;user=sa;password=123456;encrypt=false;trustServerCertificate=true"
                );
            } catch (ClassNotFoundException e) {
                System.out.println("Lỗi: Không tìm thấy driver JDBC.");
                e.printStackTrace();
            } catch (SQLException e) {
                System.out.println("Lỗi kết nối SQL Server: " + e.getMessage());
                e.printStackTrace();
            }
        }
        return con;
    }

    // Đóng kết nối cơ sở dữ liệu
    public static void disconnect() {
        if (con != null) {
            try {
                con.close();
                con = null; // Set con to null after closing it to indicate no connection
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
