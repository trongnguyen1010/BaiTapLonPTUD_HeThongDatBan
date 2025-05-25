package GUI;

import DAO.MonAn_DAO;
import Entity.MonAn;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class GoiMonDialog extends JDialog {
    private final JPanel dishWrap;
    private final JPanel selectedPanel;
    private final Map<MonAn, Integer> selectedMap = new LinkedHashMap<>();
    private boolean confirmed = false;

    public GoiMonDialog(Window owner, String maBan) {
        super(owner, "Gọi món cho bàn " + maBan, ModalityType.APPLICATION_MODAL);
        setResizable(true);

        // Main container
        JPanel main = new JPanel(new BorderLayout(10,10));
        main.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        setContentPane(main);

        // --- DANH SÁCH MÓN ĂN ---
        dishWrap = new JPanel(new WrapLayout(FlowLayout.LEFT,10,10));
        for (MonAn m : new MonAn_DAO().getAllMonAn()) {
            dishWrap.add(createDishCard(m));
        }
        JScrollPane scrollDishes = new JScrollPane(dishWrap,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollDishes.setPreferredSize(new Dimension(600, 400));

        // --- DANH SÁCH MÓN ĐÃ CHỌN ---
        selectedPanel = new JPanel();
        selectedPanel.setLayout(new BoxLayout(selectedPanel, BoxLayout.Y_AXIS));
        selectedPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.GRAY),
                "Món đã chọn",
                TitledBorder.LEADING, TitledBorder.TOP,
                getFont().deriveFont(Font.BOLD, 12f),
                Color.DARK_GRAY
        ));
        // Ban đầu chưa chọn thì hiển thị dòng chữ
        selectedPanel.add(new JLabel("Chưa chọn món nào"));
        JScrollPane scrollSelected = new JScrollPane(selectedPanel,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollSelected.setPreferredSize(new Dimension(200, 400));

        // --- SPLIT PANE ĐỂ CHIA 2 PHẦN ---
        JSplitPane centerSplit = new JSplitPane(
                JSplitPane.HORIZONTAL_SPLIT,
                scrollDishes,
                scrollSelected
        );
        centerSplit.setResizeWeight(0.75);               // 75% cho danh sách món
        centerSplit.setDividerLocation(650);
        centerSplit.setOneTouchExpandable(true);
        main.add(centerSplit, BorderLayout.CENTER);

        // --- NÚT OK / CANCEL ---
        JPanel btns = new JPanel(new FlowLayout(FlowLayout.RIGHT,10,0));
        JButton btnOK     = new JButton("OK");
        JButton btnCancel = new JButton("Cancel");
        btns.add(btnOK);
        btns.add(btnCancel);
        main.add(btns, BorderLayout.SOUTH);

        // --- SỰ KIỆN ---
        btnOK.addActionListener(e -> {
            confirmed = true;
            dispose();
        });
        btnCancel.addActionListener(e -> {
            confirmed = false;
            dispose();
        });

        // Thiết lập kích thước tối thiểu và đóng gói
        pack();
        setMinimumSize(new Dimension(820, 550));
        setLocationRelativeTo(owner);
    }

    private JPanel createDishCard(MonAn m) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        card.setBackground(Color.WHITE);
        card.setPreferredSize(new Dimension(140, 200));

        // 1. Ảnh món
        byte[] imgBytes = m.gethinhAnh();
        JLabel lblImg = new JLabel();
        lblImg.setAlignmentX(Component.CENTER_ALIGNMENT);
        if (imgBytes != null) {
            ImageIcon icon = new ImageIcon(imgBytes);
            Image img = icon.getImage().getScaledInstance(120, 80, Image.SCALE_SMOOTH);
            lblImg.setIcon(new ImageIcon(img));
        } else {
            lblImg.setText("No Image");
        }
        card.add(lblImg);

        // 2. Tên món
        JLabel lblName = new JLabel(m.getTenMonAn(), SwingConstants.CENTER);
        lblName.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(Box.createVerticalStrut(4));
        card.add(lblName);

        // 3. Giá
        JLabel lblPrice = new JLabel(String.format("%.0f VND", m.getDonGia()), SwingConstants.CENTER);
        lblPrice.setForeground(Color.RED);
        lblPrice.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(lblPrice);

        // 4. Spinner số lượng
        JPanel pnlQty = new JPanel(new FlowLayout(FlowLayout.CENTER,5,0));
        pnlQty.setBackground(Color.WHITE);
        pnlQty.add(new JLabel("SL:"));
        JSpinner sp = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
        pnlQty.add(sp);
        card.add(Box.createVerticalStrut(4));
        card.add(pnlQty);

        // Khi spinner thay đổi, cập nhật selectedMap và refresh panel bên phải
        sp.addChangeListener(ev -> {
            int qty = (Integer)sp.getValue();
            if (qty > 0) selectedMap.put(m, qty);
            else          selectedMap.remove(m);
            refreshSelectedPanel();
        });

        return card;
    }

    private void refreshSelectedPanel() {
        selectedPanel.removeAll();
        if (selectedMap.isEmpty()) {
            selectedPanel.add(new JLabel("Chưa chọn món nào"));
        } else {
            for (Map.Entry<MonAn,Integer> entry : selectedMap.entrySet()) {
                String line = String.format("%s x%d", 
                    entry.getKey().getTenMonAn(), 
                    entry.getValue());
                selectedPanel.add(new JLabel(line));
            }
        }
        selectedPanel.revalidate();
        selectedPanel.repaint();
    }

    /** Người dùng bấm OK không? **/
    public boolean isConfirmed() {
        return confirmed;
    }

    /** Trả về map Món + Số lượng đã chọn **/
    public Map<MonAn,Integer> getSelectedDishes() {
        return Collections.unmodifiableMap(selectedMap);
    }
}
