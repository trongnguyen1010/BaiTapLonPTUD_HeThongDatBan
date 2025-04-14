package GUI;

import java.awt.*;
import javax.swing.JPanel;

import java.awt.*;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

/**
 * WrapLayout - FlowLayout có khả năng tự động xuống dòng trong JScrollPane
 */
public class WrapLayout extends FlowLayout {

	public WrapLayout() {
		super();
	}

	public WrapLayout(int align) {
		super(align);
	}

	public WrapLayout(int align, int hgap, int vgap) {
		super(align, hgap, vgap);
	}

	@Override
	public Dimension preferredLayoutSize(Container target) {
		return layoutSize(target, true);
	}

	@Override
	public Dimension minimumLayoutSize(Container target) {
		Dimension minimum = layoutSize(target, false);
		minimum.width -= (getHgap() + 1);
		return minimum;
	}

	private Dimension layoutSize(Container target, boolean preferred) {
		synchronized (target.getTreeLock()) {
			int targetWidth = target.getSize().width;
			if (targetWidth == 0 && target instanceof JScrollPane) {
				targetWidth = ((JScrollPane) SwingUtilities.getAncestorOfClass(JScrollPane.class, target)).getWidth();
			}

			Insets insets = target.getInsets();
			int maxWidth = targetWidth - (insets.left + insets.right + getHgap() * 2);
			int x = 0, y = insets.top + getVgap();
			int rowHeight = 0;

			int nmembers = target.getComponentCount();
			for (int i = 0; i < nmembers; i++) {
				Component m = target.getComponent(i);
				if (!m.isVisible())
					continue;

				Dimension d = preferred ? m.getPreferredSize() : m.getMinimumSize();
				if (x + d.width > maxWidth) {
					x = 0;
					y += rowHeight + getVgap();
					rowHeight = 0;
				}

				x += d.width + getHgap();
				rowHeight = Math.max(rowHeight, d.height);
			}

			y += rowHeight + getVgap();
			return new Dimension(targetWidth, y);
		}
	}
}
