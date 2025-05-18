package GUI;

import DAO.KhachHangDAO;
import DAO.MonAn_DAO;
import Entity.KhachHang;
import Entity.MonAn;
import Entity.SelectedMon;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class DatBanDialog extends JDialog {
    // --- Thông tin đặt bàn ---
    private final String tableInfo;
    private final Date bookingDate;
    private final String startTime, endTime;

    // --- Khách hàng ---
    private JTextField txtSdt, txtTen, txtEmail;
    private JComboBox<String> comboGioiTinh;
    private JButton btnFind;

    // --- Đặt món trước? ---
    private JCheckBox chkDatMon;

    // --- Menu món ăn ---
    private JPanel dishPanel;
    private JPanel dishWrap;
    private JPanel selectedPanel;
    private final Map<MonAn,Integer> selectedMap = new LinkedHashMap<>();

    // --- OK/Cancel ---
    private boolean confirmed = false;
    private JButton btnOK, btnCancel;

    public DatBanDialog(Window owner,
                        String tableInfo,
                        Date bookingDate,
                        String startTime, String endTime) {
        super(owner, "Thông tin đặt bàn", ModalityType.APPLICATION_MODAL);
        this.tableInfo   = tableInfo;
        this.bookingDate = bookingDate;
        this.startTime   = startTime;
        this.endTime     = endTime;
        initComponents();
        pack();                     // gói lại sau khi dựng xong
        setResizable(true);
        setLocationRelativeTo(owner);
    }

    private void initComponents() {
        // === 1) Content pane ===
        JPanel content = new JPanel(new BorderLayout(10,10));
        content.setBorder(BorderFactory.createEmptyBorder(12,12,12,12));
        content.setBackground(Color.WHITE);
        setContentPane(content);

        // === 2) Header: thông tin bàn + thời gian ===
        JPanel hdr = new JPanel(new GridLayout(2,1,5,5));
        hdr.setBackground(Color.WHITE);
        hdr.setBorder(BorderFactory.createMatteBorder(0,0,1,0,Color.LIGHT_GRAY));
        JLabel lbTable = new JLabel("Bàn: " + tableInfo);
        lbTable.setFont(lbTable.getFont().deriveFont(Font.BOLD,16f));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        JLabel lbTime  = new JLabel("Ngày: " + sdf.format(bookingDate)
                                  + "    Giờ: " + startTime + " - " + endTime);
        lbTime.setFont(lbTime.getFont().deriveFont(Font.BOLD,16f));
        hdr.add(lbTable);
        hdr.add(lbTime);
        content.add(hdr, BorderLayout.NORTH);

        // === 3) Trung tâm: khách hàng + menu món (ẩn) ===
        JPanel center = new JPanel(new BorderLayout(5,5));
        center.setBackground(Color.WHITE);
        content.add(center, BorderLayout.CENTER);

        // 3.1) Khách hàng panel
        JPanel khPanel = new JPanel(new GridLayout(4,1,5,5));
        khPanel.setBackground(Color.WHITE);
        khPanel.setBorder(BorderFactory.createTitledBorder("Khách hàng"));
        txtSdt = new JTextField();
        btnFind= new JButton("Tìm");
        JPanel r1 = new JPanel(new BorderLayout(5,5));
        r1.add(new JLabel("SĐT:"), BorderLayout.WEST);
        r1.add(txtSdt, BorderLayout.CENTER);
        r1.add(btnFind, BorderLayout.EAST);
        khPanel.add(r1);
        txtTen = new JTextField();    khPanel.add(wrapRow("Tên:", txtTen));
        comboGioiTinh = new JComboBox<>(new String[]{"Nam","Nữ"});
        khPanel.add(wrapRow("Giới tính:", comboGioiTinh));
        txtEmail = new JTextField();  khPanel.add(wrapRow("Email:", txtEmail));

        // 3.2) Checkbox đặt món trước
        chkDatMon = new JCheckBox("Đặt món trước");

        // tổ hợp khách hàng + checkbox
        JPanel topCenter = new JPanel(new BorderLayout(5,5));
        topCenter.setBackground(Color.WHITE);
        topCenter.add(khPanel, BorderLayout.CENTER);
        topCenter.add(chkDatMon, BorderLayout.SOUTH);
        center.add(topCenter, BorderLayout.NORTH);

        // 3.3) Dish panel (ẩn ban đầu)
        dishPanel = new JPanel(new BorderLayout(5,5));
        dishPanel.setBackground(Color.WHITE);
        dishPanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Color.GRAY),
            "Chọn món ăn",
            TitledBorder.LEADING, TitledBorder.TOP,
            getFont().deriveFont(Font.BOLD,14f), Color.DARK_GRAY
        ));
        dishPanel.setVisible(false);

        // WrapLayout cho các card món
        dishWrap = new JPanel(new WrapLayout(FlowLayout.LEFT,10,10));
        for (MonAn m : new MonAn_DAO().getAllMonAn()) {
            dishWrap.add(createDishCard(m));
        }
        JScrollPane sc = new JScrollPane(dishWrap,
            JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        sc.setPreferredSize(new Dimension(400,300));
        dishPanel.add(sc, BorderLayout.CENTER);

        // panel hiển thị món đã chọn
        selectedPanel = new JPanel();
        selectedPanel.setLayout(new BoxLayout(selectedPanel, BoxLayout.Y_AXIS));
        selectedPanel.setBorder(BorderFactory.createTitledBorder("Món đã chọn"));
        dishPanel.add(new JScrollPane(selectedPanel,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER),
            BorderLayout.SOUTH);

        center.add(dishPanel, BorderLayout.CENTER);

        // === 4) Buttons OK / Cancel ===
        JPanel btns = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnOK     = new JButton("OK");
        btnCancel = new JButton("Cancel");
        btns.add(btnOK);
        btns.add(btnCancel);
        content.add(btns, BorderLayout.SOUTH);

        // ==== EVENTS ====
        // 4.1) Tìm khách
        btnFind.addActionListener(e -> {
            String sdt = txtSdt.getText().trim();
            if (sdt.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nhập SĐT!");
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
                JOptionPane.showMessageDialog(this, "Khách mới, vui lòng nhập thêm thông tin.");
                txtTen.setText("");
                txtEmail.setText("");
                txtTen.setEditable(true);
                txtEmail.setEditable(true);
                comboGioiTinh.setEnabled(true);
            }
        });

        // 4.2) Toggle hiển thị menu món + phóng to/thu nhỏ dialog
        chkDatMon.addActionListener(e -> {
            boolean show = chkDatMon.isSelected();
            dishPanel.setVisible(show);
            pack();  // tính toán lại kích thước tự động
        });

        // 4.3) OK / Cancel
        btnOK.addActionListener(e -> {
            if (txtSdt.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "SĐT không được trống.");
                return;
            }
            confirmed = true;
            dispose();
        });
        btnCancel.addActionListener(e -> {
            confirmed = false;
            dispose();
        });
    }

    // tiện ích tạo hàng label+comp
    private JPanel wrapRow(String label, JComponent comp) {
        JPanel p = new JPanel(new BorderLayout(5,5));
        p.setBackground(Color.WHITE);
        p.add(new JLabel(label), BorderLayout.WEST);
        p.add(comp, BorderLayout.CENTER);
        return p;
    }

    // tạo card cho mỗi món ăn
    private JPanel createDishCard(MonAn m) {
        JPanel c = new JPanel();
        c.setLayout(new BoxLayout(c, BoxLayout.Y_AXIS));
        c.setBackground(Color.WHITE);
        c.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        c.setPreferredSize(new Dimension(140,180));

        // ảnh
        JLabel img = new JLabel();
        img.setAlignmentX(Component.CENTER_ALIGNMENT);
        if (m.gethinhAnh() != null) {
            ImageIcon ic = new ImageIcon(m.gethinhAnh());
            Image im = ic.getImage().getScaledInstance(120,60,Image.SCALE_SMOOTH);
            img.setIcon(new ImageIcon(im));
        }
        c.add(img);

        // tên & giá
        JLabel lbName = new JLabel(m.getTenMonAn(), SwingConstants.CENTER);
        lbName.setAlignmentX(Component.CENTER_ALIGNMENT);
        c.add(lbName);
        JLabel lbPrice = new JLabel(String.format("%.0f VND", m.getDonGia()), SwingConstants.CENTER);
        lbPrice.setForeground(Color.RED);
        lbPrice.setAlignmentX(Component.CENTER_ALIGNMENT);
        c.add(lbPrice);

        // spinner SL
        JPanel p2 = new JPanel(new FlowLayout(FlowLayout.CENTER,5,0));
        p2.setBackground(Color.WHITE);
        p2.add(new JLabel("SL:"));
        JSpinner sp = new JSpinner(new SpinnerNumberModel(0,0,100,1));
        p2.add(sp);
        c.add(p2);

        sp.addChangeListener(ev -> {
            int qty = (Integer)sp.getValue();
            if (qty>0) selectedMap.put(m, qty);
            else          selectedMap.remove(m);
            refreshSelectedPanel();
        });

        return c;
    }

    // cập nhật lại panel danh sách đã chọn
    private void refreshSelectedPanel() {
        selectedPanel.removeAll();
        selectedMap.forEach((m,qty) -> {
            selectedPanel.add(new JLabel(m.getTenMonAn() + "  x" + qty));
        });
        selectedPanel.revalidate();
        selectedPanel.repaint();
    }

    // === PUBLIC API ===
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
        return (String)comboGioiTinh.getSelectedItem();
    }
    public List<SelectedMon> getSelectedMonList() {
        return selectedMap.entrySet().stream()
            .map(e -> new SelectedMon(e.getKey(), e.getValue()))
            .collect(Collectors.toList());
    }
    
    /** 
     * @return map các món cùng số lượng mà người dùng đã chọn
     */
    public Map<MonAn, Integer> getSelectedDishes() {
        return Collections.unmodifiableMap(selectedMap);
    }
    
}
