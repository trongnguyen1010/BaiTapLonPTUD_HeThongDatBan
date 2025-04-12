package GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JTextField;

public class RoundTextField extends JTextField {
	private int radius;
	private Color bgColor;
	private Color borderColor;

	public RoundTextField(int radius, Color bgColor, Color borderColor) {
		this.radius = radius;
		this.bgColor = bgColor;
		this.borderColor = borderColor;
		setOpaque(false); // để Swing không vẽ nền mặc định
	}

	@Override
	protected void paintComponent(Graphics g) {
		// Vẽ nền bo tròn
		Graphics2D g2 = (Graphics2D) g.create();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g2.setColor(bgColor); // Màu nền tùy ý
		g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);

		super.paintComponent(g2);
		g2.dispose();
	}

	@Override
	protected void paintBorder(Graphics g) {
		// Vẽ viền bo tròn
		Graphics2D g2 = (Graphics2D) g.create();
		g2.setColor(borderColor);
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, radius, radius);
		g2.dispose();
	}
}
