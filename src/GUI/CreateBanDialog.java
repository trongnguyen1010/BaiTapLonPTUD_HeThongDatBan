package GUI;

import Entity.Ban;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

public class CreateBanDialog extends JDialog {
    private JTextField txtMaBan;
    private JComboBox<String> comboKhuVuc;
    private JTextField txtSoCho;
    private JButton btnOK;
    private JButton btnCancel;
    private boolean confirmed = false;

    public CreateBanDialog(Window owner) {
        super(owner, "Thêm Bàn", ModalityType.APPLICATION_MODAL);
        initComponents();
    }
    
    private void initComponents() {
        // Sử dụng GridBagLayout để sắp xếp các thành phần
        JPanel contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.GRAY, 1),
                "Nhập thông tin bàn", TitledBorder.LEADING, TitledBorder.TOP,
                new Font("Segoe UI", Font.BOLD, 14), Color.DARK_GRAY));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.anchor = GridBagConstraints.WEST;

        // Mã bàn
        gbc.gridx = 0; 
        gbc.gridy = 0;
        contentPanel.add(new JLabel("Mã bàn:"), gbc);
        txtMaBan = new JTextField(15);
        gbc.gridx = 1;
        contentPanel.add(txtMaBan, gbc);

        // Khu vực: Sử dụng JComboBox
        gbc.gridx = 0; 
        gbc.gridy = 1;
        contentPanel.add(new JLabel("Khu vực:"), gbc);
        // Các lựa chọn khu vực: "KV01", "KV02", "KVIP"
        comboKhuVuc = new JComboBox<>(new String[] { "KV01", "KV02", "KVIP" });
        comboKhuVuc.setPreferredSize(new Dimension(150, 25));
        gbc.gridx = 1;
        contentPanel.add(comboKhuVuc, gbc);

        // Số chỗ
        gbc.gridx = 0; 
        gbc.gridy = 2;
        contentPanel.add(new JLabel("Số chỗ:"), gbc);
        txtSoCho = new JTextField(5);
        gbc.gridx = 1;
        contentPanel.add(txtSoCho, gbc);

        // Panel nút OK/Cancel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnOK = new JButton("OK");
        btnCancel = new JButton("Cancel");
        buttonPanel.add(btnOK);
        buttonPanel.add(btnCancel);

        // Xử lý nút OK
        btnOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (txtMaBan.getText().trim().isEmpty() ||
                    comboKhuVuc.getSelectedItem() == null ||
                    txtSoCho.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(CreateBanDialog.this, 
                            "Vui lòng điền đầy đủ thông tin!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                // Nếu số chỗ không phải số
                try {
                    Integer.parseInt(txtSoCho.getText().trim());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(CreateBanDialog.this, 
                            "Số chỗ phải là số!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                confirmed = true;
                dispose();
            }
        });

        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                confirmed = false;
                dispose();
            }
        });

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        pack();
        setLocationRelativeTo(getOwner());
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public Ban getBan() {
        // Lấy giá trị, trạng thái bàn mặc định là "Trống"
        String maBan = txtMaBan.getText().trim();
        String khuVuc = (String) comboKhuVuc.getSelectedItem();
        int soCho = Integer.parseInt(txtSoCho.getText().trim());
        return new Ban(maBan, "Trống", khuVuc, soCho);
    }
}
