package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI_QuanLyMonAn extends JPanel {

    private static final long serialVersionUID = 1L;
    private JPanel foodListPanel;  // Danh sách món ăn

    // Mảng món ăn có sẵn
    private String[] tenMon = {"Món 1", "Món 2", "Món 3", "Món 4", "Món 5", "Món 6", "Món 7", "Món 8", "Món 9", "Món 10", "Món 11", "Món 12"};
    private String[] gia = {"10.000 VND", "20.000 VND", "15.000 VND", "25.000 VND", "30.000 VND", "35.000 VND", "40.000 VND", "45.000 VND", "50.000 VND", "55.000 VND", "60.000 VND", "65.000 VND"};
    private String[] duongDanAnh = {
        "/view/image/n2.jpg", "/view/image/n2.jpg", "/view/image/n2.jpg", "/view/image/n2.jpg", "/view/image/n2.jpg",
        "/view/image/n2.jpg", "/view/image/n2.jpg", "/view/image/n2.jpg", "/view/image/n2.jpg", "/view/image/n2.jpg",
        "/view/image/n2.jpg", "/view/image/n2.jpg"
    };
    //
   
    public GUI_QuanLyMonAn() {
        setLayout(new BorderLayout());

        // --- NORTH --- 
        JPanel panelNorth = new JPanel();
        panelNorth.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 40));
        panelNorth.setBackground(Color.LIGHT_GRAY);
        panelNorth.setPreferredSize(new Dimension(1200, 100));
        add(panelNorth, BorderLayout.NORTH);

        // --- WEST: MENU DỌC --- 
        JPanel panelWest = new JPanel();
        panelWest.setLayout(new BoxLayout(panelWest, BoxLayout.Y_AXIS));
        panelWest.setBackground(Color.CYAN);
        panelWest.setPreferredSize(new Dimension(180, 0));
        add(panelWest, BorderLayout.WEST);

        // Thêm các mục menu bằng JLabel (chữ thôi)
        panelWest.add(createMenuItem("Món Mới"));
        panelWest.add(Box.createVerticalStrut(50));
        panelWest.add(createMenuItem("Khai Vị"));
        panelWest.add(Box.createVerticalStrut(50));
        panelWest.add(createMenuItem("Lẩu"));
        panelWest.add(Box.createVerticalStrut(50));
        panelWest.add(createMenuItem("Heo-Gà-Bò"));
        panelWest.add(Box.createVerticalStrut(50));
        panelWest.add(createMenuItem("Hải Sản"));
        panelWest.add(Box.createVerticalStrut(50));
        panelWest.add(createMenuItem("Đồ uống"));
        panelWest.add(Box.createVerticalStrut(50));
        panelWest.add(createMenuItem("Món Tráng Miệng"));
        panelWest.add(Box.createVerticalStrut(0));

        // --- CENTER chính ---
        JPanel centerMain = new JPanel(new BorderLayout());
        add(centerMain, BorderLayout.CENTER);

        // --- CENTER - NORTH: thanh chức năng ---
        JPanel centerNorth1 = new JPanel();
        centerNorth1.setBackground(new Color(161, 227, 249));
        centerNorth1.setPreferredSize(new Dimension(1020, 130));
        centerNorth1.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20));
        centerMain.add(centerNorth1, BorderLayout.NORTH);

        // Button THÊM
        JButton jButton3 = new JButton("THÊM");
        jButton3.setIcon(new ImageIcon(GUI_QuanLyMonAn.class.getResource("/view/icon/icon_them.png")));
        jButton3.setBorderPainted(false);
        jButton3.setContentAreaFilled(false);
        jButton3.setFocusPainted(false);
        jButton3.setHorizontalTextPosition(SwingConstants.CENTER);
        jButton3.setVerticalTextPosition(SwingConstants.BOTTOM);
        centerNorth1.add(jButton3);

        // Button CẬP NHẬT
        JButton jButton1 = new JButton("CẬP NHẬT");
        jButton1.setIcon(new ImageIcon(GUI_QuanLyMonAn.class.getResource("/view/icon/icon_capnhat.png")));
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.setFocusPainted(false);
        jButton1.setHorizontalTextPosition(SwingConstants.CENTER);
        jButton1.setVerticalTextPosition(SwingConstants.BOTTOM);
        centerNorth1.add(jButton1);

        // Button XÓA
        JButton jButton4 = new JButton("XÓA");
        jButton4.setIcon(new ImageIcon(GUI_QuanLyMonAn.class.getResource("/view/icon/icon_xoa.png")));
        jButton4.setBorderPainted(false);
        jButton4.setContentAreaFilled(false);
        jButton4.setFocusPainted(false);
        jButton4.setHorizontalTextPosition(SwingConstants.CENTER);
        jButton4.setVerticalTextPosition(SwingConstants.BOTTOM);
        centerNorth1.add(jButton4);

        // Combobox loại món
        JComboBox<String> jComboBox1 = new JComboBox<>(new String[]{"Tất cả", "Tráng miệng", "Lẩu", "Khai vị"});
        jComboBox1.setPreferredSize(new Dimension(184, 65));
        centerNorth1.add(jComboBox1);

        // TextField tìm kiếm
        JTextField jTextField1 = new JTextField("Tìm kiếm");
        jTextField1.setPreferredSize(new Dimension(362, 65));
        centerNorth1.add(jTextField1);

        // --- CENTER - CENTER: Danh sách món ăn --- 
        JPanel centerCenter = new JPanel(new BorderLayout());
        foodListPanel = createFoodList();  // Tạo danh sách món ăn
        JScrollPane scrollPane = new JScrollPane(foodListPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);  // Thanh cuộn dọc
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);  // Không có thanh cuộn ngang
        centerCenter.add(scrollPane, BorderLayout.CENTER);
        centerMain.add(centerCenter, BorderLayout.CENTER);

       
        
    }

    // Tạo JLabel cho từng mục menu
    private JPanel createMenuItem(String text) {
        JPanel item = new JPanel(new FlowLayout(FlowLayout.LEFT));
        item.setMaximumSize(new Dimension(180, 40));
        item.setBackground(Color.CYAN);

        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, 20));
        item.add(label);
        return item;
    }

    // --- Tạo danh sách món ăn ---
    private JPanel createFoodList() {
        JPanel foodListPanel = new JPanel();
        foodListPanel.setLayout(new GridLayout(4, 3, 10, 10)); // 4 hàng, 3 cột
        foodListPanel.setBackground(Color.WHITE);

        // Thêm 12 món ăn có sẵn vào danh sách
        for (int i = 0; i < tenMon.length; i++) {
            JPanel foodCard = createFoodCard(tenMon[i], gia[i], duongDanAnh[i]);
            foodListPanel.add(foodCard);
        }

        return foodListPanel;
    }


	// Tạo thẻ món ăn
	private JPanel createFoodCard(String tenMon, String gia, String duongDanAnh) {
	    JPanel card = new JPanel();
	    card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
	    card.setPreferredSize(new Dimension(200, 250));
	    card.setBackground(Color.WHITE);
	    card.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));


	    // Ảnh món ăn
	   
	    ImageIcon icon = new ImageIcon(getClass().getResource(duongDanAnh)); // Sử dụng getClass().getResource để lấy ảnh từ thư mục resource
	    Image img = icon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH); // Điều chỉnh kích thước ảnh
	    JLabel imgLabel = new JLabel(new ImageIcon(img)); // Tạo JLabel với ảnh
	    imgLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Canh giữa ảnh trong JPanel
	    card.add(imgLabel);




	    // Tên món ăn
	    JLabel lblTenMon = new JLabel(tenMon);
	    lblTenMon.setFont(new Font("Arial", Font.BOLD, 16));
	    lblTenMon.setAlignmentX(Component.CENTER_ALIGNMENT);
	    card.add(lblTenMon);

	    // Giá
	    JLabel lblGia = new JLabel(gia);
	    lblGia.setFont(new Font("Arial", Font.PLAIN, 14));
	    lblGia.setForeground(Color.RED);
	    lblGia.setAlignmentX(Component.CENTER_ALIGNMENT);
	    card.add(lblGia);
	    

	    return card;
	}

	// Main chạy chương trình
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			JFrame frame = new JFrame("Quản Lý Món Ăn");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setSize(1200, 800);
			frame.setLocationRelativeTo(null); // Canh giữa màn hình

			GUI_QuanLyMonAn panel = new GUI_QuanLyMonAn();
			frame.setContentPane(panel);

			frame.setVisible(true);
		});
	}
}
