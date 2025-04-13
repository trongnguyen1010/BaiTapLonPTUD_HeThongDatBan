package GUI;

import com.toedter.calendar.JDateChooser;
import DAO.Ban_DAO;
import Entity.Ban;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;

public class GUI_DatBan extends JPanel {
    private static final long serialVersionUID = 1L;
    private JPanel gridPanel;
    private JDateChooser dateChooser;

    public GUI_DatBan() {
        setLayout(null);

        // Toolbar tìm bàn
        JPanel bookingToolbar = new JPanel();
        bookingToolbar.setBackground(new Color(161, 227, 249));
        bookingToolbar.setBounds(220, 0, 1220, 130);
        bookingToolbar.setLayout(new BoxLayout(bookingToolbar, BoxLayout.X_AXIS));

        bookingToolbar.add(Box.createHorizontalStrut(20));

        JLabel lblPhone = new JLabel("SĐT khách:");
        JTextField txtPhone = new JTextField();
        JLabel lblKhuVuc = new JLabel("Khu vực:");
        JComboBox<String> comboKhuVuc = new JComboBox<>(new String[]{"All", "KV01", "KV02", "KVIP"});
        JLabel lblSoCho = new JLabel("Số chỗ:");
        JTextField txtSoCho = new JTextField();
        JLabel lblNgayDat = new JLabel("Ngày đặt:");
        dateChooser = new JDateChooser();
        dateChooser.setDate(new Date());
        JButton btnTimBan = new JButton("Tìm bàn");
        JButton btnDatBan = new JButton("Đặt bàn");

        for (Component comp : new Component[]{lblPhone, txtPhone, lblKhuVuc, comboKhuVuc, lblSoCho, txtSoCho,
                lblNgayDat, dateChooser, btnTimBan, btnDatBan}) {
            comp.setPreferredSize(new Dimension(100, 30));
            bookingToolbar.add(comp);
            bookingToolbar.add(Box.createHorizontalStrut(10));
        }

        add(bookingToolbar);

        // Panel hiển thị bàn
        gridPanel = new JPanel();
        JScrollPane scrollPane = new JScrollPane(gridPanel,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBounds(220, 130, 1220, 550);
        add(scrollPane);

        updateGridPanel(new Date());

        // Tìm bàn
        btnTimBan.addActionListener(e -> {
            String sdt = txtPhone.getText().trim();
            String khuVuc = (String) comboKhuVuc.getSelectedItem();
            String soChoStr = txtSoCho.getText().trim();
            int soCho = 0;
            try {
                soCho = Integer.parseInt(soChoStr);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Số chỗ nhập không hợp lệ!");
                return;
            }

            Date ngayDat = dateChooser.getDate();
            Ban_DAO dao = new Ban_DAO();

            // ✅ Lấy tất cả bàn trong ngày kèm trạng thái đúng
            List<Ban> listBan = dao.getAllBanByDate(ngayDat);

            // ✅ Lọc theo khu vực và số chỗ ngồi (nếu có)
            List<Ban> filtered = listBan.stream()
                    .filter(b -> ("All".equalsIgnoreCase(khuVuc) || b.getMaKhuVuc().equalsIgnoreCase(khuVuc)))
                    .filter(b -> b.getSoChoNgoi() >= soCho) // cần có getSoChoNgoi trong entity Ban
                    .toList();

            if (filtered.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Không còn bàn nào phù hợp.");
            }

            updateGridPanel(filtered);
        });


        // Đặt bàn (giả lập chọn bàn B005)
        btnDatBan.addActionListener(e -> {
            String maBan = "B005";
            String sdt = txtPhone.getText().trim();
            Date ngayDat = dateChooser.getDate();
            String startTime = "10:00", endTime = "12:00";
            Ban_DAO dao = new Ban_DAO();
            if (dao.isTableBooked(maBan, ngayDat, startTime, endTime)) {
                JOptionPane.showMessageDialog(this, "Bàn đã được đặt trong khoảng thời gian này.");
            } else if (dao.datBan(maBan, ngayDat, startTime, endTime, sdt)) {
                JOptionPane.showMessageDialog(this, "Đặt bàn thành công!");
                int soCho = Integer.parseInt(txtSoCho.getText().trim());
                String khuVuc = (String) comboKhuVuc.getSelectedItem();
                List<Ban> list = dao.getBanTheoTieuChi(khuVuc, soCho, ngayDat);
                updateGridPanel(list);
            } else {
                JOptionPane.showMessageDialog(this, "Có lỗi xảy ra, vui lòng thử lại.");
            }
        });
    }

    private void updateGridPanel(List<Ban> listBan) {
        gridPanel.removeAll();
        gridPanel.setLayout(new WrapLayout(FlowLayout.LEFT, 20, 20));

        for (Ban ban : listBan) {
            String defaultIconPath = "";
            if ("Đã đặt".equalsIgnoreCase(ban.getTrangThai())) {
                defaultIconPath = "/view/icon/icon_BanDaDat.png";
            } else if ("Trống".equalsIgnoreCase(ban.getTrangThai())) {
                defaultIconPath = "/view/icon/icon_BanTrong.png";
            } else if ("Đang phục vụ".equalsIgnoreCase(ban.getTrangThai())) {
                defaultIconPath = "/view/icon/icon_BanDangPhucVu.png";
            } else {
                defaultIconPath = "/view/icon/icon_Default.png";
            }

            final ImageIcon defaultIcon = new ImageIcon(
                    new ImageIcon(getClass().getResource(defaultIconPath)).getImage()
                            .getScaledInstance(100, 100, Image.SCALE_SMOOTH));

            String selectedIconPath = "/view/icon/icon_BanDangChon.png";
            final ImageIcon selectedIcon = new ImageIcon(
                    new ImageIcon(getClass().getResource(selectedIconPath)).getImage()
                            .getScaledInstance(100, 100, Image.SCALE_SMOOTH));

            JPanel tablePanel = new JPanel();
            tablePanel.setLayout(new BoxLayout(tablePanel, BoxLayout.Y_AXIS));
            tablePanel.setBorder(new LineBorder(Color.GRAY, 1, true));
            tablePanel.setPreferredSize(new Dimension(200, 160));
            tablePanel.setBackground(Color.WHITE);

            JLabel labelIcon = new JLabel(defaultIcon);
            labelIcon.setAlignmentX(Component.CENTER_ALIGNMENT);
            JLabel labelText = new JLabel("Bàn " + ban.getMaBan());
            labelText.setAlignmentX(Component.CENTER_ALIGNMENT);
            tablePanel.add(labelIcon);
            tablePanel.add(Box.createVerticalStrut(5));
            tablePanel.add(labelText);

            final boolean[] isSelected = { false };
            tablePanel.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent e) {
                    isSelected[0] = !isSelected[0];
                    if (isSelected[0]) {
                        labelIcon.setIcon(selectedIcon);
                    } else {
                        labelIcon.setIcon(defaultIcon);
                    }
                    tablePanel.revalidate();
                    tablePanel.repaint();
                }
            });

            gridPanel.add(tablePanel);
        }

        gridPanel.revalidate();
        gridPanel.repaint();
    }

    private void updateGridPanel(Date date) {
        Ban_DAO dao = new Ban_DAO();
        List<Ban> list = dao.getAllBanByDate(date);
        updateGridPanel(list);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Đặt Bàn");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1440, 680);
            frame.setLocationRelativeTo(null);
            frame.getContentPane().add(new GUI_DatBan());
            frame.setVisible(true);
        });
    }
}