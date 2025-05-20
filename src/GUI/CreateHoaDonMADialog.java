package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import DAO.MonAn_DAO;
import Entity.MonAn;

import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class CreateHoaDonMADialog extends JDialog {
	private JPanel contentPanel;
	private List<MonAn> danhSachMonAn;
	private DefaultTableModel model;
	private JTable table;
	private GUI_HoaDon_new guiHoaDon; // Đối tượng GUI truyền vào

	private List<MonAn> dsMonAnTrongHoaDon; // danh sách dùng chung bên ngoài

	public CreateHoaDonMADialog(GUI_HoaDon_new gui_HoaDon_new, List<MonAn> dsMonAnTrongHoaDon) {
		super((Frame) SwingUtilities.getWindowAncestor(gui_HoaDon_new), "Chọn món ăn", true);
		this.dsMonAnTrongHoaDon = dsMonAnTrongHoaDon;
		this.guiHoaDon = gui_HoaDon_new;

		setSize(800, 500);
		setLocationRelativeTo(gui_HoaDon_new);
		setLayout(new BorderLayout());

		contentPanel = new JPanel();
		contentPanel.setLayout(new GridLayout(0, 3, 10, 10));
		JScrollPane scrollPane = new JScrollPane(contentPanel);
		scrollPane.getVerticalScrollBar().setUnitIncrement(16);
		add(scrollPane, BorderLayout.CENTER);

		// Load dữ liệu từ DB
		loadMonAnTuDB();
	}

	private void loadMonAnTuDB() {
		danhSachMonAn = MonAn_DAO.getAllMonAn(); // gọi hàm bạn đã viết

		for (MonAn mon : danhSachMonAn) {
			JPanel panel = new JPanel();
			panel.setLayout(new BorderLayout());
			panel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
			panel.setPreferredSize(new Dimension(200, 200));

			// Ảnh món ăn
			JLabel lblImage = new JLabel();
			lblImage.setHorizontalAlignment(SwingConstants.CENTER);
			if (mon.gethinhAnh() != null) {
				ImageIcon icon = new ImageIcon(mon.gethinhAnh());
				Image img = icon.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
				lblImage.setIcon(new ImageIcon(img));
			}
			panel.add(lblImage, BorderLayout.CENTER);

			// Tên + giá
			JLabel lblInfo = new JLabel(
					"<html><center>" + mon.getTenMonAn() + "<br>Giá: " + mon.getDonGia() + "</center></html>",
					SwingConstants.CENTER);
			panel.add(lblInfo, BorderLayout.NORTH);

			// Nút chọn
			JButton btnChon = new JButton("Chọn");
			btnChon.addActionListener(e -> {
				// Yêu cầu người dùng nhập số lượng
				String quantityStr = JOptionPane.showInputDialog(null, "Nhập số lượng món ăn:", "Số lượng",
						JOptionPane.PLAIN_MESSAGE);

				// Kiểm tra và chuyển đổi số lượng nhập vào
				try {
					int quantity = Integer.parseInt(quantityStr);
					if (quantity > 0) {
						// Gọi hàm để thêm món ăn vào hóa đơn với số lượng đã nhập
						themMonAnVaoHoaDon(mon, quantity);
					} else {
						JOptionPane.showMessageDialog(null, "Số lượng phải lớn hơn 0.");
					}
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Vui lòng nhập một số hợp lệ.");
				}
			});
			panel.add(btnChon, BorderLayout.SOUTH);

			contentPanel.add(panel);
		}

		contentPanel.revalidate();
		contentPanel.repaint();
	}

//	private void themMonAnVaoHoaDon(MonAn mon, int quantity) {
//		// Tạo một đối tượng MonAn mới với số lượng đã nhập
//		MonAn monWithQuantity = new MonAn(mon.getMaMonAn(), mon.getTenMonAn(), mon.getDonGia(), quantity,mon.getMaloai(),mon.gethinhAnh());
//
//		// Thêm món ăn vào danh sách trong hóa đơn
//		dsMonAnTrongHoaDon.add(monWithQuantity);
//
//		// Cập nhật bảng hiển thị món ăn
//		// Cập nhật bảng hiển thị món ăn
//		if (guiHoaDon != null) {
//			guiHoaDon.capNhatBangMonAnHoaDon(); // Gọi đúng từ biến thành viên đã gán
//		}
//
//	}
	private void themMonAnVaoHoaDon(MonAn mon, int quantity) {
	    // Kiểm tra xem món đã có trong danh sách hóa đơn chưa
	    boolean found = false;

	    for (MonAn item : dsMonAnTrongHoaDon) {
	        if (item.getMaMonAn().equals(mon.getMaMonAn())) {
	            // Nếu đã có, hỏi người dùng có muốn cập nhật số lượng không
	            int option = JOptionPane.showConfirmDialog(null,
	                "Món ăn đã có trong hóa đơn.\nBạn có muốn cập nhật số lượng không?",
	                "Cập nhật số lượng",
	                JOptionPane.YES_NO_OPTION);
	            if (option == JOptionPane.YES_OPTION) {
	                item.setSoLuong(quantity); // Cập nhật lại số lượng
	            }
	            found = true;
	            break;
	        }
	    }

	    if (!found) {
	        // Nếu chưa có, thêm mới
	        MonAn monWithQuantity = new MonAn(
	            mon.getMaMonAn(),
	            mon.getTenMonAn(),
	            mon.getDonGia(),
	            quantity,
	            mon.getMaloai(),
	            mon.gethinhAnh()
	        );
	        dsMonAnTrongHoaDon.add(monWithQuantity);
	    }

	    // Cập nhật giao diện
	    if (guiHoaDon != null) {
	        guiHoaDon.capNhatBangMonAnHoaDon();
	    }
	}

}
