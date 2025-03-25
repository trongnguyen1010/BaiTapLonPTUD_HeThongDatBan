//package Gui;
//
//import java.awt.EventQueue;
//import javax.swing.JFrame;
//import javax.swing.JPanel;
//import javax.swing.border.EmptyBorder;
//import java.awt.Color;
//import javax.swing.JLabel;
//import java.awt.Font;
//import javax.swing.JTextField;
//import javax.swing.JButton;
//import javax.swing.ImageIcon;
//import javax.swing.SwingConstants;
//import java.awt.Image;
//import java.awt.SystemColor;
//import java.awt.event.ActionListener;
//import java.awt.event.ActionEvent;
//
//public class FrmDangNhap extends JFrame {
//
//    private static final long serialVersionUID = 1L;
//    private JPanel contentPane;
//    private JTextField txttk;
//    private JTextField txtMk;
//
//    public static void main(String[] args) {
//        EventQueue.invokeLater(() -> {
//            try {
//            	FrmDangNhap frame = new FrmDangNhap();
//                frame.setVisible(true);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        });
//    }
//
//    public FrmDangNhap() {
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setBounds(100, 100, 793, 420);
//        contentPane = new JPanel();
//        contentPane.setBackground(new Color(161, 227, 249));
//        setContentPane(contentPane);
//        contentPane.setLayout(null);
//
//        JPanel panel = createPanel();
//        contentPane.add(panel);
//
//        JLabel lbltenDN = createLabel("Đăng Nhập", 179, 72, 142, 54, new Font("Times New Roman", Font.BOLD, 24), Color.WHITE);
//        panel.add(lbltenDN);
//
//        txttk = createTextField(143, 136, 224, 40);
//        panel.add(txttk);
//
//        txtMk = createTextField(143, 209, 224, 40);
//        panel.add(txtMk);
//
//        panel.add(createLabel("Tài Khoản:", 40, 147, 93, 13, new Font("Times New Roman", Font.BOLD, 18), Color.WHITE));
//        panel.add(createLabel("Mật Khẩu:", 40, 221, 73, 13, new Font("Times New Roman", Font.BOLD, 15), Color.WHITE));
//
//        panel.add(createButton("Đăng Nhập", 200, 282, 120, 21, e -> {}));
//        panel.add(createLabel("Quên mật Khẩu?", 143, 259, 85, 13, new Font("Times New Roman", Font.PLAIN, 10), Color.WHITE));
//        panel.add(createButton("Thoát", 221, 316, 85, 21, e -> {}));
//
//        JLabel lblimg = createImageLabel();
//        contentPane.add(lblimg);
//    }
//
//    private JPanel createPanel() {
//        JPanel panel = new JPanel();
//        panel.setBackground(new Color(161, 227, 249));
//        panel.setBounds(364, 22, 372, 365);
//        panel.setLayout(null);
//        return panel;
//    }
//
//    private JLabel createLabel(String text, int x, int y, int width, int height, Font font, Color color) {
//        JLabel label = new JLabel(text);
//        label.setFont(font);
//        label.setForeground(color);
//        label.setBounds(x, y, width, height);
//        return label;
//    }
//
//    private JTextField createTextField(int x, int y, int width, int height) {
//        JTextField textField = new JTextField();
//        textField.setBackground(SystemColor.inactiveCaptionBorder);
//        textField.setBounds(x, y, width, height);
//        textField.setColumns(10);
//        return textField;
//    }
//
//    private JButton createButton(String text, int x, int y, int width, int height, ActionListener actionListener) {
//        JButton button = new JButton(text);
//        button.setForeground(SystemColor.textHighlightText);
//        button.setBackground(SystemColor.textHighlight);
//        button.setFont(new Font("Times New Roman", Font.BOLD, 15));
//        button.setBounds(x, y, width, height);
//        button.addActionListener(actionListener);
//        return button;
//    }
//
//    private JLabel createImageLabel() {
//        ImageIcon originalIcon = new ImageIcon(FrmDangNhap.class.getResource("/view/image/BTS_N2..png"));
//        Image image = originalIcon.getImage();
//        Image scaledImage = image.getScaledInstance(322, 343, Image.SCALE_SMOOTH);
//        ImageIcon scaledIcon = new ImageIcon(scaledImage);
//
//        JLabel lblimg = new JLabel("");
//        lblimg.setBounds(23, 22, 347, 355);
//        lblimg.setHorizontalAlignment(SwingConstants.CENTER);
//        lblimg.setIcon(scaledIcon);
//        return lblimg;
//    }
//    
//}
package Gui;

import javax.swing.*;
import dao.TaiKhoan_Dao;
import entity.TaiKhoan;
import java.awt.*;
import java.awt.event.*;

public class FrmDangNhap extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtDN;
    private JPasswordField pwd;
    private JButton btnDangNhap, btnThoat;
    private TaiKhoan_Dao dstk;

    public FrmDangNhap() {
        // Initialize the data access object
        this.dstk = new TaiKhoan_Dao();
        
        // Set up JFrame properties
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 793, 420);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(161, 227, 249));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Create and set up components
        JPanel panel = createPanel();
        contentPane.add(panel);

        JLabel lbltenDN = createLabel("Đăng Nhập", 179, 72, 142, 54, new Font("Times New Roman", Font.BOLD, 24), Color.WHITE);
        panel.add(lbltenDN);

        txtDN = createTextField(143, 136, 224, 40);
        panel.add(txtDN);

        pwd = new JPasswordField();
        pwd.setBackground(SystemColor.inactiveCaptionBorder);
        pwd.setBounds(143, 209, 224, 40);
        panel.add(pwd);

        panel.add(createLabel("Tài Khoản:", 40, 147, 93, 13, new Font("Times New Roman", Font.BOLD, 18), Color.WHITE));
        panel.add(createLabel("Mật Khẩu:", 40, 221, 73, 13, new Font("Times New Roman", Font.BOLD, 15), Color.WHITE));

        btnDangNhap = createButton("Đăng Nhập", 200, 282, 120, 21);
        btnDangNhap.addActionListener(this);
        panel.add(btnDangNhap);

        JLabel lblQuenMatKhau = createLabel("Quên mật Khẩu?", 143, 259, 85, 13, new Font("Times New Roman", Font.PLAIN, 10), Color.WHITE);
        panel.add(lblQuenMatKhau);

        btnThoat = createButton("Thoát", 221, 316, 85, 21);
        btnThoat.addActionListener(this);
        panel.add(btnThoat);

        JLabel lblimg = createImageLabel();
        contentPane.add(lblimg);
        
        // Set default button to "Đăng Nhập"
        this.getRootPane().setDefaultButton(btnDangNhap);
        
        // Make the frame visible
        setVisible(true);
    }

    // Create the main panel
    private JPanel createPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(161, 227, 249));
        panel.setBounds(364, 22, 372, 365);
        panel.setLayout(null);
        return panel;
    }

    // Create a label with specific parameters
    private JLabel createLabel(String text, int x, int y, int width, int height, Font font, Color color) {
        JLabel label = new JLabel(text);
        label.setFont(font);
        label.setForeground(color);
        label.setBounds(x, y, width, height);
        return label;
    }

    // Create a text field with specific parameters
    private JTextField createTextField(int x, int y, int width, int height) {
        JTextField textField = new JTextField();
        textField.setBackground(SystemColor.inactiveCaptionBorder);
        textField.setBounds(x, y, width, height);
        textField.setColumns(10);
        return textField;
    }

    // Create a button with specific parameters
    private JButton createButton(String text, int x, int y, int width, int height) {
        JButton button = new JButton(text);
        button.setForeground(SystemColor.textHighlightText);
        button.setBackground(SystemColor.textHighlight);
        button.setFont(new Font("Times New Roman", Font.BOLD, 15));
        button.setBounds(x, y, width, height);
        return button;
    }

    // Create the image label for the background image
    private JLabel createImageLabel() {
        ImageIcon originalIcon = new ImageIcon(FrmDangNhap.class.getResource("/view/image/BTS_N2..png"));
        Image image = originalIcon.getImage();
        Image scaledImage = image.getScaledInstance(322, 343, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        JLabel lblimg = new JLabel("");
        lblimg.setBounds(23, 22, 347, 355);
        lblimg.setHorizontalAlignment(SwingConstants.CENTER);
        lblimg.setIcon(scaledIcon);
        return lblimg;
    }

    // The login method that will be triggered on button click
    public void dangNhap() {
        String tenDN = this.txtDN.getText().toString().trim();
        String mk = new String(this.pwd.getPassword()).trim();
        TaiKhoan tk = this.dstk.getTaiKhoanTheoTen(tenDN);

        // Check if username exists
        if (tk == null) {
            JOptionPane.showMessageDialog(this, "Tài khoản không đúng!\nVui lòng kiểm tra lại.");
            this.txtDN.requestFocus();
        } 
        // Check if password is correct
        else if (!tk.getMatKhau().equalsIgnoreCase(mk)) {
            JOptionPane.showMessageDialog(this, "Mật khẩu không đúng!\nVui lòng kiểm tra lại.");
            this.pwd.requestFocus();
        } 
        // If both username and password are correct, proceed to main screen
        else {
            FrmCapnhatNhanVien frmMain = new FrmCapnhatNhanVien();
            frmMain.setVisible(true);
            this.setVisible(false); // Close login screen
        }
    }

    // The action performed method for handling button events
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o.equals(this.btnThoat)) {
            System.exit(0); // Exit the application
        } else if (o.equals(this.btnDangNhap)) {
            this.dangNhap(); // Call the dangNhap method when login button is clicked
        }
    }

    // Main method to launch the login screen
    public static void main(String[] args) {
        new FrmDangNhap();
    }
}
