package GUI;
import java.awt.*;
import javax.swing.border.AbstractBorder;

public class RoundBorder extends AbstractBorder {
    private int radius;
    private Color borderColor;

    public RoundBorder(int radius, Color borderColor) {
        this.radius = radius;
        this.borderColor = borderColor;
    }
    
    @Override
    public Insets getBorderInsets(Component c) {
        int pad = radius / 2;
        return new Insets(pad, pad, pad, pad);
    }
    
    @Override
    public Insets getBorderInsets(Component c, Insets insets) {
        int pad = radius / 2;
        insets.left = insets.top = insets.right = insets.bottom = pad;
        return insets;
    }
    
    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(borderColor);
        // Vẽ border với bán kính được truyền vào
        g2d.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
        g2d.dispose();
    }
}
