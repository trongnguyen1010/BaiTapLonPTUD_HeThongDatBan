package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.nio.file.Files;

import javax.swing.*;

import DAO.MonAn_DAO;
import Entity.MonAn;
import utils.RandomGenerator;
//

public class CreateMonAnDialog extends javax.swing.JDialog {

	private GUI_QuanLyMonAn MA_GUI;
	private MonAn_DAO MA_DAO = new MonAn_DAO();
	private byte[] imageBytes; // Lưu ảnh đã chọn

	public CreateMonAnDialog(java.awt.Frame parent, boolean modal) {
		super(parent, modal);
		initComponents();
	}

	public CreateMonAnDialog(java.awt.Frame parent, GUI_QuanLyMonAn MA_GUI) {
		super(parent, true);
		this.MA_GUI = MA_GUI;
		initComponents();
	}

	private MonAn getInputFields() {
		String id = txtmaMonAn.getText().trim();
		String tenMonAn = txtTenMonAn.getText().trim();

		if (tenMonAn.isEmpty() || txtDonGia.getText().trim().isEmpty() || txtSoLuong.getText().trim().isEmpty()
				|| imageBytes == null) {
			JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin và chọn ảnh.");
			return null;
		}

		try {
			double donGia = Double.parseDouble(txtDonGia.getText().trim());
			int soLuong = Integer.parseInt(txtSoLuong.getText().trim());

			return new MonAn(id, tenMonAn, donGia, soLuong, imageBytes);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "Đơn giá và số lượng phải là số.");
			return null;
		}
	}

	private void initComponents() {
		jPanelTitle = new JPanel();
		lblDialog = new JLabel();
		jPanelContent = new JPanel();
		jPanelTen = new JPanel();
		txtmaMonAn = new JTextField();
		lblTenMonAn = new JLabel();
		txtTenMonAn = new JTextField();
		jPanelDonGia = new JPanel();
		lblDonGia = new JLabel();
		txtDonGia = new JTextField();
		jPanelSoLuong = new JPanel();
		lblSoLuong = new JLabel();
		txtSoLuong = new JTextField();
		jPanelduongDanAnh = new JPanel();
		lblduongDanAnh = new JLabel();
		btnChonAnh = new JButton();
		lblAnhPreview = new JLabel();
		jPanelButtons = new JPanel();
		btnHuy = new JButton();
		btnAdd = new JButton();

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setPreferredSize(new java.awt.Dimension(500, 450));

		// Title
		jPanelTitle.setBackground(new Color(161, 227, 249));
		jPanelTitle.setPreferredSize(new java.awt.Dimension(500, 50));
		jPanelTitle.setLayout(new BorderLayout());
		lblDialog.setFont(new Font("Roboto Medium", 0, 18));
		lblDialog.setHorizontalAlignment(SwingConstants.CENTER);
		lblDialog.setText("THÊM MÓN ĂN");
		lblDialog.setForeground(Color.WHITE);
		jPanelTitle.add(lblDialog, BorderLayout.CENTER);
		getContentPane().add(jPanelTitle, BorderLayout.NORTH);

		// Content
		jPanelContent.setBackground(Color.WHITE);
		jPanelContent.setLayout(new FlowLayout(FlowLayout.CENTER, 6, 16));

		jPanelTen.setPreferredSize(new Dimension(450, 40));
		jPanelTen.setLayout(new FlowLayout(FlowLayout.LEFT, 6, 0));
		lblTenMonAn.setText("Tên món ăn:");
		lblTenMonAn.setPreferredSize(new Dimension(150, 40));
		txtTenMonAn.setPreferredSize(new Dimension(280, 40));
		jPanelTen.add(lblTenMonAn);
		jPanelTen.add(txtTenMonAn);
		jPanelContent.add(jPanelTen);

		jPanelDonGia.setPreferredSize(new Dimension(450, 40));
		jPanelDonGia.setLayout(new FlowLayout(FlowLayout.LEFT, 6, 0));
		lblDonGia.setText("Đơn giá:");
		lblDonGia.setPreferredSize(new Dimension(150, 40));
		txtDonGia.setPreferredSize(new Dimension(280, 40));
		jPanelDonGia.add(lblDonGia);
		jPanelDonGia.add(txtDonGia);
		jPanelContent.add(jPanelDonGia);

		jPanelSoLuong.setPreferredSize(new Dimension(450, 40));
		jPanelSoLuong.setLayout(new FlowLayout(FlowLayout.LEFT, 6, 0));
		lblSoLuong.setText("Số lượng:");
		lblSoLuong.setPreferredSize(new Dimension(150, 40));
		txtSoLuong.setPreferredSize(new Dimension(280, 40));
		jPanelSoLuong.add(lblSoLuong);
		jPanelSoLuong.add(txtSoLuong);
		jPanelContent.add(jPanelSoLuong);

		// Panel chọn ảnh
		jPanelduongDanAnh.setPreferredSize(new Dimension(450, 100));
		jPanelduongDanAnh.setLayout(new FlowLayout(FlowLayout.LEFT, 6, 0));

		lblduongDanAnh.setText("Chọn ảnh:");
		lblduongDanAnh.setPreferredSize(new Dimension(150, 40));

		btnChonAnh.setText("Chọn ảnh");
		btnChonAnh.setPreferredSize(new Dimension(120, 40));
		btnChonAnh.addActionListener(e -> {
			JFileChooser fileChooser = new JFileChooser();
			int result = fileChooser.showOpenDialog(CreateMonAnDialog.this);
			if (result == JFileChooser.APPROVE_OPTION) {
				try {
					java.io.File file = fileChooser.getSelectedFile();
					imageBytes = Files.readAllBytes(file.toPath());

					// Hiển thị ảnh preview
					ImageIcon icon = new ImageIcon(imageBytes);
					Image img = icon.getImage().getScaledInstance(100, 60, Image.SCALE_SMOOTH);
					lblAnhPreview.setIcon(new ImageIcon(img));
					lblAnhPreview.setText("");
				} catch (IOException ex) {
					JOptionPane.showMessageDialog(CreateMonAnDialog.this, "Lỗi khi đọc ảnh.");
				}
			}
		});

		lblAnhPreview.setText("Chưa chọn ảnh");
		lblAnhPreview.setPreferredSize(new Dimension(100, 60));

		jPanelduongDanAnh.add(lblduongDanAnh);
		jPanelduongDanAnh.add(btnChonAnh);
		jPanelduongDanAnh.add(lblAnhPreview);
		jPanelContent.add(jPanelduongDanAnh);

		getContentPane().add(jPanelContent, BorderLayout.CENTER);

		// Buttons
		jPanelButtons.setLayout(new FlowLayout(FlowLayout.CENTER, 8, 10));
		btnHuy.setText("HỦY BỎ");
		btnHuy.setPreferredSize(new Dimension(200, 40));
		btnHuy.addActionListener(evt -> dispose());
		jPanelButtons.add(btnHuy);

		btnAdd.setText("THÊM");
		btnAdd.setPreferredSize(new Dimension(200, 40));
		btnAdd.setBackground(Color.WHITE);
		btnAdd.setForeground(Color.BLACK);
		btnAdd.addActionListener((ActionEvent evt) -> {
			MonAn ma = getInputFields();
			if (ma != null) {
				MA_DAO.insert(ma);
				if (MA_GUI != null)
					MA_GUI.loadData();
				dispose();
			}
		});

		jPanelButtons.add(btnAdd);

		getContentPane().add(jPanelButtons, BorderLayout.SOUTH);
		pack();
		setLocationRelativeTo(null);

		// Tự tạo mã món ăn ngẫu nhiên
		String randomId = RandomGenerator.getRandomId();
		txtmaMonAn.setText(randomId);
	}

	// Biến giao diện
	private JPanel jPanelTitle, jPanelContent, jPanelTen, jPanelDonGia, jPanelSoLuong, jPanelButtons, jPanelduongDanAnh;
	private JLabel lblDialog, lblTenMonAn, lblDonGia, lblSoLuong, lblduongDanAnh, lblAnhPreview;
	private JTextField txtTenMonAn, txtDonGia, txtSoLuong, txtmaMonAn;
	private JButton btnAdd, btnHuy, btnChonAnh;
}
