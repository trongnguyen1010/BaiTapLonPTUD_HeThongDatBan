package GUI;

import com.toedter.calendar.JDateChooser;
import DAO.Ban_DAO;
import Entity.Ban;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;

public class GUI_QuanLyBan extends JPanel {

    private static final long serialVersionUID = 1L;
    // Panel chứa danh sách bàn, để cập nhật theo ngày
    private JPanel gridPanel;
    // Date chooser để chọn ngày hiển thị bàn
    private JDateChooser dateChooser;
    // Global lưu mã bàn được chọn (chỉ cho phép 1 bàn được chọn)
    private String selectedMaBan = null;
	protected String selectedTableInfo;

    public GUI_QuanLyBan() {
        setLayout(null);

        // ------------------ Thanh công cụ (Toolbar) ------------------
        JPanel topPanel = new JPanel();
        topPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
        topPanel.setBounds(220, 0, 1220, 130);
        topPanel.setBackground(new Color(161, 227, 249));
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));

        // Khoảng cách bên trái
        topPanel.add(Box.createHorizontalStrut(59));

        // Nút THÊM
        JButton btnThem = new JButton("THÊM", new ImageIcon(getClass().getResource("/view/icon/icon_them.png")));
        btnThem.setHorizontalTextPosition(SwingConstants.CENTER);
        btnThem.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnThem.setBorderPainted(false);
        btnThem.setContentAreaFilled(false);
        btnThem.setFocusPainted(false);
        topPanel.add(btnThem);

        topPanel.add(Box.createHorizontalStrut(30));

        // Nút CẬP NHẬT
        JButton btnCapNhat = new JButton("CẬP NHẬT", new ImageIcon(getClass().getResource("/view/icon/icon_capnhat.png")));
        btnCapNhat.setHorizontalTextPosition(SwingConstants.CENTER);
        btnCapNhat.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnCapNhat.setBorderPainted(false);
        btnCapNhat.setContentAreaFilled(false);
        btnCapNhat.setFocusPainted(false);
        topPanel.add(btnCapNhat);

        topPanel.add(Box.createHorizontalStrut(31));

        // Nút XÓA
        JButton btnXoa = new JButton("XÓA", new ImageIcon(getClass().getResource("/view/icon/icon_xoa.png")));
        btnXoa.setHorizontalTextPosition(SwingConstants.CENTER);
        btnXoa.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnXoa.setBorderPainted(false);
        btnXoa.setContentAreaFilled(false);
        btnXoa.setFocusPainted(false);
        topPanel.add(btnXoa);

        // Dãn khoảng cách đẩy các thành phần còn lại sang bên phải
        topPanel.add(Box.createHorizontalGlue());

        // Combobox lọc (ví dụ: theo khu vực)
        JComboBox<String> comboTimKiem = new JComboBox<>(new String[] { "All", "Tầng 1", "Tầng 2", "Khu VIP" });
        comboTimKiem.setPreferredSize(new Dimension(150, 30));
        comboTimKiem.setMaximumSize(new Dimension(150, 40));
        topPanel.add(comboTimKiem);

        topPanel.add(Box.createHorizontalStrut(58));

        // Ô tìm kiếm (sử dụng RoundTextField - lớp này do bạn tự tạo)
        RoundTextField txtTimKiem = new RoundTextField(30, Color.WHITE, Color.GRAY);
        txtTimKiem.setBorder(new LineBorder(new Color(171, 173, 179), 2, true));
        final String placeholder = "Tìm kiếm mã bàn";
        txtTimKiem.setText(placeholder);
        txtTimKiem.setForeground(Color.GRAY);
        txtTimKiem.setPreferredSize(new Dimension(362, 65));
        txtTimKiem.setMaximumSize(new Dimension(200, 60));
        topPanel.add(txtTimKiem);
        txtTimKiem.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent e) {
                if (txtTimKiem.getText().equals(placeholder)) {
                    txtTimKiem.setText("");
                    txtTimKiem.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(java.awt.event.FocusEvent e) {
                if (txtTimKiem.getText().isEmpty()) {
                    txtTimKiem.setText(placeholder);
                    txtTimKiem.setForeground(Color.GRAY);
                }
            }
        });

        topPanel.add(Box.createHorizontalStrut(61));

        add(topPanel);

        // ------------------ Sidebar ------------------
        JPanel panel_sidebar = new JPanel();
        panel_sidebar.setBounds(0, 0, 220, 680);
        panel_sidebar.setBackground(new Color(161, 227, 249));
        panel_sidebar.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panel_sidebar.setLayout(new BoxLayout(panel_sidebar, BoxLayout.Y_AXIS));

        // Thêm khoảng cách ở phần trên cùng
        panel_sidebar.add(Box.createVerticalStrut(20));

        // THÊM thành phần chọn ngày
        JLabel labelChonNgay = new JLabel("Chọn ngày:");
        labelChonNgay.setForeground(Color.BLACK);
        labelChonNgay.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel_sidebar.add(labelChonNgay);

        // Tạo một JPanel bao bọc cho JDateChooser để thêm padding
        JPanel datePanel = new JPanel();
        datePanel.setBackground(new Color(161, 227, 249));
        datePanel.setLayout(new BoxLayout(datePanel, BoxLayout.X_AXIS));
        datePanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        dateChooser = new JDateChooser();
        dateChooser.setDate(new Date());
        dateChooser.setPreferredSize(new Dimension(150, 30));
        dateChooser.setMaximumSize(new Dimension(150, 30));
        dateChooser.getDateEditor().getUiComponent().setPreferredSize(new Dimension(140, 20));
        datePanel.add(dateChooser);
        panel_sidebar.add(datePanel);
        panel_sidebar.add(Box.createVerticalStrut(10));

        // Lắng nghe sự thay đổi ngày: cập nhật gridPanel
        dateChooser.addPropertyChangeListener("date", evt -> {
            Date selectedDate = dateChooser.getDate();
            updateGridPanel(selectedDate);
        });

        // Sidebar hiển thị trạng thái bàn
        panel_sidebar.add(createSidebarItem("/view/icon/icon_BanDangPhucVu.png", "Đang phục vụ"));
        panel_sidebar.add(createSidebarItem("/view/icon/icon_BanTrong.png", "Trống"));
        panel_sidebar.add(createSidebarItem("/view/icon/icon_BanDangChon.png", "Đang chọn"));
        panel_sidebar.add(createSidebarItem("/view/icon/icon_BanDaDat.png", "Đã đặt"));
        panel_sidebar.add(Box.createVerticalGlue());

        add(panel_sidebar);

        // ------------------ Grid Panel ------------------
        gridPanel = new JPanel();
//        gridPanel.setLayout(new GridLayout(0, 4, 10, 10));
        gridPanel.setLayout(new WrapLayout(FlowLayout.LEFT, 10, 10));
        updateGridPanel(new Date());
        JScrollPane scrollPane = new JScrollPane(gridPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBounds(220, 130, 1220, 550);
        add(scrollPane);

     // ------------------ Sự kiện ------------------

        /* Sự kiện tìm kiếm bàn: nếu ô tìm kiếm không rỗng và không bằng placeholder,
           gọi hàm searchBanByMa của DAO và cập nhật gridPanel */
        txtTimKiem.addActionListener(e -> {
            String keyword = txtTimKiem.getText().trim();
            if (!keyword.isEmpty() && !keyword.equals(placeholder)) {
                Ban_DAO dao = new Ban_DAO();
                List<Ban> searchResult = dao.searchBanByMa(keyword);
                updateGridPanel(searchResult);
            }
        });
        
     // Sự kiện lọc theo khu vực 
        comboTimKiem.addActionListener(e -> {
            // Lấy ngày hiện tại từ dateChooser
            Date ngayDat = dateChooser.getDate();
            Ban_DAO dao = new Ban_DAO();
            // Truy xuất tất cả bàn theo ngày (để lát nữa lọc theo khu vực)
            List<Ban> list = dao.getAllBanByDate(ngayDat);
            // Lấy giá trị trên combobox
            String selectedValue = ((String) comboTimKiem.getSelectedItem()).trim();

            String mappedArea = null;
            if (selectedValue.equalsIgnoreCase("Tầng 1")) {
                mappedArea = "KV01";
            } else if (selectedValue.equalsIgnoreCase("Tầng 2")) {
                mappedArea = "KV02";
            } else if (selectedValue.equalsIgnoreCase("Khu VIP")) {
                mappedArea = "KVIP";
            }
            // Nếu mappedArea = null nghĩa là hoặc "All" hoặc không khớp => không lọc
            // Nếu mappedArea không null => lọc theo khu vực
            if (mappedArea != null) {
                final String areaFilter = mappedArea; // cần final hoặc effectively final để dùng trong stream
                list = list.stream()
                           .filter(b -> b.getMaKhuVuc().equalsIgnoreCase(areaFilter))
                           .collect(Collectors.toList());
            }

            updateGridPanel(list);
        });


        // Sự kiện nút "THÊM"
        btnThem.addActionListener(e -> {
            CreateBanDialog createDialog = new CreateBanDialog(SwingUtilities.getWindowAncestor(this));
            createDialog.setVisible(true);
            if (createDialog.isConfirmed()) {
            	Ban newBan = createDialog.getBan(); // lấy đối tượng Ban mới từ dialog
                Ban_DAO dao = new Ban_DAO();
                dao.addBan(newBan);  // Ở đây gọi hàm addBan để chèn dữ liệu vào CSDL
                JOptionPane.showMessageDialog(this, "Thêm bàn thành công!");
                updateGridPanel(dao.getAllBan());
            }
        });

        // Sự kiện nút "CẬP NHẬT"
        btnCapNhat.addActionListener(e -> {
            if (selectedMaBan == null) {
                JOptionPane.showMessageDialog(this, "Bạn chưa chọn bàn để cập nhật!");
                return;
            }
            Ban_DAO dao = new Ban_DAO();
            Ban ban = dao.getBanById(selectedMaBan);
            if (ban == null) {
                JOptionPane.showMessageDialog(this, "Không tìm thấy thông tin bàn!");
                return;
            }
            // Mở dialog cập nhật với thông tin bàn hiện tại
            UpdateBanDialog updateDialog = new UpdateBanDialog(SwingUtilities.getWindowAncestor(this), ban);
            updateDialog.setVisible(true);
            if (updateDialog.isConfirmed()) {
                Ban updatedBan = updateDialog.getUpdatedBan();
                // Gọi hàm cập nhật và kiểm tra kết quả trả về
                boolean updated = dao.updateBan(updatedBan);
                if (updated) {
                    updateGridPanel(dao.getAllBan());
                    JOptionPane.showMessageDialog(this, "Cập nhật bàn thành công!");
                    resetTableSelection();
                } else {
                    JOptionPane.showMessageDialog(this, "Cập nhật thất bại! Bàn có lịch đặt trong tương lai.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Cập nhật bị hủy bỏ!");
            }
        });


        /* Sự kiện nút "XÓA": kiểm tra nếu có bàn được chọn, xác nhận từ người dùng,
           sau đó gọi hàm deleteBan của DAO. Nếu xóa thành công, cập nhật lại danh sách bàn;
           nếu không, hiển thị thông báo lỗi (ví dụ, vì bàn đã được đặt). */
        btnXoa.addActionListener(e -> {
            if (selectedMaBan == null) {
                JOptionPane.showMessageDialog(this, "Bạn chưa chọn bàn để xóa!");
                return;
            }
            int confirm = JOptionPane.showConfirmDialog(this,
                    "Bạn có chắc chắn xóa bàn " + selectedMaBan + "?",
                    "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                Ban_DAO dao = new Ban_DAO();
                if (dao.deleteBan(selectedMaBan)) {
                    JOptionPane.showMessageDialog(this, "Xóa bàn thành công!");
                    updateGridPanel(dao.getAllBan());
                    resetTableSelection();
                } else {
                    JOptionPane.showMessageDialog(this, "Xóa bàn thất bại! Bàn đã có lịch đặt trong tương lai.");
                }
            }
        });

    }

    // Hàm tạo một item trong sidebar với icon và text
    private JPanel createSidebarItem(String iconPath, String text) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(false);
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        ImageIcon rawIcon = new ImageIcon(getClass().getResource(iconPath));
        Image scaledImg = rawIcon.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
        ImageIcon icon = new ImageIcon(scaledImg);
        JLabel iconLabel = new JLabel(icon);
        iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel textLabel = new JLabel(text);
        textLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        textLabel.setForeground(Color.BLACK);
        panel.add(iconLabel);
        panel.add(Box.createVerticalStrut(10));
        panel.add(textLabel);
        panel.add(Box.createVerticalStrut(30));
        return panel;
    }

    // Phương thức reset lại trạng thái chọn của các bàn trong gridPanel
    private void resetTableSelection() {
        // Giả sử gridPanel có chứa các panel con được tạo và mỗi panel con lưu defaultIcon trong client property
        for (Component comp : gridPanel.getComponents()) {
            if (comp instanceof JPanel) {
                JPanel panel = (JPanel) comp;
                Object obj = panel.getClientProperty("defaultIcon");
                if (obj instanceof ImageIcon) {
                    ImageIcon defaultIcon = (ImageIcon) obj;
                    // Nếu panel có ít nhất một JLabel, ta cập nhật icon của JLabel đầu tiên
                    if (panel.getComponentCount() > 0 && panel.getComponent(0) instanceof JLabel) {
                        ((JLabel) panel.getComponent(0)).setIcon(defaultIcon);
                    }
                }
            }
        }
        selectedMaBan = null;
    }

    // Phương thức cập nhật gridPanel theo danh sách bàn được truyền vào.
    private void updateGridPanel(List<Ban> listBan) {
        gridPanel.removeAll();
//        gridPanel.setLayout(new GridLayout(0, 4, 10, 10));
        gridPanel.setLayout(new WrapLayout(FlowLayout.LEFT, 20, 20));
        // Reset lựa chọn khi cập nhật grid
        selectedMaBan = null;
        for (Ban ban : listBan) {
            String defaultIconPath;
            switch (ban.getTrangThai().toLowerCase()) {
                case "đã đặt":
                    defaultIconPath = "/view/icon/icon_BanDaDat.png";
                    break;
                case "trống":
                    defaultIconPath = "/view/icon/icon_BanTrong.png";
                    break;
                case "đang phục vụ":
                    defaultIconPath = "/view/icon/icon_BanDangPhucVu.png";
                    break;
                default:
                    defaultIconPath = "/view/icon/icon_Default.png";
            }
            final ImageIcon defaultIcon = new ImageIcon(new ImageIcon(getClass().getResource(defaultIconPath))
                    .getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
            final ImageIcon selectedIcon = new ImageIcon(new ImageIcon(getClass().getResource("/view/icon/icon_BanDangChon.png"))
                    .getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));

            JPanel tablePanel = new JPanel();
            tablePanel.setLayout(new BoxLayout(tablePanel, BoxLayout.Y_AXIS));
            tablePanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1, true));
            tablePanel.setPreferredSize(new Dimension(200, 160));
            tablePanel.setBackground(Color.WHITE);

            JLabel labelIcon = new JLabel(defaultIcon);
            labelIcon.setAlignmentX(Component.CENTER_ALIGNMENT);
            // Lưu default icon cho panel để reset sau này
            tablePanel.putClientProperty("defaultIcon", defaultIcon);

            JLabel labelText = new JLabel("Bàn " + ban.getMaBan());
            labelText.setAlignmentX(Component.CENTER_ALIGNMENT);
            tablePanel.add(labelIcon);
            tablePanel.add(Box.createVerticalStrut(5));
            tablePanel.add(labelText);

            tablePanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    resetTableSelection();
                    selectedMaBan = ban.getMaBan();
                    // Tạo thông tin bàn từ các thuộc tính, ví dụ: "Bàn 5 – KV01 – 4 chỗ"
                    selectedTableInfo = "Bàn " + ban.getMaBan() + " – " + ban.getMaKhuVuc() + " – " + ban.getSoChoNgoi() + " chỗ";
                    labelIcon.setIcon(selectedIcon);
                    tablePanel.revalidate();
                    tablePanel.repaint();
                }
            });

            gridPanel.add(tablePanel);
        }
        gridPanel.revalidate();
        gridPanel.repaint();
    }

    // Phương thức cập nhật gridPanel theo ngày (nếu cần)
    private void updateGridPanel(Date date) {
        Ban_DAO dao = new Ban_DAO();
        List<Ban> list = dao.getAllBanByDate(date);
        updateGridPanel(list);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Quản Lý Bàn");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1440, 680);
            frame.setLocationRelativeTo(null);
            GUI_QuanLyBan guiQuanLyBan = new GUI_QuanLyBan();
            frame.getContentPane().add(guiQuanLyBan);
            frame.setVisible(true);
        });
    }
}
