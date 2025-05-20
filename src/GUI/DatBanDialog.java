package GUI;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import DAO.KhachHangDAO;
import Entity.KhachHang;

public class DatBanDialog extends JDialog {
	private JTextField txtSdt;
	private JTextField txtTen;
	private JComboBox<String> comboGioiTinh;
	private JTextField txtEmail;
	private JButton btnFind;
	private boolean confirmed = false;

	// Các thông tin đặt bàn được truyền vào dialog để hiển thị (read-only)
	private String tableInfo; // Ví dụ: "Bàn 5 – KV01 – 4 chỗ"
	private Date bookingDate;
	private String startTime;
	private String endTime;

	public DatBanDialog(Window owner, String tableInfo, Date bookingDate, String startTime, String endTime) {
		super(owner, "Thông tin đặt bàn", ModalityType.APPLICATION_MODAL);
		this.tableInfo = tableInfo;
		this.bookingDate = bookingDate;
		this.startTime = startTime;
		this.endTime = endTime;
		initComponents();
	}

	private void initComponents() {
		// Panel chính của dialog sử dụng BorderLayout
		JPanel mainPanel = new JPanel(new BorderLayout());
		mainPanel.setBackground(Color.WHITE);

		// --- Header Panel: Hiển thị thông tin bàn và thời gian đặt ---
		JPanel headerPanel = new JPanel(new GridLayout(2, 1));
		headerPanel.setBackground(Color.WHITE);
		headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		JLabel lblTableInfo = new JLabel("Thông tin bàn: " + tableInfo);
		lblTableInfo.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblTableInfo.setForeground(Color.BLUE);
		headerPanel.add(lblTableInfo);

		// Định dạng ngày đặt
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String bookingStr = String.format("Ngày đặt: %s   Giờ: %s - %s", sdf.format(bookingDate), startTime, endTime);
		JLabel lblBookingInfo = new JLabel(bookingStr);
		lblBookingInfo.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblBookingInfo.setForeground(Color.BLUE);
		headerPanel.add(lblBookingInfo);
		mainPanel.add(headerPanel, BorderLayout.NORTH);

		// --- Content Panel: Nhập thông tin khách hàng ---
		JPanel contentPanel = new JPanel();
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new TitledBorder(new LineBorder(Color.GRAY, 1), "Nhập thông tin khách hàng",
				TitledBorder.LEADING, TitledBorder.TOP, new Font("Segoe UI", Font.BOLD, 14), Color.DARK_GRAY));

		GroupLayout layout = new GroupLayout(contentPanel);
		contentPanel.setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);

		JLabel lblSdt = new JLabel("SĐT:");
		lblSdt.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtSdt = new JTextField(15);
		txtSdt.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtSdt.setToolTipText("Nhập số điện thoại khách hàng");

		btnFind = new JButton("Tìm");
		btnFind.setFont(new Font("Segoe UI", Font.PLAIN, 14));

		JLabel lblTen = new JLabel("Tên khách:");
		lblTen.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtTen = new JTextField(15);
		txtTen.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtTen.setToolTipText("Nhập tên khách hàng");

		JLabel lblGioiTinh = new JLabel("Giới tính:");
		lblGioiTinh.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		comboGioiTinh = new JComboBox<>(new String[] { "Nam", "Nữ" });
		comboGioiTinh.setFont(new Font("Segoe UI", Font.PLAIN, 14));

		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtEmail = new JTextField(15);
		txtEmail.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtEmail.setToolTipText("Nhập email (tùy chọn)");

		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
				.addGroup(
						layout.createSequentialGroup().addComponent(lblSdt).addComponent(txtSdt).addComponent(btnFind))
				.addGroup(layout.createSequentialGroup().addComponent(lblTen).addComponent(txtTen))
				.addGroup(layout.createSequentialGroup().addComponent(lblGioiTinh).addComponent(comboGioiTinh))
				.addGroup(layout.createSequentialGroup().addComponent(lblEmail).addComponent(txtEmail)));

		layout.setVerticalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(lblSdt)
						.addComponent(txtSdt).addComponent(btnFind))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(lblTen)
						.addComponent(txtTen))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(lblGioiTinh)
						.addComponent(comboGioiTinh))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(lblEmail)
						.addComponent(txtEmail)));

		mainPanel.add(contentPanel, BorderLayout.CENTER);

		// --- Button Panel: OK và Cancel ---
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		buttonPanel.setBackground(Color.WHITE);
		JButton btnOK = new JButton("OK");
		btnOK.setFont(new Font("Segoe UI", Font.BOLD, 14));
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setFont(new Font("Segoe UI", Font.BOLD, 14));
		buttonPanel.add(btnOK);
		buttonPanel.add(btnCancel);
		mainPanel.add(buttonPanel, BorderLayout.SOUTH);

		// Sự kiện nút "Tìm": Kiểm tra SĐT và tự điền thông tin khách nếu có
		btnFind.addActionListener(e -> {
			String sdt = txtSdt.getText().trim();
			if (sdt.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Vui lòng nhập SĐT khách.");
				return;
			}
			KhachHang kh = new KhachHangDAO().getKhachHangByPhone(sdt);
			if (kh != null) {
				txtTen.setText(kh.getTenKhachHang());
				txtEmail.setText(kh.getEmail());
				comboGioiTinh.setSelectedItem(kh.getGioiTinh());
				txtTen.setEditable(false);
				txtEmail.setEditable(false);
				comboGioiTinh.setEnabled(false);
			} else {
				txtTen.setText("");
				txtEmail.setText("");
				txtTen.setEditable(true);
				txtEmail.setEditable(true);
				comboGioiTinh.setEnabled(true);
				JOptionPane.showMessageDialog(this, "Khách hàng mới, vui lòng nhập thông tin đầy đủ.");
			}
		});

		// Sự kiện nút "OK"
		btnOK.addActionListener(e -> {
			if (txtSdt.getText().trim().isEmpty()) {
				JOptionPane.showMessageDialog(this, "SĐT không được để trống.");
				return;
			}
			// Nếu khách hàng chưa tồn tại, yêu cầu nhập tên bắt buộc
			if (new KhachHangDAO().getKhachHangByPhone(txtSdt.getText().trim()) == null) {
				if (txtTen.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog(this, "Vui lòng nhập tên khách hàng.");
					return;
				}
			}
			confirmed = true;
			dispose();
		});

		btnCancel.addActionListener(e -> {
			confirmed = false;
			dispose();
		});

		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(mainPanel, BorderLayout.CENTER);
		pack();
		// Đặt kích thước mặc định dialog lớn hơn
		setPreferredSize(new Dimension(450, 350));
		setResizable(false);
		setLocationRelativeTo(getOwner());
	}

	public boolean isConfirmed() {
		return confirmed;
	}

	public String getSdt() {
		return txtSdt.getText().trim();
	}

	public String getTenKhach() {
		return txtTen.getText().trim();
	}

	public String getEmail() {
		return txtEmail.getText().trim();
	}

	public String getGioiTinh() {
		return (String) comboGioiTinh.getSelectedItem();
	}
}
