package GUI;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 * Lớp test để chạy thử nghiệm giao diện quản lý nhân viên
 */
public class TestGUINhanVien {
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    // Cài đặt giao diện giống với hệ điều hành
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                
                // Tạo JFrame để chứa panel quản lý nhân viên
                JFrame frame = new JFrame("Quản Lý Nhân Viên");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                
                // Thêm panel quản lý nhân viên vào frame
                GUI_QuanLyNhanVien quanLyNhanVien = new GUI_QuanLyNhanVien();
                frame.getContentPane().add(quanLyNhanVien);
                
                // Điều chỉnh kích thước và hiển thị
                frame.setSize(1240, 740);
                frame.setLocationRelativeTo(null); // Hiển thị ở giữa màn hình
                frame.setResizable(true); // Cho phép thay đổi kích thước
                frame.setVisible(true);
            }
        });
    }
} 