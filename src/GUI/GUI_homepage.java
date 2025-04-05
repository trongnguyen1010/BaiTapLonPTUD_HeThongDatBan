package GUI;

import java.awt.EventQueue;

import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JMenuItem;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;

import javax.swing.border.LineBorder;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.awt.event.ActionEvent;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JComboBox;
import java.awt.Component;

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

	/**
     * Hàm xử lý hover cho menu dropdown
     */
    private void addHoverDropdownMenu(JButton button, JPopupMenu menu) {
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                menu.show(button, 0, button.getHeight()); // Hiển thị menu khi hover
            }
        });

        menu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                menu.setVisible(false); // Ẩn menu khi chuột rời khỏi menu
            }
        });
    }
    
	public GUI_homepage() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1440, 820);
		contentPane = new JPanel();
//		contentPane.setSize(new Dimension(1440, 800));
		contentPane.setBounds(new Rectangle(0, 0, 1440, 800));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(92, 171, 255));
		panel.setBounds(0, 0, 1440, 70);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblTitle = new JLabel("Hệ  thống quản lý nhà hàng N2");
//		Font customFont = CustomFontExample.loadFont("/view/image/Pacifico-Regular.ttf", 40f);
//		lblTitle.setFont(customFont);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 40));
		lblTitle.setBounds(368, 10, 646, 60);
		panel.add(lblTitle);
		
		JPanel panel_sidebar = new JPanel();
		panel_sidebar.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_sidebar.setBackground(new Color(161, 227, 249));
		panel_sidebar.setBounds(0, 119, 205, 680);
		contentPane.add(panel_sidebar);
		panel_sidebar.setLayout(null);
		
		// Tạo panel chứa menu
        JPanel menu_nav = new JPanel();
        menu_nav.setBorder(new LineBorder(new Color(0, 0, 0)));
        menu_nav.setBounds(0, 70, 1440, 50);
        contentPane.add(menu_nav);
//        menu_nav.setLayout(new FlowLayout(FlowLayout.LEFT));
        menu_nav.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

        
        
        
        
//        JButton btnHome = new JButton("<html><center>Home</center></html>");
//        btnHome.setIcon(new ImageIcon("src/view/icon/icon_taikhoan.png")); 
//		btnHome.setFont(new Font("Segoe UI Black", Font.BOLD, 18));
//		btnHome.setBorder(new LineBorder(new Color(0, 0, 0)));
//		btnHome.setForeground(Color.BLACK);
////		btnHome.setFocusPainted(false);
//		btnHome.setBackground(defaultColor);
//		btnHome.setPreferredSize(new Dimension(220, 50));
////		btnHome.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // Hiệu ứng chuột
//		btnHome.setHorizontalTextPosition(SwingConstants.RIGHT); // Chữ bên phải icon
//		btnHome.setVerticalTextPosition(SwingConstants.CENTER);  // Căn giữa icon & chữ
//		btnHome.setIconTextGap(20); // Tạo khoảng cách giữa icon và chữ
//        btnHome.addActionListener(new ActionListener() {
//        	public void actionPerformed(ActionEvent e) {
//        	}
//        });
//        menu_nav.add(btnHome);
//        
//        JButton btnTaiKhoan = new JButton("<html><center>Tài khoản</center></html>");
//        btnTaiKhoan.setIcon(new ImageIcon("src/view/icon/icon_taikhoan.png")); 
//		btnTaiKhoan.setFont(new Font("Segoe UI Black", Font.BOLD, 18));
//		btnTaiKhoan.setBorder(new LineBorder(new Color(0, 0, 0)));
//		btnTaiKhoan.setForeground(Color.BLACK);
////		btnTaiKhoan.setFocusPainted(false);
//		btnTaiKhoan.setBackground(defaultColor);
//		btnTaiKhoan.setPreferredSize(new Dimension(232, 50));
////		btnTaiKhoan.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // Hiệu ứng chuột
//		btnTaiKhoan.setHorizontalTextPosition(SwingConstants.RIGHT); // Chữ bên phải icon
//		btnTaiKhoan.setVerticalTextPosition(SwingConstants.CENTER);  // Căn giữa icon & chữ
//		btnTaiKhoan.setIconTextGap(20); // Tạo khoảng cách giữa icon và chữ
//        btnTaiKhoan.addActionListener(new ActionListener() {
//        	public void actionPerformed(ActionEvent e) {
//        	}
//        });
//        menu_nav.add(btnTaiKhoan);
//        
//        JPopupMenu popupMenu = new JPopupMenu();
//        // Tạo và thêm các JMenuItem vào popupMenu
//        JMenuItem item1 = new JMenuItem("Thông tin cá nhân");
//        item1.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                // Xử lý khi chọn "Thông tin cá nhân"
//                System.out.println("Chọn: Thông tin cá nhân");
//            }
//        });
//        popupMenu.add(item1);
//        JMenuItem item2 = new JMenuItem("Đổi mật khẩu");
//        item2.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                // Xử lý khi chọn "Đổi mật khẩu"
//                System.out.println("Ch ọn: Đổi mật khẩu");
//            }
//        });
//        popupMenu.add(item2);
//     // Gán popupMenu cho btnTaiKhoan
//        btnTaiKhoan.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mousePressed(MouseEvent e) {
//                popupMenu.show(btnTaiKhoan, e.getX(), e.getY());
//            }
//        });
		
        
        
        String[] menuItems = {"Trang Chủ", "Bàn", "Khách hàng", "Món ăn", "Hóa Đơn", "Nhân viên", "Tài Khoản"};
        Map<String, String[]> subMenuItems = new HashMap<>();

        // Khai báo menu con riêng cho từng nút
        subMenuItems.put("Trang Chủ", new String[]{"Dashboard", "Thống kê"});
        subMenuItems.put("Bàn", new String[]{"Danh sách bàn", "Đặt bàn", "Hủy bàn"});
        subMenuItems.put("Khách hàng", new String[]{"Quản lý khách hàng", "Danh sách khách"});
        subMenuItems.put("Món ăn", new String[]{"Thực đơn", "Thêm món", "Sửa món"});
        subMenuItems.put("Hóa Đơn", new String[]{"Tạo hóa đơn", "Xem hóa đơn"});
        subMenuItems.put("Nhân viên", new String[]{"Danh sách nhân viên", "Thêm nhân viên"});
        subMenuItems.put("Tài Khoản", new String[]{"Đổi mật khẩu", "Đăng xuất"});

        
        
     // 1. Khởi tạo JPanel Show trước
        Show = new JPanel();
        Show.setForeground(new Color(0, 0, 0));
        Show.setFont(new Font("Jokerman", Font.BOLD, 48));
        Show.setBorder(new LineBorder(new Color(0, 0, 0)));
        Show.setBounds(205, 120, 1220, 700);
        Show.setLayout(new CardLayout()); // Quan trọng: Cần đặt Layout trước khi thêm thành phần

        // 2. Thêm vào contentPane
        contentPane.add(Show);

        // 3. Khởi tạo CardLayout sau khi JPanel đã sẵn sàng
        CardLayout = (CardLayout) Show.getLayout();
        
        QuanLyKhachHang = new GUI_QuanLyKhachHang(); // Khởi tạo giao diện quản lý nhân viên
        Show.add(QuanLyKhachHang, "KhachHang"); // Thêm vào CardLayout
        
        for (String item : menuItems) {
            JButton menuButton = new JButton(item);
            menuButton.setBackground(new Color(161, 227, 249));
            menuButton.setFont(new Font("Segoe UI Black", Font.BOLD, 18));
            menuButton.setPreferredSize(new Dimension(205, 50));

            // Tạo menu thả xuống riêng
            JPopupMenu popupMenu = new JPopupMenu();
            popupMenu.setLayout(new GridLayout(0, 1)); // Để menu con liền nhau

         // Lấy danh sách mục con tương ứng
            String[] subItems = subMenuItems.get(item);
            if (subItems != null) {
                for (String subItem : subItems) {
                    JMenuItem subMenuItem = new JMenuItem(subItem);
                    subMenuItem.setPreferredSize(new Dimension(205, 40)); // Đặt kích thước ngang với button
//                    subMenuItem.setBackground(new Color(200, 200, 200)); // Màu nền menu con
                    subMenuItem.setFont(new Font("Segoe UI", Font.PLAIN, 16));
                    subMenuItem.setOpaque(true);
                    subMenuItem.setBackground(Color.WHITE);
                    subMenuItem.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10)); // Căn chỉnh text
                    
                    // Thêm hiệu ứng hover
                    subMenuItem.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseEntered(MouseEvent e) {
//                            subMenuItem.setBackground(new Color(180, 180, 180)); // Hiệu ứng hover menu con
                            subMenuItem.setBackground(new Color(200, 230, 255));
                        }
                        @Override
                        public void mouseExited(MouseEvent e) {
//                            subMenuItem.setBackground(new Color(200, 200, 200));
                            subMenuItem.setBackground(Color.WHITE);
                        }
                    });

                    // Xử lý sự kiện khi nhấn vào từng menu con
                    subMenuItem.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            System.out.println("Bạn đã chọn: " + subMenuItem.getText());
                            
                            // Ẩn menu sau khi chọn
                            popupMenu.setVisible(false);

                            // Thực hiện hành động tùy thuộc vào menu con
                            switch (subMenuItem.getText()) {
                                case "Dashboard":
                                    System.out.println("Chuyển đến Dashboard");
                                    break;
                                case "Thống kê":
                                    System.out.println("Mở trang thống kê");
                                    break;
                                case "Đổi mật khẩu":
                                    System.out.println("Mở giao diện đổi mật khẩu");
                                    break;
                                case "Đăng xuất":
                                    System.out.println("Đăng xuất tài khoản");
                                    break;
                                case "Quản lý khách hàng":
                                	CardLayout.show(Show, "KhachHang"); // Chuyển sang giao diện Nhân viên
                                    System.out.println("Quản lý khách hàng");
                                    break;
                                default:
                                    System.out.println("Hành động chưa được định nghĩa");
                            }
                        }
                    });

                    popupMenu.add(subMenuItem);
                }
            }
         // Hiệu ứng hover cho nút menu chính
            menuButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    menuButton.setBackground(new Color(80, 145, 220));
                    menuButton.setFont(new Font("Segoe UI", Font.BOLD, 20));   
                    popupMenu.show(menuButton, 0, menuButton.getHeight());
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    menuButton.setBackground(new Color(100, 181, 246));
                    menuButton.setFont(new Font("Segoe UI", Font.BOLD, 18));
                }
            });

            // Hiển thị menu khi hover
            menuButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    popupMenu.show(menuButton, 0, menuButton.getHeight());
                }
            });

            // Ẩn menu khi di chuột ra ngoài
            popupMenu.addPopupMenuListener(new PopupMenuListener() {
                @Override
                public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
                    menuButton.setBackground(new Color(161, 227, 249));
                    menuButton.setFont(new Font("Segoe UI Black", Font.BOLD, 18));
                }

                @Override
                public void popupMenuWillBecomeVisible(PopupMenuEvent e) {}

                @Override
                public void popupMenuCanceled(PopupMenuEvent e) {}
            });

            menu_nav.add(menuButton);
        }



        
        
//        ------------------------------------------------------------------------------------------------------------------------------
		
//		// Tạo icon từ file ảnh
//		ImageIcon icon_qldatban = new ImageIcon(getClass().getResource("/view/icon/icon_qldatban.png"));
//		
//		JPanel panel_2 = new JPanel();
//		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
//		panel_2.setBackground(new Color(161, 227, 249));
//		panel_2.setBounds(220, 189, 1220, 611);
//		
//		contentPane.add(panel_2);
//		panel_2.setLayout(null);
//		//		HoaDon = new GUI_HoaDon();
//				QuanLyKhachHang = new GUI_QuanLyKhachHang();
//				QuanLyKhachHang.setBounds(8, 24, 1218, 698);
//				panel_2.add(QuanLyKhachHang);
//		ImageIcon icon = new ImageIcon(getClass().getResource("/view/image/image_homepage.png"));
//		Image image = icon.getImage();
//		Image scaledImage = image.getScaledInstance(900, 700, Image.SCALE_SMOOTH);
////		----------------------------------------------------------------------
//		Show = new JPanel();
//		Show.setForeground(new Color(0, 0, 0));
//		Show.setFont(new Font("Jokerman", Font.BOLD, 48));
//		Show.setBorder(new LineBorder(new Color(0, 0, 0)));
//		Show.setBounds(220, 100, 1220, 700);
//		contentPane.add(Show);
//		
//		//Show pages
//		Show.setLayout(new CardLayout());
//		
//		CardLayout = (CardLayout)Show.getLayout();
//		QuanLyNhanVien = new GUI_QuanLyNhanVien();
//		QuanLyBan = new GUI_QuanLyBan();
//		
//		Show.add(QuanLyBan,"QuanLyBan");
//		Show.add(QuanLyNhanVien,"QuanLyNhanVien");
	}
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}

