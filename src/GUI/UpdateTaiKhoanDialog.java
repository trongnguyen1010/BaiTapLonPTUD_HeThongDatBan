package GUI;

import javax.swing.*;
import DAO.TaiKhoanDAO;
import DAO.NhanVienDAO;
import Entity.TaiKhoan;
import Entity.NhanVien;
import utils.RandomGenerator;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;

public class UpdateTaiKhoanDialog extends javax.swing.JDialog {
	private GUI_QuanLyTaiKhoan TK_GUI;
	private TaiKhoan tk;
	private TaiKhoanDAO TK_DAO = new TaiKhoanDAO();
	private NhanVienDAO NV_DAO = new NhanVienDAO();

	public UpdateTaiKhoanDialog(java.awt.Frame parent, boolean modal) {
		super(parent, modal);
		initComponents();
	}

	public UpdateTaiKhoanDialog(java.awt.Frame parent, boolean modal, GUI_QuanLyTaiKhoan TK_GUI, TaiKhoan tk) {
		super(parent, modal);
		initComponents();
		this.TK_GUI = TK_GUI;
		this.tk = tk;
		loadNhanVienList();
		fillInput();
	}

	private void loadNhanVienList() {
		List<NhanVien> listNhanVien = NV_DAO.getAllNhanVien();
		for (NhanVien nv : listNhanVien) {
			cboxNhanVien.addItem(nv.getTen() + " - " + nv.getMaNhanVien());
		}
	}

	private void fillInput() {
		txtTenDangNhap.setText(tk.getTenDangNhap());
		txtMatKhau.setText(tk.getMatKhau());
		// Set selected employee in combobox
		String nhanVienText = tk.getNhanVien().getTen() + " - " + tk.getNhanVien().getMaNhanVien();
		cboxNhanVien.setSelectedItem(nhanVienText);
	}

	private TaiKhoan getInputFields() {
		String id = tk.getMaTaiKhoan();
		String tenDangNhap = txtTenDangNhap.getText().trim();
		String matKhau = txtMatKhau.getText().trim();

		// Lấy mã nhân viên từ combobox
		String selectedNhanVien = cboxNhanVien.getSelectedItem().toString();
		String maNhanVien = selectedNhanVien.split(" - ")[1];
		NhanVien nv = NV_DAO.getNhanVienById(maNhanVien);

		return new TaiKhoan(id, nv, tenDangNhap, matKhau);
	}

	@SuppressWarnings("unchecked")
	private void initComponents() {
		jPanel15 = new javax.swing.JPanel();
		lblDialog = new javax.swing.JLabel();
		jPanel1 = new javax.swing.JPanel();
		jPanel18 = new javax.swing.JPanel();
		lblTenDangNhap = new javax.swing.JLabel();
		txtTenDangNhap = new javax.swing.JTextField();
		jPanel19 = new javax.swing.JPanel();
		jLabel12 = new javax.swing.JLabel();
		txtMatKhau = new javax.swing.JTextField();
		jPanel20 = new javax.swing.JPanel();
		jLabel13 = new javax.swing.JLabel();
		cboxNhanVien = new javax.swing.JComboBox<>();
		jPanel8 = new javax.swing.JPanel();
		btnHuy = new javax.swing.JButton();
		btnUpdate = new javax.swing.JButton();

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setPreferredSize(new java.awt.Dimension(600, 650));

		jPanel15.setBackground(new java.awt.Color(161, 227, 249));
		jPanel15.setPreferredSize(new java.awt.Dimension(500, 50));
		jPanel15.setLayout(new java.awt.BorderLayout());

		lblDialog.setFont(new java.awt.Font("Roboto Medium", 0, 18));
		lblDialog.setForeground(new java.awt.Color(255, 255, 255));
		lblDialog.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		lblDialog.setText("CẬP NHẬT TÀI KHOẢN");
		jPanel15.add(lblDialog, java.awt.BorderLayout.CENTER);
		getContentPane().add(jPanel15, java.awt.BorderLayout.NORTH);

		jPanel1.setBackground(new java.awt.Color(255, 255, 255));
		jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 16));

		jPanel18.setPreferredSize(new java.awt.Dimension(500, 50));
		lblTenDangNhap.setText("Tên đăng nhập");
		lblTenDangNhap.setPreferredSize(new java.awt.Dimension(150, 40));
		txtTenDangNhap.setPreferredSize(new java.awt.Dimension(330, 40));
		jPanel18.add(lblTenDangNhap);
		jPanel18.add(txtTenDangNhap);
		jPanel1.add(jPanel18);

		jPanel19.setPreferredSize(new java.awt.Dimension(500, 50));
		jLabel12.setText("Mật khẩu");
		jLabel12.setPreferredSize(new java.awt.Dimension(150, 40));
		txtMatKhau = new javax.swing.JTextField();
		txtMatKhau.setPreferredSize(new java.awt.Dimension(330, 40));
		jPanel19.add(jLabel12);
		jPanel19.add(txtMatKhau);
		jPanel1.add(jPanel19);

		jPanel20.setPreferredSize(new java.awt.Dimension(500, 50));
		jLabel13.setText("Nhân viên");
		jLabel13.setPreferredSize(new java.awt.Dimension(150, 40));
		cboxNhanVien.setPreferredSize(new java.awt.Dimension(330, 40));
		jPanel20.add(jLabel13);
		jPanel20.add(cboxNhanVien);
		jPanel1.add(jPanel20);

		getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

		jPanel8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 8, 5));
		btnHuy.setText("HỦY BỎ");
		btnHuy.setPreferredSize(new java.awt.Dimension(200, 40));
		btnHuy.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnHuyActionPerformed(evt);
			}
		});
		jPanel8.add(btnHuy);

		btnUpdate.setText("CẬP NHẬT");
		btnUpdate.setPreferredSize(new java.awt.Dimension(200, 40));
		btnUpdate.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnUpdateActionPerformed(evt);
			}
		});
		jPanel8.add(btnUpdate);

		getContentPane().add(jPanel8, java.awt.BorderLayout.PAGE_END);

		pack();
		setLocationRelativeTo(null);
	}

	private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {
		this.dispose();
	}

	private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {
		TaiKhoan tk = getInputFields();
		if (validateInput(tk)) {
			TK_DAO.update(tk);
			if (TK_GUI != null) {
				TK_GUI.loadData();
			}
			this.dispose();
		}
	}

	private boolean validateInput(TaiKhoan tk) {
		if (tk.getTenDangNhap().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Vui lòng nhập tên đăng nhập!", "Lỗi", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if (tk.getMatKhau().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Vui lòng nhập mật khẩu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if (tk.getNhanVien() == null) {
			JOptionPane.showMessageDialog(this, "Vui lòng chọn nhân viên!", "Lỗi", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}

	// Variables declaration - do not modify
	private javax.swing.JButton btnHuy;
	private javax.swing.JButton btnUpdate;
	private javax.swing.JComboBox<String> cboxNhanVien;
	private javax.swing.JLabel jLabel12;
	private javax.swing.JLabel jLabel13;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel15;
	private javax.swing.JPanel jPanel18;
	private javax.swing.JPanel jPanel19;
	private javax.swing.JPanel jPanel20;
	private javax.swing.JPanel jPanel8;
	private javax.swing.JLabel lblDialog;
	private javax.swing.JLabel lblTenDangNhap;
	private javax.swing.JTextField txtMatKhau;
	private javax.swing.JTextField txtTenDangNhap;
	// End of variables declaration
}