package thegameoflife.gui;

import java.awt.*;
import javax.swing.JPanel;
import thegameoflife.Grid;

public class Canvas extends JPanel {
	private final int cellScale;
	private Grid grid;

	public Canvas(final Dimension size, final int cellScale) {
		this.cellScale = cellScale;

		setPreferredSize(size);
		setBackground(Color.BLACK);
	}

	public void setGrid(final Grid grid) {
		this.grid = grid;
	}

	public void paintComponent(final Graphics graphics) {
		// TODO: test
		super.paintComponent(graphics);

		final Graphics2D graphics2d = (Graphics2D) graphics;

		graphics2d.scale(cellScale, cellScale);
		graphics2d.setColor(Color.ORANGE);
		graphics2d.fillRect(0, 0, 80, 80);

		final int width = grid.getWidth(), height = grid.getHeight();
		final float[][] cells = grid.getCells();

		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				if (cells[i][j] == 0) continue;

				graphics2d.setColor(new Color(0, 0, 0, cells[i][j]));
				graphics2d.fillRect(i, j, 1, 1);
			}
		}
	}
}