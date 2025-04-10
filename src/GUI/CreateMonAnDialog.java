package GUI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.swing.*;

import DAO.MonAn_DAO;
import Entity.MonAn;
import utils.RandomGenerator;

public class CreateMonAnDialog extends javax.swing.JDialog {

    private GUI_QuanLyMonAn MA_GUI;
    private MonAn_DAO MA_DAO = new MonAn_DAO();

    public CreateMonAnDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    public CreateMonAnDialog(java.awt.Frame parent, GUI_QuanLyMonAn MA_GUI) {
        super(parent, true); // true = modal
        this.MA_GUI = MA_GUI;
        initComponents();
    }
//
    private byte[] readImageToBytes(String path) {
        try {
            return Files.readAllBytes(Paths.get(path));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private MonAn getInputFields() {
        String id = txtmaMonAn.getText().trim(); 
        String tenMonAn = txtTenMonAn.getText().trim();
        String duongDan = txtDuongDanAnh.getText().trim();
        

        if (tenMonAn.isEmpty() || txtDonGia.getText().trim().isEmpty() || txtSoLuong.getText().trim().isEmpty() || duongDan.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin.");
            return null;
        }

        try {
            double donGia = Double.parseDouble(txtDonGia.getText().trim());
            int soLuong = Integer.parseInt(txtSoLuong.getText().trim());
            byte[] hinhAnh = readImageToBytes(duongDan);
            

            if (hinhAnh == null) {
                JOptionPane.showMessageDialog(this, "Không thể đọc ảnh từ đường dẫn.");
                return null;
            }

            return new MonAn(id, tenMonAn, donGia, soLuong, hinhAnh);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Đơn giá và số lượng phải là số.");
            return null;
        }
    }


    private void initComponents() {
        jPanelTitle = new JPanel();
        lblDialog = new JLabel();
        jPanelContent = new JPanel();
        jPanelTen = new JPanel();
      
        txtmaMonAn = new JTextField();
        lblTenMonAn = new JLabel();
        txtTenMonAn = new JTextField();
        jPanelDonGia = new JPanel();
        lblDonGia = new JLabel();
        txtDonGia = new JTextField();
        
        lblduongDanAnh= new JLabel();
        txtDuongDanAnh= new JTextField();
        
        jPanelSoLuong = new JPanel();
        lblSoLuong = new JLabel();
        txtSoLuong = new JTextField();
        jPanelButtons = new JPanel();
        
        jPanelduongDanAnh = new JPanel();

        btnHuy = new JButton();
        btnAdd = new JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(500, 400));

        // Title
        jPanelTitle.setBackground(new Color(161, 227, 249));
        jPanelTitle.setPreferredSize(new java.awt.Dimension(500, 50));
        jPanelTitle.setLayout(new java.awt.BorderLayout());
        lblDialog.setFont(new java.awt.Font("Roboto Medium", 0, 18));
        lblDialog.setHorizontalAlignment(SwingConstants.CENTER);
        lblDialog.setText("THÊM MÓN ĂN");
        lblDialog.setForeground(Color.WHITE);
        jPanelTitle.add(lblDialog, java.awt.BorderLayout.CENTER);
        getContentPane().add(jPanelTitle, java.awt.BorderLayout.NORTH);

        // Content
        jPanelContent.setBackground(Color.WHITE);
        jPanelContent.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 6, 16));
        
        


        jPanelTen.setPreferredSize(new java.awt.Dimension(450, 40));
        jPanelTen.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 6, 0));
        lblTenMonAn.setText("Tên món ăn:");
        lblTenMonAn.setPreferredSize(new java.awt.Dimension(150, 40));
        txtTenMonAn.setPreferredSize(new java.awt.Dimension(280, 40));
        jPanelTen.add(lblTenMonAn);
        jPanelTen.add(txtTenMonAn);
        jPanelContent.add(jPanelTen);

        jPanelDonGia.setPreferredSize(new java.awt.Dimension(450, 40));
        jPanelDonGia.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 6, 0));
        lblDonGia.setText("Đơn giá:");
        lblDonGia.setPreferredSize(new java.awt.Dimension(150, 40));
        txtDonGia.setPreferredSize(new java.awt.Dimension(280, 40));
        jPanelDonGia.add(lblDonGia);
        jPanelDonGia.add(txtDonGia);
        jPanelContent.add(jPanelDonGia);

        jPanelSoLuong.setPreferredSize(new java.awt.Dimension(450, 40));
        jPanelSoLuong.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 6, 0));
        lblSoLuong.setText("Số lượng:");
        lblSoLuong.setPreferredSize(new java.awt.Dimension(150, 40));
        txtSoLuong.setPreferredSize(new java.awt.Dimension(280, 40));
        jPanelSoLuong.add(lblSoLuong);
        jPanelSoLuong.add(txtSoLuong);
        jPanelContent.add(jPanelSoLuong);

        jPanelduongDanAnh.setPreferredSize(new java.awt.Dimension(450, 40));
        jPanelduongDanAnh.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 6, 0));
        lblduongDanAnh.setText("duong dan anh ");
        lblduongDanAnh.setPreferredSize(new java.awt.Dimension(150, 40));
        txtDuongDanAnh.setPreferredSize(new java.awt.Dimension(280, 40));
        jPanelduongDanAnh.add(lblduongDanAnh);
        jPanelduongDanAnh.add(txtDuongDanAnh);
        jPanelContent.add(jPanelduongDanAnh);

        getContentPane().add(jPanelContent, java.awt.BorderLayout.CENTER);

        // Buttons
        jPanelButtons.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 8, 10));
        btnHuy.setText("HỦY BỎ");
        btnHuy.setPreferredSize(new java.awt.Dimension(200, 40));
        btnHuy.addActionListener(evt -> dispose());
        jPanelButtons.add(btnHuy);

        btnAdd.setText("THÊM");
        btnAdd.setPreferredSize(new java.awt.Dimension(200, 40));
        btnAdd.setBackground(Color.WHITE);
        btnAdd.setForeground(Color.BLACK);
        btnAdd.addActionListener((ActionEvent evt) -> {
            MonAn ma = getInputFields();
            if (ma != null) {
                MA_DAO.insert(ma);
                if (MA_GUI != null) MA_GUI.loadData();
                dispose();
            }
        });

        jPanelButtons.add(btnAdd);

        getContentPane().add(jPanelButtons, java.awt.BorderLayout.SOUTH);
        pack();
        setLocationRelativeTo(null);
        String randomId = RandomGenerator.getRandomId();
        txtmaMonAn.setText(randomId);

    }

    private JPanel jPanelTitle, jPanelContent, jPanelTen, jPanelDonGia, jPanelSoLuong, jPanelButtons,jPanelduongDanAnh;
    private JLabel lblDialog, lblTenMonAn, lblDonGia, lblSoLuong,lblduongDanAnh;
    private JTextField txtTenMonAn, txtDonGia, txtSoLuong,txtDuongDanAnh,txtmaMonAn;
    private JButton btnAdd, btnHuy;
}
