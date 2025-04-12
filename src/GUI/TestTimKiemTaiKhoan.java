package GUI;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 * Lớp test để chạy thử nghiệm giao diện tìm kiếm tài khoản
 */
public class TestTimKiemTaiKhoan {
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    // Cài đặt giao diện giống với hệ điều hành
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                
                // Tạo JFrame để chứa panel tìm kiếm tài khoản
                JFrame frame = new JFrame("Tìm Kiếm Tài Khoản");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                
                // Thêm panel tìm kiếm tài khoản vào frame
                GUI_TimKiemTaiKhoan timKiemTaiKhoan = new GUI_TimKiemTaiKhoan();
                frame.getContentPane().add(timKiemTaiKhoan);
                
                // Điều chỉnh kích thước và hiển thị
                frame.setSize(1240, 740);
                frame.setLocationRelativeTo(null); // Hiển thị ở giữa màn hình
                frame.setResizable(true); // Cho phép thay đổi kích thước
                frame.setVisible(true);
            }
        });
    }
} 