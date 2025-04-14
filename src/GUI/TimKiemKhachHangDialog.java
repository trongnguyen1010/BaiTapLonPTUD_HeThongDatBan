package GUI;

import GUI.GUI_TimKiemKhachHang;

import Entity.KhachHang;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import DAO.KhachHangDAO;
import javax.swing.JCheckBox;
import java.util.List;

public class TimKiemKhachHangDialog extends javax.swing.JDialog {
	/**
	 * 
	 */
	private GUI_TimKiemKhachHang TKKH_GUI;
	private KhachHangDAO KH_DAO = new KhachHangDAO();
	private JCheckBox chkHoTen;
	private JCheckBox chkSdt;
	private JCheckBox chkEmail;
	private JCheckBox chkGioiTinh;

	/**
	 * @wbp.parser.constructor
	 */
	public TimKiemKhachHangDialog(java.awt.Frame parent, boolean modal) {
		super(parent, modal);
		initComponents();
	}

	public TimKiemKhachHangDialog(java.awt.Frame parent, boolean modal, GUI_TimKiemKhachHang TKKH_GUI) {
		super(parent, modal);
		initComponents();
		this.TKKH_GUI = TKKH_GUI;
	}

	@SuppressWarnings("unchecked")
	private void initComponents() {
		jPanel15 = new javax.swing.JPanel();
		lblDialog = new javax.swing.JLabel();
		lblDialog.setText("TÌM KIẾM KHÁCH HÀNG");
		jPanel1 = new javax.swing.JPanel();
		jPanel18 = new javax.swing.JPanel();
		jPanel18.setBounds(43, 16, 500, 40);
		lblHoTen = new javax.swing.JLabel();
		txtHoTen = new javax.swing.JTextField();
		jPanel19 = new javax.swing.JPanel();
		jPanel19.setBounds(43, 66, 500, 40);
		jLabel12 = new javax.swing.JLabel();
		txtSdt = new javax.swing.JTextField();
		jPanel20 = new javax.swing.JPanel(); // Thêm panel mới cho Email
		jPanel20.setBounds(43, 116, 500, 40);
		jLabel13 = new javax.swing.JLabel();
		txtEmail = new javax.swing.JTextField();
		jPanel21 = new javax.swing.JPanel();
		jPanel21.setBounds(43, 166, 500, 40);
		jLabel14 = new javax.swing.JLabel();
		cboxGioiTinh = new javax.swing.JComboBox<>();
		jPanel8 = new javax.swing.JPanel();
		btnHuy = new javax.swing.JButton();
		btnHuy.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnHuyActionPerformed(evt);
			}
		});
		btnAdd = new javax.swing.JButton();
		btnAdd.setForeground(new Color(0, 0, 0));
		btnAdd.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnAddActionPerformed(evt);
			}
		});
		btnAdd.setBackground(new Color(255, 255, 255));

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setPreferredSize(new java.awt.Dimension(600, 600));

		jPanel15.setBackground(new java.awt.Color(161, 227, 249));
		jPanel15.setPreferredSize(new java.awt.Dimension(500, 50));
		jPanel15.setLayout(new java.awt.BorderLayout());

		lblDialog.setFont(new java.awt.Font("Roboto Medium", 0, 18));
		lblDialog.setForeground(new java.awt.Color(255, 255, 255));
		lblDialog.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jPanel15.add(lblDialog, java.awt.BorderLayout.CENTER);
		getContentPane().add(jPanel15, java.awt.BorderLayout.NORTH);

		jPanel1.setBackground(new java.awt.Color(255, 255, 255));
		jPanel1.setLayout(null);

		jPanel18.setPreferredSize(new java.awt.Dimension(500, 40));
		jPanel18.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 6, 0));
		lblHoTen.setText("Họ tên");
		lblHoTen.setPreferredSize(new java.awt.Dimension(150, 40));
		txtHoTen.setPreferredSize(new java.awt.Dimension(330, 40));
		jPanel18.add(lblHoTen);
		jPanel18.add(txtHoTen);
		jPanel1.add(jPanel18);

		jPanel19.setPreferredSize(new java.awt.Dimension(500, 40));
		jPanel19.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 6, 0));
		jLabel12.setText("Số điện thoại");
		jLabel12.setPreferredSize(new java.awt.Dimension(150, 40));
		txtSdt.setPreferredSize(new java.awt.Dimension(330, 40));
		jPanel19.add(jLabel12);
		jPanel19.add(txtSdt);
		jPanel1.add(jPanel19);

		jPanel20.setPreferredSize(new java.awt.Dimension(500, 40));
		jPanel20.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 6, 0));
		jLabel13.setText("Email");
		jLabel13.setPreferredSize(new java.awt.Dimension(150, 40));
		txtEmail.setPreferredSize(new java.awt.Dimension(330, 40));
		jPanel20.add(jLabel13);
		jPanel20.add(txtEmail);
		jPanel1.add(jPanel20);

		jPanel21.setPreferredSize(new java.awt.Dimension(500, 40));
		jPanel21.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 6, 0));
		jLabel14.setText("Giới tính");
		jLabel14.setPreferredSize(new java.awt.Dimension(150, 40));
		cboxGioiTinh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nam", "Nữ" }));
		cboxGioiTinh.setPreferredSize(new java.awt.Dimension(200, 40));
		jPanel21.add(jLabel14);
		jPanel21.add(cboxGioiTinh);
		jPanel1.add(jPanel21);

		getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

		// Tạo các checkbox
		chkHoTen = new JCheckBox();
		chkHoTen.setBounds(549, 27, 21, 21);
		jPanel1.add(chkHoTen);

		// Đặt màu nền cho checkbox
		chkHoTen.setBackground(new java.awt.Color(255, 255, 255));
		chkSdt = new JCheckBox();
		chkSdt.setBounds(549, 76, 21, 21);
		jPanel1.add(chkSdt);
		chkSdt.setBackground(new java.awt.Color(255, 255, 255));
		chkGioiTinh = new JCheckBox();
		chkGioiTinh.setBounds(549, 177, 21, 21);
		jPanel1.add(chkGioiTinh);
		chkGioiTinh.setBackground(new java.awt.Color(255, 255, 255));
		chkEmail = new JCheckBox();
		chkEmail.setBounds(549, 125, 21, 21);
		jPanel1.add(chkEmail);
		chkEmail.setBackground(new java.awt.Color(255, 255, 255));

		jPanel8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 8, 5));
		btnHuy.setText("HỦY BỎ");
		btnHuy.setPreferredSize(new java.awt.Dimension(200, 40));
		jPanel8.add(btnHuy);
		btnAdd.setText("TÌM KIẾM");
		btnAdd.setPreferredSize(new java.awt.Dimension(200, 40));
		jPanel8.add(btnAdd);
		getContentPane().add(jPanel8, java.awt.BorderLayout.PAGE_END);

		pack();
		setLocationRelativeTo(null);
	}

	private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnHuyActionPerformed
		this.dispose();
	}

	private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnAddActionPerformed
		// Lấy giá trị từ các text field nếu checkbox tương ứng được chọn
		String hoTen = chkHoTen.isSelected() ? txtHoTen.getText().trim() : null;
		String sdt = chkSdt.isSelected() ? txtSdt.getText().trim() : null;
		String email = chkEmail.isSelected() ? txtEmail.getText().trim() : null;
		String gioiTinh = chkGioiTinh.isSelected() ? cboxGioiTinh.getSelectedItem().toString() : null;

		// Gọi phương thức tìm kiếm từ DAO
		List<KhachHang> ketQua = KH_DAO.timKiemKhachHang(hoTen, sdt, email, gioiTinh);

		// Hiển thị kết quả tìm kiếm (có thể cập nhật bảng hoặc giao diện khác)
		if (TKKH_GUI != null) {
			TKKH_GUI.hienThiKetQuaTimKiem(ketQua);
		}

		// Đóng dialog
		this.dispose();
	}

	private javax.swing.JButton btnAdd;
	private javax.swing.JButton btnHuy;
	private javax.swing.JComboBox<String> cboxGioiTinh;
	private javax.swing.JLabel jLabel12;
	private javax.swing.JLabel jLabel13; // Thêm nhãn cho Email
	private javax.swing.JLabel jLabel14;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel15;
	private javax.swing.JPanel jPanel18;
	private javax.swing.JPanel jPanel19;
	private javax.swing.JPanel jPanel20; // Thêm panel cho Email
	private javax.swing.JPanel jPanel21;
	private javax.swing.JPanel jPanel8;
	private javax.swing.JLabel lblDialog;
	private javax.swing.JLabel lblHoTen;
	private javax.swing.JTextField txtHoTen;
	private javax.swing.JTextField txtSdt;
	private javax.swing.JTextField txtEmail; // Thêm trường Email
}