package GUI;

import javax.swing.*;
import java.awt.*;

public class GUI_HoaDon extends JPanel {

    
    public GUI_HoaDon() {
        initComponents();
    }
    
    
    private void initComponents() {

    	// Khai báo các thành phần chính
    	mainPanel = new javax.swing.JPanel();
    	sanPhamPanel = new javax.swing.JPanel();      // Panel chứa thông tin sản phẩm (thuốc)
    	jPanel15 = new javax.swing.JPanel();          // Header của panel sản phẩm
    	lblThuoc = new javax.swing.JLabel();          // Tiêu đề: "Thông tin thuốc"

    	jPanel16 = new javax.swing.JPanel();          // Phần chứa hình và thông tin thuốc
    	jPanel22 = new javax.swing.JPanel();          // Panel chứa ảnh thuốc
    	txtHinhAnh = new javax.swing.JLabel();        // Label hiển thị ảnh thuốc

    	jPanel24 = new javax.swing.JPanel();          // Panel chứa thông tin chi tiết thuốc
//
    	// Mỗi panel cho 1 dòng thông tin
    	jPanel17 = new javax.swing.JPanel();          // Mã thuốc
    	jLabel10 = new javax.swing.JLabel();
    	txtMaThuoc = new javax.swing.JTextField();

    	jPanel18 = new javax.swing.JPanel();          // Tên thuốc
    	jLabel11 = new javax.swing.JLabel();
    	txtTenThuoc = new javax.swing.JTextField();

    	jPanel19 = new javax.swing.JPanel();          // Thành phần thuốc
    	jLabel12 = new javax.swing.JLabel();
    	jScrollPane3 = new javax.swing.JScrollPane();
    	txtThanhPhan = new javax.swing.JTextArea();

    	jPanel21 = new javax.swing.JPanel();          // Đơn giá
    	jLabel14 = new javax.swing.JLabel();
    	txtDonGia = new javax.swing.JTextField();

    	// Các phần khác của giao diện hóa đơn
    	jPanel4 = new javax.swing.JPanel();           // Chứa bảng và hành động
    	actionPanel = new javax.swing.JPanel();       // Panel tìm kiếm và thêm vào giỏ
    	jPanel12 = new javax.swing.JPanel();          // Dòng tìm kiếm
    	jPanel14 = new javax.swing.JPanel();          // Có thể là khoảng cách
    	cboxSearch = new javax.swing.JComboBox<>();
    	txtSearch = new javax.swing.JTextField();
    	btnReload = new javax.swing.JButton();

    	jPanel13 = new javax.swing.JPanel();          // Nhập số lượng và nút thêm
    	txtSoLuong = new javax.swing.JTextField();
    	btnAddCart = new javax.swing.JButton();

    	tablePanel = new javax.swing.JPanel();        // Panel chứa bảng danh sách thuốc
    	jScrollPane1 = new javax.swing.JScrollPane();
    	table = new javax.swing.JTable();

    	billPanel = new javax.swing.JPanel();         // Panel giỏ hàng
    	cardPanel = new javax.swing.JPanel();         // Chứa bảng giỏ hàng
    	jScrollPane2 = new javax.swing.JScrollPane();
    	tableCart = new javax.swing.JTable();
    	jPanel3 = new javax.swing.JPanel();           // Tiêu đề "Giỏ hàng"
    	jLabel1 = new javax.swing.JLabel();

    	jPanel20 = new javax.swing.JPanel();          // Nút xóa khỏi giỏ hàng
    	btnDeleteCartItem = new javax.swing.JButton();

    	// Panel thông tin khách hàng và thanh toán
    	billInfoPanel = new javax.swing.JPanel();
    	jPanel5 = new javax.swing.JPanel();           // Header "Thông tin hóa đơn"
    	jLabel2 = new javax.swing.JLabel();
    	jPanel6 = new javax.swing.JPanel();
    	jPanel23 = new javax.swing.JPanel();

    	jPanel7 = new javax.swing.JPanel();           // Mã hóa đơn
    	jLabel4 = new javax.swing.JLabel();
    	txtMaHoaDon = new javax.swing.JTextField();

    	jPanel25 = new javax.swing.JPanel();          // SDT khách hàng
    	jLabel8 = new javax.swing.JLabel();
    	txtSdtKH = new javax.swing.JTextField();
    	btnSearchKH = new javax.swing.JButton();
    	btnAddCustomer = new javax.swing.JButton();

    	jPanel2 = new javax.swing.JPanel();           // Tên + giới tính khách hàng
    	jLabel3 = new javax.swing.JLabel();
    	txtHoTenKH = new javax.swing.JTextField();
    	cboxGioiTinhKH = new javax.swing.JComboBox<>();
    	jSeparator1 = new javax.swing.JSeparator();

    	jPanel26 = new javax.swing.JPanel();          // Panel tổng tiền
    	jPanel11 = new javax.swing.JPanel();          // Tổng tiền
    	jLabel7 = new javax.swing.JLabel();
    	txtTong = new javax.swing.JTextField();

    	jPanel10 = new javax.swing.JPanel();          // Tiền khách đưa
    	jLabel6 = new javax.swing.JLabel();
    	txtTienKhachDua = new javax.swing.JTextField();

    	jPanel9 = new javax.swing.JPanel();           // Tiền thừa
    	jLabel5 = new javax.swing.JLabel();
    	txtTienThua = new javax.swing.JTextField();

    	jPanel8 = new javax.swing.JPanel();           // Nút Hủy và Thanh Toán
    	btnHuy = new javax.swing.JButton();
    	btnThanhToan = new javax.swing.JButton();


    	// Thiết lập cho chính Panel này
    	setBackground(new java.awt.Color(230, 245, 245));
    	setBorder(new javax.swing.border.LineBorder(new java.awt.Color(230, 245, 245), 6, true));
    	setLayout(new java.awt.BorderLayout(5, 0));

    	// Cấu hình mainPanel
    	mainPanel.setBackground(new java.awt.Color(230, 245, 245));
    	mainPanel.setLayout(new java.awt.BorderLayout(5, 5));

    	// ======== Panel sản phẩm (Thông tin thuốc) ========
    	sanPhamPanel.setBackground(new java.awt.Color(255, 255, 255));
    	sanPhamPanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(237, 237, 237), 2, true));
    	sanPhamPanel.setPreferredSize(new java.awt.Dimension(832, 300));
    	sanPhamPanel.setLayout(new java.awt.BorderLayout());

    	// ===== Header "Thông tin thuốc" =====
    	jPanel15.setBackground(new java.awt.Color(0, 153, 153));
    	jPanel15.setMinimumSize(new java.awt.Dimension(100, 60));
    	jPanel15.setPreferredSize(new java.awt.Dimension(500, 30));
    	jPanel15.setLayout(new java.awt.BorderLayout());

    	lblThuoc.setFont(new java.awt.Font("Roboto Medium", 0, 14));
    	lblThuoc.setForeground(new java.awt.Color(255, 255, 255));
    	lblThuoc.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    	lblThuoc.setText("Thông tin thuốc");
    	jPanel15.add(lblThuoc, java.awt.BorderLayout.CENTER);

    	sanPhamPanel.add(jPanel15, java.awt.BorderLayout.NORTH);

    	// ===== Thân dưới chứa ảnh và thông tin =====
    	jPanel16.setBackground(new java.awt.Color(255, 255, 255));
    	jPanel16.setLayout(new java.awt.BorderLayout(16, 16));

    	// Hình ảnh thuốc
    	jPanel22.setBackground(new java.awt.Color(255, 255, 255));
    	jPanel22.setPreferredSize(new java.awt.Dimension(300, 200));
    	jPanel22.setLayout(new java.awt.BorderLayout(20, 20));
    	txtHinhAnh.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(230, 230, 230), 4, true));
    	txtHinhAnh.setPreferredSize(new java.awt.Dimension(300, 200));
    	jPanel22.add(txtHinhAnh, java.awt.BorderLayout.CENTER);

    	jPanel16.add(jPanel22, java.awt.BorderLayout.WEST);

    	// ===== Các dòng thông tin thuốc =====
    	jPanel17.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 4, 0));
    	jLabel10.setText("Mã thuốc:");
    	jLabel10.setPreferredSize(new java.awt.Dimension(90, 40));
    	txtMaThuoc.setEditable(false);
    	txtMaThuoc.setFocusable(false);
    	txtMaThuoc.setPreferredSize(new java.awt.Dimension(120, 40));
    	jPanel17.add(jLabel10);
    	jPanel17.add(txtMaThuoc);

    	jPanel18.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 4, 0));
    	jLabel11.setText("Tên thuốc:");
    	jLabel11.setPreferredSize(new java.awt.Dimension(90, 40));
    	txtTenThuoc.setEditable(false);
    	txtTenThuoc.setFocusable(false);
    	txtTenThuoc.setPreferredSize(new java.awt.Dimension(350, 40));
    	jPanel18.add(jLabel11);
    	jPanel18.add(txtTenThuoc);

    	jPanel19.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 4, 0));
    	jLabel12.setText("Thành phần:");
    	jLabel12.setPreferredSize(new java.awt.Dimension(90, 40));
    	txtThanhPhan.setEditable(false);
    	txtThanhPhan.setColumns(20);
    	txtThanhPhan.setLineWrap(true);
    	txtThanhPhan.setRows(5);
    	txtThanhPhan.setFocusable(false);
    	jScrollPane3.setViewportView(txtThanhPhan);
    	jScrollPane3.setPreferredSize(new java.awt.Dimension(350, 100));
    	jPanel19.add(jLabel12);
    	jPanel19.add(jScrollPane3);

    	jPanel21.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 4, 0));
    	jLabel14.setText("Đơn giá:");
    	jLabel14.setPreferredSize(new java.awt.Dimension(90, 40));
    	txtDonGia.setEditable(false);
    	txtDonGia.setFont(new java.awt.Font("Roboto Mono Medium", 0, 14));
    	txtDonGia.setFocusable(false);
    	txtDonGia.setPreferredSize(new java.awt.Dimension(120, 40));
    	jPanel21.add(jLabel14);
    	jPanel21.add(txtDonGia);

    	// Gộp lại
    	jPanel24.setLayout(new javax.swing.BoxLayout(jPanel24, javax.swing.BoxLayout.Y_AXIS));
    	jPanel24.add(jPanel17);
    	jPanel24.add(jPanel18);
    	jPanel24.add(jPanel19);
    	jPanel24.add(jPanel21);

    	jPanel16.add(jPanel24, java.awt.BorderLayout.CENTER);

    	// Thêm vào sanPhamPanel
    	sanPhamPanel.add(jPanel16, java.awt.BorderLayout.CENTER);

    	// Thêm vào main
    	mainPanel.add(sanPhamPanel, java.awt.BorderLayout.PAGE_START);

    	// Phần còn lại là phần bảng sản phẩm, giỏ hàng, thanh toán...

        txtDonGia.setEditable(false);
        txtDonGia.setFont(new java.awt.Font("Roboto Mono Medium", 0, 14)); // NOI18N
        txtDonGia.setText("123123");
        txtDonGia.setFocusable(false);
        txtDonGia.setPreferredSize(new java.awt.Dimension(120, 40));
        jPanel21.add(txtDonGia);

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(612, Short.MAX_VALUE))
            .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel24Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, 869, Short.MAX_VALUE)
                        .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap()))
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel24Layout.createSequentialGroup()
                .addContainerGap(205, Short.MAX_VALUE)
                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
            .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel24Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(66, Short.MAX_VALUE)))
        );

        jPanel16.add(jPanel24, java.awt.BorderLayout.CENTER);

        sanPhamPanel.add(jPanel16, java.awt.BorderLayout.CENTER);

        mainPanel.add(sanPhamPanel, java.awt.BorderLayout.PAGE_START);

        jPanel4.setBackground(new java.awt.Color(230, 245, 245));
        jPanel4.setPreferredSize(new java.awt.Dimension(832, 400));
        jPanel4.setLayout(new java.awt.BorderLayout(0, 5));

        actionPanel.setBackground(new java.awt.Color(255, 255, 255));
        actionPanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(237, 237, 237), 2, true));
        actionPanel.setPreferredSize(new java.awt.Dimension(605, 60));
        actionPanel.setLayout(new java.awt.BorderLayout());

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 8));

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));
        jPanel14.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));
        jPanel12.add(jPanel14);

        cboxSearch.setToolTipText("");
        cboxSearch.setPreferredSize(new java.awt.Dimension(100, 40));
        jPanel12.add(cboxSearch);

        txtSearch.setToolTipText("Tìm kiếm");
        txtSearch.setPreferredSize(new java.awt.Dimension(200, 40));
        txtSearch.setSelectionColor(new java.awt.Color(230, 245, 245));
        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
               // txtSearchKeyReleased(evt);
            }
        });
        jPanel12.add(txtSearch);
        //

        btnReload.setIcon(new ImageIcon("./icon/reload.svg"));
        btnReload.setToolTipText("Làm mới");
        btnReload.setBorder(null);
        btnReload.setBorderPainted(false);
        btnReload.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnReload.setFocusPainted(false);
        btnReload.setFocusable(false);
        btnReload.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnReload.setPreferredSize(new java.awt.Dimension(40, 40));
        btnReload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
               // btnReloadActionPerformed(evt);
            }
        });
        jPanel12.add(btnReload);

        actionPanel.add(jPanel12, java.awt.BorderLayout.CENTER);

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));
        jPanel13.setPreferredSize(new java.awt.Dimension(260, 60));
        jPanel13.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 5, 8));

        txtSoLuong.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        txtSoLuong.setPreferredSize(new java.awt.Dimension(120, 40));
        jPanel13.add(txtSoLuong);

        btnAddCart.setBackground(new java.awt.Color(0, 179, 246));
        btnAddCart.setFont(new java.awt.Font("Roboto Black", 0, 16)); // NOI18N
        btnAddCart.setForeground(new java.awt.Color(255, 220, 0));
        btnAddCart.setIcon(new ImageIcon("./icon/add-to-cart.svg"));
        btnAddCart.setText("THÊM");
        btnAddCart.setBorderPainted(false);
        btnAddCart.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAddCart.setFocusPainted(false);
        btnAddCart.setFocusable(false);
        btnAddCart.setPreferredSize(new java.awt.Dimension(120, 40));
        btnAddCart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
               // btnAddCartActionPerformed(evt);
            }
        });
        jPanel13.add(btnAddCart);

        actionPanel.add(jPanel13, java.awt.BorderLayout.EAST);

        jPanel4.add(actionPanel, java.awt.BorderLayout.PAGE_START);

        tablePanel.setBackground(new java.awt.Color(255, 255, 255));
        tablePanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(237, 237, 237), 2, true));
        tablePanel.setLayout(new java.awt.BorderLayout());

        jScrollPane1.setBorder(null);

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        table.setFocusable(false);
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                //tableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table);

        tablePanel.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel4.add(tablePanel, java.awt.BorderLayout.CENTER);

        mainPanel.add(jPanel4, java.awt.BorderLayout.CENTER);

        add(mainPanel, java.awt.BorderLayout.CENTER);

        billPanel.setBackground(new java.awt.Color(230, 245, 245));
        billPanel.setPreferredSize(new java.awt.Dimension(460, 800));
        billPanel.setLayout(new java.awt.BorderLayout(0, 5));

        cardPanel.setBackground(new java.awt.Color(255, 255, 255));
        cardPanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(238, 238, 238), 2, true));
        cardPanel.setPreferredSize(new java.awt.Dimension(600, 500));
        cardPanel.setLayout(new java.awt.BorderLayout());

        jScrollPane2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(230, 230, 230), 1, true));

        tableCart.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tableCart.setFocusable(false);
        jScrollPane2.setViewportView(tableCart);

        cardPanel.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        jPanel3.setBackground(new java.awt.Color(0, 153, 153));
        jPanel3.setMinimumSize(new java.awt.Dimension(100, 60));
        jPanel3.setPreferredSize(new java.awt.Dimension(500, 30));
        jPanel3.setLayout(new java.awt.BorderLayout());

        jLabel1.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Giỏ hàng");
        jPanel3.add(jLabel1, java.awt.BorderLayout.CENTER);

        cardPanel.add(jPanel3, java.awt.BorderLayout.NORTH);

        jPanel20.setBackground(new java.awt.Color(255, 255, 255));
        jPanel20.setForeground(new java.awt.Color(255, 255, 255));
        jPanel20.setPreferredSize(new java.awt.Dimension(456, 42));
        jPanel20.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 6, 2));

        btnDeleteCartItem.setBackground(new java.awt.Color(255, 102, 102));
        btnDeleteCartItem.setFont(new java.awt.Font("Roboto Mono", 1, 14)); // NOI18N
        btnDeleteCartItem.setForeground(new java.awt.Color(255, 255, 255));
       //
        btnDeleteCartItem.setIcon(new ImageIcon("./icon/trash-cart.png"));

        btnDeleteCartItem.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDeleteCartItem.setPreferredSize(new java.awt.Dimension(50, 38));
        btnDeleteCartItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
               // btnDeleteCartItemActionPerformed(evt);
            }
        });
        jPanel20.add(btnDeleteCartItem);

        cardPanel.add(jPanel20, java.awt.BorderLayout.PAGE_END);

        billPanel.add(cardPanel, java.awt.BorderLayout.CENTER);

        billInfoPanel.setBackground(new java.awt.Color(255, 255, 255));
        billInfoPanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(238, 238, 238), 2, true));
        billInfoPanel.setPreferredSize(new java.awt.Dimension(500, 400));
        billInfoPanel.setLayout(new java.awt.BorderLayout());

        jPanel5.setBackground(new java.awt.Color(0, 153, 153));
        jPanel5.setMinimumSize(new java.awt.Dimension(100, 60));
        jPanel5.setPreferredSize(new java.awt.Dimension(500, 30));
        jPanel5.setLayout(new java.awt.BorderLayout());

        jLabel2.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Hóa đơn");
        jPanel5.add(jLabel2, java.awt.BorderLayout.CENTER);

        billInfoPanel.add(jPanel5, java.awt.BorderLayout.NORTH);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 8));

        jPanel23.setBackground(new java.awt.Color(255, 255, 255));
        jPanel23.setPreferredSize(new java.awt.Dimension(440, 140));
        jPanel23.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jLabel4.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel4.setText("Mã hóa đơn ");
        jLabel4.setPreferredSize(new java.awt.Dimension(120, 40));
        jPanel7.add(jLabel4);

        txtMaHoaDon.setEditable(false);
        txtMaHoaDon.setFont(new java.awt.Font("Roboto Mono", 1, 14)); // NOI18N
        txtMaHoaDon.setText("Z2NX8CN1A");
        txtMaHoaDon.setFocusable(false);
        txtMaHoaDon.setPreferredSize(new java.awt.Dimension(200, 40));
        jPanel7.add(txtMaHoaDon);

        jPanel23.add(jPanel7);

        jPanel25.setBackground(new java.awt.Color(255, 255, 255));
        jPanel25.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jLabel8.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel8.setText("Số điện thoại:");
        jLabel8.setPreferredSize(new java.awt.Dimension(120, 40));
        jPanel25.add(jLabel8);

        txtSdtKH.setPreferredSize(new java.awt.Dimension(200, 40));
        jPanel25.add(txtSdtKH);

        btnSearchKH.setIcon(new ImageIcon("./icon/search.svg"));
        btnSearchKH.setBorderPainted(false);
        btnSearchKH.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSearchKH.setFocusPainted(false);
        btnSearchKH.setFocusable(false);
        btnSearchKH.setPreferredSize(new java.awt.Dimension(40, 40));
        btnSearchKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
               // btnSearchKHActionPerformed(evt);
            }
        });
        jPanel25.add(btnSearchKH);

        btnAddCustomer.setIcon(new ImageIcon("./icon/add-customer.svg"));
        btnAddCustomer.setBorderPainted(false);
        btnAddCustomer.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAddCustomer.setFocusPainted(false);
        btnAddCustomer.setFocusable(false);
        btnAddCustomer.setPreferredSize(new java.awt.Dimension(40, 40));
        btnAddCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
               // btnAddCustomerActionPerformed(evt);
            }
        });
        jPanel25.add(btnAddCustomer);

        jPanel23.add(jPanel25);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jLabel3.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel3.setText("Tên khách hàng");
        jLabel3.setMaximumSize(new java.awt.Dimension(44, 40));
        jLabel3.setPreferredSize(new java.awt.Dimension(120, 40));
        jPanel2.add(jLabel3);

        txtHoTenKH.setPreferredSize(new java.awt.Dimension(200, 40));
        jPanel2.add(txtHoTenKH);

        jPanel23.add(jPanel2);

        cboxGioiTinhKH.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nam", "Nữ" }));
        cboxGioiTinhKH.setPreferredSize(new java.awt.Dimension(90, 40));
        jPanel23.add(cboxGioiTinhKH);

        jPanel6.add(jPanel23);

        jSeparator1.setPreferredSize(new java.awt.Dimension(400, 3));
        jPanel6.add(jSeparator1);

        jPanel26.setBackground(new java.awt.Color(255, 255, 255));
        jPanel26.setPreferredSize(new java.awt.Dimension(440, 150));
        jPanel26.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jLabel7.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 51, 0));
        jLabel7.setText("Tổng hóa đơn:");
        jLabel7.setPreferredSize(new java.awt.Dimension(120, 40));
        jPanel11.add(jLabel7);

        txtTong.setEditable(false);
        txtTong.setFont(new java.awt.Font("Roboto Mono Medium", 0, 14)); // NOI18N
        txtTong.setForeground(new java.awt.Color(255, 51, 0));
        txtTong.setText("1000000");
        txtTong.setFocusable(false);
        txtTong.setPreferredSize(new java.awt.Dimension(200, 40));
        jPanel11.add(txtTong);

        jPanel26.add(jPanel11);

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jLabel6.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel6.setText("Tiền khách đưa:");
        jLabel6.setPreferredSize(new java.awt.Dimension(120, 40));
        jPanel10.add(jLabel6);

        txtTienKhachDua.setPreferredSize(new java.awt.Dimension(200, 40));
        txtTienKhachDua.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
               // txtTienKhachDuaKeyReleased(evt);
            }
        });
        jPanel10.add(txtTienKhachDua);

        jPanel26.add(jPanel10);

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jLabel5.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel5.setText("Tiền thừa:");
        jLabel5.setPreferredSize(new java.awt.Dimension(120, 40));
        jPanel9.add(jLabel5);

        txtTienThua.setEditable(false);
        txtTienThua.setFont(new java.awt.Font("Roboto Mono Medium", 0, 14)); // NOI18N
        txtTienThua.setFocusable(false);
        txtTienThua.setPreferredSize(new java.awt.Dimension(200, 40));
        jPanel9.add(txtTienThua);

        jPanel26.add(jPanel9);

        jPanel6.add(jPanel26);

        billInfoPanel.add(jPanel6, java.awt.BorderLayout.CENTER);

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        btnHuy.setBackground(new java.awt.Color(255, 102, 102));
        btnHuy.setFont(new java.awt.Font("Roboto Mono Medium", 0, 16)); // NOI18N
        btnHuy.setForeground(new java.awt.Color(255, 255, 255));
        btnHuy.setText("HỦY BỎ");
        btnHuy.setBorderPainted(false);
        btnHuy.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnHuy.setFocusPainted(false);
        btnHuy.setFocusable(false);
        btnHuy.setPreferredSize(new java.awt.Dimension(200, 40));
        btnHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                //btnHuyActionPerformed(evt);
            }
        });
        jPanel8.add(btnHuy);

        btnThanhToan.setBackground(new java.awt.Color(0, 204, 51));
        btnThanhToan.setFont(new java.awt.Font("Roboto Mono Medium", 0, 16)); // NOI18N
        btnThanhToan.setForeground(new java.awt.Color(255, 255, 255));
        btnThanhToan.setText("THANH TOÁN");
        btnThanhToan.setBorderPainted(false);
        btnThanhToan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnThanhToan.setFocusPainted(false);
        btnThanhToan.setFocusable(false);
        btnThanhToan.setPreferredSize(new java.awt.Dimension(200, 40));
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                //btnThanhToanActionPerformed(evt);
            }
        });
        jPanel8.add(btnThanhToan);

        billInfoPanel.add(jPanel8, java.awt.BorderLayout.PAGE_END);

        billPanel.add(billInfoPanel, java.awt.BorderLayout.SOUTH);

        add(billPanel, java.awt.BorderLayout.EAST);
    }// </editor-fold>//GEN-END:initComponents
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Quản Lý Món Ăn");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1500, 900);
            frame.setLocationRelativeTo(null);

            GUI_HoaDon panel = new GUI_HoaDon();
            frame.setContentPane(panel);
            frame.setVisible(true);
        });
    }
   
    private javax.swing.JPanel actionPanel;
    private javax.swing.JPanel billInfoPanel;
    private javax.swing.JPanel billPanel;
    private javax.swing.JButton btnAddCart;
    private javax.swing.JButton btnAddCustomer;
    private javax.swing.JButton btnDeleteCartItem;
    private javax.swing.JButton btnHuy;
    private javax.swing.JButton btnReload;
    private javax.swing.JButton btnSearchKH;
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JPanel cardPanel;
    private javax.swing.JComboBox<String> cboxGioiTinhKH;
    private javax.swing.JComboBox<String> cboxSearch;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblThuoc;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JPanel sanPhamPanel;
    private javax.swing.JTable table;
    private javax.swing.JTable tableCart;
    private javax.swing.JPanel tablePanel;
    private javax.swing.JTextField txtDonGia;
    private javax.swing.JLabel txtHinhAnh;
    private javax.swing.JTextField txtHoTenKH;
    private javax.swing.JTextField txtMaHoaDon;
    private javax.swing.JTextField txtMaThuoc;
    private javax.swing.JTextField txtSdtKH;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtTenThuoc;
    private javax.swing.JTextArea txtThanhPhan;
    private javax.swing.JTextField txtTienKhachDua;
    private javax.swing.JTextField txtTienThua;
    private javax.swing.JTextField txtTong;
    // End of variables declaration//GEN-END:variables
}
