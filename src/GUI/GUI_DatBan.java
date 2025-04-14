package GUI;

import com.toedter.calendar.JDateChooser;
import DAO.Ban_DAO;
import Entity.Ban;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
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
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;

public class GUI_DatBan extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel gridPanel;
	private JDateChooser dateChooser;
	// Global lưu mã bàn được chọn (chỉ cho phép 1 bàn được chọn)
	private String selectedMaBan = null;
	// Lưu thông tin bàn được chọn (chuỗi mô tả bàn, ví dụ: "Bàn 5 – KV01 – 4 chỗ")
	private String selectedTableInfo = null;

	public GUI_DatBan() {
		setLayout(null);

		// ------------------ Booking Toolbar ------------------
		// Thanh tìm kiếm/đặt bàn mới
		JPanel bookingToolbar = new JPanel();
		bookingToolbar.setBackground(new Color(161, 227, 249));
		bookingToolbar.setBounds(220, 0, 1220, 130);
		bookingToolbar.setLayout(new BoxLayout(bookingToolbar, BoxLayout.X_AXIS));

		// Thêm khoảng cách bên trái
		bookingToolbar.add(Box.createHorizontalStrut(20));

		// Tiêu chí tìm bàn: chọn khu vực
		JLabel lblKhuVuc = new JLabel("Khu vực:");
		lblKhuVuc.setPreferredSize(new Dimension(70, 30));
		bookingToolbar.add(lblKhuVuc);
		JComboBox<String> comboKhuVuc = new JComboBox<>(new String[] { "All", "KV01", "KV02", "KVIP" });
		comboKhuVuc.setPreferredSize(new Dimension(100, 30));
		comboKhuVuc.setMaximumSize(new Dimension(150, 40));
		bookingToolbar.add(comboKhuVuc);
		bookingToolbar.add(Box.createHorizontalStrut(10));

		// Tiêu chí tìm bàn: số chỗ
		JLabel lblSoCho = new JLabel("Số chỗ:");
		lblSoCho.setPreferredSize(new Dimension(70, 30));
		bookingToolbar.add(lblSoCho);
		JTextField txtSoCho = new JTextField();
		txtSoCho.setPreferredSize(new Dimension(50, 30));
		txtSoCho.setMaximumSize(new Dimension(150, 40));
		bookingToolbar.add(txtSoCho);
		bookingToolbar.add(Box.createHorizontalStrut(10));

		// Thời gian đặt bàn: chọn ngày
		JLabel lblNgayDat = new JLabel("Ngày đặt:");
		lblNgayDat.setPreferredSize(new Dimension(80, 30));
		bookingToolbar.add(lblNgayDat);
		dateChooser = new JDateChooser();
		dateChooser.setDate(new Date());
		dateChooser.setPreferredSize(new Dimension(120, 30));
		// Tạo một JPanel bao bọc cho JDateChooser để thêm padding
		JPanel datePanel = new JPanel();
		datePanel.setBackground(new Color(161, 227, 249));
		datePanel.setLayout(new BoxLayout(datePanel, BoxLayout.X_AXIS));
		// Thêm border rỗng làm padding (trên, trái, dưới, phải)
		datePanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		dateChooser.setMaximumSize(new Dimension(150, 30));
		// Điều chỉnh kích thước của component bên trong (DateEditor) nếu cần
		dateChooser.getDateEditor().getUiComponent().setPreferredSize(new Dimension(140, 20));
		bookingToolbar.add(dateChooser);
		bookingToolbar.add(Box.createHorizontalStrut(10));

		// (Có thể bổ sung thêm JComboBox chọn giờ nếu muốn điều chỉnh giờ đặt)
		// Ví dụ:
		JLabel lblGioBatDau = new JLabel("Giờ BD:");
		lblGioBatDau.setPreferredSize(new Dimension(60, 30));
		bookingToolbar.add(lblGioBatDau);
		String[] gioBD = { "08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00",
				"19:00", "20:00", "21:00" };
		JComboBox<String> comboGioBatDau = new JComboBox<>(gioBD);
		comboGioBatDau.setPreferredSize(new Dimension(80, 30));
		comboGioBatDau.setMaximumSize(new Dimension(150, 40));
		bookingToolbar.add(comboGioBatDau);
		bookingToolbar.add(Box.createHorizontalStrut(10));

		JLabel lblGioKetThuc = new JLabel("Giờ KT:");
		lblGioKetThuc.setPreferredSize(new Dimension(60, 30));
		bookingToolbar.add(lblGioKetThuc);
		String[] gioKT = { "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00",
				"19:00", "20:00", "21:00", "22:00" };
		JComboBox<String> comboGioKetThuc = new JComboBox<>(gioKT);
		comboGioKetThuc.setPreferredSize(new Dimension(80, 30));
		comboGioKetThuc.setMaximumSize(new Dimension(150, 40));
		bookingToolbar.add(comboGioKetThuc);
		bookingToolbar.add(Box.createHorizontalStrut(30));

		// Nút "Tìm bàn"
		JButton btnTimBan = new JButton("Tìm bàn");
		btnTimBan.setBackground(new Color(52, 152, 219)); // Xanh dương tươi
		btnTimBan.setForeground(Color.WHITE); // Chữ trắng
		btnTimBan.setFocusPainted(false);
		btnTimBan.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnTimBan.setPreferredSize(new Dimension(100, 40));
		btnTimBan.setMaximumSize(new Dimension(100, 40));
		btnTimBan.setBorder(BorderFactory.createLineBorder(new Color(41, 128, 185), 1));
		btnTimBan.setCursor(new Cursor(Cursor.HAND_CURSOR));
		bookingToolbar.add(btnTimBan);
		bookingToolbar.add(Box.createHorizontalStrut(20));

		// Nút "Đặt bàn"
		JButton btnDatBan = new JButton("Đặt bàn");
		btnDatBan.setBackground(new Color(39, 174, 96)); // Xanh lá cây tươi
		btnDatBan.setForeground(Color.WHITE);
		btnDatBan.setFocusPainted(false);
		btnDatBan.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnDatBan.setMaximumSize(new Dimension(100, 40));
		btnDatBan.setBorder(BorderFactory.createLineBorder(new Color(30, 132, 73), 1));
		btnDatBan.setCursor(new Cursor(Cursor.HAND_CURSOR));
		bookingToolbar.add(btnDatBan);

		bookingToolbar.add(Box.createHorizontalStrut(50));

		add(bookingToolbar);

		// ------------------ Sidebar (giữ nguyên) ------------------
		JPanel panel_sidebar = new JPanel();
		panel_sidebar.setBounds(0, 0, 220, 680);
		panel_sidebar.setBackground(new Color(161, 227, 249));
		panel_sidebar.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panel_sidebar.setLayout(new BoxLayout(panel_sidebar, BoxLayout.Y_AXIS));

		panel_sidebar.add(Box.createVerticalStrut(80));
		panel_sidebar.add(createSidebarItem("/view/icon/icon_BanDangPhucVu.png", "Đang phục vụ"));
		panel_sidebar.add(createSidebarItem("/view/icon/icon_BanTrong.png", "Trống"));
		panel_sidebar.add(createSidebarItem("/view/icon/icon_BanDangChon.png", "Đang chọn"));
		panel_sidebar.add(createSidebarItem("/view/icon/icon_BanDaDat.png", "Đã đặt"));
		panel_sidebar.add(Box.createVerticalGlue());
		add(panel_sidebar);

		// ------------------ Grid Panel ------------------
		gridPanel = new JPanel();
		gridPanel.setLayout(new WrapLayout(FlowLayout.LEFT, 20, 20));
		updateGridPanel(new Date());
		JScrollPane scrollPane = new JScrollPane(gridPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(220, 130, 1220, 550);
		add(scrollPane);

		// ------------------ Sự kiện ------------------
		btnTimBan.addActionListener(e -> {
			resetTableSelection();
			String khuVuc = (String) comboKhuVuc.getSelectedItem();
			int soCho;
			try {
				soCho = Integer.parseInt(txtSoCho.getText().trim());
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(GUI_DatBan.this, "Số chỗ nhập không hợp lệ!");
				return;
			}
			Date ngayDat = dateChooser.getDate();
			Ban_DAO dao = new Ban_DAO();
			List<Ban> list = dao.getAllBanByDate(ngayDat);
			List<Ban> filtered = list.stream()
					.filter(b -> ("All".equalsIgnoreCase(khuVuc) || khuVuc.equalsIgnoreCase(b.getMaKhuVuc())))
					.filter(b -> b.getSoChoNgoi() >= soCho).collect(Collectors.toList());
			if (filtered.isEmpty()) {
				JOptionPane.showMessageDialog(GUI_DatBan.this, "Không còn bàn nào phù hợp.");
			}
			updateGridPanel(filtered);
		});

		btnDatBan.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (selectedMaBan == null) {
					JOptionPane.showMessageDialog(GUI_DatBan.this, "Bạn chưa chọn bàn nào!");
					return;
				}
				Date ngayDat = dateChooser.getDate();
				String startTime = (String) comboGioBatDau.getSelectedItem();
				String endTime = (String) comboGioKetThuc.getSelectedItem();

				Ban_DAO banDAO = new Ban_DAO();
				if (banDAO.isTableBooked(selectedMaBan, ngayDat, startTime, endTime)) {
					JOptionPane.showMessageDialog(GUI_DatBan.this, "Bàn đã được đặt trong khoảng thời gian này.");
					return;
				}
				// Xây dựng thông tin bàn để hiển thị trong dialog
				// Giả sử bạn có thông tin về khu vực và số chỗ từ đối tượng Ban, ví dụ:
				// "Bàn 5 – KV01 – 4 chỗ"
				// Ở đây, demo chỉ lấy "Bàn " + selectedMaBan
				String tableInfo = "Bàn " + selectedMaBan;

				DatBanDialog dialog = new DatBanDialog(SwingUtilities.getWindowAncestor(GUI_DatBan.this), tableInfo,
						ngayDat, startTime, endTime);
				dialog.setVisible(true);
				if (dialog.isConfirmed()) {
					String sdt = dialog.getSdt();
					boolean success = banDAO.datBan(selectedMaBan, ngayDat, startTime, endTime, sdt);
					if (success) {
						JOptionPane.showMessageDialog(GUI_DatBan.this, "Đặt bàn thành công!");
						Ban_DAO dao = new Ban_DAO();
						updateGridPanel(dao.getAllBanByDate(ngayDat));
						resetTableSelection();
					} else {
						JOptionPane.showMessageDialog(GUI_DatBan.this, "Có lỗi xảy ra, vui lòng thử lại.");
					}
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

	// Hàm reset lại trạng thái chọn của các bàn trong gridPanel
	private void resetTableSelection() {
		for (Component comp : gridPanel.getComponents()) {
			if (comp instanceof JPanel) {
				JPanel panel = (JPanel) comp;
				Object obj = panel.getClientProperty("defaultIcon");
				if (obj instanceof ImageIcon) {
					ImageIcon defaultIcon = (ImageIcon) obj;
					if (panel.getComponentCount() > 0 && panel.getComponent(0) instanceof JLabel) {
						((JLabel) panel.getComponent(0)).setIcon(defaultIcon);
					}
				}
			}
		}
		selectedMaBan = null;
	}

	// Hàm cập nhật gridPanel theo danh sách bàn được truyền vào.
	private void updateGridPanel(List<Ban> listBan) {
		gridPanel.removeAll();
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
			final ImageIcon selectedIcon = new ImageIcon(
					new ImageIcon(getClass().getResource("/view/icon/icon_BanDangChon.png")).getImage()
							.getScaledInstance(100, 100, Image.SCALE_SMOOTH));

			JPanel tablePanel = new JPanel();
			tablePanel.setLayout(new BoxLayout(tablePanel, BoxLayout.Y_AXIS));
			tablePanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1, true));
			tablePanel.setPreferredSize(new Dimension(200, 160));
			tablePanel.setBackground(Color.WHITE);

			JLabel labelIcon = new JLabel(defaultIcon);
			labelIcon.setAlignmentX(Component.CENTER_ALIGNMENT);
			// Lưu default icon cho panel để reset sau
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
					// Nếu đối tượng ban có thông tin thêm, ví dụ số chỗ và khu vực, bạn có thể tạo
					// chuỗi tableInfo:
					selectedTableInfo = "Bàn " + ban.getMaBan() + " – " + ban.getMaKhuVuc() + " – " + ban.getSoChoNgoi()
							+ " chỗ";
					// Nếu không, chỉ lưu mã bàn
					if (selectedTableInfo == null) {
						selectedTableInfo = "Bàn " + ban.getMaBan();
					}
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

	// Hàm cập nhật gridPanel theo ngày (nếu cần)
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
			GUI_DatBan guiDatBan = new GUI_DatBan();
			frame.getContentPane().add(guiDatBan);
			frame.setVisible(true);
		});
	}
}
