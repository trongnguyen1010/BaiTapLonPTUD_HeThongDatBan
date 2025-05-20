package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import DAO.HoaDon_DAO;
import Entity.HoaDon;

import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class GUI_ListHD extends JPanel {

    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField txtTimKiem;
    private JComboBox<String> cbTimKiem;
    private JButton btnThem, btnXoa, btnTimKiem, btnLamMoi;
    private HoaDon_DAO hoaDonDao; // Khai báo biến dao

    public GUI_ListHD() {
        hoaDonDao = new HoaDon_DAO();  // Khởi tạo dao 1 lần
        initComponents();
        loadData();
    }

    private void initComponents() {
        setLayout(new BorderLayout(10, 10));
        setBackground(Color.WHITE);

        // Header Panel
        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        headerPanel.setBackground(new Color(0, 102, 204));
        JLabel lblTitle = new JLabel("DANH SÁCH HÓA ĐƠN");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 26));
        lblTitle.setForeground(Color.WHITE);
        headerPanel.add(lblTitle);

        // Control Panel
        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        cbTimKiem = new JComboBox<>(new String[] {
            "Mã hóa đơn", "Tên khách hàng", "Tên nhân viên", "Ngày lập"
        });
        txtTimKiem = new JTextField(20);
        btnTimKiem = new JButton("Tìm");
        btnXoa = new JButton("Xóa");
        btnLamMoi = new JButton("Làm mới");

        controlPanel.add(new JLabel("Tìm theo:"));
        controlPanel.add(cbTimKiem);
        controlPanel.add(txtTimKiem);
        controlPanel.add(btnTimKiem);
        controlPanel.add(btnXoa);
        controlPanel.add(btnLamMoi);

        // Wrapper cho NORTH
        JPanel northWrapper = new JPanel(new BorderLayout());
        northWrapper.add(headerPanel, BorderLayout.NORTH);
        northWrapper.add(controlPanel, BorderLayout.SOUTH);
        add(northWrapper, BorderLayout.NORTH);

        // Table
        String[] columnNames = { "Mã hóa đơn", "Ngày lập", "Nhân viên","Khách hàng", "Tổng tiền" };
        tableModel = new DefaultTableModel(columnNames, 0) {
            // Chặn sửa trực tiếp trên bảng
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table = new JTable(tableModel);
        table.setRowHeight(28);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // Event
        btnLamMoi.addActionListener(e -> loadData());
        btnTimKiem.addActionListener(e -> timKiem());
        //btnXoa.addActionListener(e -> xoaHoaDon());
    }

    private void loadData() {
        tableModel.setRowCount(0);
        try {
            List<HoaDon> danhSachHoaDon = hoaDonDao.getAllHoaDon();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            for (HoaDon hoaDon : danhSachHoaDon) {
                Object[] row = {
                    hoaDon.getMaHoaDon(),
                    hoaDon.getNgayLap().format(formatter),
                    hoaDon.getMaNhanVien().getTen(), // Hiển thị tên nhân viên (nếu có)
                    hoaDon.getMaKhachHang().getTenKhachHang(),
                    hoaDon.getThanhTien()
                };
                tableModel.addRow(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi tải dữ liệu hóa đơn: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void timKiem() {
        String keyword = txtTimKiem.getText().trim().toLowerCase();
        if (keyword.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập từ khóa tìm kiếm.");
            return;
        }

        String loaiTimKiem = cbTimKiem.getSelectedItem().toString();
        boolean found = false;
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            String value = "";
            switch (loaiTimKiem) {
                case "Mã hóa đơn":
                    value = tableModel.getValueAt(i, 0).toString();
                    break;
                case "Tên khách hàng":
                    value = tableModel.getValueAt(i, 3).toString();
                    break;
                case "Tên nhân viên":
                    value = tableModel.getValueAt(i, 2).toString();
                    break;
                case "Ngày lập":
                    value = tableModel.getValueAt(i, 1).toString();
                    break;
            }
            if (value.toLowerCase().contains(keyword)) {
                table.setRowSelectionInterval(i, i);
                table.scrollRectToVisible(table.getCellRect(i, 0, true));
                found = true;
                break;
            }
        }
        if (!found) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy kết quả.");
        }
    }

//    private void xoaHoaDon() {
//        int selectedRow = table.getSelectedRow();
//        if (selectedRow == -1) {
//            JOptionPane.showMessageDialog(this, "Vui lòng chọn hóa đơn để xóa.");
//            return;
//        }
//
//        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa hóa đơn này?", "Xác nhận", JOptionPane.YES_NO_OPTION);
//        if (confirm == JOptionPane.YES_OPTION) {
//            try {
//                String maHoaDon = tableModel.getValueAt(selectedRow, 0).toString();
//                // Xóa khỏi DB trước
//               // boolean deleted = hoaDonDao.deleteHoaDon(maHoaDon);
//                if (deleted) {
//                    // Xóa khỏi bảng
//                    tableModel.removeRow(selectedRow);
//                    JOptionPane.showMessageDialog(this, "Xóa thành công.");
//                } else {
//                    JOptionPane.showMessageDialog(this, "Xóa không thành công.");
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//                JOptionPane.showMessageDialog(this, "Lỗi khi xóa hóa đơn: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
//            }
//        }
//    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Danh sách hóa đơn");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1240, 740);
        frame.setLocationRelativeTo(null);
        frame.setContentPane(new GUI_ListHD());
        frame.setVisible(true);
    }
}
