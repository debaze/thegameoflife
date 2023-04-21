package thegameoflife.gui;

import java.awt.*;
import java.awt.image.*;
import javax.swing.JPanel;
import thegameoflife.Grid;

public class Canvas extends JPanel {
	private final Grid grid;
	private final int scale;

	public Canvas(final Grid grid, final int scale) {
		this.grid = grid;
		this.scale = scale;

		setPreferredSize(new Dimension(grid.cols * scale, grid.rows * scale));
		setBackground(Color.WHITE);
		setCursor(getToolkit().createCustomCursor(new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB), new Point(), null));
	}

	public void paintComponent(final Graphics graphics) {
		super.paintComponent(graphics);

		final Graphics2D graphics2d = (Graphics2D) graphics;

		graphics2d.scale(scale, scale);

		for (int i = 0, j; i < grid.cols; i++) {
			for (j = 0; j < grid.rows; j++) {
				if (grid.viewCells[i][j] == 0) continue;

				graphics2d.setColor(new Color(0, 0, 0, grid.viewCells[i][j]));
				graphics2d.fillRect(i, j, 1, 1);
			}
		}
	}
}