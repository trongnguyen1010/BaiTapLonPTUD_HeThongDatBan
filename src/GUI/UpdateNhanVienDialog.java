package GUI;

import javax.swing.*;


import DAO.NhanVienDAO;
import Entity.NhanVien;
import utils.RandomGenerator;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UpdateNhanVienDialog extends javax.swing.JDialog {
    private GUI_QuanLyNhanVien NV_GUI;
    private NhanVien nv;
    private NhanVienDAO NV_DAO = new NhanVienDAO();

    public UpdateNhanVienDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }
	
    public UpdateNhanVienDialog(java.awt.Frame parent, boolean modal, GUI_QuanLyNhanVien NV_GUI, NhanVien nv) {
        super(parent, modal);
        initComponents();
        this.NV_GUI = NV_GUI;
        this.nv = nv;
        fillInput();
    }

    private void fillInput() {
        txtTen.setText(nv.getTen());
        txtSdt.setText(nv.getSdt());
        txtEmail.setText(nv.getEmail());
        cboxGioiTinh.setSelectedItem(nv.getGioiTinh());
        cboxChucVu.setSelectedItem(nv.getChucVu());
    }
    
    private NhanVien getInputFields() {
        String id = nv.getMaNhanVien();
        String ten = txtTen.getText().trim();
        String sdt = txtSdt.getText().trim();
        String email = txtEmail.getText().trim();
        String gioiTinh = cboxGioiTinh.getSelectedItem().toString();
        String chucVu = cboxChucVu.getSelectedItem().toString();

        return new NhanVien(id, ten, email, chucVu, gioiTinh, sdt);
    }
    
    @SuppressWarnings("unchecked")
    private void initComponents() {
        jPanel15 = new javax.swing.JPanel();
        lblDialog = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        lblTen = new javax.swing.JLabel();
        txtTen = new javax.swing.JTextField();
        jPanel19 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        txtSdt = new javax.swing.JTextField();
        jPanel20 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jPanel21 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        cboxGioiTinh = new javax.swing.JComboBox<>();
        jPanel22 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        cboxChucVu = new javax.swing.JComboBox<>();
        jPanel8 = new javax.swing.JPanel();
        btnHuy = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(600, 650));

        jPanel15.setBackground(new java.awt.Color(161, 227, 249));
        jPanel15.setPreferredSize(new java.awt.Dimension(500, 50));
        jPanel15.setLayout(new java.awt.BorderLayout());

        lblDialog.setFont(new java.awt.Font("Roboto Medium", 0, 18));
        lblDialog.setForeground(new java.awt.Color(255, 255, 255));
        lblDialog.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDialog.setText("CẬP NHẬT NHÂN VIÊN");
        jPanel15.add(lblDialog, java.awt.BorderLayout.CENTER);
        getContentPane().add(jPanel15, java.awt.BorderLayout.NORTH);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 16));

        jPanel18.setPreferredSize(new java.awt.Dimension(500, 40));
        jPanel18.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 6, 0));
        lblTen.setText("Tên");
        lblTen.setPreferredSize(new java.awt.Dimension(150, 40));
        txtTen.setPreferredSize(new java.awt.Dimension(330, 40));
        jPanel18.add(lblTen);
        jPanel18.add(txtTen);
        jPanel1.add(jPanel18);

        jPanel19.setPreferredSize(new java.awt.Dimension(500, 40));
        jPanel19.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 6, 0));
        jLabel12.setText("Số điện thoại");
        jLabel12.setPreferredSize(new java.awt.Dimension(150, 40));
        txtSdt.setPreferredSize(new java.awt.Dimension(330, 40));
        jPanel19.add(jLabel12);
        jPanel19.add(txtSdt);
        jPanel1.add(jPanel19);

        jPanel20.setPreferredSize(new java.awt.Dimension(500, 40));
        jPanel20.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 6, 0));
        jLabel13.setText("Email");
        jLabel13.setPreferredSize(new java.awt.Dimension(150, 40));
        txtEmail.setPreferredSize(new java.awt.Dimension(330, 40));
        jPanel20.add(jLabel13);
        jPanel20.add(txtEmail);
        jPanel1.add(jPanel20);

        jPanel21.setPreferredSize(new java.awt.Dimension(500, 40));
        jPanel21.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 6, 0));
        jLabel14.setText("Giới tính");
        jLabel14.setPreferredSize(new java.awt.Dimension(150, 40));
        cboxGioiTinh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nam", "Nữ" }));
        cboxGioiTinh.setPreferredSize(new java.awt.Dimension(200, 40));
        jPanel21.add(jLabel14);
        jPanel21.add(cboxGioiTinh);
        jPanel1.add(jPanel21);
        
        jPanel22.setPreferredSize(new java.awt.Dimension(500, 40));
        jPanel22.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 6, 0));
        jLabel15.setText("Chức vụ");
        jLabel15.setPreferredSize(new java.awt.Dimension(150, 40));
        cboxChucVu = new javax.swing.JComboBox<>();
        cboxChucVu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nhân viên phục vụ", "Quản lý", "Đầu bếp", "Thu ngân" }));
        cboxChucVu.setPreferredSize(new java.awt.Dimension(330, 40));
        jPanel22.add(jLabel15);
        jPanel22.add(cboxChucVu);
        jPanel1.add(jPanel22);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        jPanel8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 8, 5));
        
        btnHuy.setText("HỦY BỎ");
        btnHuy.setPreferredSize(new java.awt.Dimension(200, 40));
        btnHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyActionPerformed(evt);
            }
        });
        jPanel8.add(btnHuy);

        btnUpdate.setText("CẬP NHẬT");
        btnUpdate.setPreferredSize(new java.awt.Dimension(200, 40));
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });
        jPanel8.add(btnUpdate);

        getContentPane().add(jPanel8, java.awt.BorderLayout.PAGE_END);

        pack();
        setLocationRelativeTo(null);
    }
    
    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {
        this.dispose();
    }
    
    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {
        NhanVien nv = getInputFields();
        if (validateInput(nv)) {
            NV_DAO.update(nv);
            if (NV_GUI != null) {
                NV_GUI.loadData();
            }
            this.dispose();
        }
    }
    
    private boolean validateInput(NhanVien nv) {
        if (nv.getTen().isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, "Vui lòng nhập tên nhân viên");
            return false;
        }
        if (nv.getSdt().isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, "Vui lòng nhập số điện thoại");
            return false;
        }
        if (nv.getEmail().isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, "Vui lòng nhập email");
            return false;
        }
        if (nv.getChucVu().isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, "Vui lòng nhập chức vụ");
            return false;
        }
        return true;
    }

    private javax.swing.JButton btnUpdate;
    private javax.swing.JButton btnHuy;
    private javax.swing.JComboBox<String> cboxGioiTinh;
    private javax.swing.JComboBox<String> cboxChucVu;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JLabel lblDialog;
    private javax.swing.JLabel lblTen;
    private javax.swing.JTextField txtTen;
    private javax.swing.JTextField txtSdt;
    private javax.swing.JTextField txtEmail;
} 