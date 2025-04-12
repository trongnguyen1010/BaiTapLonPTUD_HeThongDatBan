package GUI;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;


import Entity.MonAn;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.Font;

import java.io.FileOutputStream;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import java.io.IOException;

import com.itextpdf.text.Document;
import com.itextpdf.text.Font;

import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;



public class GUI_HoaDon_new extends JPanel {

    private static final long serialVersionUID = 1L;
    private List<MonAn> dsMonAnTrongHoaDon = new ArrayList<>();
    private DefaultTableModel modelMonAnHoaDon;
    private JTable table;

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
        String[] columnNames = {"Mã", "Tên món", "Đơn giá", "Số lượng", "Thành tiền"};
        modelMonAnHoaDon = new DefaultTableModel(columnNames, 0);
        table = new JTable(modelMonAnHoaDon);
        JScrollPane scrollPane = new JScrollPane(table);
        centerSplitPanel.add(scrollPane);

        // ===== RIGHT: Thông tin hóa đơn =====
        JPanel invoiceInfoPanel = new JPanel();
        invoiceInfoPanel.setLayout(new BoxLayout(invoiceInfoPanel, BoxLayout.Y_AXIS));
        invoiceInfoPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        invoiceInfoPanel.setBackground(Color.WHITE);

        invoiceInfoPanel.add(Box.createVerticalStrut(5));
        JPanel maHDPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        maHDPanel.setBackground(Color.WHITE);
        maHDPanel.add(new JLabel("Mã Hóa Đơn: "));
        JTextField txtMaHD = new JTextField("HD001", 10);
        txtMaHD.setEditable(false);
        txtMaHD.setBorder(null);
        txtMaHD.setBackground(Color.WHITE);
        txtMaHD.setFont(new Font("Roboto", Font.PLAIN, 14));
        maHDPanel.add(txtMaHD);
        invoiceInfoPanel.add(maHDPanel);

        invoiceInfoPanel.add(Box.createVerticalStrut(10));
        JPanel ngayLapPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        ngayLapPanel.setBackground(Color.WHITE);
        ngayLapPanel.add(new JLabel("Ngày Lập: "));
        JTextField txtNgayLap = new JTextField("11/04/2025", 10);
        ngayLapPanel.add(txtNgayLap);
        invoiceInfoPanel.add(ngayLapPanel);

        invoiceInfoPanel.add(Box.createVerticalStrut(10));
        JPanel nhanVienPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        nhanVienPanel.setBackground(Color.WHITE);
        nhanVienPanel.add(new JLabel("Nhân Viên: "));
        JComboBox<String> cbNhanVien = new JComboBox<>(new String[]{"Nguyễn Văn A", "Trần Thị B", "Lê Văn C"});
        cbNhanVien.setSelectedIndex(0);
        nhanVienPanel.add(cbNhanVien);
        invoiceInfoPanel.add(nhanVienPanel);

        invoiceInfoPanel.add(Box.createVerticalStrut(10));
        JPanel tongTienPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        tongTienPanel.setBackground(Color.WHITE);
        JLabel lblTongTien = new JLabel("Tổng tiền: 0 VNĐ");
        
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
                "Phở bò", "Bún chả", "Cơm tấm", "Gà rán"
        });
        comboMonAn.setPreferredSize(new Dimension(150, 25));
        panelControls.add(comboMonAn);

        JButton btnThemMon = new JButton("Thêm");
        panelControls.add(btnThemMon);

        JButton btnXoaMon = new JButton("Xóa");
        panelControls.add(btnXoaMon);

        JButton btnReset = new JButton("Reset");
        panelControls.add(btnReset);

        panelSouth.add(panelControls, BorderLayout.NORTH);
        panelSouth.add(centerSplitPanel, BorderLayout.CENTER);

        btnThemMon.addActionListener(e -> {
            CreateHoaDonMADialog dialog = new CreateHoaDonMADialog(this, dsMonAnTrongHoaDon);
            dialog.setVisible(true);
            capNhatBangMonAnHoaDon();  // Cập nhật bảng món ăn
              
        });
    }

    public void capNhatBangMonAnHoaDon() {
        modelMonAnHoaDon.setRowCount(0); // Reset bảng

        // Duyệt qua danh sách món ăn trong hóa đơn
        for (MonAn mon : dsMonAnTrongHoaDon) {
            modelMonAnHoaDon.addRow(new Object[]{
                mon.getMaMonAn(),  // Mã món ăn
                mon.getTenMonAn(), // Tên món ăn
                mon.getDonGia(),   // Đơn giá
                mon.getSoLuong(),                 // Số lượng
                mon.getDonGia() * 1 // Thành tiền (đơn giá * số lượng)
            });
        }

        // Cập nhật tổng tiền sau khi thêm món ăn vào bảng
       
    }


    private void exportHoaDonToPDF() throws Exception {
        // Tạo tài liệu PDF
        com.itextpdf.text.Document document = new com.itextpdf.text.Document();
        PdfWriter.getInstance(document, new FileOutputStream("HoaDon.pdf"));
        document.open();

        // Thêm tiêu đề
        com.itextpdf.text.Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20); // Sử dụng font Helvetica với kiểu Bold và kích thước 20
        Paragraph title = new Paragraph("HÓA ĐƠN THANH TOÁN", fontTitle);
        title.setAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
        document.add(title);
        document.add(new Paragraph(" ")); // Dòng trắng

        // Tạo bảng cho danh sách món ăn
        PdfPTable table = new PdfPTable(5); // 5 cột: Mã, Tên, Đơn giá, SL, Thành tiền
        table.setWidthPercentage(100);

        // Header của bảng
        table.addCell("Mã món");
        table.addCell("Tên món");
        table.addCell("Đơn giá");
        table.addCell("Số lượng");
        table.addCell("Thành tiền");

        // Duyệt danh sách món ăn
        for (MonAn mon : dsMonAnTrongHoaDon) {
            table.addCell(mon.getMaMonAn());
            table.addCell(mon.getTenMonAn());
            table.addCell(String.format("%.0f VNĐ", mon.getDonGia()));
            table.addCell(String.valueOf(mon.getSoLuong()));
            table.addCell(String.format("%.0f VNĐ", mon.getDonGia() * mon.getSoLuong()));
        }

        document.add(table);

        // Tính tổng tiền
        double tongTien = dsMonAnTrongHoaDon.stream()
                .mapToDouble(mon -> mon.getDonGia() * mon.getSoLuong())
                .sum();

        // Thêm dòng tổng tiền vào tài liệu
        document.add(new Paragraph(" "));
        com.itextpdf.text.Font fontTotal = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14);  // Sử dụng font Helvetica cho phần tổng tiền với kiểu Bold và kích thước 14
        Paragraph totalParagraph = new Paragraph("Tổng tiền: " + String.format("%.0f VNĐ", tongTien), fontTotal);
        totalParagraph.setAlignment(com.itextpdf.text.Element.ALIGN_RIGHT);
        document.add(totalParagraph);

        // Đóng tài liệu
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
