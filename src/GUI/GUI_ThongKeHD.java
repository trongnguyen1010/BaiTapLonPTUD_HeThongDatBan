package GUI;


import DAO.HoaDon_DAO;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class GUI_ThongKeHD extends JPanel {
    private JLabel lblTongDoanhThu, lblSoLuongHD, lblDoanhThuTheoNgay;
    private JTextField txtFromDate, txtToDate;
    private JButton btnThongKe, btnLamMoi;
    private HoaDon_DAO hoaDonDao;

    public GUI_ThongKeHD() {
        hoaDonDao = new HoaDon_DAO();
        initComponents();
        loadThongKeTong();
    }

    private void initComponents() {
        setLayout(new BorderLayout(10, 10));
        setBackground(Color.WHITE);

        // Header
        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        headerPanel.setBackground(new Color(0, 153, 51));
        JLabel lblTitle = new JLabel("THỐNG KÊ HÓA ĐƠN");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 26));
        lblTitle.setForeground(Color.WHITE);
        headerPanel.add(lblTitle);
        add(headerPanel, BorderLayout.NORTH);

        // Main Panel
        JPanel mainPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        mainPanel.add(new JLabel("Tổng doanh thu:"));
        lblTongDoanhThu = new JLabel();
        mainPanel.add(lblTongDoanhThu);

        mainPanel.add(new JLabel("Số lượng hóa đơn:"));
        lblSoLuongHD = new JLabel();
        mainPanel.add(lblSoLuongHD);

        mainPanel.add(new JLabel("Từ ngày (yyyy-MM-dd):"));
        txtFromDate = new JTextField(LocalDate.now().minusDays(30).toString());
        mainPanel.add(txtFromDate);

        mainPanel.add(new JLabel("Đến ngày (yyyy-MM-dd):"));
        txtToDate = new JTextField(LocalDate.now().toString());
        mainPanel.add(txtToDate);

        mainPanel.add(new JLabel("Doanh thu trong khoảng:"));
        lblDoanhThuTheoNgay = new JLabel();
        mainPanel.add(lblDoanhThuTheoNgay);

        btnThongKe = new JButton("Thống kê theo ngày");
        btnLamMoi = new JButton("Làm mới");

        mainPanel.add(btnThongKe);
        mainPanel.add(btnLamMoi);

        add(mainPanel, BorderLayout.CENTER);

        // Events
        btnThongKe.addActionListener(e -> thongKeTheoNgay());
        btnLamMoi.addActionListener(e -> loadThongKeTong());
    }

    private void loadThongKeTong() {
        long tongDoanhThu = hoaDonDao.getTongDoanhThu();
        int soLuongHD = hoaDonDao.getSoLuongHoaDon();

        lblTongDoanhThu.setText(tongDoanhThu + " VND");
        lblSoLuongHD.setText(soLuongHD + " hóa đơn");
        lblDoanhThuTheoNgay.setText("Chưa thống kê");
    }

    private void thongKeTheoNgay() {
        try {
            LocalDate from = LocalDate.parse(txtFromDate.getText().trim());
            LocalDate to = LocalDate.parse(txtToDate.getText().trim());

            if (from.isAfter(to)) {
                JOptionPane.showMessageDialog(this, "Ngày bắt đầu phải trước hoặc bằng ngày kết thúc.");
                return;
            }

            long doanhThu = hoaDonDao.getDoanhThuTheoNgay(from, to);
            lblDoanhThuTheoNgay.setText(doanhThu + " VND");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi định dạng ngày. Vui lòng nhập đúng định dạng yyyy-MM-dd.");
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Thống kê hóa đơn");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1240, 740);
        frame.setLocationRelativeTo(null);
        frame.setContentPane(new GUI_ThongKeHD());
        frame.setVisible(true);
    }
}
