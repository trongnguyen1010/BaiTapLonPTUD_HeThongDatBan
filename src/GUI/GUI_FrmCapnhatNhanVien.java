package GUI;

import javax.swing.JPanel;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Image;
import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Panel;

public class GUI_FrmCapnhatNhanVien extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;

    /**
     * Create the panel.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                // Create and configure JFrame
                JFrame frame = new JFrame("Cập nhật nhân viên");
                GUI_FrmCapnhatNhanVien panel = new GUI_FrmCapnhatNhanVien();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(900, 600); // Adjust the window size
                frame.setContentPane(panel); // Set the panel as the content pane
                frame.setVisible(true); // Make the frame visible
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public GUI_FrmCapnhatNhanVien() {
        setLayout(null);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(161, 227, 249));
        panel.setBounds(-14, 0, 903, 93);
        add(panel);
        panel.setLayout(null);

        JLabel lblNewLabel = new JLabel("Thông Tin Nhân Viên");
        lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(195, 10, 458, 45);
        panel.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Mã Nhân Viên");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblNewLabel_1.setBounds(10, 103, 99, 24);
        add(lblNewLabel_1);

        textField = new JTextField();
        textField.setBounds(119, 99, 207, 28);
        add(textField);
        textField.setColumns(10);

        JLabel lblNewLabel_1_1 = new JLabel("Tên Nhân Viên");
        lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblNewLabel_1_1.setBounds(344, 103, 109, 24);
        add(lblNewLabel_1_1);

        textField_1 = new JTextField();
        textField_1.setColumns(10);
        textField_1.setBounds(447, 103, 207, 28);
        add(textField_1);

        JLabel lblNewLabel_1_2 = new JLabel("Số Điện Thoại");
        lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblNewLabel_1_2.setBounds(10, 156, 99, 24);
        add(lblNewLabel_1_2);

        JLabel lblNewLabel_1_1_1 = new JLabel("Email");
        lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblNewLabel_1_1_1.setBounds(316, 156, 109, 24);
        add(lblNewLabel_1_1_1);

        textField_2 = new JTextField();
        textField_2.setColumns(10);
        textField_2.setBounds(119, 157, 207, 28);
        add(textField_2);

        textField_3 = new JTextField();
        textField_3.setColumns(10);
        textField_3.setBounds(401, 157, 207, 28);
        add(textField_3);

        JPanel panel_1 = new JPanel();
        panel_1.setBounds(10, 201, 415, 268);
        add(panel_1);
        panel_1.setLayout(null);

        JLabel lblNewLabel_2 = new JLabel("");
        lblNewLabel_2.setIcon(new ImageIcon(GUI_FrmCapnhatNhanVien.class.getResource("/view/image/filenv.png")));
        lblNewLabel_2.setBounds(80, 10, 261, 204);
        panel_1.add(lblNewLabel_2);

        JButton btnNewButton = new JButton("chọn File ảnh");
        btnNewButton.setBounds(132, 235, 101, 21);
        panel_1.add(btnNewButton);

        JRadioButton rdbtnNewRadioButton = new JRadioButton("Nam");
        rdbtnNewRadioButton.setFont(new Font("Times New Roman", Font.BOLD, 15));
        rdbtnNewRadioButton.setBounds(739, 99, 63, 29);
        add(rdbtnNewRadioButton);

        JRadioButton rdbtnN = new JRadioButton("Nữ");
        rdbtnN.setFont(new Font("Times New Roman", Font.BOLD, 15));
        rdbtnN.setBounds(804, 99, 70, 28);
        add(rdbtnN);

        JLabel lblNewLabel_3 = new JLabel("Giới Tính");
        lblNewLabel_3.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        lblNewLabel_3.setBounds(680, 103, 63, 21);
        add(lblNewLabel_3);

//        JButton btnThmNhnVin = new JButton("Thêm Nhân Viên");
//        btnThmNhnVin.setIcon(new ImageIcon(FrmCapnhatNhanVien.class.getResource("/view/icon/icon_them.png")));
//        btnThmNhnVin.setForeground(Color.DARK_GRAY);
//        btnThmNhnVin.setBackground(SystemColor.textHighlight);
//        btnThmNhnVin.setFont(new Font("Times New Roman", Font.BOLD, 14));
//        btnThmNhnVin.setBounds(568, 233, 175, 45);
//        add(btnThmNhnVin);
        JButton btnThmNhnVin = new JButton("Thêm Nhân Viên");

     // Lấy icon gốc và thay đổi kích thước
     ImageIcon originalIcon = new ImageIcon(GUI_FrmCapnhatNhanVien.class.getResource("/view/icon/icon_them.png"));
     Image img = originalIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH); // Chỉnh kích thước icon
     btnThmNhnVin.setIcon(new ImageIcon(img));

     btnThmNhnVin.setForeground(Color.DARK_GRAY);
     btnThmNhnVin.setBackground(SystemColor.textHighlight);
     btnThmNhnVin.setFont(new Font("Times New Roman", Font.BOLD, 14));
     btnThmNhnVin.setBounds(447, 213, 198, 45);
     add(btnThmNhnVin);


        JLabel lblNewLabel_1_1_1_1 = new JLabel("Chức vụ");
        lblNewLabel_1_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblNewLabel_1_1_1_1.setBounds(634, 156, 109, 24);
        add(lblNewLabel_1_1_1_1);

        JComboBox comboBox = new JComboBox();
        comboBox.setFont(new Font("Times New Roman", Font.BOLD, 12));
        comboBox.setModel(new DefaultComboBoxModel(new String[] {"chức vụ 1", "chức vụ 2", "chức vụ 3"}));
        comboBox.setBounds(725, 160, 99, 21);
        add(comboBox);
        
        JButton btnThmNhnVin_1 = new JButton("Cập Nhật Nhân Viên");

     // Lấy icon gốc và thay đổi kích thước
     ImageIcon originalIcon_1 = new ImageIcon(GUI_FrmCapnhatNhanVien.class.getResource("/view/icon/icon_capnhat.png"));
     Image img_1 = originalIcon_1.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH); // Chỉnh kích thước icon
     btnThmNhnVin_1.setIcon(new ImageIcon(img_1));

     btnThmNhnVin_1.setForeground(Color.DARK_GRAY);
     btnThmNhnVin_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
     btnThmNhnVin_1.setBackground(SystemColor.textHighlight);
     btnThmNhnVin_1.setBounds(655, 213, 209, 45);
     add(btnThmNhnVin_1);
     
     JButton btnXaRng = new JButton("Xóa Rỗng");
     btnXaRng.addActionListener(new ActionListener() {
     	public void actionPerformed(ActionEvent e) {
     	}
     });
     btnXaRng.setForeground(Color.DARK_GRAY);
     btnXaRng.setFont(new Font("Times New Roman", Font.BOLD, 14));
     btnXaRng.setBackground(SystemColor.textHighlight);
     btnXaRng.setBounds(447, 333, 198, 45);
     add(btnXaRng);
     
     JButton btnHy = new JButton("Hủy");
     btnHy.setForeground(Color.DARK_GRAY);
     btnHy.setFont(new Font("Times New Roman", Font.BOLD, 14));
     btnHy.setBackground(Color.RED);
     btnHy.setBounds(666, 333, 198, 45);
     add(btnHy);
     
     Panel panel_2 = new Panel();
     panel_2.setBackground(new Color(161, 227, 249));
     panel_2.setBounds(0, 469, 889, 161);
     add(panel_2);
     panel_2.setLayout(null);
     
     JLabel lblNewLabel_4 = new JLabel("Quản Lý Nhà Hàng N2");
     lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
     lblNewLabel_4.setFont(new Font("Times New Roman", Font.BOLD, 30));
     lblNewLabel_4.setBounds(176, 5, 500, 51);
     panel_2.add(lblNewLabel_4);
     
     
       

    }
}
