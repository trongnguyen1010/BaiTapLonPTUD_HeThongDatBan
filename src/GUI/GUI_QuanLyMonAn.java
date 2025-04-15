package GUI;

import javax.swing.*;

import DAO.MonAn_DAO;
import Entity.KhachHang;
import Entity.MonAn;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.stream.Collectors;

public class GUI_QuanLyMonAn extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel foodListPanel;
	private MonAn selectedMonAn = null;

	public GUI_QuanLyMonAn() {
		setLayout(new BorderLayout());

		// --- NORTH ---
		JPanel panelNorth = new JPanel();
		JLabel lblTieuDe = new JLabel("DANH SÁCH MÓN ĂN");
		lblTieuDe.setFont(new java.awt.Font("Segoe UI", 0, 24));
		lblTieuDe.setForeground(Color.BLACK); // hoặc chọn màu bạn thích
		panelNorth.add(lblTieuDe);

		panelNorth.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		panelNorth.setBackground(new java.awt.Color(0, 163, 239));
		panelNorth.setPreferredSize(new Dimension(1300, 60));
		add(panelNorth, BorderLayout.NORTH);

		// --- CENTER ---
		JPanel centerMain = new JPanel(new BorderLayout());
		add(centerMain, BorderLayout.CENTER);

		// CENTER - NORTH: Thanh công cụ
		JPanel centerNorth1 = new JPanel();
		centerNorth1.setBackground(new Color(161, 227, 249));
		centerNorth1.setPreferredSize(new Dimension(820, 130));
		centerNorth1.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20));
		centerMain.add(centerNorth1, BorderLayout.NORTH);

		// Nút THÊM
		JButton btnThem = new JButton("THÊM");
		btnThem.setIcon(new ImageIcon(GUI_QuanLyMonAn.class.getResource("/view/icon/icon_them.png")));
		btnThem.setBorderPainted(false);
		btnThem.setContentAreaFilled(false);
		btnThem.setFocusPainted(false);
		btnThem.setHorizontalTextPosition(SwingConstants.CENTER);
		btnThem.setVerticalTextPosition(SwingConstants.BOTTOM);
		centerNorth1.add(btnThem);

		// Nút CẬP NHẬT
		JButton btnCapNhat = new JButton("CẬP NHẬT");
		btnCapNhat.setIcon(new ImageIcon(GUI_QuanLyMonAn.class.getResource("/view/icon/icon_capnhat.png")));
		btnCapNhat.setBorderPainted(false);
		btnCapNhat.setContentAreaFilled(false);
		btnCapNhat.setFocusPainted(false);
		btnCapNhat.setHorizontalTextPosition(SwingConstants.CENTER);
		btnCapNhat.setVerticalTextPosition(SwingConstants.BOTTOM);
		centerNorth1.add(btnCapNhat);

		// Nút XÓA
		JButton btnXoa = new JButton("XÓA");
		btnXoa.setIcon(new ImageIcon(GUI_QuanLyMonAn.class.getResource("/view/icon/icon_xoa.png")));
		btnXoa.setBorderPainted(false);
		btnXoa.setContentAreaFilled(false);
		btnXoa.setFocusPainted(false);
		btnXoa.setHorizontalTextPosition(SwingConstants.CENTER);
		btnXoa.setVerticalTextPosition(SwingConstants.BOTTOM);
		centerNorth1.add(btnXoa);

		// Combobox loại món
		JComboBox<String> comboLoai = new JComboBox<>(new String[] { "Tất cả", "Món Mới", "Khai Vị", "Lẩu", "Heo-Gà-Bò",
				"Hải Sản", "Đồ uống", "Món Tráng Miệng" });
		comboLoai.setPreferredSize(new Dimension(184, 65));
		centerNorth1.add(comboLoai);

		// TextField tìm kiếm
		JTextField txtTimKiem = new JTextField("Tìm kiếm");
		txtTimKiem.setPreferredSize(new Dimension(362, 65));
		centerNorth1.add(txtTimKiem);

		// --- CENTER - CENTER: danh sách món ăn ---
		JPanel centerCenter = new JPanel(new BorderLayout());
		foodListPanel = createFoodList();
		JScrollPane scrollPane = new JScrollPane(foodListPanel);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		centerCenter.add(scrollPane, BorderLayout.CENTER);
		centerMain.add(centerCenter, BorderLayout.CENTER);

		// --- Load data từ DB ---
		loadData();

		// Sự kiện nút Thêm
		btnThem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CreateMonAnDialog dialog = new CreateMonAnDialog(
						(JFrame) SwingUtilities.getWindowAncestor(GUI_QuanLyMonAn.this), GUI_QuanLyMonAn.this);
				dialog.setVisible(true);
			}
		});
		// Trong btnCapNhat, bạn nên hiện thông báo:
		btnCapNhat.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (selectedMonAn != null) {
					UpdateMonAnDialog dialog = new UpdateMonAnDialog(
							(JFrame) SwingUtilities.getWindowAncestor(GUI_QuanLyMonAn.this), GUI_QuanLyMonAn.this,
							selectedMonAn);
					dialog.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(GUI_QuanLyMonAn.this, "Vui lòng chọn món ăn để cập nhật.");
				}
			}
		});
		btnXoa.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				xoaMonAn();

			}
		});
		//
		comboLoai.addActionListener(e -> {
			String selectedLoai = (String) comboLoai.getSelectedItem();
			int maLoai = mapLoai(selectedLoai);

			// System.out.println("Bạn đã chọn loại: " + selectedLoai + " - Mã loại: " +
			// maLoai);
//
			List<MonAn> danhSachLoc;

			try {
				danhSachLoc = new MonAn_DAO().getAllMonAn();

				for (MonAn m : danhSachLoc) {
					// System.out.println("Món: " + m.getTenMonAn() + " - Mã loại: " +
					// m.getMaloai());
				}

				if (maLoai != -1) {
					danhSachLoc = danhSachLoc.stream().filter(mon -> mon.getMaloai() == maLoai)
							.collect(Collectors.toList());
				}

				hienThiMonAn(danhSachLoc);
			} catch (Exception ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(null, "Lỗi khi tải dữ liệu món ăn");
			}
		});

	}

//   
	private int mapLoai(String tenLoai) {
		return switch (tenLoai) {
		case "Món Mới" -> 1;
		case "Khai Vị" -> 2;
		case "Lẩu" -> 3;
		case "Heo-Gà-Bò" -> 4;
		case "Hải Sản" -> 5;
		case "Đồ uống" -> 6;
		case "Món Tráng Miệng" -> 7;
		default -> -1; // Trường hợp không khớp
		};
	}

	private void hienThiMonAn(List<MonAn> danhSach) {
		foodListPanel.removeAll(); // Xóa món cũ

		for (MonAn ma : danhSach) {
			JPanel card = createFoodCard(ma); // tạo giao diện món ăn
			foodListPanel.add(card);
		}

		foodListPanel.revalidate();
		foodListPanel.repaint();
	}

	private void xoaMonAn() {
		if (selectedMonAn == null) {
			JOptionPane.showMessageDialog(this, "Vui lòng chọn món ăn cần xóa!");
			return;
		}

		int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa món ăn này?", "Xác nhận xóa",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

		if (confirm == JOptionPane.YES_OPTION) {
			String maMonAn = selectedMonAn.getMaMonAn();
			if (new MonAn_DAO().delete(maMonAn)) {
				JOptionPane.showMessageDialog(this, "Xóa món ăn thành công!");
				selectedMonAn = null; // reset
				loadData(); // reload danh sách
			} else {
				JOptionPane.showMessageDialog(this, "Xóa món ăn thất bại!");
			}
		}
	}

	// Tạo JLabel cho menu bên trái
//    private JPanel createMenuItem(String text) {
//        JPanel item = new JPanel(new FlowLayout(FlowLayout.LEFT));
//        item.setMaximumSize(new Dimension(180, 40));
//        item.setBackground(Color.CYAN);
//
//        JLabel label = new JLabel(text);
//        label.setFont(new Font("Arial", Font.BOLD, 20));
//        item.add(label);
//        return item;
//    }

	// Panel chứa danh sách món ăn
	private JPanel createFoodList() {
		JPanel foodList = new JPanel();
//		foodList.setLayout(new GridLayout(0, 4, 10, 10)); // tự động chia dòng
		foodList.setLayout(new WrapLayout(FlowLayout.LEFT, 10, 10));
		foodList.setBackground(Color.WHITE);
		return foodList;
	}

	private JPanel lastSelectedCard = null;

	private void highlightSelectedCard(JPanel selectedCard) {
		if (lastSelectedCard != null) {
			lastSelectedCard.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		}
		selectedCard.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
		lastSelectedCard = selectedCard;
	}

	private JPanel createFoodCard(MonAn monan) {
		JPanel card = new JPanel();
		card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
		card.setPreferredSize(new Dimension(270, 200));
//		card.setMaximumSize(new Dimension(400, 250));
		card.setBackground(Color.WHITE);
		card.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

		// Lưu MonAn vào client property
		card.putClientProperty("monAn", monan);

		// Bắt sự kiện click để chọn
		card.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				selectedMonAn = monan; // set biến toàn cục
				highlightSelectedCard(card);
			}
		});

		// Hình ảnh
		JLabel lblImage = new JLabel();
		lblImage.setAlignmentX(Component.CENTER_ALIGNMENT);
		byte[] imgBytes = monan.gethinhAnh();
		if (imgBytes != null) {
			ImageIcon icon = new ImageIcon(imgBytes);
			Image image = icon.getImage().getScaledInstance(270, 140, Image.SCALE_SMOOTH);
			lblImage.setIcon(new ImageIcon(image));
		} else {
			lblImage.setText("Không có ảnh");
		}
		card.add(lblImage);

		// Mã món
		JLabel lblMaMon = new JLabel("Mã: " + monan.getMaMonAn());
		lblMaMon.setFont(new Font("Arial", Font.PLAIN, 13));
		lblMaMon.setForeground(Color.DARK_GRAY);
		lblMaMon.setAlignmentX(Component.CENTER_ALIGNMENT);
		card.add(lblMaMon);

		// Tên món
		JLabel lblTenMon = new JLabel(monan.getTenMonAn());
		lblTenMon.setFont(new Font("Arial", Font.BOLD, 16));
		lblTenMon.setAlignmentX(Component.CENTER_ALIGNMENT);
		card.add(lblTenMon);

		// Giá
		JLabel lblGia = new JLabel(monan.getDonGia() + " VND");
		lblGia.setFont(new Font("Arial", Font.PLAIN, 14));
		lblGia.setForeground(Color.RED);
		lblGia.setAlignmentX(Component.CENTER_ALIGNMENT);
		card.add(lblGia);

		return card;
	}

	// Load data từ SQL Server
	public void loadData() {
		foodListPanel.removeAll();

		List<MonAn> list = null;
		try {
			list = new MonAn_DAO().getAllMonAn();
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}

		for (MonAn mon : list) {
			JPanel card = createFoodCard(mon); // Gọi đúng hàm với MonAn
			foodListPanel.add(card);
		}

		foodListPanel.revalidate();
		foodListPanel.repaint();
	}

	// Main
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			JFrame frame = new JFrame("Quản Lý Món Ăn");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setSize(1500, 900);
			frame.setLocationRelativeTo(null);

			GUI_QuanLyMonAn panel = new GUI_QuanLyMonAn();
			frame.setContentPane(panel);
			frame.setVisible(true);
		});
	}
}
