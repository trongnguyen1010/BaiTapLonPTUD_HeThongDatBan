package GUI;

import GUI.GUI_TimKiemTaiKhoan;
import Entity.TaiKhoan;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import utils.RandomGenerator;
import DAO.TaiKhoanDAO;
import javax.swing.JComboBox;

public class TimKiemTaiKhoanDialog extends javax.swing.JDialog {
    private GUI_TimKiemTaiKhoan TK_GUI;
    private TaiKhoanDAO TK_DAO = new TaiKhoanDAO();

    /**
     * @wbp.parser.constructor
     */
    public TimKiemTaiKhoanDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    public TimKiemTaiKhoanDialog(java.awt.Frame parent, boolean modal, GUI_TimKiemTaiKhoan TK_GUI) {
        super(parent, modal);
        initComponents();
        this.TK_GUI = TK_GUI;
    }
   
    @SuppressWarnings("unchecked")
    private void initComponents() {
        jPanel15 = new javax.swing.JPanel();
        lblDialog = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        jPanel18.setBounds(43, 16, 500, 40);
        lblTen = new javax.swing.JLabel();
        lblTen.setBounds(6, 0, 150, 40);
        txtTen = new javax.swing.JTextField();
        txtTen.setBounds(162, 0, 330, 40);
        jPanel19 = new javax.swing.JPanel();
        jPanel19.setBounds(43, 72, 500, 40);
        jLabel12 = new javax.swing.JLabel();
        jLabel12.setBounds(6, 0, 150, 40);
        txtTdn = new javax.swing.JTextField();
        txtTdn.setBounds(162, 0, 330, 40);
        jPanel22 = new javax.swing.JPanel();
        jPanel22.setBounds(43, 122, 500, 40);
        jLabel15 = new javax.swing.JLabel();
        jLabel15.setBounds(6, 0, 150, 40);
        new javax.swing.JComboBox<>();
        jPanel8 = new javax.swing.JPanel();
        btnHuy = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(600, 650));

        jPanel15.setBackground(new java.awt.Color(161, 227, 249));
        jPanel15.setPreferredSize(new java.awt.Dimension(500, 50));
        jPanel15.setLayout(new java.awt.BorderLayout());

        lblDialog.setFont(new java.awt.Font("Roboto Medium", 0, 18));
        lblDialog.setForeground(new java.awt.Color(255, 255, 255));
        lblDialog.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDialog.setText("TÌM KIẾM TÀI KHOẢN");
        jPanel15.add(lblDialog, java.awt.BorderLayout.CENTER);
        getContentPane().add(jPanel15, java.awt.BorderLayout.NORTH);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(null);

        jPanel18.setPreferredSize(new java.awt.Dimension(500, 40));
        lblTen.setText("Chủ sở hữu");
        lblTen.setPreferredSize(new java.awt.Dimension(150, 40));
        txtTen.setPreferredSize(new java.awt.Dimension(330, 40));
        jPanel18.setLayout(null);
        jPanel18.add(lblTen);
        jPanel18.add(txtTen);
        jPanel1.add(jPanel18);

        jPanel19.setPreferredSize(new java.awt.Dimension(500, 40));
        jLabel12.setText("Tên đăng nhập");
        jLabel12.setPreferredSize(new java.awt.Dimension(150, 40));
        txtTdn.setPreferredSize(new java.awt.Dimension(330, 40));
        jPanel19.setLayout(null);
        jPanel19.add(jLabel12);
        jPanel19.add(txtTdn);
        jPanel1.add(jPanel19);
        
        jPanel22.setPreferredSize(new java.awt.Dimension(500, 40));
        jLabel15.setText("Chức vụ");
        jLabel15.setPreferredSize(new java.awt.Dimension(150, 40));
        cboxChucVu_1 = new javax.swing.JComboBox<>();
        cboxChucVu_1.setBounds(162, 0, 330, 40);
        cboxChucVu_1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nhân viên phục vụ", "Quản lý", "Đầu bếp", "Thu ngân" }));
        cboxChucVu_1.setPreferredSize(new java.awt.Dimension(330, 40));
        jPanel22.setLayout(null);
        jPanel22.add(jLabel15);
        jPanel22.add(cboxChucVu_1);
        jPanel1.add(jPanel22);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);
        chkTen = new javax.swing.JCheckBox();
        chkTen.setBounds(549, 26, 20, 20);
        jPanel1.add(chkTen);
        chkTen.setBackground(new java.awt.Color(255, 255, 255));
        chkTen.setPreferredSize(new java.awt.Dimension(20, 20));
        chkChucVu = new javax.swing.JCheckBox();
        chkChucVu.setBounds(549, 134, 20, 20);
        jPanel1.add(chkChucVu);
        chkChucVu.setBackground(new java.awt.Color(255, 255, 255));
        chkChucVu.setPreferredSize(new java.awt.Dimension(20, 20));
        chkTdn = new javax.swing.JCheckBox();
        chkTdn.setBounds(549, 84, 20, 20);
        jPanel1.add(chkTdn);
        chkTdn.setBackground(new java.awt.Color(255, 255, 255));
        chkTdn.setPreferredSize(new java.awt.Dimension(20, 20));

        jPanel8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 8, 5));
        btnHuy.setText("HỦY BỎ");
        btnHuy.setPreferredSize(new java.awt.Dimension(200, 40));
        btnHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyActionPerformed(evt);
            }
        });
        jPanel8.add(btnHuy);
        
        btnAdd.setText("TÌM KIẾM");
        btnAdd.setPreferredSize(new java.awt.Dimension(200, 40));
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });
        jPanel8.add(btnAdd);
        getContentPane().add(jPanel8, java.awt.BorderLayout.PAGE_END);

        pack();
        setLocationRelativeTo(null);
    }
    
    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {
        this.dispose();
    }
    
    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {
    	 // Lấy giá trị từ các text field nếu checkbox tương ứng được chọn
        String hoTen = chkTen.isSelected() ? txtTen.getText().trim() : null;
        String TenDangNhap = chkTdn.isSelected() ? txtTdn.getText().trim() : null;
        String chucVu = chkChucVu.isSelected() ? cboxChucVu_1.getSelectedItem().toString() : null;

        // Gọi phương thức tìm kiếm từ DAO
        List<TaiKhoan> ketQua = TK_DAO.timKiemTaiKhoan(hoTen, TenDangNhap, chucVu);
        
        // Hiển thị kết quả tìm kiếm (có thể cập nhật bảng hoặc giao diện khác)
        if (TK_GUI != null) {
        	TK_GUI.hienThiKetQuaTimKiem(ketQua);
        }

        // Đóng dialog
        this.dispose();
    }
    

    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnHuy;
    private JComboBox<String> cboxChucVu_1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JLabel lblDialog;
    private javax.swing.JLabel lblTen;
    private javax.swing.JTextField txtTen;
    private javax.swing.JTextField txtTdn;
    private javax.swing.JCheckBox chkTen;
    private javax.swing.JCheckBox chkTdn;
    private javax.swing.JCheckBox chkChucVu;
}