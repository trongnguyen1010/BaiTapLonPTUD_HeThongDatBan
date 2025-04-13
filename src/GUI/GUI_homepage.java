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
	private GUI_TimKiemNhanVien TimKiemNhanVien;
	private GUI_TimKiemKhachHang TimKiemKhachHang;
	private GUI_QuanLyTaiKhoan QuanLyTaiKhoan;
	private GUI_TimKiemTaiKhoan TimKiemTaiKhoan;
	private GUI_QuanLyMonAn QuanLyMonAn;
	private GUI_DatBan DatBan;
	
	
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
		setBounds(100, 100, 1455, 820);
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
		
//		JPanel panel_sidebar = new JPanel();
//		panel_sidebar.setBorder(new LineBorder(new Color(0, 0, 0)));
//		panel_sidebar.setBackground(new Color(161, 227, 249));
//		panel_sidebar.setBounds(0, 119, 205, 680);
//		contentPane.add(panel_sidebar);
//		panel_sidebar.setLayout(null);
		
		// Tạo panel chứa menu
        JPanel menu_nav = new JPanel();
        menu_nav.setBorder(new LineBorder(new Color(0, 0, 0)));
        menu_nav.setBounds(0, 70, 1440, 50);
        contentPane.add(menu_nav);
//        menu_nav.setLayout(new FlowLayout(FlowLayout.LEFT));
        menu_nav.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

        
        
        
        String[] menuItems = {"Trang Chủ", "Bàn", "Khách hàng", "Món ăn", "Hóa Đơn", "Nhân viên", "Tài Khoản"};
        Map<String, String[]> subMenuItems = new HashMap<>();

        // Khai báo menu con riêng cho từng nút
        subMenuItems.put("Trang Chủ", new String[]{"Dashboard", "Thống kê"});
        subMenuItems.put("Bàn", new String[]{"Quản lý bàn", "Đặt bàn", "Hủy bàn"});
        subMenuItems.put("Khách hàng", new String[]{"Quản lý khách hàng", "Tìm kiếm khách hàng"});
        subMenuItems.put("Món ăn", new String[]{"Quản lý món ăn", "Sửa món"});
        subMenuItems.put("Hóa Đơn", new String[]{"Tạo hóa đơn", "Xem hóa đơn"});
        subMenuItems.put("Nhân viên", new String[]{"Quản lý nhân viên", "Tìm kiếm nhân viên"});
        subMenuItems.put("Tài Khoản", new String[]{"Quản lý tài khoản", "Tìm kiếm tài khoản", "Đổi mật khẩu", "Đăng xuất"});

        
        
     // 1. Khởi tạo JPanel Show trước
        Show = new JPanel();
        Show.setForeground(new Color(0, 0, 0));
        Show.setFont(new Font("Jokerman", Font.BOLD, 48));
        Show.setBorder(new LineBorder(new Color(0, 0, 0)));
        Show.setBounds(0, 120, 1440, 700);
        Show.setLayout(new CardLayout()); // Quan trọng: Cần đặt Layout trước khi thêm thành phần

        // 2. Thêm vào contentPane
        contentPane.add(Show);

        // 3. Khởi tạo CardLayout sau khi JPanel đã sẵn sàng
        CardLayout = (CardLayout) Show.getLayout();
        
        QuanLyKhachHang = new GUI_QuanLyKhachHang(); // Khởi tạo giao diện
        Show.add(QuanLyKhachHang, "QuanLyKhachHang"); // Thêm vào CardLayout
        TimKiemKhachHang = new GUI_TimKiemKhachHang(); 
        Show.add(TimKiemKhachHang, "TimKiemKhachHang"); 
        
        QuanLyBan = new GUI_QuanLyBan();
        Show.add(QuanLyBan, "QuanLyBan");
        DatBan = new GUI_DatBan();
        Show.add(DatBan, "DatBan");
        
        QuanLyNhanVien = new GUI_QuanLyNhanVien(); 
        Show.add(QuanLyNhanVien, "QuanLyNhanVien");
        TimKiemNhanVien = new GUI_TimKiemNhanVien(); 
        Show.add(TimKiemNhanVien, "TimKiemNhanVien"); 
        
        QuanLyTaiKhoan = new GUI_QuanLyTaiKhoan(); 
        Show.add(QuanLyTaiKhoan, "QuanLyTaiKhoan");
        TimKiemTaiKhoan = new GUI_TimKiemTaiKhoan(); 
        Show.add(TimKiemTaiKhoan, "TimKiemTaiKhoan");
        
        QuanLyMonAn = new GUI_QuanLyMonAn();
        Show.add(QuanLyMonAn, "QuanLyMonAn");
        
//     ----------------------------------------------------------------------------------
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
                                case "Quản lý bàn":
                                	CardLayout.show(Show, "QuanLyBan");
                                    System.out.println("Quản lý bàn");
                                    break;
                                case "Đặt bàn":
                                	CardLayout.show(Show, "DatBan");
                                    System.out.println("Đặt bàn");
                                    break;
                                case "Đổi mật khẩu":
                                    System.out.println("Mở giao diện đổi mật khẩu");
                                    break;
                                case "Đăng xuất":
                                    System.out.println("Đăng xuất tài khoản");
                                    break;
                                case "Quản lý khách hàng":
                                	CardLayout.show(Show, "QuanLyKhachHang");
                                    break;
                                case "Tìm kiếm khách hàng":
                                	CardLayout.show(Show, "TimKiemKhachHang");
                                    break;
                                case "Quản lý nhân viên":
                                	CardLayout.show(Show, "QuanLyNhanVien");
                                    break;
                                case "Tìm kiếm nhân viên":
                                	CardLayout.show(Show, "TimKiemNhanVien");
                                    break;
                                case "Quản lý tài khoản":
                                	CardLayout.show(Show, "QuanLyTaiKhoan");
                                    break;
                                case "Tìm kiếm tài khoản":
                                	CardLayout.show(Show, "TimKiemTaiKhoan");
                                    break;
                                case "Quản lý món ăn":
                                	CardLayout.show(Show, "QuanLyMonAn");
                                    break;
                                case "Tìm kiếm ":
                                	CardLayout.show(Show, "TimKiemTaiKhoan");
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