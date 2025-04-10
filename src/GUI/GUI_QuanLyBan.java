package GUI;

import com.toedter.calendar.JDateChooser;
import DAO.Ban_DAO;
import Entity.Ban;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.Date;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI_QuanLyBan extends JPanel {
    
    private static final long serialVersionUID = 1L;
    // Panel chứa danh sách bàn, để cập nhật theo ngày
    private JPanel gridPanel;
    // Date chooser để chọn ngày hiển thị bàn
    private JDateChooser dateChooser;
    
    public GUI_QuanLyBan() {
        // Dùng layout tuyệt đối cho panel chính
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
        btnThem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Xử lý thêm bàn (nếu cần)
            }
        });
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
        JComboBox<String> comboTimKiem = new JComboBox<>(new String[]{"All", "Tầng 1", "Tầng 2", "Khu VIP"});
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
        // Thêm FocusListener để xử lý placeholder
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
        
        // Thêm thanh công cụ vào panel chính
        add(topPanel);
        
        
        /// ------------------ Sidebar ------------------
        
        
        
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
        // Thêm border rỗng làm padding (trên, trái, dưới, phải)
        datePanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JDateChooser dateChooser = new JDateChooser();
        dateChooser.setDate(new Date());
        dateChooser.setPreferredSize(new Dimension(150, 30));
        dateChooser.setMaximumSize(new Dimension(150, 30));
        // Điều chỉnh kích thước của component bên trong (DateEditor) nếu cần
        dateChooser.getDateEditor().getUiComponent().setPreferredSize(new Dimension(140, 20));

        datePanel.add(dateChooser);
        panel_sidebar.add(datePanel);
        panel_sidebar.add(Box.createVerticalStrut(10));

        // Lắng nghe sự thay đổi ngày: mỗi khi người dùng chọn ngày mới, cập nhật lại danh sách bàn
        dateChooser.addPropertyChangeListener("date", evt -> {
            Date selectedDate = dateChooser.getDate();
            updateGridPanel(selectedDate);
        });
        
        // Đang phục vụ
        JPanel item1 = new JPanel();
        item1.setLayout(new BoxLayout(item1, BoxLayout.Y_AXIS));
        item1.setOpaque(false);
        item1.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Lấy icon, scale
        ImageIcon rawIcon1 = new ImageIcon(getClass().getResource("/view/icon/icon_BanDangPhucVu.png"));
        Image scaledImg1 = rawIcon1.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
        ImageIcon icon1 = new ImageIcon(scaledImg1);

        JLabel iconLabel1 = new JLabel(icon1);
        iconLabel1.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel textLabel1 = new JLabel("Đang phục vụ");
        textLabel1.setAlignmentX(Component.CENTER_ALIGNMENT);
        textLabel1.setForeground(Color.BLACK);

        item1.add(iconLabel1);
        item1.add(Box.createVerticalStrut(10));
        item1.add(textLabel1);
        item1.add(Box.createVerticalStrut(30));

        panel_sidebar.add(item1);

        // Trống
        JPanel item2 = new JPanel();
        item2.setLayout(new BoxLayout(item2, BoxLayout.Y_AXIS));
        item2.setOpaque(false);
        item2.setAlignmentX(Component.CENTER_ALIGNMENT);

        ImageIcon rawIcon2 = new ImageIcon(getClass().getResource("/view/icon/icon_BanTrong.png"));
        Image scaledImg2 = rawIcon2.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
        ImageIcon icon2 = new ImageIcon(scaledImg2);

        JLabel iconLabel2 = new JLabel(icon2);
        iconLabel2.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel textLabel2 = new JLabel("Trống");
        textLabel2.setAlignmentX(Component.CENTER_ALIGNMENT);
        textLabel2.setForeground(Color.BLACK);

        item2.add(iconLabel2);
        item2.add(Box.createVerticalStrut(10));
        item2.add(textLabel2);
        item2.add(Box.createVerticalStrut(30));

        panel_sidebar.add(item2);

        // Đang chọn
        JPanel item3 = new JPanel();
        item3.setLayout(new BoxLayout(item3, BoxLayout.Y_AXIS));
        item3.setOpaque(false);
        item3.setAlignmentX(Component.CENTER_ALIGNMENT);

        ImageIcon rawIcon3 = new ImageIcon(getClass().getResource("/view/icon/icon_BanDangChon.png"));
        Image scaledImg3 = rawIcon3.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
        ImageIcon icon3 = new ImageIcon(scaledImg3);

        JLabel iconLabel3 = new JLabel(icon3);
        iconLabel3.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel textLabel3 = new JLabel("Đang chọn");
        textLabel3.setAlignmentX(Component.CENTER_ALIGNMENT);
        textLabel3.setForeground(Color.BLACK);

        item3.add(iconLabel3);
        item3.add(Box.createVerticalStrut(10));
        item3.add(textLabel3);
        item3.add(Box.createVerticalStrut(30));

        panel_sidebar.add(item3);

        // Đã đặt
        JPanel item4 = new JPanel();
        item4.setLayout(new BoxLayout(item4, BoxLayout.Y_AXIS));
        item4.setOpaque(false);
        item4.setAlignmentX(Component.CENTER_ALIGNMENT);

        ImageIcon rawIcon4 = new ImageIcon(getClass().getResource("/view/icon/icon_BanDaDat.png"));
        Image scaledImg4 = rawIcon4.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
        ImageIcon icon4 = new ImageIcon(scaledImg4);

        JLabel iconLabel4 = new JLabel(icon4);
        iconLabel4.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel textLabel4 = new JLabel("Đã đặt");
        textLabel4.setAlignmentX(Component.CENTER_ALIGNMENT);
        textLabel4.setForeground(Color.BLACK);

        item4.add(iconLabel4);
        item4.add(Box.createVerticalStrut(10));
        item4.add(textLabel4);
        item4.add(Box.createVerticalStrut(30));

        panel_sidebar.add(item4);

        // Thêm vertical glue để dãn phần dưới
        panel_sidebar.add(Box.createVerticalGlue());
        
        add(panel_sidebar);
        
        
        // ------------------ Phần hiển thị bàn (Grid Panel) ------------------
        // Tạo panel grid chứa các bàn, sử dụng GridLayout: 4 cột, khoảng cách 10 pixel
        gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(0, 4, 10, 10));
        // Ban đầu, load bàn theo ngày hiện tại
        updateGridPanel(new Date());
        
        // Bọc gridPanel vào JScrollPane, chỉ hiển thị thanh cuộn dọc khi cần
        JScrollPane scrollPane = new JScrollPane(
            gridPanel,
            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
        );
        // Đặt kích thước cho khu vực hiển thị các bàn: 1220 x 550
        scrollPane.setBounds(220, 130, 1220, 550);
        add(scrollPane);
    }
    
    // Phương thức cập nhật danh sách bàn theo ngày được chọn
    private void updateGridPanel(Date selectedDate) {
        // Xóa tất cả các bàn hiện có trong gridPanel
        gridPanel.removeAll();
        
        // Lấy dữ liệu bàn thông qua DAO, ví dụ sử dụng phương thức getAllBanByDate(Date date)
        Ban_DAO banDAO = new Ban_DAO();
        List<Ban> listBan = banDAO.getAllBanByDate(selectedDate); 
        // Nếu chưa có phương thức này, bạn có thể thử getAllBan() tạm thời
        
        // Duyệt từng đối tượng Ban và thêm vào gridPanel
        for (Ban ban : listBan) {
            // Xác định icon hiển thị theo trạng thái của bàn
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
            
            // Tạo icon mặc định (scale về kích thước 100x100)
            final ImageIcon defaultIcon = new ImageIcon(
                    new ImageIcon(getClass().getResource(defaultIconPath))
                    .getImage()
                    .getScaledInstance(100, 100, Image.SCALE_SMOOTH)
            );
            
            // Tạo icon cho trạng thái "Đang chọn"
            String selectedIconPath = "/view/icon/icon_BanDangChon.png";
            final ImageIcon selectedIcon = new ImageIcon(
                    new ImageIcon(getClass().getResource(selectedIconPath))
                    .getImage()
                    .getScaledInstance(100, 100, Image.SCALE_SMOOTH)
            );
            
            // Tạo panel cho mỗi bàn với kích thước cố định 300 x 150, sử dụng Border bo tròn
            JPanel tablePanel = new JPanel();
            tablePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            tablePanel.setPreferredSize(new Dimension(300, 150));
            tablePanel.setBorder(new RoundBorder(40, Color.GRAY));
            tablePanel.setLayout(new BoxLayout(tablePanel, BoxLayout.Y_AXIS));
            
            // Tạo JLabel hiển thị icon và thông tin mã bàn
            JLabel labelIcon = new JLabel(defaultIcon);
            JLabel labelText = new JLabel("Bàn " + ban.getMaBan());
            labelIcon.setAlignmentX(CENTER_ALIGNMENT);
            labelText.setAlignmentX(CENTER_ALIGNMENT);
            
            tablePanel.add(labelIcon);
            tablePanel.add(labelText);
            
            // Biến để theo dõi trạng thái chọn của bàn (không lưu vào DB)
            final boolean[] isSelected = { false };
            
            // Thêm MouseListener để toggle icon khi click
            tablePanel.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent e) {
                    isSelected[0] = !isSelected[0];
                    if (isSelected[0]) {
                        labelIcon.setIcon(selectedIcon);
                    } else {
                        labelIcon.setIcon(defaultIcon);
                        tablePanel.setBackground(null);
                    }
                    tablePanel.revalidate();
                    tablePanel.repaint();
                }
            });
            
            gridPanel.add(tablePanel);
        }
        
        // Cập nhật lại giao diện hiển thị
        gridPanel.revalidate();
        gridPanel.repaint();
    }
    
    // Phương thức main để test giao diện
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Quản Lý Bàn");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1440, 680); // 130 cho thanh toolbar + 550 cho khu vực grid
            frame.setLocationRelativeTo(null);
            
            GUI_QuanLyBan guiQuanLyBan = new GUI_QuanLyBan();
            frame.getContentPane().add(guiQuanLyBan);
            
            frame.setVisible(true);
        });
    }
}
