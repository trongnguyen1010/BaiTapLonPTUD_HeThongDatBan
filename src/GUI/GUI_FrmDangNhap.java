package GUI;

import java.awt.EventQueue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import DAO.TaiKhoanDAO;
import Entity.TaiKhoan;

public class GUI_FrmDangNhap extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txttk;
    private JPasswordField txtMk; // Đổi sang JPasswordField để bảo mật hơn

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                GUI_FrmDangNhap frame = new GUI_FrmDangNhap();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public GUI_FrmDangNhap() {
        setTitle("Đăng Nhập - Quản Lý Nhà Hàng");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 793, 420);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(161, 227, 249));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panel = createPanel();
        contentPane.add(panel);

        JLabel lbltenDN = createLabel("Đăng Nhập", 179, 72, 142, 54, new Font("Times New Roman", Font.BOLD, 24), Color.WHITE);
        panel.add(lbltenDN);

        txttk = createTextField(143, 136, 224, 40);
        panel.add(txttk);

        txtMk = createPasswordField(143, 209, 224, 40);
        panel.add(txtMk);

        panel.add(createLabel("Tài Khoản:", 40, 147, 93, 13, new Font("Times New Roman", Font.BOLD, 18), Color.WHITE));
        panel.add(createLabel("Mật Khẩu:", 40, 221, 73, 13, new Font("Times New Roman", Font.BOLD, 15), Color.WHITE));

        // Thêm sự kiện vào nút Đăng Nhập
        JButton btnDangNhap = createButton("Đăng Nhập", 200, 282, 120, 21, e -> dangNhap());
        panel.add(btnDangNhap);

        panel.add(createLabel("Quên mật Khẩu?", 143, 259, 85, 13, new Font("Times New Roman", Font.PLAIN, 10), Color.WHITE));

        JButton btnThoat = createButton("Thoát", 221, 316, 85, 21, e -> System.exit(0));
        panel.add(btnThoat);

        JLabel lblimg = createImageLabel();
        contentPane.add(lblimg);
    }

    private JPanel createPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(161, 227, 249));
        panel.setBounds(364, 22, 372, 365);
        panel.setLayout(null);
        return panel;
    }

    private JLabel createLabel(String text, int x, int y, int width, int height, Font font, Color color) {
        JLabel label = new JLabel(text);
        label.setFont(font);
        label.setForeground(color);
        label.setBounds(x, y, width, height);
        return label;
    }

    private JTextField createTextField(int x, int y, int width, int height) {
        JTextField textField = new JTextField();
        textField.setBackground(SystemColor.inactiveCaptionBorder);
        textField.setBounds(x, y, width, height);
        textField.setColumns(10);
        return textField;
    }

    private JPasswordField createPasswordField(int x, int y, int width, int height) {
        JPasswordField passwordField = new JPasswordField();
        passwordField.setBackground(SystemColor.inactiveCaptionBorder);
        passwordField.setBounds(x, y, width, height);
        passwordField.setColumns(10);
        return passwordField;
    }

    private JButton createButton(String text, int x, int y, int width, int height, ActionListener actionListener) {
        JButton button = new JButton(text);
        button.setForeground(SystemColor.textHighlightText);
        button.setBackground(SystemColor.textHighlight);
        button.setFont(new Font("Times New Roman", Font.BOLD, 15));
        button.setBounds(x, y, width, height);
        button.addActionListener(actionListener);
        return button;
    }

    private JLabel createImageLabel() {
        JLabel lblimg = new JLabel("Logo");
        lblimg.setBounds(23, 22, 347, 355);
        lblimg.setHorizontalAlignment(SwingConstants.CENTER);
        return lblimg;
    }

    // Xử lý đăng nhập
    private void dangNhap() {
        String tenDangNhap = txttk.getText().trim();
        String matKhau = new String(txtMk.getPassword()).trim(); // Lấy mật khẩu từ JPasswordField

        if (tenDangNhap.isEmpty() || matKhau.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập tài khoản và mật khẩu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        TaiKhoanDAO taiKhoanDao = new TaiKhoanDAO();
        TaiKhoan tk = taiKhoanDao.getTaiKhoanTheoTenDangNhap(tenDangNhap);

        if (tk == null) {
            JOptionPane.showMessageDialog(this, "Tài khoản không đúng!\nVui lòng kiểm tra lại.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            txttk.requestFocus();
        } else if (!tk.getMatKhau().equals(matKhau)) {
            JOptionPane.showMessageDialog(this, "Mật khẩu không đúng!\nVui lòng kiểm tra lại.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            txtMk.requestFocus();
        } else {
            JOptionPane.showMessageDialog(this, "Đăng nhập thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            GUI_homepage homepage = new GUI_homepage();
            homepage.setVisible(true);
            this.setVisible(false);
        }
    }
    
}

