package GUI;

import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;
import DAO.TaiKhoanDAO;
import Entity.NhanVien;
import Entity.TaiKhoan;

public class GUI_TimKiemTaiKhoan extends javax.swing.JPanel {
    private TaiKhoanDAO TK_DAO = new TaiKhoanDAO();

    public GUI_TimKiemTaiKhoan() {
        initComponents();
        loadData();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jTextField1 = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jTextField2 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 255, 255));
        setForeground(new java.awt.Color(161, 227, 249));
        setDoubleBuffered(false);
        setEnabled(false);
        setFocusable(false);
        setMaximumSize(new java.awt.Dimension(1220, 700));
        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(1220, 700));
        setRequestFocusEnabled(false);

        jPanel1.setBackground(new java.awt.Color(161, 227, 249));
        jPanel1.setPreferredSize(new java.awt.Dimension(1220, 130));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mã tài khoản", "Tên đăng nhập", "Chủ sở hữu" }));
        jComboBox1.setMaximumSize(new java.awt.Dimension(184, 65));
        jComboBox1.setMinimumSize(new java.awt.Dimension(184, 65));
        jComboBox1.setPreferredSize(new java.awt.Dimension(184, 65));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(161, 227, 249));
        jButton1.setIcon(new ImageIcon(GUI_TimKiemTaiKhoan.class.getResource("/view/icon/icon_tim.png")));
        jButton1.setText("TÌM KIẾM NÂNG CAO");
        jButton1.setAlignmentY(0.0F);
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.setDefaultCapable(false);
        jButton1.setFocusPainted(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btnReset.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnReset.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        btnReset.setText("RESET");
        btnReset.setIcon(new ImageIcon(GUI_TimKiemTaiKhoan.class.getResource("/view/icon/reset.png")));
        btnReset.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnReset.setFocusPainted(false);
        btnReset.setDefaultCapable(false);
        btnReset.setContentAreaFilled(false);
        btnReset.setBorderPainted(false);
        btnReset.setBackground(new java.awt.Color(161, 227, 249));
        btnReset.setAlignmentY(0.0f);
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });
        jTextField1.setText("Tìm kiếm");
        jTextField1.setMaximumSize(new java.awt.Dimension(362, 65));
        jTextField1.setMinimumSize(new java.awt.Dimension(362, 65));
        jTextField1.setName("");
        jTextField1.setPreferredSize(new java.awt.Dimension(362, 65));
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });


        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(42)
                    .addComponent(jButton1)
                    .addGap(37)
                    .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 255, Short.MAX_VALUE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(41)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(44))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(27)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(38, Short.MAX_VALUE))
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(34)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE)
                        .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(22))
        );

        jPanel2.setBackground(new java.awt.Color(0, 163, 239));
        jPanel2.setPreferredSize(new java.awt.Dimension(1220, 60));

        jTextField2.setBackground(new java.awt.Color(0, 163, 239));
        jTextField2.setFont(new java.awt.Font("Segoe UI", 0, 24));
        jTextField2.setText("TÌM KIẾM TÀI KHOẢN");
        jTextField2.setBorder(null);
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 60, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jScrollPane1.setMaximumSize(null);
        jScrollPane1.setMinimumSize(null);
        jScrollPane1.setPreferredSize(null);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã tài khoản", "Tên đăng nhập", "Mật khẩu", "Chủ sở hữu", "Chức vụ"
            }
        ));
        jTable1.setPreferredSize(null);
        jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTable1.setFillsViewportHeight(true);
        
        // Căn giữa nội dung trong các ô và tiêu đề cột
        javax.swing.table.DefaultTableCellRenderer centerRenderer = new javax.swing.table.DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(javax.swing.JLabel.CENTER);
        
        // Áp dụng căn giữa cho tất cả các cột
        for (int i = 0; i < jTable1.getColumnCount(); i++) {
            jTable1.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        
        // Căn giữa tiêu đề cột
        ((javax.swing.table.DefaultTableCellRenderer)jTable1.getTableHeader().getDefaultRenderer())
            .setHorizontalAlignment(javax.swing.JLabel.CENTER);
            
        // Đặt kích thước ưu tiên cho các cột
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(40);  // STT - giảm xuống
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(100); // Mã tài khoản
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(180); // Tên đăng nhập - tăng lên
            jTable1.getColumnModel().getColumn(3).setPreferredWidth(150); // Mật khẩu - tăng lên
            jTable1.getColumnModel().getColumn(4).setPreferredWidth(200); // Chủ sở hữu
            jTable1.getColumnModel().getColumn(5).setPreferredWidth(150); // Chức vụ
        }
        
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 1226, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1226, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1226, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 510, Short.MAX_VALUE))
        );
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        // Hiển thị hộp thoại tìm kiếm nâng cao
    	TimKiemTaiKhoanDialog dialog = new TimKiemTaiKhoanDialog(null, true, this);
        dialog.setVisible(true);
    }

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {
        // Xóa nội dung trong ô tìm kiếm
        loadData();
    }

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {
    	 String searchText = jTextField1.getText().trim();
         String searchType = (String) jComboBox1.getSelectedItem();
         
         DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
         model.setRowCount(0);
         
         List<TaiKhoan> taiKhoans = TK_DAO.searchTaiKhoan(searchType, searchText);
         int stt = 1;
         
         for (TaiKhoan tk : taiKhoans) {
             model.addRow(new Object[]{
                 stt++,
                 tk.getMaTaiKhoan(),
                 tk.getTenDangNhap(),
                 tk.getMatKhau(),
                 tk.getNhanVien().getTen(),
                 tk.getNhanVien().getChucVu()
             });
         }
    }

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    public void loadData() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0); // Xóa tất cả các dòng hiện có
        
        List<TaiKhoan> listTaiKhoan = TK_DAO.getAllTaiKhoan();
        int stt = 1;
        
        for(TaiKhoan tk : listTaiKhoan) {
            model.addRow(new Object[] {
                stt++,
                tk.getMaTaiKhoan(),
                tk.getTenDangNhap(),
                tk.getMatKhau(),
                tk.getNhanVien().getTen(),
                tk.getNhanVien().getChucVu()
            });
        }
    }

    public void hienThiKetQuaTimKiem(List<TaiKhoan> ketQua) {
    	DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0); // Xóa tất cả các dòng hiện có
        int stt = 1;
        
        for(TaiKhoan tk : ketQua) {
            model.addRow(new Object[] {
                stt++,
                tk.getMaTaiKhoan(),
                tk.getTenDangNhap(),
                tk.getMatKhau(),
                tk.getNhanVien().getTen(),
                tk.getNhanVien().getChucVu()
            });
        }
    }
    
    // Variables declaration - do not modify
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JButton btnReset;
    // End of variables declaration
}