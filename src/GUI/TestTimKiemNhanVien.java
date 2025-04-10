package GUI;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 * Lớp test để chạy thử nghiệm giao diện tìm kiếm nhân viên
 */
public class TestTimKiemNhanVien {
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    // Cài đặt giao diện giống với hệ điều hành
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                
                // Tạo JFrame để chứa panel tìm kiếm nhân viên
                JFrame frame = new JFrame("Tìm Kiếm Nhân Viên");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                
                // Thêm panel tìm kiếm nhân viên vào frame
                GUI_TimKiemNhanVien timKiemNhanVien = new GUI_TimKiemNhanVien();
                frame.getContentPane().add(timKiemNhanVien);
                
                // Điều chỉnh kích thước và hiển thị
                frame.setSize(1240, 740);
                frame.setLocationRelativeTo(null); // Hiển thị ở giữa màn hình
                frame.setResizable(true); // Cho phép thay đổi kích thước
                frame.setVisible(true);
            }
        });
    }
} 