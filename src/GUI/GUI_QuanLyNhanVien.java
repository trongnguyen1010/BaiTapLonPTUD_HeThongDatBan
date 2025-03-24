package GUI;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class GUI_QuanLyNhanVien extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public GUI_QuanLyNhanVien() {
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setSize(1220, 700);
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 163, 239));
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(0,130,1220,60);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblTitle = new JLabel("DANH SÁCH THÔNG TIN NHÂN VIÊN");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTitle.setBounds(0,0, 1220, 60);
		panel.add(lblTitle);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBounds(0, 0, 1220, 130);
		add(panel_1);
		panel_1.setLayout(null);


		
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(118, 20, 65, 100);
		panel_2.setLayout(new BorderLayout()); 
		panel_1.add(panel_2);
		
		JButton btnThem = new JButton("<html><center>Thêm</center></html>");
		btnThem.setIcon(new ImageIcon(getClass().getResource("/view/icon/icon_them.png"))); 
		btnThem.setFont(new Font("Segoe UI Black", Font.BOLD, 18));
		btnThem.setForeground(Color.BLACK);
//		btnThem.setBackground(defaultColor);
		btnThem.setPreferredSize(new Dimension(80, 100));
		btnThem.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // Hiệu ứng chuột
		btnThem.setHorizontalTextPosition(SwingConstants.CENTER); // Chữ bên phải icon
		btnThem.setVerticalTextPosition(SwingConstants.BOTTOM);  // Căn giữa icon & chữ
		btnThem.setIconTextGap(10);
		
		panel_2.add(btnThem, BorderLayout.CENTER);
		
		
	
		
	}
}
