package GUI;

import DAO.MonAn_DAO;
import Entity.MonAn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateMonAnDialog extends JDialog {

	private JTextField txtTenMon;
	private JTextField txtDonGia;
	private JLabel lblAnh;
	private JButton btnChonAnh;
	private JButton btnLuu;

	private byte[] imageBytes = null;
	private MonAn monAn;
	private GUI_QuanLyMonAn parent;
	//

	public UpdateMonAnDialog(JFrame parentFrame, GUI_QuanLyMonAn parent, MonAn monAn) {
		super(parentFrame, "Cập nhật món ăn", true);
		this.monAn = monAn;
		this.parent = parent;

		setSize(400, 500);
		setLocationRelativeTo(parentFrame);
		setLayout(new BorderLayout());

		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		add(panel, BorderLayout.CENTER);

		// Tên món
		panel.add(new JLabel("Tên món:"));
		txtTenMon = new JTextField(monAn.getTenMonAn());
		txtTenMon.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
		panel.add(txtTenMon);

		// Đơn giá
		panel.add(new JLabel("Đơn giá:"));
		txtDonGia = new JTextField(String.valueOf(monAn.getDonGia()));
		txtDonGia.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
		panel.add(txtDonGia);

		// Ảnh
		panel.add(new JLabel("Hình ảnh:"));
		lblAnh = new JLabel();
		if (monAn.gethinhAnh() != null) {
			ImageIcon icon = new ImageIcon(monAn.gethinhAnh());
			Image img = icon.getImage().getScaledInstance(150, 100, Image.SCALE_SMOOTH);
			lblAnh.setIcon(new ImageIcon(img));
			imageBytes = monAn.gethinhAnh(); // giữ lại ảnh cũ nếu không đổi
		} else {
			lblAnh.setText("Chưa có ảnh");
		}
		panel.add(lblAnh);

		// Nút chọn ảnh
		btnChonAnh = new JButton("Chọn ảnh mới");
		btnChonAnh.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				int result = fileChooser.showOpenDialog(UpdateMonAnDialog.this);
				if (result == JFileChooser.APPROVE_OPTION) {
					try {
						java.io.File file = fileChooser.getSelectedFile();
						imageBytes = java.nio.file.Files.readAllBytes(file.toPath());

						ImageIcon icon = new ImageIcon(imageBytes);
						Image img = icon.getImage().getScaledInstance(150, 100, Image.SCALE_SMOOTH);
						lblAnh.setIcon(new ImageIcon(img));
						lblAnh.setText("");
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(UpdateMonAnDialog.this, "Lỗi khi chọn ảnh");
					}
				}
			}
		});//
		panel.add(btnChonAnh);

		// Nút lưu
		btnLuu = new JButton("Lưu thay đổi");
		btnLuu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String ten = txtTenMon.getText().trim();
				String giaStr = txtDonGia.getText().trim();

				if (ten.isEmpty() || giaStr.isEmpty()) {
					JOptionPane.showMessageDialog(UpdateMonAnDialog.this, "Vui lòng nhập đầy đủ thông tin.");
					return;
				}

				try {
					double donGia = Double.parseDouble(giaStr);
					monAn.setTenMonAn(ten);
					monAn.setDonGia(donGia);
					monAn.sethinhAnh(imageBytes);

					boolean ok = new MonAn_DAO().update(monAn);
					if (ok) {
						JOptionPane.showMessageDialog(UpdateMonAnDialog.this, "Cập nhật thành công!");
						parent.loadData();
						dispose();
					} else {
						JOptionPane.showMessageDialog(UpdateMonAnDialog.this, "Cập nhật thất bại!");
					}
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(UpdateMonAnDialog.this, "Đơn giá không hợp lệ!");
				}
			}
		});
		panel.add(Box.createVerticalStrut(20));
		panel.add(btnLuu);
	}
}
