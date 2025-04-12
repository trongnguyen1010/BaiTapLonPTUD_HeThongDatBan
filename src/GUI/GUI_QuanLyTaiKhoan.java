package GUI;

import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;
import DAO.TaiKhoanDAO;
import Entity.TaiKhoan;

public class GUI_QuanLyTaiKhoan extends javax.swing.JPanel {
    private TaiKhoanDAO TK_DAO = new TaiKhoanDAO();

    public GUI_QuanLyTaiKhoan() {
        initComponents();
        loadData();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
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

        jButton1.setBackground(new java.awt.Color(161, 227, 249));
        jButton1.setIcon(new ImageIcon(GUI_QuanLyTaiKhoan.class.getResource("/view/icon/icon_capnhat.png")));
        jButton1.setText("CẬP NHẬT");
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

        jButton4.setBackground(new java.awt.Color(161, 227, 249));
        jButton4.setIcon(new ImageIcon(GUI_QuanLyTaiKhoan.class.getResource("/view/icon/icon_xoa.png")));
        jButton4.setText("XÓA");
        jButton4.setAlignmentY(0.0F);
        jButton4.setBorderPainted(false);
        jButton4.setContentAreaFilled(false);
        jButton4.setDefaultCapable(false);
        jButton4.setFocusPainted(false);
        jButton4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton4.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jButton4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(161, 227, 249));
        jButton3.setIcon(new ImageIcon(GUI_QuanLyTaiKhoan.class.getResource("/view/icon/icon_them.png")));
        jButton3.setText("THÊM");
        jButton3.setAlignmentY(0.0F);
        jButton3.setBorderPainted(false);
        jButton3.setContentAreaFilled(false);
        jButton3.setDefaultCapable(false);
        jButton3.setFocusPainted(false);
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton3.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jButton3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mã tài khoản", "Tên đăng nhập", "Chủ sở hữu" }));
        jComboBox1.setMaximumSize(new java.awt.Dimension(184, 65));
        jComboBox1.setMinimumSize(new java.awt.Dimension(184, 65));
        jComboBox1.setPreferredSize(new java.awt.Dimension(184, 65));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
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
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(jButton3)
                .addGap(30, 30, 30)
                .addComponent(jButton1)
                .addGap(31, 31, 31)
                .addComponent(jButton4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(61, 61, 61))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(0, 163, 239));
        jPanel2.setPreferredSize(new java.awt.Dimension(1220, 60));

        jTextField2.setBackground(new java.awt.Color(0, 163, 239));
        jTextField2.setFont(new java.awt.Font("Segoe UI", 0, 24));
        jTextField2.setText("DANH SÁCH THÔNG TIN TÀI KHOẢN");
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
        // Xử lý sự kiện cập nhật tài khoản
        int selectedRow = jTable1.getSelectedRow();
        if(selectedRow != -1) {
            String maTaiKhoan = jTable1.getValueAt(selectedRow, 1).toString();
            TaiKhoan tk = TK_DAO.getTaiKhoanById(maTaiKhoan);
            if(tk != null) {
                // Gọi dialog cập nhật tài khoản
                UpdateTaiKhoanDialog dialog = new UpdateTaiKhoanDialog(null, true, this, tk);
                dialog.setVisible(true);
            }
        } else {
            javax.swing.JOptionPane.showMessageDialog(this, "Vui lòng chọn tài khoản cần cập nhật!");
        }
    }

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {
        // Xử lý sự kiện xóa tài khoản
        int selectedRow = jTable1.getSelectedRow();
        if(selectedRow != -1) {
            String maTaiKhoan = jTable1.getValueAt(selectedRow, 1).toString();
            int confirm = javax.swing.JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa tài khoản này?", "Xác nhận xóa", javax.swing.JOptionPane.YES_NO_OPTION);
            if(confirm == javax.swing.JOptionPane.YES_OPTION) {
                boolean result = TK_DAO.delete(maTaiKhoan);
                if(result) {
                    javax.swing.JOptionPane.showMessageDialog(this, "Xóa tài khoản thành công!");
                    loadData();
                } else {
                    javax.swing.JOptionPane.showMessageDialog(this, "Xóa tài khoản thất bại!");
                }
            }
        }
    }

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {
        // Xử lý sự kiện thêm tài khoản
        CreateTaiKhoanDialog dialog = new CreateTaiKhoanDialog(null, true, this);
        dialog.setVisible(true);
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

    // Variables declaration - do not modify
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration
} 