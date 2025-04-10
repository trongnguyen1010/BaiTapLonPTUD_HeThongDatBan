package GUI;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 * Lớp test để chạy thử nghiệm giao diện quản lý tài khoản
 */
public class TestQuanLyTaiKhoan {
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    // Cài đặt giao diện giống với hệ điều hành
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                
                // Tạo JFrame để chứa panel quản lý tài khoản
                JFrame frame = new JFrame("Quản Lý Tài Khoản");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                
                // Thêm panel quản lý tài khoản vào frame
                GUI_QuanLyTaiKhoan qltkPanel = new GUI_QuanLyTaiKhoan();
                frame.getContentPane().add(qltkPanel);
                
                // Điều chỉnh kích thước và hiển thị
                frame.setSize(1240, 740); // Kích thước tương tự các màn hình quản lý khác
                frame.setLocationRelativeTo(null); // Hiển thị ở giữa màn hình
                frame.setResizable(true); // Cho phép thay đổi kích thước
                frame.setVisible(true);
            }
        });
    }
} 