package GUI;

import javax.swing.*;


import DAO.KhachHangDAO;
import Entity.KhachHang;
import utils.RandomGenerator;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UpdateKhachHangDialog extends javax.swing.JDialog {
    private GUI_QuanLyKhachHang KH_GUI;
    private KhachHang kh;
    private KhachHangDAO KH_DAO = new KhachHangDAO();

    public UpdateKhachHangDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }
	
    public UpdateKhachHangDialog(java.awt.Frame parent, boolean modal, GUI_QuanLyKhachHang NV_GUI, KhachHang kh) {
        super(parent, modal);
        initComponents();
        this.KH_GUI = NV_GUI;
        this.kh = kh;
        fillInput();
    }

    private void fillInput() {
        txtHoTen.setText(kh.getTenKhachHang());
        txtSdt.setText(kh.getSdt());
        txtEmail.setText(kh.getEmail());
        cboxGioiTinh.setSelectedItem(kh.getGioiTinh());
    }
    
    private KhachHang getInputFields() {
        String id = kh.getMaKhachHang();
        String hoTen = txtHoTen.getText().trim();
        String sdt = txtSdt.getText().trim();
        String email = txtSdt.getText().trim();
        String gioiTinh = cboxGioiTinh.getSelectedItem().toString();

        return new KhachHang(id, hoTen, email, gioiTinh, sdt);
    }
    @SuppressWarnings("unchecked")
    private void initComponents() {
        jPanel15 = new javax.swing.JPanel();
        lblDialog = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        lblHoTen = new javax.swing.JLabel();
        txtHoTen = new javax.swing.JTextField();
        jPanel19 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        txtSdt = new javax.swing.JTextField();
        jPanel20 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jPanel21 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        cboxGioiTinh = new javax.swing.JComboBox<>();
        jPanel8 = new javax.swing.JPanel();
        btnHuy = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(600, 600));

        jPanel15.setBackground(new java.awt.Color(161, 227, 249));
        jPanel15.setPreferredSize(new java.awt.Dimension(500, 50));
        jPanel15.setLayout(new java.awt.BorderLayout());

        lblDialog.setFont(new java.awt.Font("Roboto Medium", 0, 18));
        lblDialog.setForeground(new java.awt.Color(255, 255, 255));
        lblDialog.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDialog.setText("CẬP NHẬT KHÁCH HÀNG");
        jPanel15.add(lblDialog, java.awt.BorderLayout.CENTER);
        getContentPane().add(jPanel15, java.awt.BorderLayout.NORTH);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 16));

        jPanel18.setPreferredSize(new java.awt.Dimension(500, 40));
        jPanel18.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 6, 0));
        lblHoTen.setText("Họ tên");
        lblHoTen.setPreferredSize(new java.awt.Dimension(150, 40));
        txtHoTen.setPreferredSize(new java.awt.Dimension(330, 40));
        jPanel18.add(lblHoTen);
        jPanel18.add(txtHoTen);
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
    
    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        this.dispose();
    }
    
    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
      	KhachHang kh = getInputFields();
        KH_DAO.update(kh);
        this.dispose();
    }

    private javax.swing.JButton btnUpdate;
    private javax.swing.JButton btnHuy;
    private javax.swing.JComboBox<String> cboxGioiTinh;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JLabel lblDialog;
    private javax.swing.JLabel lblHoTen;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JTextField txtSdt;
    private javax.swing.JTextField txtEmail;
}
