package GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;


import Entity.MonAn;
import Entity.NhanVien;
import Entity.KhachHang;
import DAO.NhanVienDAO;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.awt.Font;
import java.awt.Image;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import utils.RandomGenerator;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.itextpdf.text.Document;
import com.itextpdf.text.Font ; // ❌ Java không hỗ trợ alias trong import (chỉ có trong Kotlin/C#/Python)


import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;



public class GUI_HoaDon_new extends JPanel {

    private static final long serialVersionUID = 1L;
    private List<MonAn> dsMonAnTrongHoaDon = new ArrayList<>();
    private DefaultTableModel modelMonAnHoaDon;
    private JTable table;
 // Biến toàn cục
    private JTextField txtMaThuoc, txtTenThuoc, txtDonGia,txtMaHD,txtNgayLap;
    private JLabel txtHinhAnh;
    
    private NhanVienDAO NhanVienDAO=new NhanVienDAO();
    private  JComboBox<String> cbNhanVien;
    private JTextField txtKhachHang;
    

   


    public GUI_HoaDon_new() {
        setLayout(new BorderLayout(0, 0));

        // ===== Panel chứa 2 NORTH panels =====
        JPanel topContainer = new JPanel();
        topContainer.setLayout(new BoxLayout(topContainer, BoxLayout.Y_AXIS));
        add(topContainer, BorderLayout.NORTH);

        // ===== Panel 1 (Header trên cùng) =====
        JPanel panelNorth1 = new JPanel();
        panelNorth1.setBackground(Color.RED);
        panelNorth1.setPreferredSize(new Dimension(0, 50));
        panelNorth1.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panelNorth1.add(new JLabel("Header 1 - Đỏ"));
        topContainer.add(panelNorth1);

        // ===== Panel 2 (Header thứ 2) =====
        JPanel panelNorth2 = new JPanel();
        panelNorth2.setBackground(Color.ORANGE);
        panelNorth2.setPreferredSize(new Dimension(0, 50));
        panelNorth2.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panelNorth2.add(new JLabel("Header 2 - Cam"));
        topContainer.add(panelNorth2);

        // ===== CENTER Panel =====
        JPanel panelCenter = new JPanel(new BorderLayout());
        panelCenter.setBackground(Color.LIGHT_GRAY);
        add(panelCenter, BorderLayout.CENTER);

        // ===== sanPhamPanel =====
        JPanel sanPhamPanel = new JPanel(new BorderLayout());
        sanPhamPanel.setBackground(Color.WHITE);
        sanPhamPanel.setBorder(BorderFactory.createLineBorder(new Color(237, 237, 237), 2, true));
        sanPhamPanel.setPreferredSize(new Dimension(832, 300));
        panelCenter.add(sanPhamPanel, BorderLayout.NORTH);

        // ===== Header "Thông tin món ăn" =====
        JPanel jPanel15 = new JPanel(new BorderLayout());
        jPanel15.setBackground(new Color(0, 153, 153));
        jPanel15.setPreferredSize(new Dimension(500, 30));

        JLabel lblThuoc = new JLabel("Thông tin Món ăn", SwingConstants.CENTER);
        lblThuoc.setForeground(Color.WHITE);
        lblThuoc.setFont(new Font("Arial", Font.PLAIN, 14));
        jPanel15.add(lblThuoc, BorderLayout.CENTER);
        sanPhamPanel.add(jPanel15, BorderLayout.NORTH);

        // ===== Thân dưới chứa ảnh và thông tin =====
        JPanel jPanel16 = new JPanel(new BorderLayout(20, 20));
        jPanel16.setBackground(Color.WHITE);
        jPanel16.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        // ----- Hình ảnh bên trái -----
        JPanel jPanel22 = new JPanel(new BorderLayout());
        jPanel22.setBackground(new Color(245, 245, 245));
        jPanel22.setPreferredSize(new Dimension(250, 200));

        JLabel txtHinhAnh = new JLabel();
        txtHinhAnh.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 3, true));
        txtHinhAnh.setPreferredSize(new Dimension(240, 180));
        jPanel22.add(txtHinhAnh, BorderLayout.CENTER);
        jPanel16.add(jPanel22, BorderLayout.WEST);

        // ----- Thông tin bên phải -----
        JPanel jPanel24 = new JPanel();
        jPanel24.setLayout(new BoxLayout(jPanel24, BoxLayout.Y_AXIS));
        jPanel24.setBackground(Color.WHITE);
        jPanel24.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Mã món ăn
        JPanel jPanel17 = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        jPanel17.setBackground(Color.WHITE);
        JLabel jLabel10 = new JLabel("Mã Món Ăn:");
        jLabel10.setPreferredSize(new Dimension(100, 30));
        JTextField txtMaThuoc = new JTextField();
        txtMaThuoc.setEditable(false);
        txtMaThuoc.setFocusable(false);
        txtMaThuoc.setPreferredSize(new Dimension(200, 30));
        jPanel17.add(jLabel10);
        jPanel17.add(txtMaThuoc);

        // Tên món ăn
        JPanel jPanel18 = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        jPanel18.setBackground(Color.WHITE);
        JLabel jLabel11 = new JLabel("Tên Món Ăn:");
        jLabel11.setPreferredSize(new Dimension(100, 30));
        JTextField txtTenThuoc = new JTextField();
        txtTenThuoc.setEditable(false);
        txtTenThuoc.setFocusable(false);
        txtTenThuoc.setPreferredSize(new Dimension(300, 30));
        jPanel18.add(jLabel11);
        jPanel18.add(txtTenThuoc);

        // Đơn giá
        JPanel jPanel21 = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        jPanel21.setBackground(Color.WHITE);
        JLabel jLabel14 = new JLabel("Đơn giá:");
        jLabel14.setPreferredSize(new Dimension(100, 30));
        JTextField txtDonGia = new JTextField();
        txtDonGia.setEditable(false);
        txtDonGia.setFocusable(false);
        txtDonGia.setPreferredSize(new Dimension(150, 30));
        jPanel21.add(jLabel14);
        jPanel21.add(txtDonGia);

        jPanel24.add(jPanel17);
        jPanel24.add(jPanel18);
        jPanel24.add(jPanel21);
        jPanel16.add(jPanel24, BorderLayout.CENTER);
        sanPhamPanel.add(jPanel16, BorderLayout.CENTER);

        // ===== SOUTH Panel: Bảng + Thông tin hóa đơn =====
        JPanel panelSouth = new JPanel(new BorderLayout());
        panelSouth.setPreferredSize(new Dimension(0, 250));
        panelSouth.setBorder(BorderFactory.createTitledBorder("Chi tiết hóa đơn"));
        panelCenter.add(panelSouth, BorderLayout.CENTER);

        // ===== Trung gian: chứa bảng và thông tin, chia 50/50 =====
        JPanel centerSplitPanel = new JPanel(new GridLayout(1, 2));

        // ===== LEFT: Bảng danh sách món ăn =====
        String[] columnNames = {"Mã", "Tên món", "Đơn giá", "Số lượng", "Thành tiền", "Ảnh"};
        modelMonAnHoaDon = new DefaultTableModel(columnNames, 0);
        table = new JTable(modelMonAnHoaDon);
        JScrollPane scrollPane = new JScrollPane(table);
        centerSplitPanel.add(scrollPane);


        // ===== RIGHT: Thông tin hóa đơn =====
        JPanel invoiceInfoPanel = new JPanel();
        invoiceInfoPanel.setLayout(new BoxLayout(invoiceInfoPanel, BoxLayout.Y_AXIS));
        invoiceInfoPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        invoiceInfoPanel.setBackground(Color.WHITE);

     // Tạo mã hóa đơn ngẫu nhiên
        String randomId = RandomGenerator.getRandomId(); // Tạo mã ngẫu nhiên cho hóa đơn

        // Khởi tạo panel và JTextField cho mã hóa đơn
        invoiceInfoPanel.add(Box.createVerticalStrut(5));
        JPanel maHDPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        maHDPanel.setBackground(Color.WHITE);
        maHDPanel.add(new JLabel("Mã Hóa Đơn: "));

        // Tạo JTextField và gán giá trị mã hóa đơn ngẫu nhiên
        txtMaHD = new JTextField(randomId, 10); // Thay đổi từ "HD001" thành randomId
        txtMaHD.setEditable(false); // Không cho phép chỉnh sửa mã hóa đơn
        txtMaHD.setBorder(null); // Không có viền
        txtMaHD.setBackground(Color.WHITE); // Màu nền trắng
        txtMaHD.setFont(new Font("Roboto", Font.PLAIN, 14)); // Đặt font chữ

        // Thêm vào panel
        maHDPanel.add(txtMaHD);
        invoiceInfoPanel.add(maHDPanel);

           //ngày lập
        String currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        invoiceInfoPanel.add(Box.createVerticalStrut(10));
        JPanel ngayLapPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        ngayLapPanel.setBackground(Color.WHITE);
        ngayLapPanel.add(new JLabel("Ngày Lập: "));
        txtNgayLap = new JTextField(currentDate, 10); // Gán ngày hiện tại vào đây
        txtNgayLap.setEditable(false); // Nếu bạn không muốn cho người dùng chỉnh sửa
        txtNgayLap.setBorder(null);
        txtNgayLap.setBackground(Color.WHITE);
        txtNgayLap.setFont(new Font("Roboto", Font.PLAIN, 14));
        ngayLapPanel.add(txtNgayLap);
        invoiceInfoPanel.add(ngayLapPanel);

     // Giả sử bạn có danh sách nhân viên (List<NhanVien>)
        List<NhanVien> dsNhanVien = null;
        // Lấy danh sách nhân viên từ NhanVienDAO
		dsNhanVien = NhanVienDAO.getAllNhanVien();

        // ComboBox nhân viên
        cbNhanVien = new JComboBox<>();

        // Duyệt qua danh sách nhân viên và thêm vào ComboBox
        if (dsNhanVien != null) {
            for (NhanVien nv : dsNhanVien) {
                cbNhanVien.addItem(nv.getTen()); // 
            }
        }

        // Thiết lập giá trị mặc định (nếu cần)
        if (dsNhanVien != null && !dsNhanVien.isEmpty()) {
            cbNhanVien.setSelectedIndex(0);  
        }

        // Tạo panel cho nhân viên và thêm vào invoiceInfoPanel
        invoiceInfoPanel.add(Box.createVerticalStrut(10));
        JPanel nhanVienPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        nhanVienPanel.setBackground(Color.WHITE);
        nhanVienPanel.add(new JLabel("Nhân Viên: "));
        nhanVienPanel.add(cbNhanVien);
        invoiceInfoPanel.add(nhanVienPanel);

     // Tạo panel cho khách hàng
        invoiceInfoPanel.add(Box.createVerticalStrut(10));

        // Tạo panel cho tên khách hàng
        JPanel khachHangPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        khachHangPanel.setBackground(Color.WHITE);

        // Tạo và thêm JLabel cho tên khách hàng
        khachHangPanel.add(new JLabel("Khách Hàng: "));
   
        // Tạo JTextField để nhập tên khách hàng
         txtKhachHang = new JTextField();
         txtKhachHang.setPreferredSize(new Dimension(200, 30));// Chiều dài của JTextField là 20 ký tự
        khachHangPanel.add(txtKhachHang);

        // Thêm panel vào invoiceInfoPanel
        invoiceInfoPanel.add(khachHangPanel);


        invoiceInfoPanel.add(Box.createVerticalStrut(10));
        JPanel tongTienPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        tongTienPanel.setBackground(Color.WHITE);
        JLabel lblTongTien = new JLabel("Tổng tiền:");
        
        lblTongTien.setForeground(Color.RED);
        tongTienPanel.add(lblTongTien);
        invoiceInfoPanel.add(tongTienPanel);

        invoiceInfoPanel.add(Box.createVerticalStrut(15));
        JPanel actionButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        actionButtonPanel.setBackground(Color.WHITE);

        JButton btnHuy = new JButton("Hủy");
        JButton btnIn = new JButton("In hóa đơn");
        btnIn.setBackground(Color.RED);
        btnIn.setForeground(Color.WHITE);
        btnIn.setFocusPainted(false);
        btnIn.setOpaque(true);
        btnIn.setBorderPainted(false);
        actionButtonPanel.add(btnHuy);
        actionButtonPanel.add(Box.createHorizontalStrut(10));
        actionButtonPanel.add(btnIn);
        btnIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Call method to export the invoice to a PDF
                    exportHoaDonToPDF();
                    // Optionally, you can show a message that the invoice was exported successfully
                    JOptionPane.showMessageDialog(GUI_HoaDon_new.this, "Hóa đơn đã được in thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    // Handle any exception that might occur during the PDF generation
                    JOptionPane.showMessageDialog(GUI_HoaDon_new.this, "Lỗi khi in hóa đơn: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        });


        invoiceInfoPanel.add(actionButtonPanel);

        centerSplitPanel.add(invoiceInfoPanel);

        // ===== NORTH: Thanh điều khiển thêm/xóa món =====
        JPanel panelControls = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
     
        JComboBox<String> comboMonAn = new JComboBox<>(new String[]{
        	    "Tất cả",
        	    "Món Mới",
        	    "Khai Vị",
        	    "Lẩu",
        	    "Heo-Gà-Bò",
        	    "Hải Sản",
        	    "Đồ uống",
        	    "Món Tráng Miệng"
        	});
        comboMonAn.setPreferredSize(new Dimension(150, 25));
        panelControls.add(comboMonAn);

        JButton btnThemMon = new JButton("Thêm");
        panelControls.add(btnThemMon);

        JButton btnXoaMon = new JButton("Xóa");
        panelControls.add(btnXoaMon);
        btnXoaMon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
                int selectedRow = table.getSelectedRow(); // đúng

                if (selectedRow != -1) {
                    dsMonAnTrongHoaDon.remove(selectedRow); // Xóa trong danh sách
                    capNhatBangMonAnHoaDon();               // Cập nhật bảng
                } else {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn món ăn cần xóa.");
                }
            }
        });



        JButton btnReset = new JButton("Reset");
        panelControls.add(btnReset);
        btnReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Xóa toàn bộ dữ liệu trong bảng
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                model.setRowCount(0); // Xóa sạch tất cả dòng
            }
        });


        panelSouth.add(panelControls, BorderLayout.NORTH);
        panelSouth.add(centerSplitPanel, BorderLayout.CENTER);

        btnThemMon.addActionListener(e -> {
            CreateHoaDonMADialog dialog = new CreateHoaDonMADialog(this, dsMonAnTrongHoaDon);
            dialog.setVisible(true);
            
            capNhatBangMonAnHoaDon();  // Cập nhật bảng món ăn
              
        });
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    // Lấy dữ liệu từ model
                    String maMon = table.getValueAt(selectedRow, 0).toString();
                    String tenMon = table.getValueAt(selectedRow, 1).toString();
                    String donGia = table.getValueAt(selectedRow, 2).toString();
                    ImageIcon imageIcon = (ImageIcon) table.getValueAt(selectedRow, 5); // Lấy ImageIcon trực tiếp từ cột ảnh

                    // Gán dữ liệu lên các textfield
                    txtMaThuoc.setText(maMon);
                    txtTenThuoc.setText(tenMon);
                    txtDonGia.setText(donGia);

                    // Hiển thị hình ảnh
                    if (imageIcon != null) {
                        Image img = imageIcon.getImage().getScaledInstance(
                            txtHinhAnh.getWidth(), txtHinhAnh.getHeight(), Image.SCALE_SMOOTH);
                        txtHinhAnh.setIcon(new ImageIcon(img));
                    } else {
                        txtHinhAnh.setIcon(null); // Xóa ảnh cũ nếu không có ảnh
                        System.out.println("Không tìm thấy ảnh.");
                    }
                }
            }
        });



    }
    
    

    public void capNhatBangMonAnHoaDon() {
        modelMonAnHoaDon.setRowCount(0); // Reset bảng

        // Duyệt qua danh sách món ăn trong hóa đơn
        for (MonAn mon : dsMonAnTrongHoaDon) {
            // Lấy dữ liệu hình ảnh dưới dạng byte[]
            byte[] hinhAnhPath = mon.gethinhAnh();

            // Nếu có hình ảnh (kiểm tra nếu mảng byte không rỗng)
            ImageIcon icon = null;
            if (hinhAnhPath != null && hinhAnhPath.length > 0) {
                icon = new ImageIcon(hinhAnhPath);  // Tạo ImageIcon từ byte[]
                Image img = icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);  // Điều chỉnh kích thước ảnh
                icon = new ImageIcon(img);  // Tạo lại ImageIcon với kích thước mới
            }

            modelMonAnHoaDon.addRow(new Object[]{
                mon.getMaMonAn(),               // Mã món ăn
                mon.getTenMonAn(),              // Tên món ăn
                mon.getDonGia(),                // Đơn giá
                mon.getSoLuong(),               // Số lượng
                mon.getDonGia() * mon.getSoLuong(), // Thành tiền
                icon                           // Hình ảnh
            });
        }
    }





    private void exportHoaDonToPDF() throws Exception {
        // Tạo tài liệu PDF
    	String tenKhachHang = txtKhachHang.getText(); 
        Document document = new Document();
       // Sử dụng txtMaHD đã được khai báo bên ngoài

       
        PdfWriter.getInstance(document, new FileOutputStream("src/utils/HoaDon.pdf"));
        document.open();

        BaseFont bf = BaseFont.createFont("src/utils/NotoSans-Regular.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);

        com.itextpdf.text.Font fontNormal = new com.itextpdf.text.Font(bf, 12, com.itextpdf.text.Font.NORMAL);
        com.itextpdf.text.Font fontBold = new com.itextpdf.text.Font(bf, 12, com.itextpdf.text.Font.BOLD);
        com.itextpdf.text.Font fontItalic = new com.itextpdf.text.Font(bf, 12, com.itextpdf.text.Font.ITALIC);
        com.itextpdf.text.Font fontHeading = new com.itextpdf.text.Font(bf, 14, com.itextpdf.text.Font.BOLD);
        com.itextpdf.text.Font fontTitle = new com.itextpdf.text.Font(bf, 20, com.itextpdf.text.Font.BOLD);



        // ======= HEADER (Thông tin cửa hàng) =======
        Paragraph storeName = new Paragraph("Nhà Hàng N2", fontTitle);
        storeName.setAlignment(Element.ALIGN_CENTER);
        document.add(storeName);

        document.add(new Paragraph("Địa chỉ: 49 Nguyễn Văn Bảo, Phường 4, Gò Vấp, TP.HCM", fontNormal));
        document.add(new Paragraph("Điện thoại: 0346851496", fontNormal));
        document.add(new Paragraph(" "));
        Paragraph hoaDonTitle = new Paragraph("HÓA ĐƠN THANH TOÁN", fontHeading);
        hoaDonTitle.setAlignment(Element.ALIGN_CENTER);
        document.add(hoaDonTitle);
        document.add(new Paragraph(" "));

        // ======= THÔNG TIN HÓA ĐƠN =======
        document.add(new Paragraph("Mã hóa đơn: " + txtMaHD.getText(), fontNormal));
        document.add(new Paragraph("Ngày lập: " + txtNgayLap.getText(), fontNormal));
        try {
           

            // Kiểm tra nếu cbNhanVien có giá trị được chọn
            String nhanVien = (cbNhanVien.getSelectedItem() != null) ? cbNhanVien.getSelectedItem().toString() : "Chưa chọn nhân viên";

            // Thêm vào PDF
            document.add(new Paragraph("Nhân viên: " + nhanVien, fontNormal));

           
        } catch (Exception e) {
            e.printStackTrace();
        }
     // Thêm tên khách hàng vào tài liệu PDF
        document.add(new Paragraph("Khách hàng: " + tenKhachHang, fontNormal));

        document.add(new Paragraph(" "));

        // ======= TABLE DANH SÁCH MÓN ĂN =======
        PdfPTable table = new PdfPTable(5); // 5 cột
        table.setWidthPercentage(100);
        table.setSpacingBefore(10f);
        table.setSpacingAfter(10f);
        float[] columnWidths = {1.5f, 4f, 2f, 1.5f, 2.5f};
        table.setWidths(columnWidths);
        table.setHorizontalAlignment(Element.ALIGN_CENTER);

        // Header của bảng
        table.addCell(new PdfPCell(new Paragraph("Mã món", fontBold)));
        table.addCell(new PdfPCell(new Paragraph("Tên món", fontBold)));
        table.addCell(new PdfPCell(new Paragraph("Đơn giá", fontBold)));
        table.addCell(new PdfPCell(new Paragraph("Số lượng", fontBold)));
        table.addCell(new PdfPCell(new Paragraph("Thành tiền", fontBold)));

        // Dữ liệu món ăn
        for (MonAn mon : dsMonAnTrongHoaDon) {
            table.addCell(new PdfPCell(new Paragraph(mon.getMaMonAn(), fontNormal)));
            table.addCell(new PdfPCell(new Paragraph(mon.getTenMonAn(), fontNormal)));
            table.addCell(new PdfPCell(new Paragraph(String.format("%.0f VNĐ", mon.getDonGia()), fontNormal)));
            table.addCell(new PdfPCell(new Paragraph(String.valueOf(mon.getSoLuong()), fontNormal)));
            table.addCell(new PdfPCell(new Paragraph(String.format("%.0f VNĐ", mon.getDonGia() * mon.getSoLuong()), fontNormal)));
        }

        document.add(table);

        // ======= FOOTER (Tổng tiền + Thuế) =======
        double tongTien = dsMonAnTrongHoaDon.stream()
                .mapToDouble(mon -> mon.getDonGia() * mon.getSoLuong())
                .sum();
        double thueVAT = tongTien * 0.10;
        double tongThanhToan = tongTien + thueVAT;

        document.add(new Paragraph("Tổng tiền: " + tongTien, fontNormal));
        document.add(new Paragraph("Thuế VAT (10%): " + String.format("%.000f VNĐ", thueVAT), fontNormal));
        Paragraph tongCong = new Paragraph("Tổng cộng: " + String.format("%.000f VNĐ", tongThanhToan), fontBold);
        tongCong.setAlignment(Element.ALIGN_RIGHT);
        document.add(tongCong);

        document.add(new Paragraph(" "));
        Paragraph camOn = new Paragraph("Xin cảm ơn và hẹn gặp lại Quý khách!", fontItalic);
        camOn.setAlignment(Element.ALIGN_CENTER);
        document.add(camOn);

        // ======= KẾT THÚC =======
        document.close();
    }


   


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Giao diện Hóa Đơn Mới");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1500, 900);
            frame.setLocationRelativeTo(null);
            frame.setContentPane(new GUI_HoaDon_new());
            frame.setVisible(true);
        });
    }
}
