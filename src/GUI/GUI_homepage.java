package GUI;

import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;

import javax.swing.border.LineBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.awt.event.ActionEvent;

import java.io.File;
import java.io.IOException;

public class GUI_homepage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI_homepage frame = new GUI_homepage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
	// Thêm phương thức addHoverEffect
	
	// Tạo màu cho hiệu ứng
	Color defaultColor = new Color(217, 217, 217); // Màu nền ban đầu
	Color hoverColor = new Color(161, 227, 249);   // Màu khi hover
	Color activeColor = new Color(180, 180, 180);  // Màu khi nhấn
	Font defaultFont = new Font("Segoe UI Black", Font.BOLD, 18);
	Font hoverFont = new Font("Segoe UI Black", Font.BOLD, 20); // Phóng to khi hover
	
	private JPanel Show;
	private CardLayout CardLayout;
	private GUI_QuanLyBan QuanLyBan;
	private GUI_QuanLyKhachHang QuanLyKhachHang;
	private GUI_QuanLyNhanVien QuanLyNhanVien;
	
	
	private void addButtonEffects(JButton button, Color hoverColor, Font hoverFont, Color defaultColor, Font defaultFont, Color activeColor) {
		button.setBackground(defaultColor);
		button.setOpaque(true);
		button.setBorderPainted(false);
		
		button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		button.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseEntered(MouseEvent e) {
		button.setBackground(hoverColor);
		button.setFont(hoverFont);
		}
		
		@Override
		public void mouseExited(MouseEvent e) {
		button.setBackground(defaultColor);
		button.setFont(defaultFont);
		button.revalidate();
        button.repaint();
		}
		
		@Override
		public void mousePressed(MouseEvent e) {
		button.setBackground(activeColor);
		}
		
		@Override
		public void mouseReleased(MouseEvent e) {
		button.setBackground(hoverColor);
		}
		});
	}

	public class CustomFontExample {
	    public static Font loadFont(String path, float size) {
	    	try {
	    	    return Font.createFont(Font.TRUETYPE_FONT, new File(path)).deriveFont(size);
	    	} catch (FontFormatException e) { 
	    	    e.printStackTrace();
	    	    return new Font("SansSerif", Font.PLAIN, (int) size);
	    	} catch (IOException e) {
	    	    e.printStackTrace();
	    	    return new Font("SansSerif", Font.PLAIN, (int) size);
	    	}

	    }
	}

	
	public GUI_homepage() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1455, 820);
		contentPane = new JPanel();
//		contentPane.setSize(new Dimension(1440, 800));
		contentPane.setBounds(new Rectangle(0, 0, 1440, 800));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(92, 171, 255));
		panel.setBounds(0, 0, 1440, 100);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblTitle = new JLabel("Hệ  thống quản lý nhà hàng N2");
//		Font customFont = CustomFontExample.loadFont("/view/image/Pacifico-Regular.ttf", 40f);
//		lblTitle.setFont(customFont);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 40));
		lblTitle.setBounds(333, 10, 700, 80);
		panel.add(lblTitle);
		
		JPanel panel_sidebar = new JPanel();
		panel_sidebar.setBackground(new Color(161, 227, 249));
		panel_sidebar.setBounds(0, 100, 220, 700);
		contentPane.add(panel_sidebar);
		panel_sidebar.setLayout(null);
		
//-----------------------------------------------------------------------------------
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(217, 217, 217));
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBounds(0, 0, 220, 80);
		panel_1.setLayout(new BorderLayout());
		panel_sidebar.add(panel_1);
		
		// Tạo icon từ file ảnh
		ImageIcon icon_qldatban = new ImageIcon(getClass().getResource("/view/icon/icon_qldatban.png"));

		// Tạo JButton với icon và text
		JButton btnQuanLyDatban = new JButton("<html><center>Quản lý <br> đặt bàn</center></html>");
		btnQuanLyDatban.setIcon(icon_qldatban); // Thêm icon vào button
		btnQuanLyDatban.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnQuanLyDatban.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnQuanLyDatban.setForeground(Color.BLACK);
		btnQuanLyDatban.setFont(new Font("Segoe UI Black", Font.BOLD, 18));
		btnQuanLyDatban.setFocusPainted(false);
		btnQuanLyDatban.setBackground(new Color(217, 217, 217));
		btnQuanLyDatban.setPreferredSize(new Dimension(220, 80));

		// Căn chỉnh icon và chữ
		btnQuanLyDatban.setHorizontalTextPosition(SwingConstants.RIGHT); // Chữ bên phải icon
		btnQuanLyDatban.setVerticalTextPosition(SwingConstants.CENTER);  // Căn giữa icon & chữ
		btnQuanLyDatban.setIconTextGap(30); // Tạo khoảng cách giữa icon và chữ

		// Thêm hiệu ứng hover
		addButtonEffects(btnQuanLyDatban, hoverColor, hoverFont, defaultColor, defaultFont, activeColor);

		// Thêm sự kiện click cho button
		btnQuanLyDatban.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        // Xử lý sự kiện khi nhấn vào button
		    }
		});
		panel_1.add(btnQuanLyDatban, BorderLayout.CENTER);

		
//		JPanel panel_1 = new JPanel();
//		panel_1.setBackground(new Color(217, 217, 217));
//		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
//		panel_1.setBounds(0, 0, 220, 80);
//		panel_1.setLayout(new BorderLayout()); // Sử dụng layout phù hợp
//		panel_sidebar.add(panel_1);
//
//		// Tạo JButton và set layout
//		JButton btnQuanLyDatban = new JButton();
//		btnQuanLyDatban.setPreferredSize(new Dimension(200, 80));
//
//		ImageIcon icon_qldatban = new ImageIcon("src/view/icon/icon_qldatban.png"); 
//		JLabel lblIcon_qldatban = new JLabel(icon_qldatban);
//		lblIcon_qldatban.setHorizontalAlignment(SwingConstants.CENTER);
//
//		JLabel lblText_qldatban = new JLabel("<html><center>Quản lý<br>đặt bàn</center></html>", SwingConstants.CENTER);
//		lblText_qldatban.setFont(new Font("Segoe UI Black", Font.BOLD, 18));
//		lblText_qldatban.setForeground(Color.BLACK);
//
//		btnQuanLyDatban.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10)); // Căn trái và cách 10px
//		btnQuanLyDatban.add(lblIcon_qldatban);
//		btnQuanLyDatban.add(lblText_qldatban);
//		
//		addButtonEffects(btnQuanLyDatban, hoverColor, hoverFont, defaultColor, defaultFont, activeColor);
//		// Thêm button vào panel_1
//		panel_1.add(btnQuanLyDatban, BorderLayout.CENTER);
		
//-------------------------------------------------------------------------
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setBackground(new Color(217, 217, 217));
		panel_1_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1_1.setBounds(0, 80, 220, 80);
		panel_1_1.setLayout(new BorderLayout());
		panel_sidebar.add(panel_1_1);

		JButton btnQuanLyBan = new JButton("<html><center>Quản lý <br> bàn</center></html>");
		btnQuanLyBan.setIcon(new ImageIcon("src/view/icon/icon_qlban.png")); 
		btnQuanLyBan.setFont(new Font("Segoe UI Black", Font.BOLD, 18));
		btnQuanLyBan.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnQuanLyBan.setForeground(Color.BLACK);
//		btnQuanLyBan.setFocusPainted(false);
		btnQuanLyBan.setBackground(defaultColor);
		btnQuanLyBan.setPreferredSize(new Dimension(220, 80));
		btnQuanLyBan.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // Hiệu ứng chuột
		btnQuanLyBan.setHorizontalTextPosition(SwingConstants.RIGHT); // Chữ bên phải icon
		btnQuanLyBan.setVerticalTextPosition(SwingConstants.CENTER);  // Căn giữa icon & chữ
		btnQuanLyBan.setIconTextGap(15); // Tạo khoảng cách giữa icon và chữ
		// Thêm hiệu ứng hover
		addButtonEffects(btnQuanLyBan, hoverColor, hoverFont, defaultColor, defaultFont, activeColor);
		panel_1_1.add(btnQuanLyBan, BorderLayout.CENTER);

		btnQuanLyBan.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        // Xử lý sự kiện khi nhấn vào button
		    }
		});
		
//		----------------------------------------------------------------------
		
		
		JPanel panel_1_2 = new JPanel();
		panel_1_2.setBackground(new Color(217, 217, 217));
		panel_1_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1_2.setBounds(0, 160, 220, 80);
		panel_1_2.setLayout(new BorderLayout());
		panel_sidebar.add(panel_1_2);
		
		JButton btnQuanLyMonAn = new JButton("<html><center>Quản lý <br> món ăn</center></html>");
		btnQuanLyMonAn.setIcon(new ImageIcon("src/view/icon/icon_monan.png")); 
		btnQuanLyMonAn.setFont(new Font("Segoe UI Black", Font.BOLD, 18));
		btnQuanLyMonAn.setForeground(Color.BLACK);
		btnQuanLyMonAn.setBackground(defaultColor);
		btnQuanLyMonAn.setPreferredSize(new Dimension(220, 80));
		btnQuanLyMonAn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // Hiệu ứng chuột
		btnQuanLyMonAn.setHorizontalTextPosition(SwingConstants.RIGHT); // Chữ bên phải icon
		btnQuanLyMonAn.setVerticalTextPosition(SwingConstants.CENTER);  // Căn giữa icon & chữ
		btnQuanLyMonAn.setIconTextGap(30); // Tạo khoảng cách giữa icon và chữ
		// Thêm hiệu ứng hover
		addButtonEffects(btnQuanLyMonAn, hoverColor, hoverFont, defaultColor, defaultFont, activeColor);
		panel_1_2.add(btnQuanLyMonAn, BorderLayout.CENTER);
		
//		-------------------------------------------------------------------------------
		JPanel panel_1_3 = new JPanel();
		panel_1_3.setBackground(new Color(217, 217, 217));
		panel_1_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1_3.setBounds(0, 240, 220, 80);
		panel_1_3.setLayout(new BorderLayout());
		panel_sidebar.add(panel_1_3);
		
		JButton btnHoaDon = new JButton("<html><center>Hóa Đơn</center></html>");
		btnHoaDon.setIcon(new ImageIcon("src/view/icon/icon_hoadon.png")); 
		btnHoaDon.setFont(new Font("Segoe UI Black", Font.BOLD, 18));
		btnHoaDon.setForeground(Color.BLACK);
		btnHoaDon.setBackground(defaultColor);
		btnHoaDon.setPreferredSize(new Dimension(220, 80));
		btnHoaDon.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // Hiệu ứng chuột
		btnHoaDon.setHorizontalTextPosition(SwingConstants.RIGHT); // Chữ bên phải icon
		btnHoaDon.setVerticalTextPosition(SwingConstants.CENTER);  // Căn giữa icon & chữ
		btnHoaDon.setIconTextGap(15); // Tạo khoảng cách giữa icon và chữ
		// Thêm hiệu ứng hover
		addButtonEffects(btnHoaDon, hoverColor, hoverFont, defaultColor, defaultFont, activeColor);
		panel_1_3.add(btnHoaDon, BorderLayout.CENTER);
		
//		-------------------------------------------------------------------
		
		JPanel panel_1_4 = new JPanel();
		panel_1_4.setBackground(new Color(217, 217, 217));
		panel_1_4.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1_4.setBounds(0, 320, 220, 80);
		panel_1_4.setLayout(new BorderLayout());
		panel_sidebar.add(panel_1_4);

		JButton btnThongKe = new JButton("<html><center>Thống Kê</center></html>");
		btnThongKe.setIcon(new ImageIcon("src/view/icon/icon_thongke.png")); 
		btnThongKe.setFont(new Font("Segoe UI Black", Font.BOLD, 18));
		btnThongKe.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnThongKe.setForeground(Color.BLACK);
//		btnThongKe.setFocusPainted(false);
		btnThongKe.setBackground(defaultColor);
		btnThongKe.setPreferredSize(new Dimension(220, 80));
		btnThongKe.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // Hiệu ứng chuột
		btnThongKe.setHorizontalTextPosition(SwingConstants.RIGHT); // Chữ bên phải icon
		btnThongKe.setVerticalTextPosition(SwingConstants.CENTER);  // Căn giữa icon & chữ
		btnThongKe.setIconTextGap(15); // Tạo khoảng cách giữa icon và chữ
		// Thêm hiệu ứng hover
		addButtonEffects(btnThongKe, hoverColor, hoverFont, defaultColor, defaultFont, activeColor);
		panel_1_4.add(btnThongKe, BorderLayout.CENTER);

		btnThongKe.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        // Xử lý sự kiện khi nhấn vào button
		    }
		});
//		-------------------------------------------------------------------
		
		JPanel panel_1_5 = new JPanel();
		panel_1_5.setBackground(new Color(217, 217, 217));
		panel_1_5.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1_5.setBounds(0, 400, 220, 80);
		panel_1_5.setLayout(new BorderLayout());
		panel_sidebar.add(panel_1_5);
		
		JButton btnQuanLyKhachHang = new JButton("<html><center>Quản lý <br> khách hàng</center></html>");
		btnQuanLyKhachHang.setIcon(new ImageIcon("src/view/icon/icon_qlkhachhang.png")); 
		btnQuanLyKhachHang.setFont(new Font("Segoe UI Black", Font.BOLD, 18));
		btnQuanLyKhachHang.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnQuanLyKhachHang.setForeground(Color.BLACK);
//		btnQuanLyKhachHang.setFocusPainted(false);
		btnQuanLyKhachHang.setBackground(defaultColor);
		btnQuanLyKhachHang.setPreferredSize(new Dimension(220, 80));
		btnQuanLyKhachHang.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // Hiệu ứng chuột
		btnQuanLyKhachHang.setHorizontalTextPosition(SwingConstants.RIGHT); // Chữ bên phải icon
		btnQuanLyKhachHang.setVerticalTextPosition(SwingConstants.CENTER);  // Căn giữa icon & chữ
		btnQuanLyKhachHang.setIconTextGap(10); // Tạo khoảng cách giữa icon và chữ
		// Thêm hiệu ứng hover
		addButtonEffects(btnQuanLyKhachHang, hoverColor, hoverFont, defaultColor, defaultFont, activeColor);
		panel_1_5.add(btnQuanLyKhachHang, BorderLayout.CENTER);

		btnQuanLyKhachHang.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        // Xử lý sự kiện khi nhấn vào button
		    }
		});
		
//		-----------------------------------------------------------------
		JPanel panel_1_6 = new JPanel();
		panel_1_6.setBackground(new Color(217, 217, 217));
		panel_1_6.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1_6.setBounds(0, 480, 220, 80);
		panel_1_6.setLayout(new BorderLayout());
		panel_sidebar.add(panel_1_6);
		
		JButton btnQuanLyNhanVien = new JButton("<html><center>Quản lý <br> nhân viên</center></html>");
		btnQuanLyNhanVien.setIcon(new ImageIcon("src/view/icon/icon_qlnhanvien.png")); 
		btnQuanLyNhanVien.setFont(new Font("Segoe UI Black", Font.BOLD, 18));
		btnQuanLyNhanVien.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnQuanLyNhanVien.setForeground(Color.BLACK);
//		btnQuanLyNhanVien.setFocusPainted(false);
		btnQuanLyNhanVien.setBackground(defaultColor);
		btnQuanLyNhanVien.setPreferredSize(new Dimension(220, 80));
		btnQuanLyNhanVien.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // Hiệu ứng chuột
		btnQuanLyNhanVien.setHorizontalTextPosition(SwingConstants.RIGHT); // Chữ bên phải icon
		btnQuanLyNhanVien.setVerticalTextPosition(SwingConstants.CENTER);  // Căn giữa icon & chữ
		btnQuanLyNhanVien.setIconTextGap(20); // Tạo khoảng cách giữa icon và chữ
		// Thêm hiệu ứng hover
		addButtonEffects(btnQuanLyNhanVien, hoverColor, hoverFont, defaultColor, defaultFont, activeColor);
		panel_1_6.add(btnQuanLyNhanVien, BorderLayout.CENTER);

		btnQuanLyNhanVien.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        // Xử lý sự kiện khi nhấn vào button
		    }
		});
		
//		--------------------------------------------------------------------------
		
		JPanel panel_1_7 = new JPanel();
		panel_1_7.setBackground(new Color(217, 217, 217));
		panel_1_7.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1_7.setBounds(0, 560, 220, 80);
		panel_1_7.setLayout(new BorderLayout());
		panel_sidebar.add(panel_1_7);
		
		JButton btnTaiKhoan = new JButton("<html><center>Tài khoản</center></html>");
		btnTaiKhoan.setIcon(new ImageIcon("src/view/icon/icon_taikhoan.png")); 
		btnTaiKhoan.setFont(new Font("Segoe UI Black", Font.BOLD, 18));
		btnTaiKhoan.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnTaiKhoan.setForeground(Color.BLACK);
//		btnTaiKhoan.setFocusPainted(false);
		btnTaiKhoan.setBackground(defaultColor);
		btnTaiKhoan.setPreferredSize(new Dimension(220, 80));
		btnTaiKhoan.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // Hiệu ứng chuột
		btnTaiKhoan.setHorizontalTextPosition(SwingConstants.RIGHT); // Chữ bên phải icon
		btnTaiKhoan.setVerticalTextPosition(SwingConstants.CENTER);  // Căn giữa icon & chữ
		btnTaiKhoan.setIconTextGap(20); // Tạo khoảng cách giữa icon và chữ
		// Thêm hiệu ứng hover
		addButtonEffects(btnTaiKhoan, hoverColor, hoverFont, defaultColor, defaultFont, activeColor);
		panel_1_7.add(btnTaiKhoan, BorderLayout.CENTER);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2.setBackground(new Color(161, 227, 249));
		panel_2.setBounds(220, 100, 1220, 700);
		
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel = new JLabel();
		ImageIcon icon = new ImageIcon(getClass().getResource("/view/image/image_homepage.png"));
		Image image = icon.getImage();
		Image scaledImage = image.getScaledInstance(900, 700, Image.SCALE_SMOOTH);
		lblNewLabel .setIcon(new ImageIcon(scaledImage));
		lblNewLabel.setBounds(180, 0, 900, 700);
		panel_2.add(lblNewLabel);

		btnTaiKhoan.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        // Xử lý sự kiện khi nhấn vào button
		    }
		});
//		----------------------------------------------------------------------
		Show = new JPanel();
		Show.setForeground(new Color(0, 0, 0));
		Show.setFont(new Font("Jokerman", Font.BOLD, 48));
		Show.setBorder(new LineBorder(new Color(0, 0, 0)));
		Show.setBounds(220, 100, 1220, 700);
		contentPane.add(Show);
		
		//Show pages
		Show.setLayout(new CardLayout());
		
		CardLayout = (CardLayout)Show.getLayout();
//		HoaDon = new GUI_HoaDon();
		QuanLyKhachHang = new GUI_QuanLyKhachHang();
		QuanLyNhanVien = new GUI_QuanLyNhanVien();
		QuanLyBan = new GUI_QuanLyBan();
		
		Show.add(QuanLyBan,"QuanLyBan");
//		Show.add(HoaDon,"HoaDon");
		Show.add(QuanLyKhachHang,"QuanLyKhachHang");
		Show.add(QuanLyNhanVien,"QuanLyNhanVien");
//		Show.add(ThongKe,"ThongKe");
//		Show.add(TT_Hoadon,"TT_Hoadon");
		
		
		btnQuanLyKhachHang.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
//		        QuanLyKhachHang.refreshData(); 
				CardLayout.show(Show,"QuanLyKhachHang");
			}
			
		});
		btnQuanLyNhanVien.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
//		        QuanLyKhachHang.refreshData(); 
				CardLayout.show(Show,"QuanLyNhanVien");
			}
			
		});
	}

}
